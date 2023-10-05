package src;
import java.util.Scanner;
public class Balikan {
    public Matrix result;
    Scanner scanner = new Scanner(System.in);
    public void Gausjordaninvers(Matrix m){
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

}
