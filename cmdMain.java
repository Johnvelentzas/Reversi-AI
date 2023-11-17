import java.util.*;

//TODO FIX THE SCORE
//TODO MAKE THE AI AND FIND A WAY FOR THE PLAYER TO PLAY WITH THE COMPUTER
public class cmdMain{


    public static void chooseAmove(Player pl, int maxDepth, Board board, int boardDimentions, boolean validInput, Scanner answer) {
        //System.out.println("\nchooseAmove method called for player: " + pl);
    //------------------------------------------- INITIALIZING GAME ------------------------------------------------ 
        boolean moveFound = false;
        int playerletter = pl.getPlayerLetter();

        //TEMPORARY TO CALCULATE SCORE --> NOT EFFICIENT
        int score1 = 0;
        int score2 = 0;

        for (int i = 0; i<board.getDimention(); i++) {
            for (int j = 0; j<board.getDimention(); j++) {
                if (board.getGameBoard()[i][j] == 1) {
                    score1++;
                }
                else if (board.getGameBoard()[i][j] == -1) {
                    score2++;
                }
            }
        }
        //TEMPORARY TO CALCULATE SCORE --> NOT EFFICIENT


        //TO CALCULATE SCORE MORE EFFICIENTLY
        //int score1 = board.getPlayer1Score();
        //int score2 = board.getPlayer2Score();

        System.out.println("---------------------------------------------------------");
        System.out.println("\nPlayer1 score = " + score1 + "\tPlayer2 score = " + score2);
        board.print();

        ArrayList<Move> playerPossibleMoves = board.findPossibleMoves(playerletter);

        System.out.println(playerPossibleMoves);
        if (pl.getPlayerLetter() == 1) {
            if (playerPossibleMoves.isEmpty()) {
                System.out.println("\nNo available moves for player 1: TURN SKIPPED");
                moveFound = true;
            }
            else {
                System.out.println("\n~ Make your move PLAYER 1");
            }
        }
        else if (pl.getPlayerLetter() == -1) {
            if (playerPossibleMoves.isEmpty()) {
                System.out.println("\nNo available moves for player 2: TURN SKIPPED");
                moveFound = true;
            }
            else {
                System.out.println("\n~ Make your move PLAYER 2");
            }
        }

    // ----------------------- CHOOSING A MOVE ----------------------------

        while (!moveFound) {
            //--------------------------------- ROW ----------------------------------
            validInput = false;
            int chosenRow = 0;
            while(!validInput) {
                System.out.println("\n~ ROW:");
                if (answer.hasNextInt()) {
                    int ans = answer.nextInt();
                    if (ans < boardDimentions && ans >= 0) {
                        chosenRow = ans;
                        validInput = true;
                    }
                    else {
                        System.out.println("\n! Invalid row. Please try again.");
                        answer.next();
                    }
                }
                else {
                    System.out.println("\n! Invalid row. Please try again.");
                    answer.next();
                }
            }

            //---------------------------------- COLUMN -----------------------------------
            validInput = false;
            int chosenCol = 0;
            while (!validInput) {
                System.out.println("\n~ COLUMN:");
                if (answer.hasNextInt()) {
                    int ans = answer.nextInt();
                    if (ans < boardDimentions && ans >= 0) {
                        chosenCol = ans;
                        validInput = true;
                    }
                    else {
                        System.out.println("\n! Invalid row. Please try again.");
                        answer.next();
                    }
                }
                else {
                    System.out.println("\n! Invalid column. Please try again.");
                    answer.next();
                }
            }

            //------------------------------------------- MAKING THE MOVE ---------------------------------------------
            Move playerMove = new Move(chosenRow, chosenCol);
            for (Move move : playerPossibleMoves) {
                if (playerMove.equals(move)) {  
                    System.out.println("\n\nMOVE MADE!");
                    board.makeMove(playerMove, playerletter);
                    moveFound = true;
                }
            }
            if (!moveFound) {
                System.out.println("\n\n! THIS MOVE IS NOT AVAILABLE. Please try again.\n");
                System.out.println(playerPossibleMoves);
            }
        }

        if (board.isTerminal()) {
            int winner = board.findTheWinner(score1, score2);
            if (winner == 1) {
                System.out.println("\n\n\t\t CONGRATULATIONS PLAYER 1. YOU ARE THE WINNER!");
                System.exit(0);
            }
            else if (winner == -1) {
                System.out.println("\n\n\t\t CONGRATULATIONS PLAYER 2. YOU ARE THE WINNER!");
                System.exit(0);
            }
            else {
                System.out.println("\n\n\t\t IT'S A TIE!");
                System.exit(0);
            }
        }

        playerPossibleMoves.clear();
    }




//----------------------------------------------------------------------------------------------------------------------------------------
//                                                             | MAIN | 
//----------------------------------------------------------------------------------------------------------------------------------------
    public static void main(String args[]) {
        System.out.println("\n\n\t\t | WELCOME TO REVERSI |");
        Scanner answer = new Scanner(System.in);        

//--------------------------------------------- MAX DEPTH ----------------------------------------------
        int DEFAULT_MAX_DEPTH = 8;
        boolean validInput = false;
        int maxDepth = DEFAULT_MAX_DEPTH;
        while (!validInput) { // Keep asking for input until a valid integer is entered
            System.out.print("\n\nPlease give the desirable max depth for your opponent's skills in reversi (chose 0 for the default depth): ");
            if (answer.hasNextInt()) {
                maxDepth = answer.nextInt();
                if (maxDepth == 0 || maxDepth < 0) {
                    maxDepth = DEFAULT_MAX_DEPTH;
                    validInput = true;
                }
                validInput = true;
            }
            else {
                System.out.println("\n! Invalid depth. Please try again.");
                answer.next();
            }
        }
        //System.out.println("\n~The max depth you chose is " + maxDepth);

//------------------------------------------- BOARD DIMENTION ------------------------------------------------
        int DEFAULT_DIMENTION = 8;
        validInput = false;
        int boardDimentions = DEFAULT_DIMENTION;
        while (!validInput) { // Keep asking for input until a valid integer is entered
            System.out.print("\n\nPlease give the board's dimentions (Integer) from 6x6 to 20x20 (chose anything else for the default dimentions): ");
            if (answer.hasNextInt()) {
                boardDimentions = answer.nextInt();
                if (boardDimentions < 6) {
                    boardDimentions = DEFAULT_DIMENTION;
                    validInput = true;
                }
                if (boardDimentions > 20) {
                    boardDimentions = 20;
                    validInput = true;
                }
                validInput = true;
            }
            else {
                System.out.println("\n! Invalid dimentions. Please try again.");
                answer.next();
            }
        }
        //System.out.println("\n~The board's dimentions you chose is " + boardDimentions);




//---------------------------------------- START GAME ---------------------------------------------------
        Board board = new Board(boardDimentions);
        Player pl1;
        Player pl2;
        int turn = 0;

        //------------------------------ CHOOSE THE PLAYERS -----------------------------
        System.out.print("\n\nDo you want to play with another player? (press 0 if YES - press any other key to play with the computer): ");

        if (answer.hasNextInt() && answer.nextInt() == 0) { //the user chose to play with another human being
            System.out.println("\nYou chose to play with a friend :)");
            pl1 = new Player(maxDepth, 1); //create player1
            pl2 = new Player(maxDepth, -1); //create player2
            while (!board.isTerminal()) {
                if (turn % 2 == 0) {
                    chooseAmove(pl1, maxDepth, board, boardDimentions, false, answer);
                    //System.out.println("\nOut of chooseAmove method for: " + pl1);
                    turn++;
                }
                else if (turn % 2 == 1) {
                    chooseAmove(pl2, maxDepth, board, boardDimentions, false, answer);
                    //System.out.println("\nOut of chooseAmove method for: " + pl2);                
                    turn++;
                }
            }
        }
            
        /* 
        else { //the user chose to play with CPU
            System.out.println("\nYou chose to play with a CPU :)");
            pl1 = new Player(maxDepth, 1); //create player1
            pl2 = new Player(maxDepth, -1); //create player2 - AI
        }
       
        while (!board.isTerminal()) {
            int turn = 0;
            if (turn % 2 == 0) {
                chooseAmove(pl1, maxDepth, board, boardDimentions, false, answer);
                System.out.println("\nOut of chooseAmove method for: " + pl1);
                turn++;
            }
            else if (turn % 2 == 1) {
                chooseAmove(pl2, maxDepth, board, boardDimentions, false, answer);
                System.out.println("\nOut of chooseAmove method for: " + pl2);                
                turn++;
            }
        }
        */
        answer.close();
    } //Main


} //class cmdMain