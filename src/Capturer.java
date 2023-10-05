package src;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class Capturer {
    private final PrintStream initiate;
    private final OutputStream output;

    public Capturer() {
        initiate = System.out;
        output = new ByteArrayOutputStream();
        PrintStream newOut = new PrintStream(output);
        System.setOut(newOut);
    }

    public void mulai() {
  
        System.setOut(new PrintStream(output));
    }

    public String stop() {
        
        System.setOut(initiate);
        return output.toString();
    }
}
