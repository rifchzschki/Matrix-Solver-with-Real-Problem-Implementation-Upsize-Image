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
        // Matrix M = new Matrix(mat);
        // M.Copy(mat);
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
        System.out.println("parametric");
        String[] solution = new String[m.GetLastIdxBrs()+1];
        double temp;
        solution[m.GetLastIdxBrs()] = "k";
        double[] coef = new double[m.GetLastIdxKol()-1];
        double[] cons = new double[m.GetLastIdxKol()-1];

        cons[m.GetLastIdxBrs()-1]=m.GetElmt(m.GetLastIdxBrs()-1, m.GetLastIdxKol());

        for(int i=m.GetLastIdxBrs()-1;i>=0;--i){
            if (i<m.GetLastIdxBrs()-1){
                System.out.println(cons[i]);
                cons[i]=findCons(cons,i);
            }
            System.out.println(cons[i]);
            // solution[i] = String.valueOf(m.GetElmt(i, m.GetLastIdxKol()));
            // benerin dlu cari tau cara printnya
            //simpan koefisien dari hasil perhitungan
            // cons -= m.GetElmt(i, m.GetLastIdxKol());
            // coef = 0;
            for(int j=i+1;j<m.GetLastIdxKol();++j){
                // coef += m.GetElmt(i, j)*(-1);
                // coef = m.GetElmt(i,j)*temp;
                // System.out.println(m.GetElmt(i,j));
                // solution[i] += ("-"+String.valueOf(m.GetElmt(i,j))+solution[j]);
            }
            // solution[i] /= m.GetElmt(i, i);
        }

        for(int i=1;i<=m.GetLastIdxBrs()+1;++i){
            System.out.println("x"+i+"="+solution[i-1]);
        }

    }

    public static double findCons(double[] arrRow, int k){
        double cons=arrRow[k+1];

        for(int i=arrRow.length-1; i>k;--i){
            System.out.println("indx"+i);
            cons-=arrRow[i];
        }
        
        return cons;
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

    public void OBEdeterminant(Matrix m){
        double diagonal = 1;
        double det =1;
        double p = m.pow((-1), m.matriksSegitigaAtas());
        double epsilon = 1e-10; // Toleransi yang sangat kecil
        for (int i =0; i<= m.GetLastIdxBrs();i++){
            for(int j =0; j<= m.GetLastIdxKol();j++){
                if (i == j){
                    if (Math.abs(m.GetElmt(i, j)) < epsilon) {
                        diagonal = 0.0; // Jika elemen sangat mendekati 0, dianggap sebagai 0
                    }
                    diagonal *= m.GetElmt(i, j);
                }
            }
        }
        det = (p*diagonal)+0;
        System.out.format("%.2f", det);
    }
    
}





    // void menuSPL (int pilihan){
    //     switch(pilihan){
    //         case 1:
    //             System.out.println("Solusi SPL menggunakan eliminasi Gauss:");
    //             // eksekusi metode
    //             break;
    //         case 2:
    //             System.out.println("Solusi SPL menggunakan eliminasi Gauss-Jordan:");
    //             // eksekusi metode 
    //             break;
    //         case 3:
    //             System.out.println("Solusi SPL menggunakan eliminasi Matriks Balikan:");
    //             // eksekusi metode 
    //             break;
    //         case 4:
    //             System.out.println("Solusi SPL menggunakan eliminasi Kaidah Crammer:");
    //             break;
    //             // eksekusi metode 
    //     }
    // }



    

