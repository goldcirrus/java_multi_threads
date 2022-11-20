/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrixproduct;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author kevinzhong
 */


/*a Thread class */
class RowWorker implements Runnable
{
      private double[][] result;
      private double[][] matrix1;
      private double[][] matrix2;
      int i;
      int j;
      int k;
    
      
     
      
      /*constructor; the thread pass in i,j,k from the constructor when this class object is created*/
      RowWorker(double[][] result,double[][] matrix1,double[][] matrix2,int i,int j,int k)
      {
          this.result=result;
          this.matrix1=matrix1;
          this.matrix2=matrix2;
          this.i=i;
          this.j=j;
          this.k=k;
      }

      /*run() execute the thread work.*/
      @Override
      public void run(){
          result[i][j] += matrix1[i][k]*matrix2[k][j];
      }
      
}



public class MatrixProduct {
    
    public static void main(String[] args) {
         
        
        int m,q,n;          //size: first matrix is mxq, second matrix is qxn
        double sum=0.0;
        
        /*two operand matrixes*/
        double[][] first;    
        double[][] second;
        
        /*let user enter the row number and column number for the first matrix.*/
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the number of rows and columns for the first matrix:");
        
        m = scan.nextInt();
        q = scan.nextInt();
        /*input check to ensure user's input is larger than 1. arrayboundary */
        while(m<1 || q<1)
        {
            System.out.println("The row number and column number must be larger than or equal to 1! Enter again: ");
            m = scan.nextInt();
            q = scan.nextInt();
        }
        
        first = new double[m][q];
        
       /*to fill the first matrix with random numbers and display it on screen by invoking displayMatrix() method. */
        
        
       /*fill the first matrix with random double numbers. */
         Random rand = new Random();
         for(int i=0; i<m; i++) {
              for(int j=0; j<q; j++) {
                  first[i][j]=rand.nextDouble()*100;
              }
         }
         
        /*display the first matrix on the screen*/
        displayMatrix(first);
        
        /*let user enter the second matrix's column number*/
        Scanner scan2 = new Scanner(System.in);
        System.out.println("Enter the column number for the second matrix. (its row number must be equal to the first matrix's column number:"+q+").");
        n = scan2.nextInt();
        /*input check to ensure user's enter is >= 1. arrayboundary */
        while(n<1)
        {
            System.out.println("Column number must be larger than or equal to 1. Enter again:");
            n = scan2.nextInt();
        }
        
        second = new double[q][n];
        /*to fill the second matrix with random double numbers.*/
        
        Random rand1 = new Random();
        for(int i=0; i<q; i++) {
            for(int j=0; j<n; j++) {
                second[i][j]=rand1.nextDouble()*100;
            }
        }
         
         /*display the second matrix on the screen*/
         displayMatrix(second);
        
        /*parallel threads to calculate the matrix product*/
        int rows1 = first.length;
        int cols1 = first[0].length;
        int cols2 = second[0].length;
        
        double[][] resultMatrix = new double[rows1][cols2];
        
        for(int i=0; i<rows1; i++)
        {
            for(int j=0; j<cols2; j++)
            {
                for(int k=0; k<cols1; k++)
                {       /*a thread pass in i,j,k from a new RowWorker object's constructor. */
                     RowWorker celltask = new RowWorker(resultMatrix,first,second,i,j,k);
                     Thread cellthread = new Thread(celltask);
                     cellthread.start();
                }
            }
        }
        
        displayMatrix(resultMatrix);
      
    }
    
    
   /*display the matrix on screen*/
    public static void displayMatrix(double[][] matrix){
        System.out.println("the matrix is :");
        DecimalFormat dft = new DecimalFormat("0.##");
        
        for(double[] rowVariable: matrix){
            for(double volumnVariable: rowVariable) {
                System.out.print(dft.format(volumnVariable)+"\t");
            }
            System.out.println();
        }
    }
    
}
