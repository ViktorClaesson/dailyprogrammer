import java.util.List;
import java.util.Stack;

public class Cell {

    private boolean active;
    private char region;
    private int blockCount;
    private Stack<Cell> blocked = new Stack<>();

    private List<Cell> row, col, reg, dia; // row, column and region and diagonals

    Cell(char region) {
        this.region = region;
    }

    void setRow(List<Cell> row) {
        this.row = row;
    }

    void setCol(List<Cell> col) {
        this.col = col;
    }

    void setReg(List<Cell> reg) {
        this.reg = reg;
    }

    void setDia(List<Cell> dia) {
        this.dia = dia;
    }

    boolean inRegion(char region) {
        return this.region == region;
    }

    boolean isActive() {
        return active;
    }

    boolean isBlocked(int N) {
        return blockCount >= N;
    }

    void blockOthers(boolean block) {
        active = block;
        blockList(row, block);
        blockList(col, block);
        blockList(reg, block);
        blockList(dia, block);
    }

    private void blockList(List<Cell> list, boolean block) {
        list.forEach(c -> c.blockOthers(this, block));
    }

    private void blockOthers(Cell caller, boolean block) {
        if (caller != this) {
            if (block) {
                blockCount++;
                blocked.push(caller);
            } else {
                blockCount--;
                if(blocked.peek() != caller) {
                    System.out.println("Caller != peek");
                }
                blocked.pop();
            }
        }
    }

    @Override
    public String toString() {
        return Character.toString(region);
    }

}
