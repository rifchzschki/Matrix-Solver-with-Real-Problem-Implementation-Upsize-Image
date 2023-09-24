import java.util.Scanner;

public class Abstraksi {
    static int baris,kolom; //m itu baris,n itu kolom
    static int[][] matrix;
    static int[] b;
    static int[] array;
    static int[][] augmentedMatrix;
    Scanner scanner = new Scanner(System.in);
    
    public static void main(String args[])
    {
        SPL Matrix = new SPL();
        // readMatrix();
        // printMatrix(matrix,3,3);

    }
    
    
    public void printMatrix(int[][] matrix,int baris, int kolom)
    {
        for(int i = 0; i < baris; i++)
        {
            
            for(int j = 0; j < kolom; j++)
            {   
               System.out.format(" %d ",matrix[i][j]);
            }
            
            System.out.println();
        }
        // static void Gaus(String args[]) 
    }

    
}

// . Library tersebut berisi fungsi-fungsi seperti eliminasi Gauss,
// eliminasi Gauss-Jordan,
//     menentukan balikan matrix,
//     menghitung determinan, kaidah

// Cramer (kaidah Cramer khusus untuk SPL dengan n peubah dan n persamaan).
class SPL{
    static int baris,kolom; //m itu baris,n itu kolom
    static double[][] matrix;
    static double[] b;
    static double[] array;
    static double[][] augmentedMatrix;
    Scanner scanner = new Scanner(System.in);
    public double[][] readMatrix()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Jumlah Baris : ");//m
        baris = scanner.nextInt();
        System.out.println("Jumlah Kolom : ");//n
        kolom = scanner.nextInt();
        matrix = new double[baris][kolom]; //a[i][j]
        array = new double[baris]; //b[i]
        for(int i = 0; i < baris; i++)
        {
            for(int j = 0; j < kolom; j++)
            {   
                System.out.println("Isi elemen a ke [" + (i) + "]" +"[" + (j) + "] : ");
                double elemen = scanner.nextDouble();
                matrix[i][j] = elemen;//koef a[i][j]
                
            }
            System.out.println("Isi elemen b ke [" + (i) + "] : ");
            double elemen = scanner.nextDouble(); //elemen b[i]
            array[i]=elemen;
        }
        return matrix;
    }

    public double[][] createAugmentedMatrix(int[][] a, int[] b)
    {
        Scanner scanner = new Scanner(System.in);
        for(int i = 0; i < kolom; i++)
        {
            System.out.println("Isi b : ");
            b[i] = scanner.nextInt();
        }
        for(int i = 0; i < baris; i++)
        {
            for(int j = 0; j < kolom+1; j++)
            {   
                if (j == kolom)
                {
                    augmentedMatrix[i][j] = b[i];
                }
                augmentedMatrix[i][j] = matrix[i][j];
            }
        }

        return augmentedMatrix;
    }

    public double[][] obe()
    {

        System.out.print("Masukkan jumlah baris/kolom matrix: ");
        readMatrix();

        // Eliminasi Gauss
        // for (int i = 0; i < baris; i++) {
        //     for (int j = i + 1; j < kolom; j++) {
        //         double ratio = matrix[j][i] / matrix[i][i];
        //         for (int k = 0; k < baris + 1; k++) {
        //             matrix[j][k] -= ratio * matrix[i][k];
        //         }
        //     }
        // }

        // Penyelesaian balik (back substitution)
        double[] solusi = new double[baris];
        for (int i = baris - 1; i >= 0; i--) {
            solusi[i] = matrix[i][baris];
            for (int j = i + 1; j < baris; j++) {
                solusi[i] -= matrix[i][j] * solusi[j];
            }
            solusi[i] /= matrix[i][i];
        }

        System.out.println("Hasil setelah eliminasi Gauss:");
        for (int i = 0; i < baris; i++) {
            System.out.println("x" + (i + 1) + " = " + solusi[i]);
        }
        return matrix;
    }









}
