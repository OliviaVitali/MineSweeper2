import java.util.Random;

public class MineSweeperGame {
    private Cell[][]board;
    private GameStatus status;

    //constructor (?) for this class
    public MineSweeperGame() {
        status = GameStatus.NotOverYet;
        board = new Cell[5][5];//creats a 2D array of Cells that's later be linked to the MineSweeperPanel's board[][]
        setEmpty();
        layMines(7);//sets 7 mines on the board
    }

    private void setEmpty(){
        for (int r=0; r<5;r++)
            for (int c=0; c<5; c++){
                board[r][c] = new Cell(false, false);  //totally clears the board of Cells
            }

    }

    //getter method for the cell from MineSweeperGame's board variable
    public Cell getCell(int row, int col){
        return board[row][col];
    }

    //TODO limit bounds on row and col to avoid outOfBounds error
    public void select(int row, int col){
        board[row][col].setExposed(true);

        status = GameStatus.Won; //by default, the game is Wom



        for (int r = 0; r<5; r++)
            for (int c =0; c<5; c++)
                if (!board[r][c].isExposed() && !board[r][c].isMine())
                    status = GameStatus.NotOverYet;
        if (board[row][col].isMine())       //if the item selected is a mine, the game is lost
            status = GameStatus.Lost;

    }

    public GameStatus getGameStatus(){ return status;}

    public void reset() {
        status = GameStatus.NotOverYet;
        setEmpty();
        layMines(10);//minecount 10  TODO may need to adjust this value
    }

    //changes the num
    private void updateNumMines(int r, int c){
        if (r+1 <5)
            board[r+1][c].increaseSurroundingMines();
        if (r+1 <5 && c+1<5)
            board[r+1][c+1].increaseSurroundingMines();
        if (r+1 <5 && c-1 >=0)
            board[r+1][c-1].increaseSurroundingMines();

        if (c-1 >=0)
            board[r][c-1].increaseSurroundingMines();
        if (c+1 <5)
            board[r][c+1].increaseSurroundingMines();

        if (r-1 >=0 && c-1 >=0)
            board[r-1][c-1].increaseSurroundingMines();
        if (r-1 >=0)
            board[r-1][c].increaseSurroundingMines();
        if (r-1>=0 && c+1 <5)
            board[r-1][c+1].increaseSurroundingMines();

    }

    //TODO update this method to use recursion instead of non-recursive placement of mines
    private void layMines(int mineCount){
        int i=0;        //ensure all mines are set in place.  Current number of mines placed

        //creates bounds for number of mines placed on board
        if (mineCount >25 || mineCount < 0)
            System.out.print("error MineSweeperGame.java line 53");//TODO implement this.  We can't have more mines than spaces on the board
        Random random = new Random();

        //until there are mineCount mines on the board, the loop runs
        //there is a small chance of an infinite loop if the Random Function produces the same (mineCount-i) locations
        while (i < mineCount){
            int c = random.nextInt(5);
            int r = random.nextInt(5);

            if (!board[r][c].isMine()) {
                board[r][c].setMine(true);
                updateNumMines(r, c);
                i++;
            }
        }
    }


}