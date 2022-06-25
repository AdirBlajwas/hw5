public interface Lock extends AutoCloseable {
    void acquire() throws InterruptedException;
    boolean tryAcquire();
    void release();
}
