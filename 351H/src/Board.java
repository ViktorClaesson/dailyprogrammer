import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Board {

    private Cell board[][];

    public Board(int S, int[][] regions) {
        board = new Cell[S][S];

        List<List<Cell>> rows = new ArrayList<>();
        List<List<Cell>> cols = new ArrayList<>();
        List<List<Cell>> regs = new ArrayList<>();

        for(int i = 0; i < S; i++) {
            rows.add(new ArrayList<>());
            cols.add(new ArrayList<>());
            regs.add(new ArrayList<>());
        }

        for(int r = 0; r < S; r++) {
            List<Cell> row = rows.get(r);
            for(int c = 0; c < S; c++) {
                List<Cell> col = cols.get(c);
                List<Cell> reg = regs.get(regions[r][c]);

                Cell cell = new Cell(row, col, reg);

                row.add(cell);
                col.add(cell);
                reg.add(cell);

                board[r][c] = cell;
            }
        }
    }

    private boolean[][] solution() {
        boolean[][] solution = new boolean[board.length][board.length];

        for(int row = 0; row < solution.length; row++) {
            for(int col = 0; col < solution.length; col++) {
                solution[row][col] = board[row][col].isBlocked();
            }
        }

        return solution;
    }

    public Optional<boolean[][]> solve() {
        if(solve(0, 0)) {
            return Optional.of(solution());
        } else {
            return Optional.empty();
        }
    }

    private boolean solve(int row, int col) {
        if(row >= board.length)
            return false;

        return true;
    }


}
