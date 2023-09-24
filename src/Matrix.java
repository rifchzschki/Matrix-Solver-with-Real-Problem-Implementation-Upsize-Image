
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
        this.b = new double[baris]; 
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
    
    
    
    

    
    /* ********** INPUT/OUTPUT MATRIX ********** */
    public void readMatrix() //procedure baca matrix dari input keyboard
    {
        // this.b = new double[this.row];

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

    
    
    /* ********** TIPE MATRIX ********** */
    public static Matrix Identitas(int N) {
        Matrix M = new Matrix(N, N);
        for (int i = 0; i < N; i++)
            M.matrix[i][i] = 1;
        return M;
    }

    // Operasi Baris Elementor
    public void swap (int row1, int row2){
        double [] temp = matrix[row1];
        matrix [row1] = matrix[2];
        matrix[row2] = temp;
    }

    public void kalirow(int row,  double k){
        int j;
        for (j = 0; j<col; j++){
            matrix[row][j] *= k;
        }
    }

    public void pluskalirow(int row1, int row2, double k){
        int j;
        for (j = 0; j<col; j++){
            matrix[row1][j] += matrix[row2][j]*k;
        }
    }

    public void minuskalirow(int row1, int row2, double k){
        int j;
        for (j = 0; j<col; j++){
            matrix[row1][j] -= matrix[row2][j]*k;
        }
    }

    public void plusrow(int row1, int row2){
        pluskalirow(row1,row2,1);
    }

    public void minusrow(int row1, int row2){
        minuskalirow(row1,row2,1);
    }
    public static Matrix Hilbert(int N) {
        Matrix M = new Matrix(N, N + 1);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                M.matrix[i][j] = 1.0 / (i + j + 1);
            }
        }
        return M;
    }

    /* ********** SIFAT MATRIX ********** */
    public static boolean IsIdentitas(Matrix M) {
        boolean out = true;
        for (int i = 0; i < M.row; i++) {
            for (int j = 0; j < M.col; j++) {
                if (!(((i == j) && M.matrix[i][j] == 1) || ((i != j) && M.matrix[i][j] == 0))) {
                    out = false;
                }
            }
        }
        return out;
    }

    // ***Operasi MATRIX***
    public static void main(String args[])
    {

        Matrix testSpl = new Matrix(3,3);
        testSpl.readMatrix();
        testSpl.printMatrix(false);
        testSpl.createAugmentedMatrix();
        testSpl.printMatrix(true);

}

public static void Copy(Matrix in, Matrix out){
    out.row = in.row;
    out.col = in.col;
    out.matrix = new double[in.row][in.col];

    for(int i=0; i<in.row;++i){
        for(int j=0; j<in.col; ++j){
            out.matrix[i][j] = in.matrix[i][j];
        }
    }
}

public static Matrix EliminasiGauss(Matrix M) {


    // Inisialisasi
    Matrix N = new Matrix(1, 1);
    Copy(M,N);

    // Proses mengurutkan baris
    int[] zero = new int[N.row];
    for (int i = 0; i < N.row; i++) { // Kalkulasi jumlah 0
        zero[i] = 0;
        int j = 0;
        while (j < N.col && N.matrix[i][j] == 0) {
            zero[i]++;
            j++;
        }
    }
    for (int i = 0; i < N.row; i++) { // Algoritma Pengurutan
        for (int j = 0; j < N.row - 1; j++) {
            if (zero[j] > zero[j + 1]) {
                int temp;
                N.Swap(j, j + 1);
                temp = zero[j];
                zero[j] = zero[j + 1];
                zero[j + 1] = temp;
            }
        }
    }

    // Proses mereduksi baris
    int indent = 0;

    for (int i = 0; i < N.row; i++) {
        // Mencari sel bernilai
        while (i + indent < N.col && N.matrix[i][i + indent] == 0) {
            indent++;
        }

        if (i + indent < N.col) {
            // Ubah angka depan jadi 1
            N.KaliBaris(i, 1 / N.matrix[i][i + indent]);

            // Pengurangan baris dibawahnya
            for (int j = i + 1; j < N.row; j++) {
                if (N.matrix[j][i + indent] != 0) {
                    N.KaliBaris(j, 1 / N.matrix[j][i + indent]);
                    N.MinusBaris(j, i);
                }
            }
        }
    }
    N.Approximate();






}
}

