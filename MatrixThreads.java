import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;

public class MatrixThreads
{
    public static void main(String[] args) {
         
        
        int m,q,n;          //size: first matrix is mxq, second matrix is qxn
        Double sum=0.0;
        
        /*two operand matrixes*/
        Double[][] first;    
        Double[][] second;
        
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
        
        first = new Double[m][q];
        
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
        System.out.println("Enter the column number for the second matrix. (its row number must be equal to the first matrix's column number:"+q+")");
        n = scan2.nextInt();
        /*input check to ensure user's enter is >= 1. arrayboundary */
        while(n<1)
        {
            System.out.println("Column number must be larger than or equal to 1. Enter again:");
            n = scan2.nextInt();
        }
        
        second = new Double[q][n];
        /*to fill the second matrix with random double numbers.*/
        
        Random rand1 = new Random();
        for(int i=0; i<q; i++) {
            for(int j=0; j<n; j++) {
                second[i][j]=rand1.nextDouble()*100;
            }
        }
         
         /*display the second matrix on the screen*/
         displayMatrix(second);
        
}  

/*display the matrix*/
    public static void displayMatrix(Double[][] matrix){
        System.out.println("the matrix is :");
        DecimalFormat dft = new DecimalFormat("0.##");
        
        for(Double[] rowVariable: matrix){
            for(Double volumnVariable: rowVariable) {
                System.out.print(dft.format(volumnVariable)+"\t");
            }
            System.out.println();
        }
    }
}
