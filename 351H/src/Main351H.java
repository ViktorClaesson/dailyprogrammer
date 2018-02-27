import java.io.*;

public class Main351H {

    public static void main(String[] args) throws IOException {
        InputStream in = Main351H.class.getResourceAsStream("res/test.txt");
        int c;
        while((c = in.read()) != -1) {
            System.out.print(String.format("%d, %c\n", c, c));
        }
        in.close();

        /*
        Board b = new Board(6, new int[0][0]);
        Optional<boolean[][]> result = b.solve();
        result.ifPresent(Main351H::displayResult);
        */
    }

    private static void displayResult(boolean[][] results) {
        StringBuilder sb = new StringBuilder();
        for(boolean[] row : results) {
            for(boolean b : row) {
                sb.append(b ? '*' : '-');
            }
            sb.append('\n');
        }
        System.out.println(sb.toString());
    }


}
