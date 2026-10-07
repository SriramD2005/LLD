//import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

//import javax.xml.crypto.Data;
public class ReadWrite {
    public static void main(String[] args) {
        DataClass obj = new DataClass();
        for (int i = 0; i < 6; i++){
            new Thread(() -> {
                obj.read();
                obj.write(6);
                System.out.println("Wrote 6");
            }).start();
        }
    }
}

class DataClass {
    int data;
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    
    void read(){
        try{
            lock.readLock().lock();
            Thread.sleep(2000);
            System.out.println(data);
            lock.readLock().unlock();
        }
        catch (Exception e) {System.err.println(e);}
    }

    void write(int data){
        try{
            lock.writeLock().lock();
            Thread.sleep(2000);
            this.data = data;
            lock.writeLock().unlock();
        }
        catch (Exception e){System.err.println(e);}
    }
}
