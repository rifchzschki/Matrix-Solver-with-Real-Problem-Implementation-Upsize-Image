// import java.util.Scanner;

// public class SPL{
//     public int baris,kolom; //m itu baris,n itu kolom
//     public double[][] matrix;
//     public double[] b;
//     public double[] array;
//     public double[][] augmentedMatrix;
//     Scanner scanner = new Scanner(System.in);
//     // public void readMatrix()
//     // {
//     //     Scanner scanner = new Scanner(System.in);
//     //     System.out.println("Jumlah Baris : ");//m
//     //     baris = scanner.nextInt();
//     //     System.out.println("Jumlah Kolom : ");//n
//     //     kolom = scanner.nextInt();
//     //     matrix = new double[baris][kolom]; //a[i][j]
//     //     array = new double[baris]; //b[i]
//     //     for(int i = 0; i < baris; i++)
//     //     {
//     //         for(int j = 0; j < kolom; j++)
//     //         {   
//     //             System.out.println("Isi elemen a ke [" + (i) + "]" +"[" + (j) + "] : ");
//     //             double elemen = scanner.nextDouble();
//     //             matrix[i][j] = elemen;//koef a[i][j]
                
//     //         }
//     //         System.out.println("Isi elemen b ke [" + (i) + "] : ");
//     //         double elemen = scanner.nextDouble(); //elemen b[i]
//     //         array[i]=elemen;
//     //         // scanner.close();
//     //     }
//     //     // return matrix;
//     // }

//     // public double[][] createAugmentedMatrix(double[][] a, double[] b)
//     // {
//     //     augmentedMatrix = new double[baris][kolom+1];
        
        
//     //     for(int i = 0; i < baris; i++)
//     //     {
//     //         for(int j = 0; j < kolom+1; j++)
//     //         {   
//     //             if (j == kolom)
//     //             {
//     //                 augmentedMatrix[i][j] = b[i];
//     //             }
//     //             else
//     //             {
//     //             augmentedMatrix[i][j] = a[i][j];
//     //             }
//     //         }
//     //     }

//     //     return augmentedMatrix;
//     // }

//     public double[][] Gauss()
//     {

        
//         readMatrix();
//         createAugmentedMatrix(matrix, array);

//         // Eliminasi Gauss
//         for (int i = 0; i < baris; i++) {
//             for (int j = i + 1; j < kolom; j++) {
//                 double ratio = augmentedMatrix[j][i] / augmentedMatrix[i][i];
//                 for (int k = 0; k < kolom + 1; k++) {
//                     augmentedMatrix[j][k] -= ratio * augmentedMatrix[i][k];
//                 }
//             }
//         }
//         printMatrix(augmentedMatrix,baris,kolom+1);

//         // Penyelesaian balik (back substitution)
//         double[] solusi = new double[baris];
//         for (int i = baris - 1; i >= 0; i--) {
//             solusi[i] = augmentedMatrix[i][baris];
//             for (int j = i + 1; j < baris; j++) {
//                 solusi[i] -= augmentedMatrix[i][j] * solusi[j];
//             }
//             solusi[i] /= augmentedMatrix[i][i];
//         }

//         System.out.println("Hasil setelah eliminasi Gauss:");
//         for (int i = 0; i < baris; i++) {
//             System.out.println("x" + (i + 1) + " = " + solusi[i]);
//         }
//         return augmentedMatrix;
//     }




//      public static void main(String args[])
//     {
//         SPL testSpl = new SPL();
//         testSpl.Gauss();

//         // readMatrix();
//         // printMatrix(matrix,3,3);

//     }

   
    
    
//     publ
    
//         // static void Gaus(String args[]) 
//     }

//     // public double[][] obe(double[][] matrix){
//     //     static hasil[][];
//     //     hasil



//     //     return hasil;
//     // }

    
// }

// // . Library tersebut berisi fungsi-fungsi seperti eliminasi Gauss,
// // eliminasi Gauss-Jordan,
// //     menentukan balikan matrix,
// //     menghitung determinan, kaidah

// // Cramer (kaidah Cramer khusus untuk SPL dengan n peubah dan n persamaan).

    



