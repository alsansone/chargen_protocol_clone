import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Main {
    public static void main(String[] args) {
        final int firstChar = 33; // decimal representation of starting character of ASCII table
        final int numberOfChars = 94; // number of ASCII characters we will loop through
        final int charsPerLine = 72; // number of characters we want per line
        int count = 0; // keep track of how many lines we've written
        final byte[] line = new byte[charsPerLine + 2]; //buffer to hold a line of characters

        int start = firstChar;
        try (OutputStream out = new BufferedOutputStream(new FileOutputStream("output.txt"))) {
            while (count < 23) {
                for (int i = start; i < start + charsPerLine; i++) {
                    /* since we are returning a decimal we must cast it to a byte to
                    place it in the buffer */
                    line[i - start] = (byte) ((i - firstChar) % numberOfChars + firstChar);
                }
                line[72] = (byte) '\r';
                line[73] = (byte) '\n';
                out.write(line);
                start = ((start + 1) - firstChar) % numberOfChars + firstChar; // update new starting character
                count++;
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
