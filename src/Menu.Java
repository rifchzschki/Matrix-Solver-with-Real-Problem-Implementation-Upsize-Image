package src;
import java.util.Scanner;

public class Menu{
    Scanner scanner = new Scanner(System.in);
    public Menu() {

    }
    int listmenu(){
        System.out.println("----------Menu----------");
        System.out.println("1.Sistem Persamaan Linier");
        System.out.println("2.Determinan");
        System.out.println("3.Matriks balikan");
        System.out.println("4.Interpolasi Polinom");
        System.out.println("5.Interpolasi Bicubic Spline");
        System.out.println("6.Regresi linier berganda");
        System.out.println("7.Keluar\n");

        System.out.print("Pilihan Menu : ");
        int input = scanner.nextInt();
        
        return input;
    }

    int subMenuSPL(){
        System.out.println("\n----------Pilih Metode Penyelesaian----------");
        System.out.println("1.Metode eliminasi Gauss");
        System.out.println("2.Metode eliminasi Gauss-Jordan");
        System.out.println("3.Metode matriks balikan");
        System.out.println("4.Kaidah Cramer");

        System.out.print("Pilihan Metode : ");
        int input = scanner.nextInt();

        return input;
    }

    int subMenuDeterminan(){
        System.out.println("\n----------Pilih Metode Penyelesaian----------");
        System.out.println("1.Reduksi Baris");
        System.out.println("2.Ekspansi Kofaktor");

        System.out.print("Pilihan Metode : ");
        int input = scanner.nextInt();

        return input;
    }

    int subMenuBalikan(){
        System.out.println("\n----------Pilih Metode Penyelesaian----------");
        System.out.println("1.Metode eliminasi Gauss-Jordan");
        System.out.println("2.Adjoint");

        System.out.print("Pilihan Metode : ");
        int input = scanner.nextInt();

        return input;
    }

    int subMenuinput(){
        System.out.println("\n----------Pilih Metode Input----------");
        System.out.println("1.Keyboard");
        System.out.println("2.File");

        System.out.print("Pilihan Metode Input : ");
        int input = scanner.nextInt();

        return input;
    }

    public void run(){
        int menu,subSpl, subInput;
        menu = listmenu();
        while (menu < 1 || menu > 7){
            System.out.println("\nMasukan Tidak Valid\n");
            menu = listmenu();
        }

        if (menu == 1){
            subSpl = subMenuSPL();
            while (subSpl < 1 || subSpl > 4){
                System.out.println("\nMasukan Tidak Valid\n");
                subSpl = subMenuSPL();
            }
        
        subInput = subMenuinput();
        while (subInput < 1 || subInput > 2){
            System.out.println("\nMasukan Tidak Valid\n");
            subInput = subMenuinput();
        }
        if (subInput == 1){
            System.out.print("\nMasukan Baris : ");
            int row = scanner.nextInt();
            System.out.print("\nMasukan Kolom : ");
            int col = scanner.nextInt();
            Matrix m = new Matrix(row, col);
            m.readMatrix();
            m.printMatrix(false);

        }
        }

        scanner.close();


         
    
    }
}

