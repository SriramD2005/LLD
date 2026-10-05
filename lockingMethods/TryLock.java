import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TryLock {
    ReentrantLock lock = new ReentrantLock();
    void lockRes(){
        try{
            boolean aquired = lock.tryLock(2, TimeUnit.SECONDS);
            if (! aquired){
                System.out.println(Thread.currentThread().getName() + " can't get the token");
                return;
            }
            System.out.println("token aquired by: " + Thread.currentThread().getName());
            System.out.println("processing in " + Thread.currentThread().getName());
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " has released the lock");
        }
        catch (Exception e){
            System.err.println(e);
        }
        finally{
            if (lock.isHeldByCurrentThread()) lock.unlock();
        }
    }
    public static void main(String[] args) {
        TryLock tryLock = new TryLock();
        for (int i = 0; i < 4; i++){
            new Thread(tryLock::lockRes).start();
        }
    }
}
