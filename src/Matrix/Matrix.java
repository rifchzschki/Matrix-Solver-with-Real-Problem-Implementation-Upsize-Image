package src.Matrix;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Matrix {
    private int row,col; //m itu baris,n itu kolom
    private double[][] matrix;//ax
    public static final double decPoint = 10000000000d;
    Scanner scanner = new Scanner(System.in);
    /* ********** KONSTRUKTOR ********** */

    public Matrix(int baris, int kolom) {
        this.row = baris;
        this.col = kolom;
        this.matrix = new double[baris][kolom];
    }
    
    public Matrix(Matrix m) {
        // Konstruktor dari matrix
        this.row = m.row;
        this.col = m.col;
        this.matrix = new double[m.row][m.col];
        
    }

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
        System.out.print("\n");
    }

    public double[] BacaFileArray(String ArrayFileName, int n) throws FileNotFoundException {
        Scanner file2Scanner = new Scanner(new File("test/input/"+ArrayFileName));

        // Inisialisasi matriks kedua
        double[] arr = new double[n];

        // Baca elemen-elemen matriks kedua
        for (int i = 0; i < n; i++) {
            arr[i] = file2Scanner.nextDouble();
        }

        // Tutup file kedua
        file2Scanner.close();
        return arr;
    }
    public Matrix BacaFileMatriks(String matrixFileName) throws FileNotFoundException {
        int NRow = 0;
        int NCol = 0;
        int col = 0;
        int idx;
        File bacafile = new File("test/input/"+matrixFileName);
        Matrix M = new Matrix(1000,1000);

        try (Scanner scanBaris = new Scanner(bacafile)) {
            while (scanBaris.hasNextLine()) {
                NRow++;
                // NCol = 0;
                Scanner scanNumber = new Scanner(scanBaris.nextLine());
                while (scanNumber.hasNextFloat()) {
                    if(NRow==1){
                        col+=1;
                    }
                    NCol++;
                    if(NCol%col==0){
                        idx=col-1;
                    }else{
                        idx = (NCol%col) - 1;
                    }
                    M.matrix[NRow - 1][idx] = scanNumber.nextFloat();
                    
                    
                    
                }
            }
        }
    
        M.row = NRow;
        M.col = col;
        return M;
    }
    
    
    
    public static void writeProgramOutputToFile(String fileName, String data) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(data);

            
            bufferedWriter.close();

            System.out.println("Data berhasil ditulis ke " + fileName);
        } catch (IOException e) {
            System.err.println("Terjadi kesalahan saat menulis file: " + e.getMessage());
        }
    }

    public void printMatrix() {
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                this.matrix[i][j]+=0; //menghilangkan -0
                System.out.format(" %.2f ", this.matrix[i][j]);
            }

            System.out.print("\n");
        }
        System.out.print("\n");
    } 
    
    /* ********** SETTER ********** */
    public void setEl(int i, int j, double val){
        this.matrix[i][j] = val;
    }

    public void setCol(int n){
        this.col=n;
    }

    public void setRow(int n){
        this.row=n;
    }

    /* ********** GET ********** */
    public double[] GetRow(int row){
        return this.matrix[row];
    }

    public double GetElmt(int i, int j){
        return this.matrix[i][j];
    }

    public int GetLastIdxBrs() {
        return this.row - 1;
    }

    public int GetLastIdxKol() {
        return this.col - 1;
    }

    // *** Operasi perkalian matriks ***
    public Matrix multiple (Matrix m, double k){
        Matrix result = new Matrix(m.row,m.col);
        result.Copy(m);
        for(int i=0;i<m.row;++i){
            for(int j=0;j<m.col;++j){
                result.matrix[i][j] *= k;
            }
        }
        return result;
    }

    public static Matrix matMultiple(Matrix m1, Matrix m2){
        Matrix result = new Matrix(m1.row, m2.col);

        for(int i=0;i<m1.row;++i){
            for(int j=0;j<m2.col;++j){
                result.matrix[i][j]= 0;
                for(int k=0;k<m1.col;++k){
                    result.matrix[i][j] += m1.matrix[i][k]*m2.matrix[k][j];
                }
            }
        }

        return result;
    }

    // *** Operasi Matriks ***
    public double getDeterminant() {
        int size = this.row;
        double[][] M = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                M[i][j] = matrix[i][j]; 
            }
        }

        double determinant = 1.0;
        int swaps = 0;

        for (int i = 0; i < size; i++) {
            int pivot = i;

            for (int j = i; j < size; j++) {
                if (Math.abs(M[j][i]) > Math.abs(M[pivot][i])) {
                    pivot = j;
                }
            }

    
            if (Math.abs(M[pivot][i]) < 1e-9) {
                return 0.0;
            }

            if (pivot != i) {
                swaps++;
                double[] temp = M[i];
                M[i] = M[pivot];
                M[pivot] = temp;
            }

            for (int j = i + 1; j < size; j++) {
                double factor = M[j][i] / M[i][i];
                for (int k = i; k < size; k++) {
                    M[j][k] -= factor * M[i][k];
                }
            }

            determinant *= M[i][i];
        }

        if (swaps % 2 == 1) {
            determinant = -determinant;
        }

        return determinant;
    }

    public Matrix transpose(){
        Matrix A = new Matrix(this);
        A.Copy(this);
        for(int i=0;i< A.row;i++){
            for(int j=0;j<A.col;j++){
                A.matrix[i][j] = this.matrix[j][i];
            }
        }
        return A;
    }

       //fungsi inverse untuk bicubic
    //CHECK LAGI
    public boolean inverseMatrix() {
        int n = this.row;
        Matrix inverse = new Matrix(n, 2 * n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                inverse.matrix[i][j] = this.matrix[i][j];
                inverse.matrix[i][j + n] = (i == j) ? 1 : 0; // matrix identitas
            }
        }

        inverse.eselonBarisReduksi(); // Apply Gauss-Jordan elimination
        //check jumlah 0
        int zeroCount=0;
        boolean isInverse=true;//asumsi matrix memiliki inverse
        for (int i = 0; i < n; i++) {
            zeroCount=0;
            for (int j = 0; j < n; j++) {
                if (inverse.matrix[i][j]==0)
                {
                    zeroCount++;
                }
            }
            if (zeroCount==n)
            {
                isInverse=false;
            }
        }
        if (isInverse)
        {
            for (int i = 0; i < n; i++) {
                for (int j = n; j < 2 * n; j++) {
                    this.matrix[i][j - n] = inverse.matrix[i][j];
            }
        }
        }
        else{
            System.out.println("\n Tidak ada inverse.");
        }

        // Extract the inverse matrix from the augmented matrix
        return isInverse;
    }

    public  Matrix multiplyMatrix(Matrix matrix1, Matrix matrix2) {
               //asumsi square matrix
        
                Matrix result = new Matrix(matrix1.row, matrix1.row);
        
                for (int i = 0; i < matrix1.row; i++) {
                    for (int j = 0; j < matrix1.row; j++) {
                        double sum = 0.0;
                        for (int k = 0; k < matrix1.row; k++) {
                            sum += matrix1.matrix[i][k] * matrix2.matrix[k][j];
                        }
                        result.matrix[i][j]=sum;
                    }
                }
        
                return result;
    }



    /* ********** TIPE MATRIX ********** */
    public static Matrix Identitas(int N) {
        Matrix M = new Matrix(N, N);
        for (int i = 0; i < N; i++)
        {
            for (int j =0;j<N;j++)
            {
                M.matrix[i][j]=0;
            
            }
            M.matrix[i][i] = 1;
        }
        return M;
    }


    /* ********** OPERASI BARIS ELEMENTOR ********** */
    public void swap (int row1, int row2){
        double [] temp = matrix[row1];
        matrix [row1] = matrix[row2];
        matrix[row2] = temp;
    }

    public void timesrow(int row,  double k){
        int j;
        for (j = 0; j<col; j++){
            matrix[row][j] *= k;
        }
    }

    public void plustimesrow(int row1, int row2, double k){
        int j;
        for (j = 0; j<col; j++){
            matrix[row1][j] += matrix[row2][j]*k;
        }
    }

    public void minustimesrow(int row1, int row2, double k){
        int j;
        for (j = 0; j<col; j++){
            matrix[row1][j] -= matrix[row2][j]*k;
        }
    }

    public void plusrow(int row1, int row2){
        plustimesrow(row1,row2,1);
    }

    public void minusrow(int row1, int row2){
        minustimesrow(row1,row2,1);
    }

    public void eselonBaris() {
        int rowTemp = 0; // Menunjukkan baris saat ini
        int colTemp = 0; // Menunjukkan kolom saat ini

        while (rowTemp < this.row && colTemp < this.col) {
            // Cari baris dengan elemen pertama yang bukan nol
            int nonZeroRow = rowTemp;
            while (nonZeroRow < this.row && matrix[nonZeroRow][colTemp] == 0) {
                nonZeroRow++;
            }

            if (nonZeroRow < this.row) {
                // Swap baris dengan baris yang memiliki elemen pertama yang bukan nol
                swap(rowTemp, nonZeroRow);

                // Buat elemen pertama menjadi 1
                double pivot = matrix[rowTemp][colTemp];
                timesrow(rowTemp, 1.0 / pivot);

                // Eliminasi baris-baris di bawahnya
                for (int i = rowTemp + 1; i < this.row; i++) {
                    double factor = matrix[i][colTemp];
                    minustimesrow(i, rowTemp, factor);
                }

                rowTemp++; // Pindah ke baris berikutnya
            }

            colTemp++; // Pindah ke kolom berikutnya
        }
    }



    public void eselonBarisReduksi() {
        eselonBaris(); // Panggil eselonBaris terlebih dahulu untuk mendapatkan bentuk eselon baris

        // Mulai dari baris terbawah, kerja ke atas untuk menghilangkan elemen di atas
        // pivot
        for (int rowTemp = this.row - 1; rowTemp > 0; rowTemp--) {
            int colTemp = 0;
            while (colTemp < this.col && matrix[rowTemp][colTemp] == 0) {
                colTemp++;
            }

            if (colTemp < this.col) {
                // Eliminasi elemen di atas pivot
                for (int i = rowTemp - 1; i >= 0; i--) {
                    double factor = matrix[i][colTemp];
                    minustimesrow(i, rowTemp, factor);
                }
            }
        }
    }

    /* ********** SIFAT MATRIX ********** */
    public boolean isIdentity(int N)//mengecheck apakah matriks[N][N] adalah matriks identitas
    {
        boolean check = true;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!(((i == j) && matrix[i][j] == 1) || ((i != j) && matrix[i][j] == 0))) {
                    check = false;
                }
            }
        }
        return check;
    }

    public boolean IsSquare(){
        return (this.row == this.col);
    }

    // ***OPERASI MATRIX(KHUSUS)***

    public void Copy(Matrix in){
        
        this.row = in.row;
        this.col = in.col;
        this.matrix = new double[in.row][in.col];


        for(int i=0; i<in.row;++i){
            for(int j=0; j<in.col; ++j){
                this.matrix[i][j] = in.matrix[i][j];
            }
        }
    }

    public Matrix subcramer(int k){
        double [] temp = new double[this.row];
        for (int i =0; i< this.row;i++){
                temp[i] = this.matrix[i][this.col-1];
        }
        Matrix N = new Matrix(this);
        N.Copy(this);
        N.col -= 1;
        for (int j =0; j < N.row;j++){
            N.matrix[j][k] = temp[j];
        }

        return N;
    }


    public double matriksSegitigaAtas() {
        int rowTemp = 0; // Menunjukkan baris saat ini
        int colTemp = 0; // Menunjukkan kolom saat ini
        double jumlahswap = 0;
        while (rowTemp < this.row && colTemp < this.col) {
            int nonZeroRow = rowTemp;
            while (nonZeroRow < this.row && matrix[nonZeroRow][colTemp] == 0) {
                nonZeroRow++;
                
            }
    
            if (nonZeroRow < this.row) {
                swap(rowTemp, nonZeroRow);
                if (rowTemp != nonZeroRow){
                    jumlahswap++;
                }
                

                for (int i = rowTemp + 1; i < this.row; i++) {
                    double factor = matrix[i][colTemp] / matrix[rowTemp][colTemp];
                    minustimesrow(i, rowTemp, factor);
                }
    
                rowTemp++; 
            }
    
            colTemp++; 
        }
        return jumlahswap;
    }

    public Matrix subkofaktor(int baris, int kolom) {
        Matrix n = new Matrix(this.col-1,this.row-1);
        int k = 0;
        int z = 0;
    
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                if (i != baris && j != kolom) {
                    n.matrix[k][z] = this.matrix[i][j];
                    z += 1;
                    if (z == n.col) {
                        z = 0; // Kembali ke kolom pertama jika sudah mencapai kolom terakhir di submatriks
                        k += 1; // Pindah ke baris berikutnya di submatriks
                    }
                }
            }
        }
    
        return n;
    }
    
    public Matrix matrixkofaktor(){
        Matrix N = new Matrix(this);
        Matrix A = new Matrix(this);
        A.Copy(this);
        double subdet;
        for(int i =0; i< this.row;i++){
            for(int j=0;j< this.col;j++){
                N= A.subkofaktor(i, j);
                subdet = N.getDeterminant();;
                if (i %2 ==0){
                   if(j%2==0){
                      this.matrix[i][j] = subdet;
                    }
                    else{
                        this.matrix[i][j] = -1*subdet;
                    }
                }
                else{
                    if(j%2==0){
                      this.matrix[i][j] = -1*subdet;
                    }
                    else{
                        this.matrix[i][j] = subdet;
                    }
                }
                
            }
        }
        return this;
    }
    


    
    public double pow(double x,int y)
    {
        if (x==0&&y<0)
        {
            return 0;
        }
        else{
      return Math.pow(x,y);
        }
    }

    public static String KaidahCramer(Matrix m)
    {
        Matrix A = new Matrix (m);
        A.Copy(m);
        Matrix n = new Matrix(m.row,1);
        for (int i =0; i < m.row; i++){
            n.matrix[i][0] = A.matrix[i][m.col-1];
        }
        A.col -= 1;
        Matrix B = new Matrix(A);
        B.Copy(A);
        if (A.IsSquare() && A.inverseMatrix()){
            return SPL.getsolustioncramer(m, B);
        }
        else{
            return SPL.eksekusiGauss(m, false);

        }
    }  
    public static String inverseGausJordan(Matrix m)
    {
        Matrix A = new Matrix (m);
        A.Copy(m);
        Matrix n = new Matrix(m.row,1);
        for (int i =0; i < m.row; i++){
            n.matrix[i][0] = A.matrix[i][m.col-1];
        }
        A.col -= 1;
        if (A.inverseMatrix()){
            //contoh solusi jadi
            Matrix result = new Matrix(n);
            result = matMultiple(A, n);
            Capturer capturer = new Capturer();
            capturer.mulai();
            for (int i =0;i<result.row;i++){
                System.out.println("solusi  X" + (i+1) + " : " + result.GetElmt(i, 0));
            }
            String consoleOutput = capturer.stop();
            return consoleOutput;
        }
        else{
            return SPL.eksekusiGauss(m, false);

        }
    }  
}
