package src;
import java.util.*;



public class SPL {
    static Scanner scan = new Scanner(System.in);
    public static void SPLGaus(){
        System.out.print("M:");
        int m = scan.nextInt();
        System.out.print("N:");
        int n = scan.nextInt();
        Matrix mat = new Matrix(m, n+1);
        mat.readMatrix();
        eksekusiGauss(mat);
    }

    public static void eksekusiGauss(Matrix mat){
        mat.eselonBaris();
        mat.printMatrix();
  
        if(mat.GetElmt(mat.GetLastIdxBrs(), mat.GetLastIdxKol()-1)==0){
            if(mat.GetElmt(mat.GetLastIdxBrs(), mat.GetLastIdxKol())==0){//solusi banyak
                parametricSolution(mat);                
            }else{//solusi tidak ada
                System.out.println("Solusi tidak ada.");
            }
        } else {
            System.out.println("ada");
            singleSolution(mat);
        }
      

    }
    
    public static void singleSolution(Matrix m){
        double[] solution = new double[m.GetLastIdxBrs()+1];


        for(int i=m.GetLastIdxBrs();i>=0;--i){
            solution[i] = m.GetElmt(i, m.GetLastIdxKol());
            for(int j=i+1;j<m.GetLastIdxKol();++j){
                solution[i] -= m.GetElmt(i,j)*solution[j];
            }
            solution[i] /= m.GetElmt(i, i);
        }

        for(int i=1;i<=m.GetLastIdxBrs()+1;++i){
            System.out.println("x"+i+"="+solution[i-1]);
        }
    }
    public static void parametricSolution(Matrix m){
        System.out.println("\nSolusi parametrik:");
        for (int row = m.GetLastIdxBrs()-1; row >= 0; row--) {
            System.out.print("x" + (row + 1) + " = " + m.GetElmt(row, m.GetLastIdxKol()));
            for (int col = row + 1; col < m.GetLastIdxKol(); col++) {
                System.out.print(" - " + m.GetElmt(row, col) + "x" + (col + 1));
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args){
        SPL.SPLGaus();
    }
    public void getsolustioncramer(Matrix m, Matrix k){
        for (int i =0; i<= m.GetLastIdxBrs(); i++){    
            k = k.subcramer(m, i);
            double det = k.getDeterminant()/m.getDeterminant();
            System.out.format("X%d = %f\n",i+1, det);
        }
    }
}






// 1 -1 0 0 1 3
// 1 1 0 -3 0 6
// 2 -1 0 1 -1 5
// -1 2 0 -2 -1 -1

// 1 1 2 4
// 2 -1 1 2
// 1 2 3 6

    