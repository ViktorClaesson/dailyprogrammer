import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Matrix {

    private final int S;
    private List<List<Cell>> matrix;

    Matrix(int size, List<Cell> elements) {
        S = size;
        matrix = new ArrayList<>(size);
        for(int i = 0; i < size; i++) {
            List<Cell> row = new ArrayList<>(size);
            for(int j = 0; j < size; j++) {
                row.add(elements.get(i * size + j));
            }
            matrix.add(row);
        }
    }

    Cell get(int row, int col) {
        return matrix.get(row).get(col);
    }

    List<Cell> getRow(int row) {
        return matrix.get(row);
    }

    List<Cell> getCol(int col) {
        return matrix.stream().map(row -> row.get(col)).collect(Collectors.toList());
    }

    List<Cell> getReg(char reg) {
        return matrix.stream().flatMap(Collection::stream).filter(c -> c.inRegion(reg)).collect(Collectors.toList());
    }

    List<Cell> getDia(int row, int col) {
        List<Cell> dia = new ArrayList<>();
        if(row > 0) {
            if(col > 0)
                dia.add(matrix.get(row - 1).get(col - 1));
            if(col < S - 1)
                dia.add(matrix.get(row - 1).get(col + 1));
        }
        if(row < S - 1) {
            if(col > 0)
                dia.add(matrix.get(row + 1).get(col - 1));
            if(col < S - 1)
                dia.add(matrix.get(row + 1).get(col + 1));
        }
        return dia;
    }

    @Override
    public String toString() {
        return matrix.stream()
                .map(l -> l.stream().map(Cell::toString).reduce("", (a,b)->a+b))
                .collect(Collectors.joining("\n"));
    }

    public String toString(Function<Cell, String> f) {
        return matrix.stream()
                .map(l -> l.stream().map(f).reduce("", (a,b)->a+b))
                .collect(Collectors.joining("\n"));
    }

}
