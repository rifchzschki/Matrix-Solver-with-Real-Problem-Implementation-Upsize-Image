package src;
import java.util.*;

public class Matrix {

    private int row,col; //m itu baris,n itu kolom
<<<<<<< HEAD
    private int rowmax=100,colmax=100;
    // private int rowMin=0;
    // private int colMin =0;
=======
    
>>>>>>> 88c100b6d1b1115be8132c5aef36c3f2362f2d9b
    private double[][] matrix;//ax
    
    public static final double decPoint = 10000000000d;
    Scanner scanner = new Scanner(System.in);
    /* ********** KONSTRUKTOR ********** */

    public Matrix() {
        this.col = 0;
        this.row = 0;
        for (int i = 0; i <= this.rowmax; i++) {
            for(int j = 0; j<= this.colmax; j++) {
                this.matrix[i][j] = 0;
            }
        }
    }
    public Matrix(int baris, int kolom) {
        this.row = baris;
        this.col = kolom;
        this.matrix = new double[baris][kolom];
        // this.augmentedMatrix = new double[baris][kolom+1];// gabungin di akhir operasi gauss
        // this.b = new double[baris]; 
        // createAugmentedMatrix();
    }
    
    public Matrix(double[][] matrix) {
        // Konstruktor dari tabel
        this.row = matrix.length;
        this.col = matrix[0].length;
        this.matrix = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[0].length; j++)
            {
                this.matrix[i][j] = matrix[i][j];
            }
        // this.b[i] = b[i];
        }
    }
    // public void createAugmentedMatrix() //ngebuat augmented matrix dari matrix a dan persamaan b,ax=b
    // {
    //         for(int i = 0; i < row; i++)
    //         {
    //             for(int j = 0; j < col+1; j++)
    //             {   
    //                 if (j == col)
    //                 {
    //                     this.augmentedMatrix[i][j] = this.b[i];
    //                 }
    //                 else
    //                 {
    //                     this.augmentedMatrix[i][j] = this.matrix[i][j];
    //                 }
    //             }
    //         }
    //     }
    
    
    
    

    
    /* ********** INPUT/OUTPUT MATRIX ********** */
    public void readMatrix(){ //procedure baca matrix dari input keyboard
    
        // this.b = new double[this.row];

        for(int i = 0; i < this.row; i++)
        {
            for(int j = 0; j < this.col; j++)
            {   
                System.out.println("Isi elemen ke [" + (i) + "]" +"[" + (j) + "] : ");
                double elemen = scanner.nextDouble();
                this.matrix[i][j] = elemen;//koef a[i][j]
                
            }
        }
           
        // if (isAugmented)
        // { 
        //     for(int i = 0; i < this.row; i++)
        //     {
        //     System.out.println("Isi elemen b ke [" + (i) + "] : ");
        //     double elemen = scanner.nextDouble(); //elemen b[i]
        //     this.b[i]=elemen;
        //     }
        // }
            
        
        // if (isAugmented)
        // {
        //     createAugmentedMatrix();
        // }
        System.out.print("\n");
    }

   

    public void printMatrix() {
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                this.matrix[i][j]+=0; //menghilangkan -0
                System.out.format(" %.2f ", this.matrix[i][j]);
            }

            System.out.print("\n");
        }
    } 
    

    /* ********** SELEKTOR ********** */
    // public int GetFirstIdxBrs(Matrix M) {
    //     return 0;
    // }

    // public int GetFirstIdxKol(Matrix M) {
    //     return 0;
    // }

    // public int GetLastIdxBrs(Matrix M) {
    //     return M.row - 1;
    // }

    // public int GetLastIdxKol(Matrix M) {
    //     return M.col - 1;
    // }

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
        matrix [row1] = matrix[row2];
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
    public static void main(String args[]){

        Matrix testSpl = new Matrix(3,4);
        testSpl.readMatrix();
        testSpl.printMatrix();
        testSpl.eselonBaris();
        System.out.println("\n");
        testSpl.printMatrix();
        testSpl.eselonBarisReduksi();
        System.out.println("\n");
        testSpl.printMatrix();

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

    // public void eselonBaris() {


    

    //     // Proses mengurutkan baris
    //     int[] zero = new int[this.row];
    //     for (int i = 0; i < this.row; i++) { // Kalkulasi jumlah 0
    //         zero[i] = 0;
    //         int j = 0;
    //         while (j < this.col && matrix[i][j] == 0) {
    //             zero[i]++;
    //             j++;
    //         }
    //     }

    //     for (int i = 0; i < this.row; i++) { // Algoritma Pengurutan
    //         for (int j = 0; j < this.row - 1; j++) {
    //             if (zero[j] > zero[j + 1]) {
    //                 int temp;
    //                 swap(j, j + 1);
    //                 temp = zero[j];
    //                 zero[j] = zero[j + 1];
    //                 zero[j + 1] = temp;
    //             }
    //         }
    //     }

    //     // Proses mereduksi baris
    //     int indent = 0;

    //     for (int i = 0; i < this.row; i++) {
    //         // Mencari sel bernilai
    //         while (i + indent < this.col && this.matrix[i][i + indent] == 0) {
    //             indent++;
    //         }

    //         if (i + indent < this.col-1) {
    //             // Ubah angka depan jadi 1
    //             kalirow(i, 1 / this.matrix[i][i + indent]);

    //             // Pengurangan baris dibawahnya
    //             for (int j = i + 1; j < this.row; j++) {
    //                 if (this.matrix[j][i + indent] != 0) {
    //                     kalirow(j, 1 / this.matrix[j][i + indent]);
    //                     minusrow(j, i);
    //                 }
    //             }
    //         }
    //     }
    //     approximate();
    // }
    
    // public void eselonBarisReduksi()
    // {
    //     eselonBaris();
    //     int indent =0;

    //     for (int i = 0; i < this.row; i++) {
    //         // Pencarian sel tidak nol
    //         while (i + indent <this.col && matrix[i][i + indent] == 0) {
    //             indent++;
    //         }

    //         if (i + indent < this.col) {

    //             // Pengurangan baris diatasnya
    //             for (int j = i - 1; j >= 0; j--) {
    //                 if (matrix[j][i + indent] != 0) {
    //                     minuskalirow(j, i, -matrix[j][i + indent]);
    //                 }
    //             }
    //         }
    //     }

    //     approximate();

    // }


    public void eselonBaris() {
        int rowPos = 0; // Menunjukkan posisi baris saat ini
        int colPos = 0; // Menunjukkan posisi kolom saat ini

        while (rowPos < this.row && colPos < this.col) {
            // Cari baris dengan elemen pertama yang bukan nol
            int nonZeroRow = rowPos;
            while (nonZeroRow < this.row && matrix[nonZeroRow][colPos] == 0) {
                nonZeroRow++;
            }

            if (nonZeroRow < this.row) {
                // Swap baris dengan baris yang memiliki elemen pertama yang bukan nol
                swap(rowPos, nonZeroRow);

                // Buat elemen pertama menjadi 1
                double pivot = matrix[rowPos][colPos];
                kalirow(rowPos, 1.0 / pivot);

                // Eliminasi baris-baris di bawahnya
                for (int i = rowPos + 1; i < this.row; i++) {
                    double factor = matrix[i][colPos];
                    minuskalirow(i, rowPos, factor);
                }

                rowPos++; // Pindah ke baris berikutnya
            }

            colPos++; // Pindah ke kolom berikutnya
        }
<<<<<<< HEAD

        // Proses mereduksi baris
        int indent = 0;

        for (int i = 0; i < this.row; i++) {
            // Mencari sel bernilai
            while (i + indent < this.col && this.matrix[i][i + indent] == 0) {
                indent++;
            }

            if (i + indent < this.col) {
                // Ubah angka depan jadi 1
                kalirow(i, 1 / this.matrix[i][i + indent]);

                // Pengurangan baris dibawahnya
                for (int j = i + 1; j < this.row; j++) {
                    if (this.matrix[j][i + indent] != 0) {
        for (i =0;i<row;i++){
            for (j =0 ; j<col;j++){
=======
    }

    public void eselonBarisReduksi() {
        eselonBaris(); // Panggil eselonBaris terlebih dahulu untuk mendapatkan bentuk eselon baris

        // Mulai dari baris terbawah, kerja ke atas untuk menghilangkan elemen di atas
        // pivot
        for (int rowPos = this.row - 1; rowPos > 0; rowPos--) {
            int colPos = 0;
            while (colPos < this.col && matrix[rowPos][colPos] == 0) {
                colPos++;
            }

            if (colPos < this.col) {
                // Eliminasi elemen di atas pivot
                for (int i = rowPos - 1; i >= 0; i--) {
                    double factor = matrix[i][colPos];
                    minuskalirow(i, rowPos, factor);
                }
            }
        }
    }






    
    public void approximate()
    {
        for (int i =0;i<row;i++)
        {
            for (int j =0 ; j<col;j++)
            {
>>>>>>> 88c100b6d1b1115be8132c5aef36c3f2362f2d9b
                matrix[i][j]=(Math.round(matrix[i][j] * decPoint)/decPoint);
            }
        }
        }

    }

    
}
}
}
}