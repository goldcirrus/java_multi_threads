/*java to create a new thread, approach 2:to define a class that implements Runnable interface*/
/*the Runnable interface defines a sngle abstract method run() with the signature public void*/
/*the code in the run() method of that calass that implements Runnable interface is what executes in a new thread*/


/*Worker Class to implements Runnable interface and override run() method*/
class Worker implements Runnable
{
   int id;
   
   //constructor to create a new Worker object
   Worker(int id){
      this.id=id;
   }

   //override run() method from Runnable interface, its code will be executed in a new thread
   public void run(){
      System.out.println("I am "+id+"'th worker thread.");  
   }
}

/*the main class*/
public class Third
{
   public static void main(String args[]){
       Worker worker = new Worker(1);
       Thread runnerOne = new Thread(worker);
    
       Thread runnerTwo = new Thread(new Worker(2));
    
       runnerOne.start();
       runnerTwo.start();

       System.out.println("I am in the main thread now.");
   }
}

