package src;

public class Balikan {
    public void Gausjordaninvers(Matrix m){
        if(m.inverseMatrix()){
            m.printMatrix();;
        }
    }

    public void Adjoint(Matrix m){
        Matrix A = new Matrix (m);
        A.Copy(m);
        if(A.inverseMatrix()){
            double p = 1/m.getDeterminant();
            A = m.matrixkofaktor();
            A = A.transpose();
            A = A.multiple(A, p);
            A.printMatrix();
        }
       

    }
}
