package src;
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
    public static Matrix multiple (Matrix m, double k){
        Matrix result = new Matrix(m.row,m.col);
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

    // ***Operasi MATRIX***
    public static void main(String args[]){

        Matrix testSpl = new Matrix(4,4);
        Matrix A = new Matrix(testSpl);
        // testSpl.interpolasiGaus(3);
        // A = testSpl.regresiberganda();
        // A.printMatrix();
        SPL tes = new SPL();
        // testSpl.readMatrix();
        // // A.Copy(testSpl);
        // testSpl.printMatrix();
        System.out.println("\n");
        testSpl.bicubicSplineInterpolation();
        // A.printMatrix();
        // tes.getsolustioncramer(testSpl, A);
        // A = A.subcramer(testSpl, 0);
        // A.printMatrix();
        // testSpl.eselonBaris();
        // System.out.println("\n");
        // testSpl.printMatrix();
        // testSpl.eselonBarisReduksi();
        // System.out.println("\n");
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
            // //contoh solusi jadi
            // double[] solusi = new double[this.row];
            // double temp1=0;
            // int x =0;
            // for (int i = 0;i < this.row;i++)
            // {
            //     temp1=0;
            //     for ( int j = 0;j<this.col-1;j++)
            //     {
            //         temp1+=this.matrix[i][j]*this.matrix[x][this.col-1];
            //         x++;
            //         if (x>this.row-1)
            //         {
            //             x=0;
            //         }
            //     }
            //     solusi[i]=temp1;
            // }
            // for (int i =0;i<this.row;i++)
            // {
            //     System.out.println("solusi  X" + (i+1) + " : " + solusi[i]);
            // }
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
        Matrix n = new Matrix(this.row - 1, this.col - 1);
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

    public void bicubicSplineInterpolation()
    {
        Matrix bicubic = new Matrix(16,16);
        double[] solution = new double[16];
        //masukan nilai f(x,y)
        double x[] = { 0, 1, 0, 1 };
        double y[] = { 0, 0, 1, 1 };
        int pos=0;
        int tempi=0,tempj=0;
    for (int i=0;i<16;i++) //membuat matrix X
    {
        for(int j =0;j<16;j++)
        {
            
            if (i<=3)
            {
                bicubic.matrix[i][j]=pow(x[pos],tempi)*pow(y[pos], tempj);
                tempi++;
                if(tempi>3)
                {
                    tempi=0;
                    tempj++;
                }
                if (tempj>3)
                {
                    tempj=0;
                    tempi=0;
                    break;
                }
            }
            else if (i>=4&&i<=7)//turunan x
            {
                
                    bicubic.matrix[i][j]=pow(x[pos],tempi-1)*pow(y[pos], tempj)*tempi;
                    tempi++;
                    if(tempi>3)
                    {
                        tempi=0;
                        tempj++;
                    }
                    if (tempj>3)
                    {
                        tempj=0;
                        tempi=0;
                        break;
                    }
                
            }
            else if(i>=8&&i<=11) //turunnan y
            {
                
                    bicubic.matrix[i][j]=pow(x[pos],tempi)*pow(y[pos], tempj-1)*tempj;
                    tempi++;
                    if(tempi>3)
                    {
                        tempi=0;
                        tempj++;
                    }
                    if (tempj>3)
                    {
                        tempi=0;
                        tempj=0;
                        break;
                    }
                
            }
            else if(i>11) //turunan xy
            {
               
                    bicubic.matrix[i][j]=pow(x[pos],tempi-1)*pow(y[pos], tempj-1)*tempj*tempi;
                    tempi++;
                    if(tempi>3)
                    {
                        tempi=0;
                        tempj++;
                    }
                    if (tempj>3)
                    {
                        tempi=0;
                        tempj=0;
                        break;
                    }
                

            }
        }
        pos++;
        if (pos>3)
        {
            pos=0;
        }
    }
    bicubic.printMatrix();
    bicubic.inverseMatrix();//melakukaninverse x
    

    Matrix fx = new Matrix(16,1);//MELETAKAN HASIL BACA FILE MATRIX KE MATRIX BERKOLOM 1
    int k=0;
    for (int i =0;i<4;i++)
    {
        for (int j =0;j<4;j++)
        {
            fx.matrix[k][0]=this.matrix[i][j];
            k++;
        }
    }
//MELETAKAN HASIL KE DALAM ARRAY SOLUTION
fx.printMatrix();//hasil f(x,y) pada matrix berkolom 1
bicubic.printMatrix();//hasil inverse

    
for (int i=0;i<16;i++)
{
    double tempresult=0;
    for(int j =0;j<16;j++)
    {
        tempresult+=bicubic.matrix[i][j]*fx.matrix[j][0];
    }
    solution[i]=tempresult;
}
//test print solusi
System.out.print("[ ");
for (int i =0 ;i<16;i++)
{
    System.out.print(" " + solution[i]+" ");
}
System.out.print("] ");



System.out.print("a: ");
double a = scanner.nextDouble();
System.out.print("b: ");
double b = scanner.nextDouble();

double hasil=0;
tempi=0;
tempj=0;

for (int i=0;i<16;i++) //membuat matrix X
    {
       
        
            
            
    hasil+=pow(a,tempi)*pow(b, tempj)*solution[i];
    tempi++;
    if(tempi>3)
    {
        tempi=0;
        tempj++;
    }
    if (tempj>3)
    {
        tempj=0;
        tempi=0;
        break;
    }

    }
    

           
        System.out.println(hasil);
    
    }

    //fungsi inverse untuk bicubic
    //CHECK LAGI
    public void inverseMatrix() {
        if (this.row != this.col) {
            System.out.println("Matrix tidak berbentuk square");
            return;
        }

        int n = this.row;
        Matrix inverse = new Matrix(n, 2 * n);

        //membuat matrix inverse dengan
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                inverse.matrix[i][j] = this.matrix[i][j];
                inverse.matrix[i][j + n] = (i == j) ? 1 : 0; // matrix identitas
            }
        }

        inverse.eselonBarisReduksi(); // Apply Gauss-Jordan elimination

        // Extract the inverse matrix from the augmented matrix
        for (int i = 0; i < n; i++) {
            for (int j = n; j < 2 * n; j++) {
                this.matrix[i][j - n] = inverse.matrix[i][j];
            }
        }
    }




















}

