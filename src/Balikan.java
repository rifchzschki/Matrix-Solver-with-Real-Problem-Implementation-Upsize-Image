package src;
import java.util.Scanner;
public class Balikan {
    public Matrix result;
    Scanner scanner = new Scanner(System.in);
    public void Gausjordaninvers(){//APAKAH BELUM SELESAI?
        System.out.print("\nMasukan N : ");
        int n = scanner.nextInt();
        Matrix m = new Matrix(n,n);
        m.readMatrix();
        m.printMatrix();
        if(m.inverseMatrix()){
            System.out.println("Hasil Invers :");
            m.printMatrix();
        }
    }

    public Matrix Adjoint(Matrix m){
        Matrix A = new Matrix (m);
        A.Copy(m);
        if(A.inverseMatrix()){
            double p = 1/m.getDeterminant();
            A = m.matrixkofaktor();
            A = A.transpose();
            A = A.multiple(A, p);
        }
        return A;

    }

    public void runAdjoint(){
        System.out.print("\nMasukan N : ");
        int n = scanner.nextInt();
        Matrix m = new Matrix(n,n);
        m.readMatrix();
        m.printMatrix();
        m = Adjoint(m);
        System.out.println("Hasil Invers :");
        m.printMatrix();
    }
}
