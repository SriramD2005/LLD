package multithreading;
// an object monitor is the token to access the synchronized blocks/methods of an object
public class ObjectMonitorLock {
    public static void main(String[] args) {
        Example obj = new Example();
        Thread t1 = new Thread(() -> {
                synchronized(obj){ // attaining the object monitor of obj
                obj.criticalBlock();
            }
        });
        t1.start();
    }
}

class Example{
    synchronized void criticalBlock(){
        System.out.println("critical section execution");
    }
}
