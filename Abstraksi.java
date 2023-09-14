import java.util.Scanner;

public class Abstraksi {
    static int baris,kolom; //m itu baris,n itu kolom
    static int[][] matrix;
    public static void main(String args[])
    {
        matrix = createMatrix();
        printMatrix(matrix,baris,kolom);

    }
    static int[][] createMatrix()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Jumlah Baris : ");
        baris = scanner.nextInt();
        System.out.println("Jumlah Kolom : ");
        kolom = scanner.nextInt();
        matrix = new int[baris][kolom];
        for(int i = 0; i < baris; i++)
        {
            for(int j = 0; j < kolom; j++)
            {   
                int elemen = scanner.nextInt();
                matrix[i][j] = elemen;
            }
        }
        return matrix;
    }
    static void printMatrix(int[][] matrix,int baris, int kolom)
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
