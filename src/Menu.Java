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
        
        while (input < 1 || input > 7){
            System.out.println("\nMasukan Tidak Valid\n");
            System.out.print("Pilihan Menu : ");
            input = scanner.nextInt();
        }
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

        while (input < 1 || input > 4){
            System.out.println("\nMasukan Tidak Valid\n");
            System.out.print("Pilihan Menu : ");
            input = scanner.nextInt();
        }
        return input;
    }

    int subMenuDeterminan(){
        System.out.println("\n----------Pilih Metode Penyelesaian----------");
        System.out.println("1.Reduksi Baris");
        System.out.println("2.Ekspansi Kofaktor");

        System.out.print("Pilihan Metode : ");
        int input = scanner.nextInt();

        while (input < 1 || input > 2){
            System.out.println("\nMasukan Tidak Valid\n");
            System.out.print("Pilihan Menu : ");
            input = scanner.nextInt();
        }
        return input;
    }

    int subMenuBalikan(){
        System.out.println("\n----------Pilih Metode Penyelesaian----------");
        System.out.println("1.Metode eliminasi Gauss-Jordan");
        System.out.println("2.Adjoint");

        System.out.print("Pilihan Metode : ");
        int input = scanner.nextInt();

        while (input < 1 || input > 2){
            System.out.println("\nMasukan Tidak Valid\n");
            System.out.print("Pilihan Menu : ");
            input = scanner.nextInt();
        }
        return input;
    }

    int subMenuinput(){
        System.out.println("\n----------Pilih Metode Input----------");
        System.out.println("1.Keyboard");
        System.out.println("2.File");

        System.out.print("Pilihan Metode Input : ");
        int input = scanner.nextInt();

        while (input < 1 || input > 2){
            System.out.println("\nMasukan Tidak Valid\n");
            System.out.print("Pilihan Menu : ");
            input = scanner.nextInt();
        }
        return input;
    }

    void Continue(){
        System.out.println("\n----------Program----------");
        System.out.println("1.Kembali ke Menu");
        System.out.println("2.Keluar");

        System.out.print("Pilihan : ");
        int input = scanner.nextInt();
        
        while (input < 1 || input > 2){
            System.out.println("\nMasukan Tidak Valid\n");
            System.out.print("Pilihan Menu : ");
            input = scanner.nextInt();
        }
        
        if (input == 1){
            run();
        }
    }
    public void run(){
        int menu,subSpl, subInput,subdeterminan,subbalikan;
        menu = listmenu();
        if (menu == 1){
            subSpl = subMenuSPL();
        }
        else if (menu == 2){
            Determinan d = new Determinan();
            subdeterminan = subMenuDeterminan();

            if (subdeterminan == 1){
                subInput = subMenuinput();
                
                if (subInput == 1){
                    d.OBEDeterminant();
                    Continue();
                }
            }
            else{
                subInput = subMenuinput();
                if (subInput == 1){
                    d.kofaktorDeterminant();
                    Continue();
                }
            }
        }
        else if (menu == 3){
            Balikan invers = new Balikan();
            subbalikan = subMenuBalikan();
            if (subbalikan == 1){
                subInput = subMenuinput();
                
                if (subInput == 1){
                    invers.Gausjordaninvers();
                    Continue();
                }
            }
            else{
                subInput = subMenuinput();
                
                if (subInput == 1){
                    invers.runAdjoint();
                    Continue();
                }
            }
        }
        else if(menu == 4){
            Interpolasi interpolasi = new Interpolasi();
            subInput = subMenuinput();

            if (subInput == 1){
                interpolasi.interpolasiGaus();
                Continue();
            }
            
        }

        else if(menu == 5){
            Regresi regresi =new Regresi();
            subInput = subMenuinput();

            if(subInput == 1){
                regresi.regresiberganda();
                Continue();
            }
        }

        else if(menu == 6){
            Interpolasi interpolasi = new Interpolasi();
            subInput = subMenuinput();

            if (subInput == 1){
                Matrix m = new Matrix(4,4 );
                m.readMatrix();
                Interpolasi.bicubicSplineInterpolation(m,0.5,0.5);
                Continue();
            }
        }
        
        scanner.close();

    }
}

