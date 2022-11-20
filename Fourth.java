/*java to create a new thread with the approach to implement Runnable interface in the main driver claiss*/

/*the main driver class*/
public class Fourth implements Runnable
{
   int id;
   
   /*constructor to create a Fourth(driver) class object*/
   Fourth(int id){
       this.id=id;
   }

   /*override the run() method from the Runnable interface*/
   public void run(){
        System.out.println("I am "+id+"'th driver/worker thread.");
   }

   /*the main method*/
   public static void main(String args[]){
       Runnable worker = new Fourth(1);     
       Thread runnerOne = new Thread(worker);

       Thread runnerTwo = new Thread(new Fourth(2));

       runnerOne.start();    //start a new thread and call run()
       runnerTwo.start();    //start a new thread and call run()

       /*back to the main thread*/
       System.out.println("I am in the main thread.");
   }
}
