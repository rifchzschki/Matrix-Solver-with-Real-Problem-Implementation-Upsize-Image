package src;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int menu = scanner.nextInt();

        if (menu == 4) {
            double val;
            int N;
            System.out.print("N: ");
            
            // Capture the output from this point
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream printStream = new PrintStream(outputStream);
            PrintStream originalOut = System.out;
            System.setOut(printStream);

            N = scanner.nextInt();
            Matrix point = new Matrix(N, 2); // Input x and y values for each point

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < 2; j++) {
                    if (j == 0) {
                        System.out.print("X" + i + ": ");
                    } else {
                        System.out.print("Y" + i + ": ");
                    }
                    val = scanner.nextDouble();
                    point.setEl(i, j, val);
                }
            }

            // Perform the procedure that requires user input
            // This will capture the output from this point onwards
            // ...

            // Restore the original standard output
            System.out.flush();
            System.setOut(originalOut);

            // Get the captured output as a string
            String capturedOutput = outputStream.toString();

            // Continue with the rest of your code
            // ...

            System.out.println(capturedOutput);
            // saveFile(capturedOutput);
        }
    }
}

