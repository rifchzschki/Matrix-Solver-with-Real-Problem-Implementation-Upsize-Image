package src;
import java.io.FileNotFoundException;
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

    int subMenuInterpolasi(){
        System.out.println("\n----------Pilih Metode Penyelesaian----------");
        System.out.println("1.Interpolasi Bicubic");
        System.out.println("2.Interpolasi Bicubic Bonus");

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

    void savefile(String consol){
        System.out.print("Apakah Anda ingin simpan dalam file (Y/N)? ");
        String valid = scanner.nextLine();
        while (!valid.equals("y") && !valid.equals("Y") && !valid.equals("n") && !valid.equals("N")){
            System.out.print("Apakah Anda ingin simpan dalam file (Y/N)? ");
            valid = scanner.nextLine();    
        }
        if (valid.equals("y") || valid.equals("Y")){
            System.out.print("Masukkan nama file (.txt) : ");
            String name = scanner.nextLine();
            String filename =  ("test/output/" + name);
            Matrix.writeProgramOutputToFile(filename, consol);
            Continue();
        }
        else{
            Continue();
        }
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
        int menu,subSpl, subInput,subdeterminan,subbalikan,subInterpolasi;
        menu = listmenu();
        if (menu == 1){
            subSpl = subMenuSPL();
            if (subSpl == 1){
                subInput = subMenuinput();
                scanner.nextLine();
                if (subInput ==1){
                    System.out.print("M:");
                    int m = scanner.nextInt();
                    System.out.print("N:");
                    int n = scanner.nextInt();
                    Matrix mat = new Matrix(m, n);
                    mat.readMatrix();
                    Capturer capturer = new Capturer();
                    capturer.mulai();
                    System.out.println("\n Input Matrix : \n");
                    mat.printMatrix();

                    String consoleOutput = capturer.stop();
                    String output = SPL.eksekusiGauss(mat, false);
                    String result = consoleOutput + output;
                    System.out.println(result);
                    savefile(result);
                }
                else{
                    try {
                        System.out.print("Masukkan nama file(.txt) : ");
                        String a = scanner.nextLine();
                        Matrix m= new Matrix(1,1);
                        m = m.BacaFileMatriks(a);
                        Capturer capturer = new Capturer();
                        capturer.mulai();

                        System.out.println("\n Input Matrix : \n");
                        m.printMatrix();
                        String ConsoleOuput = capturer.stop();
                        String output = SPL.eksekusiGauss(m, false);
                        String result = ConsoleOuput + output;
                        System.out.println(result);
                        savefile(result);

                    } catch (FileNotFoundException e) {
                        System.err.println("File not found: " + e.getMessage());
                        Continue();
                    }
                }
            }
            else if (subSpl == 2){
                subInput = subMenuinput();
                scanner.nextLine();
                if (subInput ==1){
                    System.out.print("M:");
                    int m = scanner.nextInt();
                    System.out.print("N:");
                    int n = scanner.nextInt();
                    Matrix mat = new Matrix(m, n);
                    mat.readMatrix();
                    Capturer capturer = new Capturer();
                    capturer.mulai();
                    System.out.println("\n Input Matrix : \n");
                    mat.printMatrix();

                    String consoleOutput = capturer.stop();
                    String output = SPL.eksekusiGauss(mat, true);
                    String result = consoleOutput + output;
                    System.out.println(result);
                    savefile(result);
                }
                else{
                    try {
                        System.out.print("Masukkan nama file(.txt) : ");
                        String a = scanner.nextLine();
                        Matrix m= new Matrix(1,1);
                        m = m.BacaFileMatriks(a);
                        Capturer capturer = new Capturer();
                        capturer.mulai();

                        System.out.println("\n Input Matrix : \n");
                        m.printMatrix();
                        String ConsoleOuput = capturer.stop();
                        String output = SPL.eksekusiGauss(m, true);
                        String result = ConsoleOuput + output;
                        System.out.println(result);
                        savefile(result);

                    } catch (FileNotFoundException e) {
                        System.err.println("File not found: " + e.getMessage());
                        Continue();
                    }
                }
            }
            else if (subSpl == 3){
                subInput = subMenuinput();
                scanner.nextLine();
                if (subInput ==1){
                    System.out.print("M:");
                    int m = scanner.nextInt();
                    System.out.print("N:");
                    int n = scanner.nextInt();
                    Matrix mat = new Matrix(m, n);
                    mat.readMatrix();
                    Capturer capturer = new Capturer();
                    capturer.mulai();
                    System.out.println("\n Input Matrix : \n");
                    mat.printMatrix();
                    String consoleOutput = capturer.stop();
                    String output = Matrix.inverseGausJordan(mat);
                    String result = consoleOutput + output;
                    System.out.println(result);
                    savefile(result);
                }
                else{
                    try {
                        System.out.print("Masukkan nama file(.txt) : ");
                        String a = scanner.nextLine();
                        Matrix m= new Matrix(1,1);
                        m = m.BacaFileMatriks(a);
                        Capturer capturer = new Capturer();
                        capturer.mulai();

                        System.out.println("\n Input Matrix : \n");
                        m.printMatrix();
                        String ConsoleOuput = capturer.stop();
                        String output = Matrix.inverseGausJordan(m);
                        String result = ConsoleOuput + output;
                        System.out.println(result);
                        savefile(result);

                    } catch (FileNotFoundException e) {
                        System.err.println("File not found: " + e.getMessage());
                        Continue();
                    }
                }
            }
            else{
                subInput = subMenuinput();
                scanner.nextLine();
                if (subInput ==1){
                    System.out.print("M:");
                    int m = scanner.nextInt();
                    System.out.print("N:");
                    int n = scanner.nextInt();
                    Matrix mat = new Matrix(m, n);
                    mat.readMatrix();
                    Capturer capturer = new Capturer();
                    capturer.mulai();
                    System.out.println("\n Input Matrix : \n");
                    mat.printMatrix();
                    String consoleOutput = capturer.stop();
                    String output = Matrix.KaidahCramer(mat);
                    String result = consoleOutput + output;
                    System.out.println(result);
                    savefile(result);
                }
                else{
                    try {
                        System.out.print("Masukkan nama file(.txt) : ");
                        String a = scanner.nextLine();
                        Matrix m= new Matrix(1,1);
                        m = m.BacaFileMatriks(a);
                        Capturer capturer = new Capturer();
                        capturer.mulai();

                        System.out.println("\n Input Matrix : \n");
                        m.printMatrix();
                        String ConsoleOuput = capturer.stop();
                        String output = Matrix.KaidahCramer(m);
                        String result = ConsoleOuput + output;
                        System.out.println(result);
                        savefile(result);

                    } catch (FileNotFoundException e) {
                        System.err.println("File not found: " + e.getMessage());
                        Continue();
                    }
                }
            }
        }
        else if (menu == 2){
            Determinan det = new Determinan();
            subdeterminan = subMenuDeterminan();

            if (subdeterminan == 1){
                subInput = subMenuinput();
                scanner.nextLine();
                if (subInput == 1){//keyboard
                    System.out.print("\nMasukan N : ");
                    int n = scanner.nextInt();
                    Matrix m = new Matrix(n,n);
                    m.readMatrix();

                    Capturer capturer = new Capturer();
                    capturer.mulai();

                    System.out.println("\n Input Matrix : \n");
                    m.printMatrix();
                    det.OBEDeterminant(m);

                    String consoleOutput = capturer.stop();
                    System.out.println(consoleOutput);
                    
                    savefile(consoleOutput);
                }
                else{
                   try {
                        System.out.print("Masukkan nama file(.txt) : ");
                        String a = scanner.nextLine();
                        Matrix m= new Matrix(1,1);
                        m = m.BacaFileMatriks(a);
                        Capturer capturer = new Capturer();
                        capturer.mulai();

                        System.out.println("\n Input Matrix : \n");
                        m.printMatrix();
                        det.OBEDeterminant(m);

                        String consoleOuput = capturer.stop();
                        System.out.println(consoleOuput);
                        
                        savefile(consoleOuput);

                    } catch (FileNotFoundException e) {
                        System.err.println("File not found: " + e.getMessage());
                        Continue();
                    }
                }
            }
            else{
                subInput = subMenuinput();
                scanner.nextLine();
                if (subInput == 1){
                    System.out.print("\nMasukan N : ");
                    int n = scanner.nextInt();
                    Matrix m = new Matrix(n,n);
                    m.readMatrix();

                    Capturer capturer = new Capturer();
                    capturer.mulai();

                    System.out.println("\n Input Matrix : \n");
                    m.printMatrix();
                    det.kofaktorDeterminant(m);
                    
                    String consoleOutput = capturer.stop();
                    System.out.println(consoleOutput);
                    savefile(consoleOutput);
                }
                else{
                    try {
                        System.out.print("Masukkan nama file(.txt) : ");
                        String a = scanner.nextLine();
                        Matrix m = new Matrix(1,1);
                        m = m.BacaFileMatriks(a);
                        
                        Capturer capturer = new Capturer();
                        capturer.mulai();

                        System.out.println("\n Input Matrix : \n");
                        m.printMatrix();
                        det.kofaktorDeterminant(m);

                        String consoleOutput = capturer.stop();
                        System.out.println(consoleOutput);
                        savefile(consoleOutput);
                    } catch (FileNotFoundException e) {
                        System.err.println("File not found: " + e.getMessage());
                        Continue();
                    }
                }
            }
                
        }
        else if (menu == 3){
            Balikan invers = new Balikan();
            subbalikan = subMenuBalikan();
            if (subbalikan == 1){
                subInput = subMenuinput();
                scanner.nextLine();
                if (subInput == 1){
                    System.out.print("\nMasukan N : ");
                    int n = scanner.nextInt();
                    Matrix m = new Matrix(n,n);
                    m.readMatrix();

                    Capturer capturer = new Capturer();
                    capturer.mulai();

                    System.out.println("\n Input Matrix : \n");
                    m.printMatrix();
                    invers.Gausjordaninvers(m);
                    
                    String consoleOutput = capturer.stop();
                    System.out.println(consoleOutput);
                    savefile(consoleOutput);
                }
                else{
                    try {
                        System.out.print("Masukkan nama file(.txt) : ");
                        String a = scanner.nextLine();
                        Matrix m = new Matrix(1,1);
                        m = m.BacaFileMatriks(a);
                        
                        Capturer capturer = new Capturer();
                        capturer.mulai();

                        System.out.println("\n Input Matrix : \n");
                        m.printMatrix();
                        invers.Gausjordaninvers(m);

                        String consoleOutput = capturer.stop();
                        System.out.println(consoleOutput);
                        savefile(consoleOutput);
                    } catch (FileNotFoundException e) {
                        System.err.println("File not found: " + e.getMessage());
                        Continue();
                    }
                }
            }
            else{
                subInput = subMenuinput();
                scanner.nextLine();
                if (subInput == 1){
                    System.out.print("\nMasukan N : ");
                    int n = scanner.nextInt();
                    Matrix m = new Matrix(n,n);
                    m.readMatrix();

                    Capturer capturer = new Capturer();
                    capturer.mulai();

                    System.out.println("\n Input Matrix : \n");
                    m.printMatrix();
                    m = invers.Adjoint(m);
                    m.printMatrix();
                    String consoleOutput = capturer.stop();
                    System.out.println(consoleOutput);
                    savefile(consoleOutput);
                }
                else{
                    try {
                        System.out.print("Masukkan nama file(.txt) : ");
                        String a = scanner.nextLine();
                        Matrix m = new Matrix(1,1);
                        m = m.BacaFileMatriks(a);
                        
                        Capturer capturer = new Capturer();
                        capturer.mulai();

                        System.out.println("\n Input Matrix : \n");
                        m.printMatrix();
                        m = invers.Adjoint(m);
                        m.printMatrix();
                        String consoleOutput = capturer.stop();
                        System.out.println(consoleOutput);
                        savefile(consoleOutput);
                    } catch (FileNotFoundException e) {
                        System.err.println("File not found: " + e.getMessage());
                        Continue();
                    }
                }
            }
        }
        else if(menu == 4){
            subInput = subMenuinput();
            scanner.nextLine();
            if(subInput == 1){
                double val;
                int N;
                System.out.print("N: ");
                N = scanner.nextInt();
                Matrix point = new Matrix(N,2);//masukan nilai x,y pada tiap titik
            
                for (int i =0;i<N;i++){
                    for (int j =0;j<2;j++){
                        if (j==0){
                            System.out.print("X"+i+": ");
                        } else{
                            System.out.print("Y"+i+": ");
                        }
                        val = scanner.nextDouble();
                        point.setEl(i, j, val);
                    }
                }
                System.out.print("Masukkan nilai X yang ingin ditaksir"+": ");
                double X = scanner.nextDouble();
                String output = Interpolasi.interpolasiGaus(N, point, X);
                System.out.println(output);
                savefile(output);
            }
            else{
                 try {
                        int N;
                        System.out.print("N: ");
                        N = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Masukkan nama file(.txt) : ");
                        String a = scanner.nextLine();
                        Matrix m = new Matrix(1,1);
                        m = m.BacaFileMatriks(a);
                        String output = Interpolasi.interpolasiGaus(N,m, 0);
                        System.out.println(output);
                        savefile(output);
                    } catch (FileNotFoundException e) {
                        System.err.println("File not found: " + e.getMessage());
                        Continue();
                    }
            }
        }   
        else if(menu == 5){
            Interpolasi interpolasi = new Interpolasi();
            subInterpolasi = subMenuInterpolasi();
            if (subInterpolasi == 1){
                subInput = subMenuinput();
                scanner.nextLine();
                if(subInput == 1){
                    Matrix m = new Matrix(4,4);
                    m.readMatrix();
                    Capturer capturer = new Capturer();
                    capturer.mulai();

                    System.out.println("\n Input Matrix : \n");
                    m.printMatrix();
                    String consoleOutput = capturer.stop();
                    String output = Interpolasi.bicubicSplineInterpolation(m);
                    String result = consoleOutput + output;
                    System.out.println(result);
                    savefile(result);
                }
                else{
                    try {
                        System.out.print("Masukkan nama file(.txt) : ");
                        String a = scanner.nextLine();
                        Matrix m = new Matrix(1,1);
                        m = m.BacaFileMatriks(a);
                        Capturer capturer = new Capturer();
                        capturer.mulai();

                        System.out.println("\n Input Matrix : \n");
                        m.printMatrix();
                        String consoleOutput = capturer.stop();
                        String output = Interpolasi.bicubicSplineInterpolation(m);
                        String result = consoleOutput + output;
                        System.out.println(result);
                        savefile(result);
                    } catch (FileNotFoundException e) {
                        System.err.println("File not found: " + e.getMessage());
                        Continue();
                    }
                }
            
            }
            else{
                interpolasi.bonus();
            }
        }
        else if(menu == 6){
            subInput = subMenuinput();

            if(subInput == 1){
                System.out.print("Masukkan jumlah peubah x(n) : ");
                int n = scanner.nextInt();
                n += 1;
                System.out.print("Masukkan jumlah sampel(m) : ");
                int m = scanner.nextInt();
                Matrix x = new Matrix(m, n);
                double val;
                for (int i =0; i<m;i++){
                    for (int j=0; j <n;j++){
                        if (j == n-1){
                            System.out.print("Y" +(i+1)+" : " );
                        }
                        else{
                            System.out.print("X" +(j+1)+" : ");
                        }
                        val = scanner.nextDouble();
                        x.setEl(i, j, val);
                    }
                }
                System.out.println("Masukkan nilai X yang ingin ditaksir: ");
                double [] X = new double[n];
                for(int i=0;i<n-1;i++){
                    System.out.print("X" + (i+1) + ": ");
                    X[i] = scanner.nextDouble();

                }

                Matrix A = new Matrix(1,1);
                A = Regresi.regresiberganda(m, n, x);
                A = Regresi.hitungbeta(A);
                String output = Regresi.printEquation(A,X);
                System.out.println(output);
                savefile(output);
            }
            else{
                // try {
                //     System.out.print("Masukkan nama file(.txt) : ");
                //     String a = scanner.nextLine();
                //     Matrix m = new Matrix(1,1);
                //     m = m.BacaFileMatriks(a);
                //     String output = Interpolasi.interpolasiGaus(N,m, 0);
                //     System.out.println(output);
                //     savefile(output);
                // } catch (FileNotFoundException e) {
                //     System.err.println("File not found: " + e.getMessage());
                //     Continue();
                // }
            }
        }
        
        scanner.close();

    }
}



