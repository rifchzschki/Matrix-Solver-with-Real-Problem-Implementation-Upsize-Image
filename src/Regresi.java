package src;
import java.util.*;

public class Regresi {
    static Scanner scan = new Scanner(System.in);

    public static Matrix regresiberganda(int m, int n, Matrix x){
        
        Matrix regresi = new Matrix(m,n+1);

        for (int i =0; i<=regresi.GetLastIdxBrs();i++){
            for (int j=0; j <=regresi.GetLastIdxKol();j++){
                if (j == 0){
                    regresi.setEl(i, j, 1);
                }
                else{
                    regresi.setEl(i, j, x.GetElmt(i, j-1));
                }
                
            }
        }

        
        // //(X'X)^-1(X'Y)
        // hitungbeta(regresi, X);
        return regresi;

    
    }

    public static Matrix matMultiple(Matrix m1, Matrix m2){
        Matrix result = new Matrix(m1.GetLastIdxBrs()+1, m2.GetLastIdxKol()+1);
        double temp;

        for(int i=0;i<m1.GetLastIdxBrs()+1;++i){
            for(int j=0;j<m2.GetLastIdxKol()+1;++j){
                result.setEl(i, j, 0);
                for(int k=0;k<m1.GetLastIdxKol()+1;++k){
                    temp = result.GetElmt(i,j);
                    temp += m1.GetElmt(i, k)*m2.GetElmt(k, j);
                    result.setEl(i, j, temp);
                }
            }
        }

        return result;
    }

    public static Matrix transpose(Matrix m){
        Matrix A = new Matrix(m.GetLastIdxKol()+1,m.GetLastIdxBrs()+1);
        for(int i=0;i<= m.GetLastIdxBrs();i++){
            for(int j=0;j<=m.GetLastIdxKol();j++){
                A.setEl(j, i, m.GetElmt(i, j));
            }
        }
        return A;
    }

    public static Matrix hitungbeta(Matrix m){
        Balikan opBalikan = new Balikan();
        Matrix x = new Matrix(m.GetLastIdxBrs()+1, m.GetLastIdxKol());
        Matrix Y = new Matrix(m.GetLastIdxBrs()+1,1);
        Matrix Mresult;

        for (int i=0; i<=m.GetLastIdxBrs();++i){
            for (int j=0; j<=m.GetLastIdxKol();++j){
                if(j==m.GetLastIdxKol()){
                    Y.setEl(i, 0, m.GetElmt(i, j));
                } else {
                    x.setEl(i, j, m.GetElmt(i, j));
                }
            }

        }
        Matrix invAA = (opBalikan.Adjoint(matMultiple(transpose(x), x)));
        invAA.printMatrix();
        Mresult = matMultiple((invAA),(matMultiple(transpose(x), Y)));
        // Mresult.printMatrix();
        // printEquation(Mresult,X);
        return Mresult;
        
    }
    public static String printEquation(Matrix m, double[] X){
        Capturer capturer = new Capturer();
        capturer.mulai();

        String hasil = String.format("P(X)= (%.4f)",m.GetElmt(0, 0));
        System.out.print(hasil);
        
        for (int i =1;i<=m.GetLastIdxBrs();i++){
            hasil = String.format("+(%.4f)X%d",m.GetElmt(i, 0),i);
            System.out.print(hasil);
        }
        System.out.println();
        
        
        
        double Y = m.GetElmt(0,0);
        System.out.print("f(");
        for (int i =1;i<=m.GetLastIdxBrs();i++){
            Y+=X[i-1]*m.GetElmt(i,0);
            System.out.printf("%.02f",X[i-1]);
            if(i<m.GetLastIdxBrs()){
                System.out.print(",");

            }
        }
        System.out.printf(") = %.02f",Y);

        String consoleOutput = capturer.stop();
        return consoleOutput;
    }

    // public static void main(String[] args){
        
    //     regresiberganda();
    // }

}
