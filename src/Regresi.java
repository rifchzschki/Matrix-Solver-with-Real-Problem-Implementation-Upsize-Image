package src;
import java.util.*;

public class Regresi {
    static Scanner scan = new Scanner(System.in);

    public static void regresiberganda(){
        System.out.print("Masukkan jumlah peubah x(n) : ");
        int n = scan.nextInt();
        n += 1;
        System.out.print("Masukkan jumlah sampel(m) : ");
        int m = scan.nextInt();
        double [][] x = new double [m][n];

        for (int i =0; i<m;i++){
            for (int j=0; j <n;j++){
                if (j == n-1){
                    System.out.print("Y" +(i+1)+" : " );
                }
                else{
                    System.out.print("X" +(j+1)+" : ");
                }
                x[i][j] = scan.nextDouble();
            }
        }

        Matrix regresi = new Matrix(m,n+1);

        for (int i =0; i<=regresi.GetLastIdxBrs();i++){
            for (int j=0; j <=regresi.GetLastIdxKol();j++){
                if (j == 0){
                    regresi.setEl(i, j, 1);
                }
                else{
                    regresi.setEl(i, j, x[i][j-1]);
                }
                
            }
        }

        
        //(X'X)^-1(X'Y)
        hitungbeta(regresi);


    
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

    public static void hitungbeta(Matrix m){
        Balikan opBalikan = new Balikan();
        Matrix X = new Matrix(m.GetLastIdxBrs()+1, m.GetLastIdxKol());
        Matrix Y = new Matrix(m.GetLastIdxBrs()+1,1);
        Matrix Mresult;

        for (int i=0; i<=m.GetLastIdxBrs();++i){
            for (int j=0; j<=m.GetLastIdxKol();++j){
                if(j==m.GetLastIdxKol()){
                    Y.setEl(i, 0, m.GetElmt(i, j));
                } else {
                    X.setEl(i, j, m.GetElmt(i, j));
                }
            }

        }
        Matrix invAA = (opBalikan.Adjoint(matMultiple(transpose(X), X)));
        Mresult = matMultiple((invAA),(matMultiple(transpose(X), Y)));
        // Mresult.printMatrix();
        printEquation(Mresult);
        
    }
    public static void printEquation(Matrix m){
        String hasil = String.format("P(X)= (%.4f)",m.GetElmt(0, 0));
        System.out.print(hasil);
        
        for (int i =1;i<=m.GetLastIdxBrs();i++){
            hasil = String.format("+(%.4f)X%d",m.GetElmt(i, 0),i);
            System.out.print(hasil);
        }
        System.out.println();
        
        
        System.out.print("X"+": ");
        double X = scan.nextDouble();
        double Y = 0;
        for (int i =1;i<=m.GetLastIdxBrs();i++){
            Y+=X*m.GetElmt(i,0);
        }
        Y+=m.GetElmt(0, 0);
        System.out.print("f("+X+")"+"= "+ Y);
    }

    public static void main(String[] args){
        Regresi.regresiberganda();
    }

}
