import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;


class BookShow{
    int availableSeats = 10;
    Boolean isLocked = false;
    ReentrantLock lock = new ReentrantLock();
    ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    void bookSeat(){
        lock.lock();
        try{
            System.out.println("lock acquired by: " + Thread.currentThread().getName());
            Thread.sleep(2000);
            isLocked = true;
            availableSeats--;
            System.out.println(availableSeats);
            System.out.println("lock unlocked by: " + Thread.currentThread().getName());
            lock.unlock();
            
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


}

public class ReEntrantLockEx {
    public static void main(String[] args) {
        BookShow bookShow = new BookShow();
        new Thread(bookShow::bookSeat).start();
        new Thread(bookShow::bookSeat).start();
    }
}