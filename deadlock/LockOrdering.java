import java.util.Arrays;
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

class LockOrdering{
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
        BankAccount[] locksOrder = {from, to};
        Arrays.sort(locksOrder, (a, b) -> a.name - b.name);  //order of the locks
        
        synchronized(locksOrder[0]){
            System.out.println(Thread.currentThread().getName() + " aquired token for " + locksOrder[0].name + " account object");
            
            try{
                Thread.sleep(1000);
            }
            catch(Exception e){
                
            }
            
            synchronized(locksOrder[1]){
                System.out.println(Thread.currentThread().getName() + " aquired token for " + locksOrder[1].name + " account object");
                from.amt -= amt;
                to.amt += amt;
            }
            System.out.println("released " + locksOrder[1].name + " token");
        }
        System.out.println("released " + locksOrder[0].name + " token");
    }
}
