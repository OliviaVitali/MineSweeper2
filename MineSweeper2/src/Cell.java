/*
*Defines variables for each Cell
 */
public class Cell {
    private boolean isExposed;
    private boolean isMine;
    private int numSurroundingMines;

    public Cell(boolean exposed, boolean mine) {
        isExposed = exposed;
        isMine = mine;
        numSurroundingMines=0;
    }

    public boolean isExposed() {
        return isExposed;
    }

    public void setExposed(boolean exposed) {
        isExposed = exposed;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }

    public void increaseSurroundingMines(){
        numSurroundingMines +=1;
    }

    public int getSurroundingMines(){return numSurroundingMines;}
}