
public class Seventh implements Runnable
{
    static int common;      //variable shared among objects, i.e: threads
    int id;                //not shared    

    /*constructor*/
    Seventh(int id){
        this.id=id;
        common=id;
    }

    /*override run() method from Runnable interface, which is executed in the created thread*/
    public void run(){
        System.out.println("I am "+id+"'th worker_thread - "+common);
    }

    /*main method*/
    public static void main(String args[]){
        Thread runnerOne=new Thread(new Seventh(1));
        Thread runnerTwo=new Thread(new Seventh(2));

        runnerOne.start();
        runnerTwo.start();

        System.out.println("I am in the main thread now.");
    }

}


