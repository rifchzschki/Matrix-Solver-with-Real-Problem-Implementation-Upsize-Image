package src;
import java.io.PrintStream;
import java.util.*;

public class Interpolasi {
    static Scanner scan = new Scanner(System.in);
    
    static double pow(double x,int y)
    {
        
        if (y==0)
        {
            return 1;

        }
        else
        {
            return (x*pow(x,y-1));
        }
    
    }

    public static String interpolasiGaus(int N, Matrix point, double x){
        
        Matrix equation =new Matrix(N,N+1);//masukan dalam matrix untuk eliminasi gauss jordan
        int power;
        for(int i =0;i<N;i++)
        {   
            power =1;
            for(int j =0;j<N+1;j++)
            {
                if (j==0)
                {
                    equation.setEl(i, j, 1);
                }
                else if (j==N)
                {
                    equation.setEl(i, j, point.GetElmt(i, 1));
                }
                else
                {
                    equation.setEl(i, j, pow(point.GetElmt(i,0),power));
                    power++;
                }
            }
        }

        //langkah gaus jordan
        equation.eselonBarisReduksi();

        //simpan hasil solusi

        double[] solution= new double[N];
         // Penyelesaian balik (back substitution)
         for (int i = N - 1; i >= 0; i--) {
             solution[i] = equation.GetElmt(i, N);
             for (int j = i + 1; j < N; j++) {
                 solution[i] -= equation.GetElmt(i, j) * solution[j];
             }
             solution[i] /= equation.GetElmt(i, i);
         }

         
         

         Capturer capturer = new Capturer();
         capturer.mulai();
         //PRINT PERSAMAAN
         String hasil = String.format("P(X)= (%.4f)",solution[0]);
         System.out.print(hasil);
         
         for (int i =1;i<N;i++)
         {
            hasil = String.format("+(%.4f)X^%d",solution[i],i);
            System.out.print(hasil);
         }
         System.out.println();
         
         
         double Y = 0;
         power =1;
         if (point.GetLastIdxBrs()==N)
         {
            x = point.GetElmt(N,0);
         }
         
         for (int i =1;i<N;i++)
         {
            Y+=solution[i]*pow(x,power);
            power++;
         }
         Y+=solution[0];
         System.out.print("f("+x+")"+"= "+ Y);
        
         String consoleOutput = capturer.stop();
         return consoleOutput;
    }

    public static void bicubicSplineInterpolation(Matrix m, double a,double b){
        Matrix bicubic = new Matrix(16,16);
        double[] solution = new double[16];
        //masukan nilai f(x,y)
        double x[] = { 0, 1, 0, 1 };
        double y[] = { 0, 0, 1, 1 };
        int pos=0;
        int tempi=0,tempj=0;
        for (int i=0;i<16;i++){ //membuat matrix X
            for(int j =0;j<16;j++){
                if (i<=3){
                    double powx = pow(x[pos],tempi); double powy = pow(y[pos], tempj);
                    bicubic.setEl(i,j,powx*powy);
                    tempi++;
                    if(tempi>3){
                        tempi=0;
                        tempj++;
                    }
                    if (tempj>3){
                        tempj=0;
                        tempi=0;
                        break;
                    }
                }
                else if (i>=4&&i<=7){//turunan x
                    double powx = pow(x[pos],tempi-1); double powy = pow(y[pos], tempj)*tempi;
                    bicubic.setEl(i,j,powx*powy);
                    tempi++;
                    if(tempi>3){
                        tempi=0;
                        tempj++;
                    }
                    if (tempj>3){
                        tempj=0;
                        tempi=0;
                        break;
                    }
                }
                else if(i>=8&&i<=11){ //turunnan y
                    double powx = pow(x[pos],tempi); double powy = pow(y[pos], tempj-1)*tempj;
                    bicubic.setEl(i,j,powx*powy);
                    tempi++;
                    if(tempi>3){
                        tempi=0;
                        tempj++;
                    }
                    if (tempj>3){
                        tempi=0;
                        tempj=0;
                        break;
                    }
                }
                else if(i>11){ //turunan xy
                    double powx = pow(x[pos],tempi-1);double powy = pow(y[pos], tempj-1)*tempj*tempi;
                    bicubic.setEl(i,j,powx*powy);
                    tempi++;
                    if(tempi>3){
                        tempi=0;
                        tempj++;
                    }
                    if (tempj>3){
                        tempi=0;
                        tempj=0;
                        break;
                    }
                }
            }
            pos++;
            if (pos>3){
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
                fx.setEl(k, 0, m.GetElmt(i, j));
                k++;
            }
        }
        
        //MELETAKAN HASIL KE DALAM ARRAY SOLUTION
        fx.printMatrix();//hasil f(x,y) pada matrix berkolom 1
        bicubic.printMatrix();//hasil inverse
    
        for (int i=0;i<16;i++){
            double tempresult=0;
            for(int j =0;j<16;j++){
                tempresult+=bicubic.GetElmt(i,j)*fx.GetElmt(j, 0);
            }
            solution[i]=tempresult;
        }
        
        //test print solusi
        System.out.print("[ ");
        for (int i =0 ;i<16;i++){
            System.out.print(" " + solution[i]+" ");
        }
        System.out.print("] ");

        double hasil=0;
        tempi=0;
        tempj=0;
        
        for (int i=0;i<16;i++){ //mencari nilai y
            hasil+=pow(a,tempi)*pow(b, tempj)*solution[i];
            tempi++;
            if(tempi>3){
                tempi=0;
                tempj++;
            }
            if (tempj>3){
                tempj=0;
                tempi=0;
                break;
            }
        }
        System.out.println(hasil);
        
    }
            
    public static void main(String[] args){
        // Interpolasi.interpolasiGaus();
        Matrix m = new Matrix(4,4 );
        m.readMatrix();
        Interpolasi.bicubicSplineInterpolation(m,0.5,0.5);
        // double x = pow(2,10);
        // System.out.println(x);
    }


}
