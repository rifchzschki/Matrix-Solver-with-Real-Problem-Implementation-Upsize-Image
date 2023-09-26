package src;
import java.util.*;
// import src.Matrix;


public class SPL extends Matrix {
    
    void menuSPL (int pilihan){
        switch(pilihan){
            case 1:
                System.out.println("Solusi SPL menggunakan eliminasi Gauss:");
                // eksekusi metode
                break;
            case 2:
                System.out.println("Solusi SPL menggunakan eliminasi Gauss-Jordan:");
                // eksekusi metode 
                break;
            case 3:
                System.out.println("Solusi SPL menggunakan eliminasi Matriks Balikan:");
                // eksekusi metode 
                break;
            case 4:
                System.out.println("Solusi SPL menggunakan eliminasi Kaidah Crammer:");
                break;
                // eksekusi metode 
        }
    }


    // // Metode gauss jordan
    // void gaussJordan(){
    //     Matrix m = this.M;
    // }
}
//     