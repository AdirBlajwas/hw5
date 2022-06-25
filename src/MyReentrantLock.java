import java.util.concurrent.atomic.AtomicBoolean;
import  java.lang.Thread;
/**A reentrant lock*/
public class MyReentrantLock implements Lock{
    private Thread lockedBy;
    AtomicBoolean isLocked;
    int lockCounter;
    int backOff = 10; // value for sleep method (milliseconds)

    MyReentrantLock(){
        isLocked = new AtomicBoolean();
        lockCounter = 0;
    }

    /**
     * Waiting for the lock to be unlocked and locks it by the current thread
     */
    public void acquire(){
        if(tryAcquire()) return;
        if(Thread.currentThread() == lockedBy){
            lockCounter++;
            return;
        }
        while (!isLocked.compareAndSet(false, true)) {
            try {
                Thread.sleep(backOff);
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
      lockedBy = Thread.currentThread();
      lockCounter++;
    }

    /**
     * Trying to lock the lock if it is unlocked
     * @return true if succeeded false otherwise
     */
    public boolean tryAcquire(){
        boolean succeeded  = isLocked.compareAndSet(false,true);
        if(succeeded) {
            lockedBy = Thread.currentThread();
            lockCounter++;
        }
        return succeeded;
    }

    /**
     * Releasing the lock if it was locked by current thread
     * releasing one internal lock if it was locked several times internally
     * @throws IllegalReleaseAttempt if the current thread is not the thread
        which locked the lock or if the lock is unlocked
     */
    public void release(){
        boolean sameThread = Thread.currentThread() == lockedBy;
        if(!sameThread || !isLocked.get()) throw new IllegalReleaseAttempt();
        lockCounter--;
        if (lockCounter >= 1) {
            return;
        }
        lockedBy = null;
        isLocked.compareAndSet(true, false);
    }

    @Override
    /**
     * closing the lock and releasing it.
     */
    public void close(){
        try {
            this.release();
        }
        catch (Exception ignore){
            throw new IllegalReleaseAttempt();
        }
    }
}
