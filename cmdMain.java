import java.util.*;
public class cmdMain implements Config { 


    public static void chooseAmove(Player pl, int maxDepth, Board board, int boardDimentions, boolean validInput, Scanner answer, boolean AI) {
    //------------------------------------------- INITIALIZING GAME ------------------------------------------------ 
        boolean moveFound = false;
        int playerletter = pl.getPlayerLetter();
        int score1 = board.getPlayer1Score();
        int score2 = board.getPlayer2Score();
        System.out.println("---------------------------------------------------------");
        System.out.println("\nPlayer1 score = " + score1 + "\tPlayer2 score = " + score2);
        board.print();

    //-------------------------------------- FOR PLAYERS NOT AI ----------------------------------------
        if (!AI) {
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
                    System.out.print("\n~ ROW: ");
                    if (answer.hasNextInt()) {
                        int ans = answer.nextInt();
                        if (ans < boardDimentions && ans >= 0) {
                            chosenRow = ans;
                            validInput = true;
                        }
                        else {
                            System.out.print("\n! Invalid row. Please try again --> ");
                        }
                    }
                    else {
                        System.out.print("\n! Invalid row. Please try again --> ");
                        answer.next();
                    }
                }

                //---------------------------------- COLUMN -----------------------------------
                validInput = false;
                int chosenCol = 0;
                while (!validInput) {
                    System.out.print("\n~ COLUMN: ");
                    if (answer.hasNextInt()) {
                        int ans = answer.nextInt();
                        if (ans < boardDimentions && ans >= 0) {
                            chosenCol = ans;
                            validInput = true;
                        }
                        else {
                            System.out.print("\n! Invalid column. Please try again --> ");
                        }
                    }
                    else {
                        System.out.print("\n! Invalid column. Please try again --> ");
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
            playerPossibleMoves.clear();
        }// FOR PLAYERS NOT AI ^

    //------------------------------------------- FOR AI --------------------------------------------
        else if (AI) {
            ArrayList<Move> computerPossibleMoves = board.findPossibleMoves(playerletter);
            System.out.println(computerPossibleMoves);

            if (computerPossibleMoves.isEmpty()) {
                System.out.println("\nNo available moves for computer: TURN SKIPPED");
            }
            //---------------------------------- CHOOSING A MOVE ----------------------------------------
            else {
                Move computerMove = pl.MiniMax(board, maxDepth, playerletter);
                System.out.println("\n\n ! The computer placed a pawn at " + computerMove.toString());

            //---------------------------------- MAKING A MOVE -------------------------------------
                board.makeMove(computerMove, playerletter); 
            }
            
            computerPossibleMoves.clear();
        }// FOR ΑΙ ^


        //----------------------------------- FINDING THE WINNER --------------------------------------
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
            board.print();
        }  
    }




//----------------------------------------------------------------------------------------------------------------------------------------
//                                                             | MAIN | 
//----------------------------------------------------------------------------------------------------------------------------------------
    public static void main(String args[]) {
        System.out.println("\n\n\t\t | WELCOME TO REVERSI |");
        Scanner answer = new Scanner(System.in);        

//------------------------------------------- BOARD DIMENTION ------------------------------------------------
        boolean validInput = false;
        int boardDimentions = DEFAULT_DIM;
        while (!validInput) { // Keep asking for input until a valid integer is entered
            System.out.print("\n\nPlease give the board's dimentions (Integer) from 6x6 to 18x18 (chose anything else for the default dimentions): ");
            if (answer.hasNextInt()) {
                boardDimentions = answer.nextInt();
                if (boardDimentions < MIN_DIM || boardDimentions > MAX_DIM) {
                    boardDimentions = DEFAULT_DIM;
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

//--------------------------------------------- MAX DEPTH ----------------------------------------------
        validInput = false;
        int DEFAULT_MAX_DEPTH = 8;
        int maxDepth = DEFAULT_MAX_DEPTH;
        while (!validInput) { // Keep asking for input until a valid integer is entered
            System.out.print("\n\nPlease give the desirable max depth for your opponent's skills in reversi (from 2 to 8) (any other key for the default depth): ");
            if (answer.hasNextInt()) {
                maxDepth = answer.nextInt();
                if (maxDepth < 2 || maxDepth > 8) {
                    maxDepth = DEFAULT_DEPTH;
                    //System.out.println(maxDepth);
                    validInput = true;
                }
                /*
                else if (maxDepth > ((boardDimentions*boardDimentions) - 4)) { //the max depth of the tree made in MINIMAX depends on the case where the whole board if full on pawns.
                    maxDepth = (boardDimentions*boardDimentions) - 4;          //That can happen if the right moves are made and they can amount to the board's available spots at the start of the game --> boardDimentions^2 - 4
                    System.out.println(maxDepth);
                    validInput = true;
                }
                */
                validInput = true;
            }
            else {
                System.out.println("\n! Invalid depth. Please try again.");
                answer.next();
            }
        }
        //System.out.println("\n~The max depth you chose is " + maxDepth);


//---------------------------------------- START GAME ---------------------------------------------------
        Board board = new Board(boardDimentions);
        Player pl1 = new Player(maxDepth, 1); //create player1
        Player pl2 = new Player(maxDepth, -1); //create player2
        int turn = 0;
        boolean validChoice = false;

        //------------------------------ CHOOSE THE PLAYERS -----------------------------
        System.out.print("\n\nDo you want to play with another player? (press 0 if YES - press any other Integer to play with the computer): ");

        while (!validChoice) {
            if (answer.hasNextInt()) { 
                int ans = answer.nextInt();
                if (ans == 0) { //the user chose to play with another human being
                    System.out.println("\nYou chose to play with a friend :)");
                    validChoice = true;
                    while (!board.isTerminal()) {
                        if (turn % 2 == 0) {
                            chooseAmove(pl1, maxDepth, board, boardDimentions, false, answer, false);
                            turn++;
                        }
                        else if (turn % 2 == 1) {
                            chooseAmove(pl2, maxDepth, board, boardDimentions, false, answer, false);
                            turn++;
                        }
                    }
                }
                else if (ans != 0) { //the user chose to play against the computer
                    System.out.println("\nYou chose to play with the computer :)");
                    Player computer = pl2; //the computer is now player2 (the user is player 1)
                    validChoice = true;
                    while (!board.isTerminal()) {
                        if (turn % 2 == 0) {
                            chooseAmove(pl1, maxDepth, board, boardDimentions, false, answer, false);
                            turn++;
                        }
                        else if (turn % 2 == 1) {
                            chooseAmove(computer, maxDepth, board, boardDimentions, false, answer, true);
                            turn++;
                        }
                    }    
                }
            }
            System.out.print("\n ! Invalid choice: please choose an Integer (0 for player2, any other for computer) --> ");
            answer.next();
        }
        answer.close();
    } //Main


} //class cmdMain