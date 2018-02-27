import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main351H {

    public static void main(String[] args) throws IOException {
        String path = Main351H.class.getResource("input2.txt").getPath();
        BufferedReader reader = new BufferedReader(new FileReader(path));
        List<String> lines = reader.lines().collect(Collectors.toList());

        Solver b = new Solver(lines.get(1).length(), Character.getNumericValue(lines.get(0).charAt(0)), regions(lines.subList(1, lines.size())));
        long timestamp = System.currentTimeMillis();
        Optional<String> result = b.solve();
        long timestamp2 = System.currentTimeMillis();
        System.out.println(String.format("It took %.3f s", (timestamp2 - timestamp) / 1000.0));
        result.ifPresentOrElse(System.out::println, () -> System.out.println("Unable to find solution!"));
    }

    private static char[][] regions(List<String> lines) {
        return lines.stream().map(String::toCharArray).collect(Collectors.toList()).toArray(new char[lines.size()][lines.size()]);
    }

}
