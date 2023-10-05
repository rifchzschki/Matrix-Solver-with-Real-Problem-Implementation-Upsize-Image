package src;
import java.util.*;



public class SPL {
    static Scanner scan = new Scanner(System.in);
    public static void SPLGaus(boolean jordan){
        System.out.print("M:");
        int m = scan.nextInt();
        System.out.print("N:");
        int n = scan.nextInt();
        Matrix mat = new Matrix(m, n+1);
        mat.readMatrix();
        if(jordan){
            eksekusiGauss(mat,true);
        } else {
            eksekusiGauss(mat,false);
        }

    }

    // public static void eksekusiGaussJordan(mat){

    // }

    public static void eksekusiGauss(Matrix mat,boolean jordan){
        mat.eselonBaris();
        if (jordan){
            mat.eselonBarisReduksi();
        }
        mat.printMatrix();
  
        if(mat.GetElmt(mat.GetLastIdxBrs(), mat.GetLastIdxKol()-1)==0 || ((mat.GetLastIdxBrs()+1)<(mat.GetLastIdxKol()))){
            if(mat.GetElmt(mat.GetLastIdxBrs(), mat.GetLastIdxKol())==0 || ((mat.GetLastIdxBrs()+1)<(mat.GetLastIdxKol()))){//solusi banyak
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
        double[] solution = new double[m.GetLastIdxKol()];


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
        SPL.SPLGaus(false);

    }
    public void getsolustioncramer(Matrix m, Matrix k){
        for (int i =0; i<= m.GetLastIdxBrs(); i++){    
            k = k.subcramer(m, i);
            double det = k.getDeterminant()/m.getDeterminant();
            System.out.format("X%d = %f\n",i+1, det);
        }
    }
}


//solusi tidak ada
// 1 1 -1 -1 1
// 2 5 -7 -5 -2
// 2 -1 1 3 4
// 5 2 -4 2 6

//solusi parametrik
// 1 -1 0 0 1 3
// 1 1 0 -3 0 6
// 2 -1 0 1 -1 5
// -1 2 0 -2 -1 -1

//solusi parametrik
// 1 1 2 4
// 2 -1 1 2
// 1 2 3 6

// solusi parametrik
// 0 1 0 0 1 0 2
// 0 0 0 1 1 0 -1
// 0 1 0 0 0 1 1    

// solusi tunggal
// 1 1 1 0
// 2 3 1 1
// 3 1 2 1