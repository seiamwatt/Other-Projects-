import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Minefield {
    /**
    Global Section
    */
    public static final String ANSI_YELLOW_BRIGHT = "\u001B[33;1m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE_BRIGHT = "\u001b[34;1m";
    public static final String ANSI_BLUE = "\u001b[34m";
    public static final String ANSI_RED_BRIGHT = "\u001b[31;1m";
    public static final String ANSI_RED = "\u001b[31m";
    public static final String ANSI_GREEN = "\u001b[32m";
    public static final String ANSI_PURPLE = "\u001b[35m";
    public static final String ANSI_CYAN = "\u001b[36m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001b[47m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001b[45m";
    public static final String ANSI_GREY_BACKGROUND = "\u001b[0m";
    public static final String ANSI_CYAN_BRIGHT = "\u001B[0;96m";

    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_PURPLE_BRIGHT = "\u001B[0;95m";

    public static final String ANSI_GREEN_BOLD_BRIGHT = "\u001B[1;92m";

    /* 
     * Class Variable Section
     * 
    */

    /*Things to Note:
     * Please review ALL files given before attempting to write these functions.
     * Understand the Cell.java class to know what object our array contains and what methods you can utilize
     * Understand the StackGen.java class to know what type of stack you will be working with and methods you can utilize
     * Understand the QGen.java class to know what type of queue you will be working with and methods you can utilize
     */

    public Cell[][] minefield;
    public int rows;
    public int columns;
    public int flags;
    public int revealedCount;

    public int mineCount;


    /**
     * Minefield
     * 
     * Build a 2-d Cell array representing your minefield.
     * Constructor
     * @param rows       Number of rows.
     * @param columns    Number of columns.
     * @param flags      Number of flags, should be equal to mines
     */


    public Minefield(int rows, int columns, int flags) {
        this.minefield = new Cell[rows][columns];
        this.rows = rows;
        this.columns = columns;
        this.flags = flags;
        this.revealedCount = 0;
        this.mineCount = flags;
    }

    /**
     * evaluateField
     * 
     *
     * @function:
     * Evaluate entire array.
     * When a mine is found check the surrounding adjacent tiles. If another mine is found during this check, increment adjacent cells status by 1.
     * 
     */
    public void evaluateField(){

        int cellStatus = 0;

        for(int row = 0; row < this.minefield.length; row++){
            for(int column = 0; column < this.minefield[row].length; column++){
                if(this.minefield[row][column].getStatus().equals("M")){
                    for(int i = -1;i <= 1 ; i++){
                        for(int j = -1; j <= 1; j++){
                            int newRow = row + i;
                            int newColumn = column + j;
                            if (newRow >= 0 && newRow < minefield.length && newColumn >= 0 && newColumn < minefield[0].length && !this.minefield[newRow][newColumn].getStatus().equals("M" ) && !(i == 0 && j == 0)){
                                String currStatus = this.minefield[newRow][newColumn].getStatus();
                                int newStatus = Integer.parseInt(currStatus) + 1;
                                this.minefield[newRow][newColumn].setStatus(Integer.toString(newStatus));
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * createMines
     * 
     * Randomly generate coordinates for possible mine locations.
     * If the coordinate has not already been generated and is not equal to the starting cell set the cell to be a mine.
     * utilize rand.nextInt()
     * 
     * @param x       Start x, avoid placing on this square.
     * @param y        Start y, avoid placing on this square.
     * @param mines      Number of mines to place.
     */
    public void createMines(int x, int y, int mines) {
        int count = 0;
        Random random = new Random();
        int randomRow = 0;
        int randomCol = 0;

        for(int row = 0; row < this.minefield.length;row++){
            for(int column = 0; column < this.minefield[row].length; column++){
                Cell cell = new Cell(false,"0",row,column);
                this.minefield[row][column] = cell;
            }
        }

        while(count < mines){
            randomRow = random.nextInt(0,this.rows);
            randomCol = random.nextInt(0,this.columns);
            Cell cell = this.minefield[randomRow][randomCol];

            if(randomRow !=  x && randomCol != y && cell.getStatus() != "M") {
                cell.setStatus("M");
                count++;
            }
        }

    }

    /**
     * guess
     * 
     * Check if the guessed cell is inbounds (if not done in the Main class). 
     * Either place a flag on the designated cell if the flag boolean is true or clear it.
     * If the cell has a 0 call the revealZeroes() method or if the cell has a mine end the game.
     * At the end reveal the cell to the user.
     * 
     * 
     * @param x       The x value the user entered.
     * @param y       The y value the user entered.
     * @param flag    A boolean value that allows the user to place a flag on the corresponding square.
     * @return boolean Return false if guess did not hit mine or if flag was placed, true if mine found.
     */
    public boolean guess(int x, int y, boolean flag) {

        if(flag){
            this.minefield[x][y].setStatus("F");
            this.minefield[x][y].setRevealed(true);
            return false;
        }

        Cell guessCell = this.minefield[x][y];
        if(!guessCell.getRevealed()){
            guessCell.setRevealed(true);
            this.revealedCount++;

        }


        if(guessCell.getStatus().equals("M")){
            return true;
        }

        return false;
    }

    /**
     * gameOver
     * 
     * Ways a game of Minesweeper ends:
     * 1. player guesses a cell with a mine: game over -> player loses
     * 2. player has revealed the last cell without revealing any mines -> player wins
     * 
     * @return boolean Return false if game is not over and squares have yet to be revealed, otheriwse return true.
     */
    public boolean gameOver() {

        if((this.rows * this.columns) - this.revealedCount == this.mineCount){
            return true;
        }

        for(int x = 0;x < this.minefield.length; x++){
            for(int y = 0; y < this.minefield[x].length; y++){
                if(this.minefield[x][y].getRevealed() && this.minefield[x][y].getStatus().equals("M")){
                    return true;
                }
            }
        }


        return false;
    }

    /**
     * Reveal the cells that contain zeroes that surround the inputted cell.
     * Continue revealing 0-cells in every direction until no more 0-cells are found in any direction.
     * Utilize a STACK to accomplish this.
     *
     * This method should follow the psuedocode given in the lab writeup.
     * Why might a stack be useful here rather than a queue?
     *
     * @param x      The x value the user entered.
     * @param y      The y value the user entered.
     */
    public void revealZeroes(int x, int y) {
        StackGen<Cell> stack = new Stack1Gen<>();



        Cell cell = this.minefield[x][y];
        stack.push(cell);


        while(!stack.isEmpty()){
            cell = stack.pop();

            if(!cell.getRevealed()){
                cell.setRevealed(true);
                this.revealedCount++;
            }
            //cell.setRevealed(true);
           // this.revealedCount++;
            x = cell.getRow();
            y = cell.getColumn();


            if(x - 1 >= 0){
                cell = this.minefield[x-1][y];

                if(cell.getStatus().equals("0") && !cell.getRevealed()){
                    //cell.setRevealed(true);

                    stack.push(cell);
                }
            }

            if(y - 1 >= 0){
                cell = this.minefield[x][y-1];

                if(cell.getStatus().equals("0") && !cell.getRevealed()){
                    //cell.setRevealed(true);



                    stack.push(cell);
                }
            }

            if(x + 1 < this.rows){
                cell = this.minefield[x+1][y];

                if(cell.getStatus().equals("0") && !cell.getRevealed()){
                    //cell.setRevealed(true);


                    stack.push(cell);
                }
            }

            if(y + 1 < this.columns){
                cell = this.minefield[x][y+1];

                if(cell.getStatus().equals("0") && !cell.getRevealed()){
                    //cell.setRevealed(true);

                    stack.push(cell);
                }
            }


        }
    }

    /**
     * revealStartingArea
     *
     * On the starting move only reveal the neighboring cells of the inital cell and continue revealing the surrounding concealed cells until a mine is found.
     * Utilize a QUEUE to accomplish this.
     * 
     * This method should follow the psuedocode given in the lab writeup.
     * Why might a queue be useful for this function?
     *
     * @param x     The x value the user entered.
     * @param y     The y value the user entered.
     */
    public void revealStartingArea(int x, int y) {
       QGen<Cell> queue = new Q1Gen<>();

       Cell cell = this.minefield[x][y];
       queue.add(cell);

       while(queue.length() != 0){
           cell = queue.remove();

           if(!cell.getRevealed()){
               cell.setRevealed(true);
               this.revealedCount++;
           }

           x = cell.getRow();
           y = cell.getColumn();

           if(x - 1 >= 0 ){
               cell = this.minefield[x-1][y];

               if(cell.getStatus().equals("M")){
                   break;
               }

               //cell.setRevealed(true);
//
//               if(!cell.getRevealed()){
//                   cell.setRevealed(true);
//                   this.revealedCount++;
//               }

               queue.add(cell);

           }

           if(y - 1 >= 0){
               cell = this.minefield[x][y-1];

               if(cell.getStatus().equals("M")){
                   break;
               }
               //cell.setRevealed(true);
//
//               if(!cell.getRevealed()){
//                   cell.setRevealed(true);
//                   this.revealedCount++;
//               }

               queue.add(cell);


           }

           if(x + 1 < this.rows){
               cell = this.minefield[x+1][y];

               if(cell.getStatus().equals("M")){
                   break;
               }

               //cell.setRevealed(true);
//
//               if(!cell.getRevealed()){
//                   cell.setRevealed(true);
//                   this.revealedCount++;
//               }

               queue.add(cell);
           }

           if(y + 1 < this.columns){
               cell = this.minefield[x][y+1];

               if(cell.getStatus().equals("M")){
                   break;
               }

              // cell.setRevealed(true);
//               if(!cell.getRevealed()){
//                   cell.setRevealed(true);
//                   this.revealedCount++;
//               }

               queue.add(cell);
           }
       }
    }

    /**
     * For both printing methods utilize the ANSI colour codes provided! 
     * 
     * 
     * 
     * 
     * 
     * debug
     *
     * @function This method should print the entire minefield, regardless if the user has guessed a square.
     * *This method should print out when debug mode has been selected. 
     */
    public void debug() {

        String entireMinefield = "  ";
        for (int i = 0; i < this.columns; i++) {
            entireMinefield += i + " ";
        }
        entireMinefield += "\n";

        for (int x = 0; x < this.rows; x++) {
            entireMinefield += x + " ";
            for (int y = 0; y < this.columns; y++) {
                if(this.minefield[x][y].getStatus().equals("M")){
                    entireMinefield += ANSI_RED + this.minefield[x][y].getStatus() + ANSI_GREY_BACKGROUND + " ";
                }

                if(this.minefield[x][y].getStatus().equals("0")){
                    entireMinefield += ANSI_GREEN + this.minefield[x][y].getStatus() + ANSI_GREY_BACKGROUND + " ";
                }

                if(this.minefield[x][y].getStatus().equals("1")){
                    entireMinefield += ANSI_BLUE + this.minefield[x][y].getStatus() + ANSI_GREY_BACKGROUND + " ";
                }

                if(this.minefield[x][y].getStatus().equals("2")){
                    entireMinefield += ANSI_YELLOW + this.minefield[x][y].getStatus() + ANSI_GREY_BACKGROUND + " ";
                }

                if(this.minefield[x][y].getStatus().equals("3")){
                    entireMinefield += ANSI_CYAN + this.minefield[x][y].getStatus() + ANSI_GREY_BACKGROUND + " ";
                }

                if(this.minefield[x][y].getStatus().equals("4")){
                    entireMinefield += ANSI_PURPLE + this.minefield[x][y].getStatus() + ANSI_GREY_BACKGROUND + " ";
                }

                if(this.minefield[x][y].getStatus().equals("5")){
                    entireMinefield += ANSI_BLUE_BRIGHT + this.minefield[x][y].getStatus() + ANSI_GREY_BACKGROUND + " ";
                }

                if(this.minefield[x][y].getStatus().equals("6")){
                    entireMinefield += ANSI_YELLOW_BRIGHT + this.minefield[x][y].getStatus() + ANSI_GREY_BACKGROUND + " ";
                }

                if(this.minefield[x][y].getStatus().equals("7")){
                    entireMinefield += ANSI_WHITE + this.minefield[x][y].getStatus() + ANSI_GREY_BACKGROUND + " ";
                }

                if(this.minefield[x][y].getStatus().equals("8")){
                    entireMinefield += ANSI_PURPLE_BRIGHT + this.minefield[x][y].getStatus() + ANSI_GREY_BACKGROUND + " ";
                }

                if(this.minefield[x][y].getStatus().equals("9")){
                    entireMinefield += ANSI_CYAN_BRIGHT + this.minefield[x][y].getStatus() + ANSI_GREY_BACKGROUND + " ";
                }

                if(this.minefield[x][y].getStatus().equals("F")){
                    entireMinefield += ANSI_GREEN_BOLD_BRIGHT  + this.minefield[x][y].getStatus() + ANSI_GREY_BACKGROUND + " ";
                }
                //entireMinefield += this.minefield[x][y].getStatus() + " ";
            }
            entireMinefield += "\n";
        }

        System.out.println(entireMinefield);

    }

    /**
     * toString
     *
     * @return String The string that is returned only has the squares that has been revealed to the user or that the user has guessed.
     */
    public String toString() {

        String entireMinefield = "  ";
        for (int i = 0; i < this.columns; i++) {
            entireMinefield += i + " ";
        }
        entireMinefield += "\n";

        for (int x = 0; x < this.rows; x++) {
            entireMinefield += x + " ";
            for (int y = 0; y < this.columns; y++) {
                if(this.minefield[x][y].getRevealed()){

                    if(this.minefield[x][y].getStatus().equals("M")){
                        entireMinefield += ANSI_RED + this.minefield[x][y].getStatus() + ANSI_GREY_BACKGROUND + " ";
                    }

                    if(this.minefield[x][y].getStatus().equals("0")){
                        entireMinefield += ANSI_GREEN + this.minefield[x][y].getStatus() + ANSI_GREY_BACKGROUND + " ";
                    }

                    if(this.minefield[x][y].getStatus().equals("1")){
                        entireMinefield += ANSI_BLUE + this.minefield[x][y].getStatus() + ANSI_GREY_BACKGROUND + " ";
                    }

                    if(this.minefield[x][y].getStatus().equals("2")){
                        entireMinefield += ANSI_YELLOW + this.minefield[x][y].getStatus() + ANSI_GREY_BACKGROUND + " ";
                    }

                    if(this.minefield[x][y].getStatus().equals("3")){
                        entireMinefield += ANSI_CYAN + this.minefield[x][y].getStatus() + ANSI_GREY_BACKGROUND + " ";
                    }

                    if(this.minefield[x][y].getStatus().equals("4")){
                        entireMinefield += ANSI_PURPLE + this.minefield[x][y].getStatus() + ANSI_GREY_BACKGROUND + " ";
                    }

                    if(this.minefield[x][y].getStatus().equals("5")){
                        entireMinefield += ANSI_BLUE_BRIGHT + this.minefield[x][y].getStatus() + ANSI_GREY_BACKGROUND + " ";
                    }

                    if(this.minefield[x][y].getStatus().equals("6")){
                        entireMinefield += ANSI_YELLOW_BRIGHT + this.minefield[x][y].getStatus() + ANSI_GREY_BACKGROUND + " ";
                    }

                    if(this.minefield[x][y].getStatus().equals("7")){
                        entireMinefield += ANSI_WHITE + this.minefield[x][y].getStatus() + ANSI_GREY_BACKGROUND + " ";
                    }

                    if(this.minefield[x][y].getStatus().equals("8")){
                        entireMinefield += ANSI_PURPLE_BRIGHT + this.minefield[x][y].getStatus() + ANSI_GREY_BACKGROUND + " ";
                    }

                    if(this.minefield[x][y].getStatus().equals("9")){
                        entireMinefield += ANSI_CYAN_BRIGHT + this.minefield[x][y].getStatus() + ANSI_GREY_BACKGROUND + " ";
                    }

                    if(this.minefield[x][y].getStatus().equals("F")){
                        entireMinefield += ANSI_GREEN_BOLD_BRIGHT  + this.minefield[x][y].getStatus() + ANSI_GREY_BACKGROUND + " ";
                    }

                }else{
                    entireMinefield += "-" + " ";
                }
            }
            entireMinefield += "\n";
        }

        return entireMinefield;
    }
}
