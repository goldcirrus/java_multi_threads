/*java program to create and manage thread*/
/*approach one: to create a new class that is derived from the Thread class and to override its run() method*/

/*use Worker class object to create a new thread*/
class Worker extends Thread
{
    int id;   //not shared among threads, instance variable for an Worker object

    Worker(int id)     //constructor used to create an Worker class object
    { 
       this.id=id;    
    }

                       /*when a Worker(Thread) class object invokes start() method, it calls the run() method*/
                      /*start() allocates memory and initialize a new thread in jvm*/

    public void run() {      //override run() method from Thread class.
         System.out.println("I am "+id+"'th worker thread");
    }
}

/*main class of this program*/
public class First
{
    public static void main(String args[]){
        Worker runnerOne= new Worker(1);    //create a Worker object to create a new thread when start() is called
        Worker runnerTwo= new Worker(2);

        runnerOne.start();    //when start() is called, it allocates memory,initialize a new thread, and call run()
        runnerTwo.start();

        System.out.println("Finished to create new threads. I am in the main thread now.");
    }
}


/*to compile the program, type: javac First.java */
/*to run, type: java First */

