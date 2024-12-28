//Import Section
import javax.crypto.spec.PSource;
import java.util.Random;
import java.util.Scanner;

/*
 * Provided in this class is the neccessary code to get started with your game's implementation
 * You will find a while loop that should take your minefield's gameOver() method as its conditional
 * Then you will prompt the user with input and manipulate the data as before in project 2
 * 
 * Things to Note:
 * 1. Think back to project 1 when we asked our user to give a shape. In this project we will be asking the user to provide a mode. Then create a minefield accordingly
 * 2. You must implement a way to check if we are playing in debug mode or not.
 * 3. When working inside your while loop think about what happens each turn. We get input, user our methods, check their return values. repeat.
 * 4. Once while loop is complete figure out how to determine if the user won or lost. Print appropriate statement.
 */




public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose difficulty (easy/medium/hard)");
        String output = scanner.nextLine();

        Minefield minefield;
        int numMines = 0;
        int numFlags = 0;



        //asks user what difficulty they want and create a minefield object with that difficulty
        //if the user does not state a difficulty, the game will set to medium

        if (output.equals("easy")) {
            minefield = new Minefield(5, 5, 5);
            numMines = 5;
            numFlags = 5;

        } else if (output.equals("medium")) {
            minefield = new Minefield(9, 9, 12);
            numMines = 12;
            numFlags = 12;
        } else if (output.equals("hard")) {
            minefield = new Minefield(20, 20, 40);
            numMines = 40;
            numFlags = 40;
        } else {
            minefield = new Minefield(9, 9, 12); //sets to medium mode
            numMines = 12;
            numFlags = 12;
        }

        //if debug mode is true every cell will print regardless of the reveal status, debug mode will also print normal minefield
        boolean debug = false;
        System.out.println("Debug mode? true/false");
        debug = scanner.nextBoolean();


        int chosenRow = 0;
        int chosenColumn = 0;
        boolean minesCreated = false;
        boolean isFlag = false;

        //users enter their starting row and column

        System.out.println("enter  row: ");
        chosenRow = scanner.nextInt();
        System.out.println("enter  column: ");
        chosenColumn = scanner.nextInt();


        minefield.createMines(chosenRow, chosenColumn, numMines);
        minefield.evaluateField();


        minefield.minefield[chosenRow][chosenColumn].setRevealed(true);
        minefield.revealedCount++;

        minefield.revealStartingArea(chosenRow,chosenColumn);
        System.out.println("count: " + minefield.revealedCount);



        //As mentioned above if it is in debug mode it prints a normal minefield game and a minefield game that
        //has every cell printed
        if(debug){
            System.out.println(minefield);
            minefield.debug();
        }else if(!debug){
            System.out.println(minefield);
        }

        //if player choose no debug mode
        if(!debug){
            //game continues until player has hit all minefield or revealed all required cells
            while (!minefield.gameOver()) {
                System.out.println("guess row: ");
                chosenRow = scanner.nextInt();

                System.out.println("guess column: ");
                chosenColumn = scanner.nextInt();

                System.out.println("flag? true/false ");
                isFlag = scanner.nextBoolean();

                if(minefield.guess(chosenRow,chosenColumn,isFlag)){
                    minefield.gameOver();
                    System.out.println("you lose!");
                    break;
                }

                if(isFlag && numFlags > 0){
                    minefield.minefield[chosenRow][chosenColumn].setStatus("F");
                    numFlags--;
                }

                if(minefield.minefield[chosenRow][chosenColumn].getStatus().equals("0")){
                    minefield.revealZeroes(chosenRow,chosenColumn);
                }

                System.out.println(minefield);


                //if all required cells are revealed the player wins
                if(minefield.gameOver()){
                    System.out.println("you win!");
                    break;
                }


            }
        }


        //if player choose a debug mode
        if(debug){
            //game continues until player has hit all minefield or revealed all required cells
            while (!minefield.gameOver()) {
                System.out.println("guess row: ");
                chosenRow = scanner.nextInt();

                System.out.println("guess column: ");
                chosenColumn = scanner.nextInt();

                System.out.println("flag? true/false ");
                isFlag = scanner.nextBoolean();

                //if the player hits a mine, the game will stop
                if(minefield.guess(chosenRow,chosenColumn,isFlag)){
                    minefield.gameOver();
                    System.out.println("you lose!");
                    break;
                }

                if(isFlag && numFlags > 0){
                    minefield.minefield[chosenRow][chosenColumn].setStatus("F");
                    numFlags--;
                }

                if(minefield.minefield[chosenRow][chosenColumn].getStatus().equals("0")) {
                    minefield.revealZeroes(chosenRow, chosenColumn);
                }

                System.out.println(minefield);
                minefield.debug();

                //if all required cells are revealed the player wins
                if(minefield.gameOver()){
                    System.out.println(" you win!");
                    break;
                }

                //System.out.println("reveal count: " + minefield.revealedCount);
            }
        }










    }
}


































