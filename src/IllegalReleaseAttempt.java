/**IllegalReleaseAttempt is an unchecked exception
 thrown when trying to release a lock that was locked by another thread or an open lock .*/
public class IllegalReleaseAttempt extends IllegalMonitorStateException{
    /**Constructs a new illegal release attempt exception with null as its detail message*/
    public IllegalReleaseAttempt(){
        super();
    }
    /**Constructs a new illegal release attempt exception with the specified detail message.*/
    public IllegalReleaseAttempt(String message){
        super(message);
    }
}
