package src;

public class Balikan {
    public Matrix result;
    public void Gausjordaninvers(Matrix m){//APAKAH BELUM SELESAI?
        if(m.inverseMatrix()){
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
            result = A;
        }
       return result;

    }
}
