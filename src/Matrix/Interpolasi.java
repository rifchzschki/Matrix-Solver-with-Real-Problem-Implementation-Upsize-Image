package src;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;



public class Interpolasi {
    static Scanner scan = new Scanner(System.in);
    
    public static double pow(double x,int y)
    {
        
        if (x==0&&y<0)
        {
            return 0;

        }
        else
        {
            return (Math.pow(x,y));
        }
    
    }

    public static String interpolasiGaus(int N, Matrix point, double x){
        
        Matrix equation =new Matrix(N,N+1);//masukan dalam matrix untuk eliminasi gauss jordan
        int power;
        for(int i =0;i<N;i++)
        {   
            power =1;
            for(int j =0;j<N+1;j++)
            {
                if (j==0)
                {
                    equation.setEl(i, j, 1);
                }
                else if (j==N)
                {
                    equation.setEl(i, j, point.GetElmt(i, 1));
                }
                else
                {
                    equation.setEl(i, j, pow(point.GetElmt(i,0),power));
                    power++;
                }
            }
        }

        //langkah gaus jordan
        equation.eselonBarisReduksi();

        //simpan hasil solusi

        double[] solution= new double[N];
         // Penyelesaian balik (back substitution)
         for (int i = N - 1; i >= 0; i--) {
             solution[i] = equation.GetElmt(i, N);
             for (int j = i + 1; j < N; j++) {
                 solution[i] -= equation.GetElmt(i, j) * solution[j];
             }
             solution[i] /= equation.GetElmt(i, i);
         }

         
         

         Capturer capturer = new Capturer();
         capturer.mulai();
         //PRINT PERSAMAAN
         String hasil = String.format("P(X)= (%.4f)",solution[0]);
         System.out.print(hasil);
         
         for (int i =1;i<N;i++)
         {
            hasil = String.format("+(%.4f)X^%d",solution[i],i);
            System.out.print(hasil);
         }
         System.out.println();
         
         
         double Y = 0;
         power =1;
         if (point.GetLastIdxBrs()==N)
         {
            x = point.GetElmt(N,0);
         }
         
         for (int i =1;i<N;i++)
         {
            Y+=solution[i]*pow(x,power);
            power++;
         }
         Y+=solution[0];
         System.out.print("f("+x+")"+"= "+ Y);
        
         String consoleOutput = capturer.stop();
         return consoleOutput;
    }

