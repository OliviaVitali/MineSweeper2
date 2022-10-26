import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MineSweeperPanel extends JPanel {

    private JButton[][] board; //a 2D array of JButtons that is the game board
    private JButton butQuit; //a quit button
    private Cell iCell;
    private JButton cheatMode;  //determines if the mines should be hidden or
    private boolean cheatAllowed;
    private MineSweeperGame game;  //model


    public MineSweeperPanel() {
        JPanel center = new JPanel();   //creates panels
        JPanel bottom = new JPanel();
        //create game listener
        ButtonListener listener = new ButtonListener();

        game = new MineSweeperGame();
        cheatMode = new JButton("Cheat Mode");
        cheatMode.addActionListener(listener);
        cheatAllowed = true;
        //create the board
        center.setLayout(new GridLayout( 5,5));//defines a 5x5 grid layout for the center panel
        board = new JButton[5][5];  //declares a 5x5 board of buttons


        for (int row= 0; row <5; row++) {
            for (int col = 0; col < 5 ; col++) {
                board[row][col] = new JButton("");//instantiates new Jbutton without text
                board[row][col].addActionListener(listener);//adds an the actionListener created earlier as the listener
                center.add(board[row][col]);//adds the button to the center panel
            }
        }

        bottom.add(cheatMode);

        //after board is created, we use the displayBoard method of MineSweeperPanel to display the board
        displayBoard();




        //add all to contentPane
        add(new JLabel("MineSweeper"), BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
    }

    /*
    *A Method to render the board visable with the updated Status of the buttons (exposed, isMine)
    * Also updates the text of the board, so we know how many mines surround an exposed Cell.
     */
    private void displayBoard() {

        for (int r=0; r<5; r++)
            for (int c=0;c<5;c++){

                iCell = game.getCell(r,c);

                board[r][c].setText("");

                //readable, ifs are welcome
                if (cheatAllowed) {
                    if (iCell.isMine())
                        board[r][c].setText("!");
                }
                else {
                    if (iCell.isMine())
                        board[r][c].setText(" ");
                }
                if (iCell.isExposed()) {
                    board[r][c].setEnabled(false);

                    if (!iCell.isMine())
                        board[r][c].setText(""+iCell.getSurroundingMines());
                    else
                        board[r][c].setText("!");   //! indicates a bomb for the user
                }
                else
                    board[r][c].setEnabled(true);

            }
    }

    /*
    When an actionEvent happens, the ButtonListener selects the source of the event (ie whatever is at the board[r][c]
    Then it recreates the display
     */
    private class ButtonListener implements ActionListener {


        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == cheatMode) {
                System.out.println("Cheat mode clicked");
                cheatAllowed = !cheatAllowed;
                }


            for (int r = 0; r < 5; r++){
                for (int c = 0; c < 5 ; c++) {
                    if (board[r][c] == e.getSource())
                        game.select(r, c);
                }
            }
            displayBoard(); //updates the board to display

            //updates player if game is lost
            if (game.getGameStatus()== GameStatus.Lost){
                displayBoard(); //TODO test if this line can be removed
                JOptionPane.showMessageDialog(null, "You lost.\nGame will reset");
                game.reset();//resets the game
                displayBoard();
            }
            if (game.getGameStatus()== GameStatus.Won){
                JOptionPane.showMessageDialog(null, "You win.\nGame will reset");
                game.reset();
                displayBoard();
            }

        }
    }
}