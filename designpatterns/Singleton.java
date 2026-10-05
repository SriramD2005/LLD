package designpatterns;

public class Singleton {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            SynchronizedCreation sc = SynchronizedCreation.getInstance();
            sc.print();
            System.out.println("from thread 1");
        });
        Thread t2 = new Thread(() -> {
            SynchronizedCreation sc = SynchronizedCreation.getInstance();
            sc.print();
            System.out.println("from thread 2");
        });
        t2.start();
        t1.start();
    }
}

class SynchronizedSingleton{  //avoids creation of multiple instances at a time by diff threads, always blocks threads when one thread gets the instance although there is only getting, not creating instance
    SynchronizedSingleton instance;
    synchronized SynchronizedSingleton getInstance(){
        if (instance == null) instance = new SynchronizedSingleton();
        return instance;
    }

    void print(){
        System.out.println("printing..");
    }
}

class SynchronizedCreation{
    static volatile SynchronizedCreation instance;
    class Util{
        synchronized static void createInstance(){
            if (instance == null) instance = new SynchronizedCreation(); //Double Check(what if two threads sees null at a time?)
            //return instance;
        }
    }

    static SynchronizedCreation getInstance(){
        if (instance == null) Util.createInstance();
        return instance;
    }

    void print(){
        System.out.println("printing..");
    }
}