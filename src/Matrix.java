
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
        this.augmentedMatrix = new double[baris][kolom+1];
        // this.b = new double[baris]; 
    }
    
    public Matrix(double[][] matrix) {
        // Konstruktor dari tabel
        this.row = matrix.length;
        this.col = matrix[0].length;
        this.matrix = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix[0].length; j++)
            {
                this.matrix[i][j] = matrix[i][j];
            }
        // this.b[i] = b[i];
    }
}
public void createAugmentedMatrix() //ngebuat augmented matrix dari matrix a dan persamaan b,ax=b
{
        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col+1; j++)
            {   
                if (j == col)
                {
                    this.augmentedMatrix[i][j] = this.b[i];
                }
                else
                {
                    this.augmentedMatrix[i][j] = this.matrix[i][j];
                }
            }
        }
    }
    
    
    
    
    
    
    /* ********** INPUT/OUTPUT MATRIKS ********** */
    public void readMatrix() //procedure baca matrix dari input keyboard
    {
        this.b = new double[this.row];

        for(int i = 0; i < this.row; i++)
        {
            for(int j = 0; j < this.col; j++)
            {   
                System.out.println("Isi elemen a ke [" + (i) + "]" +"[" + (j) + "] : ");
                double elemen = scanner.nextDouble();
                this.matrix[i][j] = elemen;//koef a[i][j]
                
            }
            System.out.println("Isi elemen b ke [" + (i) + "] : ");
            double elemen = scanner.nextDouble(); //elemen b[i]
            this.b[i]=elemen;
            // scanner.close();
        }
        // return matrix;
    }

   

    public void printMatrix(boolean isAugmented) {
        if (!isAugmented){
            for (int i = 0; i < this.row; i++) {
                for (int j = 0; j < this.col; j++) {
                    this.matrix[i][j]+=0; //menghilangkan -0
                    System.out.format(" %.2f ", this.matrix[i][j]);
                }
    
                System.out.println();
            }
        } else {
            for (int i = 0; i < this.row; i++) {
                for (int j = 0; j < this.col + 1; j++) {
                    this.augmentedMatrix[i][j]+=0; //menghilangkan -0
                    System.out.format(" %.2f ", this.augmentedMatrix[i][j]);
                }
    
                System.out.println();
            }
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

    // *** Operasi perkalian matriks ***
    public static Matrix multiple (Matrix m, double k){
        Matrix result = new Matrix(m.row,m.col);
        for(int i=0;i<m.row;++i){
            for(int j=0;j<m.col;++j){
                result.matrix[i][j] *= k;
            }
        }
        return result;
    }

    
    

    // ***Operasi Matriks***
    public static void main(String args[])
    {

        Matrix testSpl = new Matrix(3,3);
        testSpl.readMatrix();
        testSpl.printMatrix(false);
        testSpl.createAugmentedMatrix();
        testSpl.printMatrix(true);


}
}
