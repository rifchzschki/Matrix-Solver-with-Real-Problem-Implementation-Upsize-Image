package src;
import java.util.*;

//untuk interpolasi bonus
import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
        // for (int i = 0; i < matrix.length; i++){
        //     for (int j = 0; j < matrix[0].length; j++)
        //     {
        //         this.matrix[i][j] = matrix[i][j];
        //     }
        // this.b[i] = b[i];
        // }
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
        System.out.print("\n");
    } 
    
    /* ********** SETTER ********** */
    public void setEl(int i, int j, double val){
        this.matrix[i][j] = val;
    }


    /* ********** SELEKTOR ********** */
    // public int GetFirstIdxBrs(Matrix M) {
    //     return 0;
    // }

    // public int GetFirstIdxKol(Matrix M) {
    //     return 0;
    // }

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

            // Cari baris pivot yang elemen diagonalnya bukan nol.
            for (int j = i; j < size; j++) {
                if (Math.abs(M[j][i]) > Math.abs(M[pivot][i])) {
                    pivot = j;
                }
            }

    
            // Jika elemen diagonal pivot adalah nol, maka determinan adalah nol.
            if (Math.abs(M[pivot][i]) < 1e-9) {
                return 0.0;
            }

    
            // Tukar baris jika pivot bukan pada baris i.
            if (pivot != i) {
                swaps++;
                double[] temp = M[i];
                M[i] = M[pivot];
                M[pivot] = temp;
            }

    
            // Kurangi baris-baris di bawah pivot untuk membuat elemen di bawah pivot menjadi nol.
            for (int j = i + 1; j < size; j++) {
                double factor = M[j][i] / M[i][i];
                for (int k = i; k < size; k++) {
                    M[j][k] -= factor * M[i][k];
                }
            }

            // Kalikan determinan dengan elemen diagonal (pivot).
            determinant *= M[i][i];
        }
    
        // Jika jumlah tukar baris ganjil, maka determinan negatif.
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



    // Operasi Baris Elementor
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

    /* ********** SIFAT MATRIX ********** */
    public boolean IsIdentitas() {
        boolean out = true;
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                if (!(((i == j) && matrix[i][j] == 1) || ((i != j) && matrix[i][j] == 0))) {
                    out = false;
                }
            }
        }
        return out;
    }

    public boolean IsSquare(){
        return (this.row == this.col);
    }

    // ***Operasi MATRIX***
    public static void main(String args[]){

        Matrix testSpl = new Matrix(3,3);
        Matrix A = new Matrix(testSpl);
        // testSpl.interpolasiGaus(3);
        // A = testSpl.regresiberganda();
        // A.printMatrix();
        Balikan tes = new Balikan();
        boolean eka = true;
        testSpl.readMatrix();
        // // A.Copy(testSpl);
        testSpl.printMatrix();
        System.out.println("");
        // testSpl.bicubicSplineInterpolation();
        // testSpl.inverseMatrix();
        // testSpl.printMatrix();
<<<<<<< Updated upstream
        // System.out.println("");
        // eka = tes.isinversvalid(testSpl);
        // System.out.println(eka+"");
        // if(testSpl.IsSquare() == true && tes.isinversvalid(testSpl) == true){
        //     testSpl.inverseMatrix();
        //     testSpl.printMatrix();
        // }
=======
        System.out.println("\n");
        testSpl.bonus();
        // try{
        //     BufferedImage foto = ImageIO.read(new File("fototest.jpg"));
        //     tes.bonus();

        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
        // A.printMatrix();
>>>>>>> Stashed changes
        // tes.getsolustioncramer(testSpl, A);
        // A = A.subcramer(testSpl, 0);
        // A.printMatrix();
        // testSpl.eselonBaris();
        // System.out.println("\n");
        // testSpl.printMatrix();
        tes.Adjoint(testSpl);
        // testSpl.printMatrix();
        // testSpl.printMatrix();

    }

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

    public Matrix subcramer(Matrix m, int k){
        double [] temp = new double[m.row];
        for (int i =0; i< m.row;i++){
            for (int j =0; j < m.col;j++){
                if (j == m.col-1){
                    temp[i] = m.matrix[i][j];
                }
            }
        }
        Matrix N = new Matrix(m);
        N.Copy(m);
        N.col -= 1;
        for (int i =0; i< N.col;i++){
            for (int j =0; j < N.row;j++){
                if (i == k){
                    N.matrix[j][i] = temp[j];
                }
            }
        }

        return N;
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






    
    public void approximate()
    {
        for (int i =0;i<row;i++)
        {
            for (int j =0 ; j<col;j++)
            {
                matrix[i][j]=(Math.round(matrix[i][j] * decPoint)/decPoint);
            }
        }
        }
    // public void approximate()
    // {
    //     for (int i =0;i<row;i++)
    //     {
    //         for (int j =0 ; j<col;j++)
    //         {
    //             matrix[i][j]=(Math.round(matrix[i][j] * decPoint)/decPoint);
    //         }
    //     }
    // }

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

    //  public static Matrix Identitas(int N) {
    //     Matrix M = new Matrix(N, N);
    //     for (int i = 0; i < N; i++)
    //         M.matrix[i][i] = 1;
    //     return M;
    // }

    public void inverseGausJordan()
    {
        //asumsi matrix NxN
        Matrix inverse = new Matrix(this.row,(this.row)*2);
        Matrix matrixIdentity = new Matrix(this.row,this.row);
        matrixIdentity=Identitas(this.row);
        int k=0,l=0;
        //buat matriks baru yang berisi gabungan matrix dan matrix identitas
        for (int i = 0;i<this.row;i++)
        {
            
            for (int j = 0 ;j<(this.row)*2;j++)
            {
                if (j<(this.col-1))
                {
                    inverse.matrix[i][j]=this.matrix[i][j];
                }
                else 
                {
                    inverse.matrix[i][j]=matrixIdentity.matrix[i][l];
                    l++;
                    if (l>this.row-1)
                    {
                        l =0;
                    }
                }
                
            }
            
        }
       //melakukan operasi eselon baris reduksi pada matrix inverse
        inverse.eselonBarisReduksi();
        

        //mengecek apakah adanya baris yang bernilai 0
        // int [] zeroCount=new int[this.row];
        //menghitung nilai 0 pada tiap baris
        int temp;
        boolean inverseExist = true;
        for(int i = 0 ; i <this.row;i++)
        {
            temp =0;
            for (int j =0 ;j<this.row;j++)
            {
                if (inverse.matrix[i][j]==0)
                {
                    temp++;
                }
            }
            if (temp == this.row)
            {
                
                inverseExist=false;
                break;
            }
        }
         
        
        // inverse.printMatrix();
        if (inverseExist)
        {
            //mengembalikan hasil inverse ke this.matrix
            for (int i = 0;i < this.row;i++)
            {
                for ( int j = this.row;j<(this.row)*2;j++)
                {
                    this.matrix[i][j-this.row]=inverse.matrix[i][j];
                    
                }
            }
            // contoh solusi jadi
            double[] solusi = new double[this.row];
            double temp1=0;
            int x =0;
            for (int i = 0;i < this.row;i++)
            {
                temp1=0;
                for ( int j = 0;j<this.col-1;j++)
                {
                    temp1+=this.matrix[i][j]*this.matrix[x][this.col-1];
                    x++;
                    if (x>this.row-1)
                    {
                        x=0;
                    }
                }
                solusi[i]=temp1;
            }
            for (int i =0;i<this.row;i++)
            {
                System.out.println("solusi  X" + (i+1) + " : " + solusi[i]);
            }
        }
        else
        {
            System.out.println("KONTOL!!!");
            
        }
        
   

    }


    public double matriksSegitigaAtas() {
        int rowTemp = 0; // Menunjukkan baris saat ini
        int colTemp = 0; // Menunjukkan kolom saat ini
        double jumlahswap = 0;
        while (rowTemp < this.row && colTemp < this.col) {
            // Cari baris dengan elemen pertama yang bukan nol
            int nonZeroRow = rowTemp;
            while (nonZeroRow < this.row && matrix[nonZeroRow][colTemp] == 0) {
                nonZeroRow++;
                
            }
    
            if (nonZeroRow < this.row) {
                // Swap baris dengan baris yang memiliki elemen pertama yang bukan nol
                swap(rowTemp, nonZeroRow);
                if (rowTemp != nonZeroRow){
                    jumlahswap++;
                }
                

                // Eliminasi baris-baris di bawahnya
                for (int i = rowTemp + 1; i < this.row; i++) {
                    double factor = matrix[i][colTemp] / matrix[rowTemp][colTemp];
                    minustimesrow(i, rowTemp, factor);
                }
    
                rowTemp++; // Pindah ke baris berikutnya
            }
    
            colTemp++; // Pindah ke kolom berikutnya
        }
        // this.printMatrix();
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

    public Matrix regresiberganda(){
        System.out.print("Masukkan jumlah peubah x(n) : ");
        int n = scanner.nextInt();
        n += 1;
        System.out.print("Masukkan jumlah sampel(m) : ");
        int m = scanner.nextInt();
        double [][] x = new double [m][n];

        for (int i =0; i<m;i++){
            for (int j=0; j <n;j++){
                if (j == n-1){
                    System.out.print("Y" +(i+1)+" : " );
                }
                else{
                    System.out.print("X" +(j+1)+" : ");
                }
                x[i][j] = scanner.nextDouble();
            }
        }

        Matrix regresi = new Matrix(m,n+1);

        for (int i =0; i<regresi.row;i++){
            for (int j=0; j <regresi.col;j++){
                if (j == 0){
                    regresi.matrix[i][j] = 1;
                }
                else{
                    regresi.matrix[i][j] = x[i][j-1];
                }
                
            }
        }

        return regresi;


    
    }

   



    public int bicubicSplineInterpolationBonus(BufferedImage foto, double pixelx, double pixely) throws IOException {
        Matrix bicubic = new Matrix(16, 16);
        double x[] = { 0, 1, 0, 1 };
        double y[] = { 0, 0, 1, 1 };
        int pos = 0;
        int tempi = 0, tempj = 0;
        for (int i = 0; i < 16; i++) // membuat matrix X
        {
            for (int j = 0; j < 16; j++) {

                if (i <= 3) {
                    bicubic.matrix[i][j] = pow(x[pos], tempi) * pow(y[pos], tempj);
                    tempi++;
                    if (tempi > 3) {
                        tempi = 0;
                        tempj++;
                    }
                    if (tempj > 3) {
                        tempj = 0;
                        tempi = 0;
                        break;
                    }
                } else if (i >= 4 && i <= 7)// turunan x
                {

                    bicubic.matrix[i][j] = pow(x[pos], tempi - 1) * pow(y[pos], tempj) * tempi;
                    tempi++;
                    if (tempi > 3) {
                        tempi = 0;
                        tempj++;
                    }
                    if (tempj > 3) {
                        tempj = 0;
                        tempi = 0;
                        break;
                    }

                } else if (i >= 8 && i <= 11) // turunnan y
                {

                    bicubic.matrix[i][j] = pow(x[pos], tempi) * pow(y[pos], tempj - 1) * tempj;
                    tempi++;
                    if (tempi > 3) {
                        tempi = 0;
                        tempj++;
                    }
                    if (tempj > 3) {
                        tempi = 0;
                        tempj = 0;
                        break;
                    }

                } else if (i > 11) // turunan xy
                {

                    bicubic.matrix[i][j] = pow(x[pos], tempi - 1) * pow(y[pos], tempj - 1) * tempj * tempi;
                    tempi++;
                    if (tempi > 3) {
                        tempi = 0;
                        tempj++;
                    }
                    if (tempj > 3) {
                        tempi = 0;
                        tempj = 0;
                        break;
                    }

                }
            }
            pos++;
            if (pos > 3) {
                pos = 0;
            }
        }
        bicubic.inverseMatrix();// melakukaninverse matrix x

        int xi[] = { -1, 0, 1, 2 };
        int yi[] = { -1, 0, 1, 2 };

        // buat Matrix D
        Matrix bicubicbonus = new Matrix(16, 16);
        pos = 0;
        tempi = 0;
        tempj = 0;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (i <= 3) {

                    if (x[pos] == xi[tempj] && y[pos] == yi[tempi]) {
                        bicubicbonus.matrix[i][j] = 1;
                        tempi = 0;
                        tempj = 0;
                        break;
                    }
                    tempj++;
                    if (tempj > 3) {
                        tempj = 0;
                        tempi++;
                    }
                    if (tempi > 3) {
                        tempj = 0;
                        tempi = 0;
                    }

                } else if (i >= 4 && i <= 7)// turunan x
                {

                    if ((x[pos] + 1 == (xi[tempj])) && (y[pos] == yi[tempi])) {
                        bicubicbonus.matrix[i][j] = 1 / (double) 2;

                    } else if ((x[pos] - 1) == (xi[tempj]) && (y[pos] == yi[tempi])) {
                        bicubicbonus.matrix[i][j] = -0.5;

                    }
                    tempj++;
                    if (tempj > 3) {
                        tempj = 0;
                        tempi++;
                    }
                    if (tempi > 3) {
                        tempj = 0;
                        tempi = 0;
                    }

                } else if (i >= 8 && i <= 11) // turunnan y
                {

                    if ((x[pos] == (xi[tempj])) && (y[pos] + 1 == (yi[tempi]))) {
                        bicubicbonus.matrix[i][j] = (double) 1 / 2;

                    } else if ((x[pos] == (xi[tempj])) && (y[pos] - 1 == (yi[tempi]))) {
                        bicubicbonus.matrix[i][j] = -1 / (double) 2;

                    }
                    tempj++;
                    if (tempj > 3) {
                        tempj = 0;
                        tempi++;
                    }
                    if (tempi > 3) {
                        tempj = 0;
                        tempi = 0;
                    }

                } else if (i > 11) // turunan xy
                {

                    if (x[pos] + 1 == (xi[tempj]) && y[pos] + 1 == (yi[tempi])) {
                        bicubicbonus.matrix[i][j] = 1 / (double) 4;

                    } else if (x[pos] - 1 == (xi[tempj]) && y[pos] == (yi[tempi])) {
                        bicubicbonus.matrix[i][j] = -1 / (double) 4;

                    } else if (x[pos] == (xi[tempj]) && y[pos] - 1 == (yi[tempi])) {
                        bicubicbonus.matrix[i][j] = -1 / (double) 4;

                    } else if (x[pos] == (xi[tempj]) && y[pos] == (yi[tempi])) {
                        bicubicbonus.matrix[i][j] = -1 / (double) 4;

                    }
                    tempj++;
                    if (tempj > 3) {
                        tempj = 0;
                        tempi++;
                    }
                    if (tempi > 3) {
                        tempj = 0;
                        tempi = 0;
                    }

                }
            }
            pos++;
            if (pos > 3) {
                pos = 0;
            }
        }
        

        // kali inverse matrix x dengan matrix d
        Matrix result = new Matrix(16, 16);
        result = multiplyMatrix(bicubic, bicubicbonus);// hasil kali matrix inverse X dengan matrix D
        // mencari nilai citra tiap koordinat gambar
        Matrix nilaiPixel = new Matrix(16, 4);//nilai rgb
        //[red][green][blue][nilai pixel]
        // kalkulasi nilai per RGB
        tempi = 0;
        tempj = 0;
        int xwidth[] = {(int) pixelx-2, (int)pixelx-1, (int) pixelx+1,(int) pixelx+2};//mencari titik referensi, sudah benarkah?
        int ywidth[] = {(int) pixely-2, (int) pixely-1, (int) pixely+1, (int) pixely+2 };
        for (int i = 0; i < 16; i++) {
            xwidth[tempj]=Math.min(foto.getWidth()-1, Math.max(0, xwidth[tempj]));
            ywidth[tempi]=Math.min(foto.getHeight()-1, Math.max(0, ywidth[tempi]));
            nilaiPixel.matrix[i][3] = foto.getRGB(xwidth[tempj], ywidth[tempi]);
            nilaiPixel.matrix[i][0]=  ((int)nilaiPixel.matrix[i][3] >> 16) & 0xFF;//nilai Citra I(x,y) tiap RGB
            nilaiPixel.matrix[i][1]=  ((int)nilaiPixel.matrix[i][3] >>8 ) & 0xFF;
            nilaiPixel.matrix[i][2]=  ((int)nilaiPixel.matrix[i][3]) & 0xFF;
            tempj++;
            if (tempj > 3) {
                tempi++;
                tempj = 0;
            }
            if (tempi > 3) {
                tempi = 0;
                tempj = 0;
                break;
            }
    

        }
        Matrix solution = new Matrix(16,3);//[red][green][blue]
        // mencari nilai a
        tempi = 0;
        tempj = 0;
        int red=0;
        int blue=0;
        int green=0;
        for (int i = 0; i < 16; i++) {
            red = 0;
            blue=0;
            green=0;
            for (int j = 0; j < 16; j++) {
                red += result.matrix[i][j] * nilaiPixel.matrix[j][0];
                green += result.matrix[i][j] * nilaiPixel.matrix[j][1];
                blue += result.matrix[i][j] * nilaiPixel.matrix[j][2];
            }
            //nilai koefisien untuk tiap warna
            solution.matrix[i][0] = red;
            solution.matrix[i][1] = green;
            solution.matrix[i][2] = blue;
        }
        int newValueRed = 0;
        int newValueGreen = 0;
        int newValueBlue = 0;
        for (int i = 0; i < 16; i++) // mencari nilai hasil nilai tiap RGB dengan f(x,y)
        {
            newValueRed += pow(pixelx/foto.getWidth(), tempi) * pow(pixely/foto.getHeight(), tempj) * solution.matrix[i][0];
            newValueGreen += pow(pixelx/foto.getWidth(), tempi) * pow(pixely/foto.getHeight(), tempj) * solution.matrix[i][1];
            newValueBlue += pow(pixelx/foto.getWidth(), tempi) * pow(pixely/foto.getHeight(), tempj) * solution.matrix[i][2];
            tempj++;
            if (tempj > 3) {
                tempj = 0;
                tempi++;
            }
            if (tempi > 3) {
                tempj = 0;
                tempi = 0;
                break;
            }
        }
        red = (newValueRed);
        green = (newValueGreen);
        blue = newValueBlue;
        //nilai max RGB berada diantara 0 sampai 255
        red = Math.min(255, Math.max(0, red));
        green = Math.min(255, Math.max(0, green));
        blue = Math.min(255, Math.max(0, blue));
        // Gabungkan nilai-nilai saluran ke nilai warna RGB
        int rgbColor = (red << 16) | (green << 8) | blue;  // Kembalikan nilai RGB yang sudah dinormalisasi
        return rgbColor;

    }

    public void bonus()//fungsi utama bonus
    {
        try { // Baca gambar sumber
            System.out.print("File path:  ");
            String filepath = scanner.nextLine();
            BufferedImage foto = ImageIO.read(new File(filepath));

            // Tentukan faktor perbesaran (misalnya, dua kali lipat)
            System.out.print("Besar skala perbesaran yang diinginkan: ");
            int scaleFactor = scanner.nextInt();//baru bisa buat yang integer...
            System.out.println("Loading... ");
            
            int newWidth =  foto.getWidth() * scaleFactor;
            int newHeight = foto.getHeight() * scaleFactor;

            // Buat gambar hasil dengan resolusi baru
            BufferedImage resultImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);

            // Lakukan bicubic spline interpolation pada gambar
            for (int y = 0; y < newHeight; y++) {
                for (int x = 0; x < newWidth; x++) {
                    // Lakukan interpolasi bicubic pada setiap piksel
                    int interpolatedColor = bicubicSplineInterpolationBonus(foto, (x+0.5)/(double) scaleFactor -0.5,(y+0.5)/(double) scaleFactor-0.5);

                    // Tetapkan warna hasil ke piksel
                    resultImage.setRGB(x, y, interpolatedColor);
                }
            }

            // Simpan gambar hasil
            saveImage(resultImage,"src/output.jpg");
            System.out.println("Hasil gambar bernama output.jpg ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    

   //fungsi menyimpan gambar
    public void saveImage(BufferedImage image, String filePath) throws IOException {
        File outputImageFile = new File(filePath);
        ImageIO.write(image, "jpg", outputImageFile);
    }  
   //fungsi membaca gambar
   
   
   
   
   
   
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








}







<<<<<<< Updated upstream















=======
>>>>>>> Stashed changes
