/*thread cancellation in java: invoke the interrupt() method, which sets the interruption status of the target thread to true*/

/*main class*/
public class Sixth implements Runnable
{
    int id;

    /*constructor*/
    Sixth(int id){
        this.id=id;
    }

    /*override method run() from Runnable interface, which executed in the new thread created. */
    /*this created thread check its interruption status in every 1000ms interval.*/  
    /*if interrupted, it exits means this thread terminates. */
    public void run(){
        while(true){
            try{Thread.sleep(1000);}    //in this new thread, do sleep 1000ms
            catch(InterruptedException e) {               //catched InterruptedException is displaied
               System.out.println("I (" + id + "'th worker) am interrupted.");
               break;         //exit run()/the thread's execution, if an interruptedException is thrown
             }
            System.out.println("I am "+id+"'th worker thread.");
            if(Thread.currentThread().isInterrupted()){         //a thread checks its interruption status
               System.out.println("I ("+id+"'th worker_thread) am interrupted.");
               break;        //exit run()/the thread's execution, if an interrupt is detected by .isInterrupted
            }
        }
    }
   
    /*the main method*/
    public static void main(String args[]){
        Runnable worker=new Sixth(1);
        Thread runnerOne=new Thread(worker);

        Thread runnerTwo=new Thread(new Sixth(2));
        
        runnerOne.start();
        runnerTwo.start();

        runnerOne.stop();    //stop runnerOne thread, deprecated
                 /*stop the running thread and remove it from the waiting threads pool and garbage collected*/

        try{runnerOne.join();}     //make parent/main thread wait until runnerOne terminates
        catch(InterruptedException e) {}   //if catch, do nothing

        System.out.println("The first worker_thread done.");

        runnerTwo.interrupt();   //interrupt runnerTwo
  /*to cancel a java thread, invoke .interrupt() to set the interruption status of the target thread to ture*/
  
        System.out.println("I am in the main thread now.");
    }
}
