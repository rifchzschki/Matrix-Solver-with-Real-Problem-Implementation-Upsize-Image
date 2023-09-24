
import java.util.Scanner;

public class Matrix {

    public int row,col; //m itu baris,n itu kolom
    public int rowMin=0;
    public int colMin =0;
    public double[][] matrix;//ax
    public double[] b;//b
    public double[] array;
    public double[][] augmentedMatrix;
    Scanner scanner = new Scanner(System.in);
    /* ********** KONSTRUKTOR ********** */
    
    public Matrix(int baris, int kolom) {
        this.row = baris;
        this.col = kolom;
        this.matrix = new double[baris][kolom];
    }
    public double[][] createAugmentedMatrix(double[][] a, double[] b) //ngebuat augmented matrix dari matrix a dan persamaan b,ax=b
    {
        augmentedMatrix = new double[row][col+1];
        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col+1; j++)
            {   
                if (j == col)
                {
                    augmentedMatrix[i][j] = b[i];
                }
                else
                {
                augmentedMatrix[i][j] = a[i][j];
                }
            }
        }

        return augmentedMatrix;
    }
    
    
    
    
    
    
    /* ********** INPUT/OUTPUT MATRIKS ********** */
    public void readMatrix() //procedure baca matrix dari input keyboard
    {
        
        System.out.println("Jumlah Baris : ");//m
        this.row = scanner.nextInt();
        System.out.println("Jumlah Kolom : ");//n
        this.col = scanner.nextInt();
        matrix = new double[this.row][this.col]; //a[i][j]
        array = new double[this.row]; //b[i]
        for(int i = 0; i < this.row; i++)
        row = scanner.nextInt();
        System.out.println("Jumlah Kolom : ");//n
        col = scanner.nextInt();
        matrix = new double[row][col]; //a[i][j]
        array = new double[row]; //b[i]
        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {   
                System.out.println("Isi elemen a ke [" + (i) + "]" +"[" + (j) + "] : ");
                double elemen = scanner.nextDouble();
                matrix[i][j] = elemen;//koef a[i][j]
                
            }
            System.out.println("Isi elemen b ke [" + (i) + "] : ");
            double elemen = scanner.nextDouble(); //elemen b[i]
            array[i]=elemen;
            // scanner.close();
        }
        // return matrix;
    }

   

    public void printMatrix(double[][] matrix) {
        for (int i = 0; i < row; i++) {

            for (int j = 0; j < col; j++) {
                matrix[i][j]+=0; //menghilangkan -0
                System.out.format(" %.2f ", matrix[i][j]);
            }

            System.out.println();
        }
        System.out.println();
    }

    /* ********** SELEKTOR ********** */
    public int GetFirstIdxBrs(Matrix M) {
        return 0;
    }

    public int GetFirstIdxKol(Matrix M) {
        return 0;
    }

    public int GetLastIdxBrs(Matrix M) {
        return M.row - 1;
    }

    public int GetLastIdxKol(Matrix M) {
        return M.col - 1;
    }

    // ***Operasi Matriks***
    public Matrix reductionMat(Matrix M){
        static Mat;

        Matrix()
        return Mat;
    }

}
