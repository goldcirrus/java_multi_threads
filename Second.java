/*java to create a new thread with the approach of making the main class appends the Thread class and override its run() mehtod. */

/*the main class*/
public class Second extends Thread
{
      int id;     //variable belongs to each Second class object created by the below constructor

      /*constructor to create a Second class object*/
      Second (int id)
     {    
        this.id=id;
     }

     /*override the run() method inherited from the Thread class*/
     public void run(){
         System.out.println("I am "+id+"'th worker thread.");
     }

     /*the main method*/
     public static void main(String args[]){
            Second runnerOne = new Second(1);
            Second runnerTwo = new Second(2);

            runnerOne.start();
            runnerTwo.start();

            System.out.println("I am in the main thread now.");
     } 
}


