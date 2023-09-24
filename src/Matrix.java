package src;

import java.util.Scanner;

public class Matrix {

    public int baris,kolom; //m itu baris,n itu kolom
    public int barisMin=0;
    public int kolomMin =0;
    public double[][] matrix;//ax
    public double[] b;//b
    public double[] array;
    public double[][] augmentedMatrix;
    Scanner scanner = new Scanner(System.in);
    /* ********** KONSTRUKTOR ********** */
    public double[][] createAugmentedMatrix(double[][] a, double[] b) //ngebuat augmented matrix dari matrix a dan persamaan b,ax=b
    {
        augmentedMatrix = new double[baris][kolom+1];
        
        
        for(int i = 0; i < baris; i++)
        {
            for(int j = 0; j < kolom+1; j++)
            {   
                if (j == kolom)
                {
                    augmentedMatrix[i][j] = b[i];
                }
                else
                {
                augmentedMatrix[i][j] = a[i][j];
                }
            }
        }

        return augmentedMatrix;
    }
    
    
    
    
    
    
    /* ********** INPUT/OUTPUT MATRIKS ********** */
    public void readMatrix() //procedure baca matrix dari input keyboard
    {
        
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
            // scanner.close();
        }
        // return matrix;
    }

   

    public void printMatrix(double[][] matrix) {
        for (int i = 0; i < baris; i++) {

            for (int j = 0; j < kolom; j++) {
                matrix[i][j]+=0; //menghilangkan -0
                System.out.format(" %.2f ", matrix[i][j]);
            }

            System.out.println();
        }
        System.out.println();
    }

    /* ********** SELEKTOR ********** */
    public int GetFirstIdxBrs(Matrix M) {
        return 0;
    }

    public int GetFirstIdxKol(Matrix M) {
        return 0;
    }

    public int GetLastIdxBrs(Matrix M) {
        return M.baris - 1;
    }

    public int GetLastIdxKol(Matrix M) {
        return M.kolom - 1;
    }







}
