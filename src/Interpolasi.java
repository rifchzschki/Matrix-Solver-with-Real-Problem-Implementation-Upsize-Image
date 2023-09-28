package src;
import java.util.*;

public class Interpolasi {
    static Scanner scan = new Scanner(System.in);
    
    static double pow(double x,double y)
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

    public static void interpolasiGaus(){
        double val;
        int N;
        System.out.print("N: ");
        N = scan.nextInt();
        Matrix point = new Matrix(N,2);//masukan nilai x,y pada tiap titik
        
        for (int i =0;i<N;i++){
            for (int j =0;j<2;j++){
                if (j==0){
                    System.out.print("X"+i+": ");
                } else{
                    System.out.print("Y"+i+": ");
                }
                val = scan.nextDouble();
                point.setEl(i, j, val);
            }
        }

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
         //PRINT PERSAMAAN
         String hasil = String.format("P(X)= (%.4f)",solution[0]);
         System.out.print(hasil);
         
         for (int i =1;i<N;i++)
         {
            hasil = String.format("+(%.4f)X^%d",solution[i],i);
            System.out.print(hasil);
         }
         System.out.println();
         
         
         System.out.print("X"+": ");
         double X = scan.nextDouble();
         double Y = 0;
         power =1;
         for (int i =1;i<N;i++)
         {
            Y+=solution[i]*pow(X,power);
            power++;
         }
         Y+=solution[0];
         System.out.print("f("+X+")"+"= "+ Y);
    
    }

    public static void main(String[] args){
        Interpolasi.interpolasiGaus();
    }


}
