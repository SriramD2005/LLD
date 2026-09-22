class BankAccount{
    int amt;
    int name;
    BankAccount(int amt, int name){
        this.amt = amt;
        this.name = name;
    }
    
    synchronized void credit(int amt){
        this.amt += amt;
    }
    
    synchronized void withdraw(int amt){
        this.amt -= amt;
    }
}

class DeadLock{
    public static void main(String args[]){
        BankAccount a = new BankAccount(1000, 1);
        BankAccount b = new BankAccount(500, 2);
        Thread t1 = new Thread(() -> {
            transfer(a, b, 100);
        });
        Thread t2 = new Thread(() -> {
            transfer(b, a, 200);
        });
        t1.start();
        t2.start();
        try{
            t1.join();
            t2.join();
        }
        catch(Exception e){
            
        }
        
        System.out.println("transaction complete");
    }
    
    static void transfer(BankAccount from, BankAccount to, int amt){
        synchronized(from){
            System.out.println(Thread.currentThread().getName() + " aquired token for " + from.name + " account object");
            try{
                Thread.sleep(1000);
            }
            catch(Exception e){
                
            }
            
            synchronized(to){
                System.out.println(Thread.currentThread().getName() + " aquired token for " + from.name + " account object");
                from.amt -= amt;
                to.amt += amt;
            }
            System.out.println("released " + to.name + " token");
        }
        System.out.println("released " + from.name + " token");
    }
}
