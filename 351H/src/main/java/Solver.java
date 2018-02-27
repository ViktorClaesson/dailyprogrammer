import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Solver {

    private int S, N;
    private Matrix board;

    Solver(int S, int N, char[][] regions) {
        this.S = S;
        this.N = N;
        List<Cell> cells = new ArrayList<>(S * S);
        for (int i = 0; i < S * S; i++) {
            cells.add(new Cell(regions[i / S][i % S]));
        }
        board = new Matrix(S, cells);

        for (int row = 0; row < S; row++) {
            List<Cell> rowList = board.getRow(row);
            rowList.forEach(c -> c.setRow(rowList));
        }

        for (int col = 0; col < S; col++) {
            List<Cell> colList = board.getCol(col);
            colList.forEach(c -> c.setCol(colList));
        }

        for (int reg = 0; reg < S; reg++) {
            List<Cell> regList = board.getReg((char) ('A' + reg));
            regList.forEach(c -> c.setReg(regList));
        }

        for (int row = 0; row < S; row++) {
            for (int col = 0; col < S; col++) {
                List<Cell> diaList = board.getDia(row, col);
                board.get(row, col).setDia(diaList);
            }
        }
    }

    public Optional<String> solve() {
        if (solve(0, 0)) {
            return Optional.of(showActive());
        } else {
            return Optional.empty();
        }
    }

    private boolean solve(int row, int col) {
        if(board.get(0, 3).isActive() && board.get(0, 9).isActive()) {
            System.out.println();
        }
        if(col == 0 && row > 0 && !check(board.getRow(row - 1), N)) {
            return false;
        }

        if (row == S) {
            return checkBoard();
        }

        int nRow = row + (col + 1) / S;
        int nCol = (col + 1) % S;

        Cell c = board.get(row, col);
        if (!c.isBlocked(N)) {
            c.blockOthers(true);
            if (solve(nRow, nCol)) {
                return true;
            }
            c.blockOthers(false);
        }

        return solve(nRow, nCol);
    }

    private boolean checkBoard() {
        boolean checkRows = true;
        for (int row = 0; row < S; row++) {
            checkRows &= check(board.getRow(row), N);
        }

        boolean checkCols = true;
        for (int col = 0; col < S; col++) {
            checkCols &= check(board.getCol(col), N);
        }

        boolean checkRegs = true;
        for (int reg = 0; reg < S; reg++) {
            checkRegs &= check(board.getReg((char) ('A' + reg)), N);
        }

        boolean checkDias = true;
        for (int row = 0; row < S; row++) {
            for (int col = 0; col < S; col++) {
                if (board.get(row, col).isActive()) {
                    checkDias &= check(board.getDia(row, col), 0);
                }
            }
        }


        return checkRows && checkCols && checkRegs && checkDias;
    }

    private boolean check(List<Cell> list, int checkSum) {
        return list.stream().map(c -> c.isActive() ? 1 : 0).reduce(0, (a, b) -> a + b) == checkSum;
    }

    public String toString() {
        return showActive() + "\n-----\n" + showNonBlocked();
    }

    private String showActive() {
        return board.toString(c -> c.isActive() ? c.toString() : c.toString().toLowerCase());
    }

    private String showNonBlocked() {
        return board.toString(c -> !c.isBlocked(N) ? c.toString() : c.toString().toLowerCase());
    }

}
