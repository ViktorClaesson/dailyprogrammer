import java.util.List;

public class Cell {

    private int blockCount;

    List<Cell> row, col, reg; // row, column and region.

    public Cell(List<Cell> row, List<Cell> col, List<Cell> reg) {
        this.row = row;
        this.col = col;
        this.reg = reg;
    }

    void blockList(String choice) {
        switch (choice) {
            case "row":
                blockList(row);
                break;
            case "col":
                blockList(col);
                break;
            case "reg":
                blockList(reg);
                break;
            default: break;
        }
    }

    boolean isBlocked() {
        return blockCount <= 0;
    }

    private void blockList(List<Cell> list) {
        list.forEach(c -> c.block(this));
    }

    private void block(Cell caller) {
        if(caller != this)
            blockCount++;
    }

    private void unblock(Cell caller) {
        if(caller != this)
            blockCount--;
    }

}
