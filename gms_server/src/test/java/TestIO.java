import java.io.*;

/**
 * Created by Kevin on 2015/4/3.
 */
public class TestIO {
    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\Kevin\\Desktop\\zhidao.txt");

        Reader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);
        int b = 0;
        boolean flag = false;
        int n = 0;
        while ((b = bufferedReader.read())!= -1) {
            char c = (char)b;
            if (flag || n == 0) {
                System.out.print((c+"").toUpperCase());
                flag = false;
            } else {
                System.out.print(c);
            }

            if (c == ' ' || c == ',' || c == '.') {
                flag = true;
            }
            n++;
        }
        reader.close();
        bufferedReader.close();

    }
}
