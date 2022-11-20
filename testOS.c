#include<stdio.h>
#include<sys/types.h>
#include<unistd.h>

int main(int args, char *argv[])
{
  printf("\nInitial for all:before fork creating a new process, child(testOS) ID=%d, parent(shell) ID=%d\n", getpid(), getppid());
  pid_t fork_return;
	pid_t pid;
	pid=getpid();
	fork_return=fork();    //using fork() function to create a new process inside testOS process
                

  if(fork_return==-1){       //-1 when an error happens
      printf("\nError creating process!");
      return 0;
  }
  if(fork_return==0){       //when fork() returns 0, we are in the child process 
      printf("\nAfter fork(), in child process: process child ID=%d, Parent ID=%d, fork_return=%d \n", getpid(), getppid(),fork_return);
      execl("/bin/cat","cat","-b","-v","-t",argv[1],NULL);
      printf("Try to insert a print out after calling execl function.\n");
      printf("aaaaaaa\n");
      printf("bbbb\n");
      printf("ccccc\n");
  }
  else{       /*else when for() returns a positive number/non zero, we are in the parent process and 
               this returned number is the PID of the newly created child process */
    
      wait(NULL);                 /*when the new child process is runing, the parent process wait. */    
      printf("\nChild process completes. Fork_return=%d", fork_return);       /*when child prkcess fininish, fork() return a non-zero number*/
      printf("\nIN the parent process now. ");
      printf("Child ID=%d, Parent ID=%d\n", getpid(), getppid());
  }
  
  return 0;
}
