//This is the change done remotely pull it and see!
import java.util.concurrent.Semaphore;
public class SemaphoreEx {
    public static void main(String[] args) {
        Resource res = new Resource();
        for (int i = 0; i < 7; i++){
            try{
                Thread.sleep(500);
                new Thread(() -> res.write(7)).start();
            }
            catch(Exception e){System.err.println(e);}
        }
    }
}

class Resource{
    int data;
    Semaphore semaphore = new Semaphore(3);
    void read(){
        try{
            boolean gotToken = semaphore.tryAcquire();
            if (! gotToken) {
                System.out.println(Thread.currentThread().getName() + " can't get token");
                return;
            }
            Thread.sleep(2000);
            System.out.println(data);
        }
        catch (Exception e) {
            System.err.println(e);
        }
        finally{
            semaphore.release();
        }
    }
    void write(int data){
        try{
            boolean gotToken = semaphore.tryAcquire();
            if (! gotToken) {
                System.out.println(Thread.currentThread().getName() + " can't aquire token");
                return;
            }
            Thread.sleep(2000);
            this.data = data;
            System.out.println("wrote data: " + data + " by " + Thread.currentThread().getName());
        }
        catch (Exception e){System.err.println(e);}
        finally {
            semaphore.release();
        }
    }
}