    public static String bicubicSplineInterpolation(Matrix m){
        Matrix bicubic = new Matrix(16,16);
        double[] solution = new double[16];
        //masukan nilai f(x,y)
        double x[] = { 0, 1, 0, 1 };
        double y[] = { 0, 0, 1, 1 };
        int pos=0;
        int tempi=0,tempj=0;
        for (int i=0;i<16;i++){ //membuat matrix X
            for(int j =0;j<16;j++){
                if (i<=3){
                    double powx = pow(x[pos],tempi); 
                    double powy = pow(y[pos], tempj);
                    bicubic.setEl(i,j,powx*powy);
                    tempi++;
                    if(tempi>3){
                        tempi=0;
                        tempj++;
                    }
                    if (tempj>3){
                        tempj=0;
                        tempi=0;
                        break;
                    }
                }
                else if (i>=4&&i<=7){//turunan x
                    double powx = pow(x[pos],tempi-1); 
                    double powy = pow(y[pos], tempj)*tempi;
                    bicubic.setEl(i,j,powx*powy);
                    tempi++;
                    if(tempi>3){
                        tempi=0;
                        tempj++;
                    }
                    if (tempj>3){
                        tempj=0;
                        tempi=0;
                        break;
                    }
                }
                else if(i>=8&&i<=11){ //turunnan y
                    double powx = pow(x[pos],tempi); 
                    double powy = pow(y[pos], tempj-1)*tempj;
                    bicubic.setEl(i,j,powx*powy);
                    tempi++;
                    if(tempi>3){
                        tempi=0;
                        tempj++;
                    }
                    if (tempj>3){
                        tempi=0;
                        tempj=0;
                        break;
                    }
                }
                else if(i>11){ //turunan xy
                    double powx = pow(x[pos],tempi-1);
                    double powy = pow(y[pos], tempj-1)*tempj*tempi;
                    bicubic.setEl(i,j,powx*powy);
                    tempi++;
                    if(tempi>3){
                        tempi=0;
                        tempj++;
                    }
                    if (tempj>3){
                        tempi=0;
                        tempj=0;
                        break;
                    }
                }
            }
            pos++;
            if (pos>3){
                pos=0;
            }
        }
        // bicubic.printMatrix();
        bicubic.inverseMatrix();//melakukaninverse x
    

        Matrix fx = new Matrix(16,1);//MELETAKAN HASIL BACA FILE MATRIX KE MATRIX BERKOLOM 1
        int k=0;
        for (int i =0;i<4;i++)
        {
            for (int j =0;j<4;j++)
            {
                fx.setEl(k, 0, m.GetElmt(i, j));
                k++;
            }
        }
        
        //MELETAKAN HASIL KE DALAM ARRAY SOLUTION
        // fx.printMatrix();//hasil f(x,y) pada matrix berkolom 1
        // bicubic.printMatrix();//hasil inverse
    
        for (int i=0;i<16;i++){
            double tempresult=0;
            for(int j =0;j<16;j++){
                tempresult+=bicubic.GetElmt(i,j)*fx.GetElmt(j, 0);
            }
            solution[i]=tempresult;
        }

        double hasil=0;
        tempi=0;
        tempj=0;
        double a,b;
        if (m.GetLastIdxBrs()==4)
        {
            a = m.GetElmt(4,0 );
            b = m.GetElmt(4, 1);
        }
        else 
        {
            System.out.print("Masukkan nilai a: ");

            a = scan.nextDouble();
            System.out.print("Masukkan nilai b: ");
            b = scan.nextDouble();
        }
        for (int i=0;i<16;i++){ //mencari nilai y
            hasil+=pow(a,tempi)*pow(b, tempj)*solution[i];
            tempi++;
            if(tempi>3){
                tempi=0;
                tempj++;
            }
            if (tempj>3){
                tempj=0;
                tempi=0;
                break;
            }
        }
        Capturer capturer = new Capturer();
        capturer.mulai();
        System.out.print("f("+a+","+b+") : ");
        System.out.print(hasil);
        String consoleoutput = capturer.stop();
        return consoleoutput;
    }
            
public int bicubicSplineInterpolationBonus(BufferedImage foto, double pixelx, double pixely) throws IOException {
        Matrix bicubic = new Matrix(16, 16);
        double x[] = { 0, 1, 0, 1 };
        double y[] = { 0, 0, 1, 1 };
        int pos = 0;
        int tempi = 0, tempj = 0;
        for (int i = 0; i < 16; i++) // membuat matrix X
        {
            for (int j = 0; j < 16; j++) {

                if (i <= 3) {
                    bicubic.setEl(i,j,(pow(x[pos], tempi) * pow(y[pos], tempj)));
                    tempi++;
                    if (tempi > 3) {
                        tempi = 0;
                        tempj++;
                    }
                    if (tempj > 3) {
                        tempj = 0;
                        tempi = 0;
                        break;
                    }
                } else if (i >= 4 && i <= 7)// turunan x
                {

                    bicubic.setEl(i,j,(pow(x[pos], tempi - 1) * pow(y[pos], tempj) * tempi));
                    tempi++;
                    if (tempi > 3) {
                        tempi = 0;
                        tempj++;
                    }
                    if (tempj > 3) {
                        tempj = 0;
                        tempi = 0;
                        break;
                    }

                } else if (i >= 8 && i <= 11) // turunnan y
                {

                    bicubic.setEl(i,j,(pow(x[pos], tempi) * pow(y[pos], tempj - 1) * tempj)) ;
                    tempi++;
                    if (tempi > 3) {
                        tempi = 0;
                        tempj++;
                    }
                    if (tempj > 3) {
                        tempi = 0;
                        tempj = 0;
                        break;
                    }

                } else if (i > 11) // turunan xy
                {

                    bicubic.setEl(i,j,(pow(x[pos], tempi - 1) * pow(y[pos], tempj - 1) * tempj * tempi));
                    tempi++;
                    if (tempi > 3) {
                        tempi = 0;
                        tempj++;
                    }
                    if (tempj > 3) {
                        tempi = 0;
                        tempj = 0;
                        break;
                    }

                }
            }
            pos++;
            if (pos > 3) {
                pos = 0;
            }
        }
        bicubic.inverseMatrix();// melakukaninverse matrix x

        int xi[] = { -1, 0, 1, 2 };
        int yi[] = { -1, 0, 1, 2 };

        // buat Matrix D
        Matrix bicubicbonus = new Matrix(16, 16);
        pos = 0;
        tempi = 0;
        tempj = 0;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (i <= 3) {

                    if (x[pos] == xi[tempj] && y[pos] == yi[tempi]) {
                        bicubicbonus.setEl(i,j,1);
                        tempi = 0;
                        tempj = 0;
                        break;
                    }
                    tempj++;
                    if (tempj > 3) {
                        tempj = 0;
                        tempi++;
                    }
                    if (tempi > 3) {
                        tempj = 0;
                        tempi = 0;
                    }

                } else if (i >= 4 && i <= 7)// turunan x
                {

                    if ((x[pos] + 1 == (xi[tempj])) && (y[pos] == yi[tempi])) {
                        bicubicbonus.setEl(i, j, 1 / (double) 2);

                    } else if ((x[pos] - 1) == (xi[tempj]) && (y[pos] == yi[tempi])) {
                        bicubicbonus.setEl(i, j, -0.5);

                    }
                    tempj++;
                    if (tempj > 3) {
                        tempj = 0;
                        tempi++;
                    }
                    if (tempi > 3) {
                        tempj = 0;
                        tempi = 0;
                    }

                } else if (i >= 8 && i <= 11) // turunnan y
                {

                    if ((x[pos] == (xi[tempj])) && (y[pos] + 1 == (yi[tempi]))) {
                        bicubicbonus.setEl(i, j, (double) 1 / 2);

                    } else if ((x[pos] == (xi[tempj])) && (y[pos] - 1 == (yi[tempi]))) {
                        bicubicbonus.setEl(i, j, -((double) 1 / 2));

                    }
                    tempj++;
                    if (tempj > 3) {
                        tempj = 0;
                        tempi++;
                    }
                    if (tempi > 3) {
                        tempj = 0;
                        tempi = 0;
                    }

                } else if (i > 11) // turunan xy
                {

                    if (x[pos] + 1 == (xi[tempj]) && y[pos] + 1 == (yi[tempi])) {
                        bicubicbonus.setEl(i, j, (double) 1 / 4);

                    } else if (x[pos] - 1 == (xi[tempj]) && y[pos] == (yi[tempi])) {
                        bicubicbonus.setEl(i, j, -((double) 1 / 4));

                    } else if (x[pos] == (xi[tempj]) && y[pos] - 1 == (yi[tempi])) {
                        bicubicbonus.setEl(i, j, -((double) 1 / 4));

                    } else if (x[pos] == (xi[tempj]) && y[pos] == (yi[tempi])) {
                        bicubicbonus.setEl(i, j, -((double) 1 / 4));

                    }
                    tempj++;
                    if (tempj > 3) {
                        tempj = 0;
                        tempi++;
                    }
                    if (tempi > 3) {
                        tempj = 0;
                        tempi = 0;
                    }

                }
            }
            pos++;
            if (pos > 3) {
                pos = 0;
            }
        }
        

        // kali inverse matrix x dengan matrix d
        Matrix result = new Matrix(16, 16);
        result = result.multiplyMatrix(bicubic, bicubicbonus);// hasil kali matrix inverse X dengan matrix D
        // result.printMatrix();
        // mencari nilai citra tiap koordinat gambar
        Matrix nilaiPixel = new Matrix(16, 4);//nilai rgb
        //[red][green][blue][nilai pixel]
        // kalkulasi nilai per RGB
        tempi = 0;
        tempj = 0;
        int xwidth[] = {(int) pixelx-2, (int)pixelx-1, (int) pixelx+1,(int) pixelx+2};//mencari titik referensi, sudah benarkah?
        int ywidth[] = {(int) pixely-2, (int) pixely-1, (int) pixely+1, (int) pixely+2 };
        for (int i = 0; i < 16; i++) {
            xwidth[tempj]=Math.min(foto.getWidth()-1, Math.max(0, xwidth[tempj]));
            ywidth[tempi]=Math.min(foto.getHeight()-1, Math.max(0, ywidth[tempi]));
            nilaiPixel.setEl(i,3, foto.getRGB(xwidth[tempj], ywidth[tempi]));
            nilaiPixel.setEl(i, 0, ((int)nilaiPixel.GetElmt(i,3) >> 16) & 0xFF);//nilai Citra I(x,y) tiap RGB
            nilaiPixel.setEl(i, 1,((int)nilaiPixel.GetElmt(i,3) >>8 ) & 0xFF);
            nilaiPixel.setEl(i, 2, ((int)nilaiPixel.GetElmt(i,3)) & 0xFF)  ;
            tempj++;
            if (tempj > 3) {
                tempi++;
                tempj = 0;
            }
            if (tempi > 3) {
                tempi = 0;
                tempj = 0;
                break;
            }
    

        }
        Matrix solution = new Matrix(16,3);//[red][green][blue]
        // mencari nilai a
        tempi = 0;
        tempj = 0;
        int red=0;
        int blue=0;
        int green=0;
        for (int i = 0; i < 16; i++) {
            red = 0;
            blue=0;
            green=0;
            for (int j = 0; j < 16; j++) {
                red += result.GetElmt(i, j) * nilaiPixel.GetElmt(j,0);
                green += result.GetElmt(i, j) * nilaiPixel.GetElmt(j, 1);
                blue += result.GetElmt(i, j) * nilaiPixel.GetElmt(j, 2);
            }
            //nilai koefisien untuk tiap warna
            solution.setEl(i,0,red);
            solution.setEl(i,1,green);
            solution.setEl(i,2,blue);
        }
        int newValueRed = 0;
        int newValueGreen = 0;
        int newValueBlue = 0;
        for (int i = 0; i < 16; i++) // mencari nilai hasil nilai tiap RGB dengan f(x,y)
        {
            newValueRed += pow(pixelx/foto.getWidth(), tempi) * pow(pixely/foto.getHeight(), tempj) * solution.GetElmt(i,0);
            newValueGreen += pow(pixelx/foto.getWidth(), tempi) * pow(pixely/foto.getHeight(), tempj) * solution.GetElmt(i,1);
            newValueBlue += pow(pixelx/foto.getWidth(), tempi) * pow(pixely/foto.getHeight(), tempj) * solution.GetElmt(i,2);
            tempj++;
            if (tempj > 3) {
                tempj = 0;
                tempi++;
            }
            if (tempi > 3) {
                tempj = 0;
                tempi = 0;
                break;
            }
        }
        red = (newValueRed);
        green = (newValueGreen);
        blue = newValueBlue;
        //nilai max RGB berada diantara 0 sampai 255
        red = Math.min(255, Math.max(0, red));
        green = Math.min(255, Math.max(0, green));
        blue = Math.min(255, Math.max(0, blue));
        // Gabungkan nilai-nilai saluran ke nilai warna RGB
        int rgbColor = (red << 16) | (green << 8) | blue;  // Kembalikan nilai RGB yang sudah dinormalisasi
        return rgbColor;

    }

    public void bonus()//fungsi utama bonus
    {
        try { // Baca gambar sumber
            System.out.print("File path:  ");
            String filepath = scan.nextLine();
            BufferedImage foto = ImageIO.read(new File(filepath));

            // Tentukan faktor perbesaran (misalnya, dua kali lipat)
            System.out.print("Besar skala perbesaran yang diinginkan: ");
            double scaleFactor = scan.nextDouble();//baru bisa buat yang integer...
            System.out.println("Loading... ");
            
            double newWidth =  foto.getWidth() * scaleFactor;
            double newHeight = foto.getHeight() * scaleFactor;

            // Buat gambar hasil dengan resolusi baru
            BufferedImage resultImage = new BufferedImage((int)newWidth, (int)newHeight, BufferedImage.TYPE_INT_RGB);

            // Lakukan bicubic spline interpolation pada gambar
            for (int y = 0; y < newHeight; y++) {
                for (int x = 0; x < newWidth; x++) {
                    // Lakukan interpolasi bicubic pada setiap piksel
                    int interpolatedColor = bicubicSplineInterpolationBonus(foto, (x+0.5)/(double) scaleFactor -0.5,(y+0.5)/(double) scaleFactor-0.5);

                    // Tetapkan warna hasil ke piksel
                    resultImage.setRGB(x, y, interpolatedColor);
                }
            }

            // Simpan gambar hasil
            saveImage(resultImage,"test\\output\\outputfoto.jpg");
            System.out.println("Hasil gambar bernama outputfoto.jpg ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    

   //fungsi menyimpan gambar
    public void saveImage(BufferedImage image, String filePath) throws IOException {
        File outputImageFile = new File(filePath);
        ImageIO.write(image, "jpg", outputImageFile);
    }  


}



