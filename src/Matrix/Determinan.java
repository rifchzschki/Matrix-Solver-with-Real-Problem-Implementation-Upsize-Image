package src;
import java.util.Scanner;

public class Determinan {
    Scanner scanner = new Scanner(System.in);
    public void kofaktorDeterminant(Matrix m){
        Matrix N = new Matrix(m);
        int i =0;
        double det =0;
        double subdet;
        double epsilon = 1e-10; // Toleransi yang sangat kecil
        for(int j=0;j<= m.GetLastIdxKol();j++){
            N= m.subkofaktor(i, j);
            subdet = m.GetElmt(i,j)*N.getDeterminant();;
            if (j %2 ==0){
                det += subdet;
            }
            else{
                det -= subdet;
            }
            
        }
        if (Math.abs(det) < epsilon) {
            det = 0.0; // Jika elemen sangat mendekati 0, dianggap sebagai 0
        }
        System.out.format("Hasil Determinant: %.2f\n", det);
    }
    
    public void OBEDeterminant(Matrix m){
        double diagonal = 1;
        double det =1;
        double p = ((m.matriksSegitigaAtas() %2 == 0)? 1 : -1 ) ;
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
        det = (p*diagonal);
        System.out.format("Hasil Determinant: %.2f\n", det);
    }

}

