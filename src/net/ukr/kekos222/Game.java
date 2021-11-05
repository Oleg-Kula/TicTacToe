package net.ukr.kekos222;

import java.util.Random;
import java.util.Scanner;

class Game {

    private static final Figure[][] board = new Figure[3][3];
    private static final Player player1 = new Player(1, Figure.X);
    private static final Player player2 = new Player(2, Figure.O);
    private static final Scanner scanner = new Scanner(System.in);
    private static Random random;

    //Assigns character 'O' or 'X' from Figure's enum to array's values
    //with indexes 'width' and 'height'
    private static boolean placeFigure(int width, int height, Figure f) {
        if (board[width - 1][height - 1] == null) {
            board[width - 1][height - 1] = f;
            return true;
        }
        return false;
    }

    //Checks combinations for win the game
    private static boolean checkWin() {
        for (int i = 0; i <= 2; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != null ||
                    board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != null)
                return true;
        }

        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != null)
            return true;

        if (board[2][0] == board[1][1] && board[1][1] == board[0][2] && board[2][0] != null)
            return true;


        return false;
    }

    //Checks if the 2d array is completely filled
    private static boolean checkTie() {
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                if (board[i][j] == null)
                    return false;
            }
        }
        return true;
    }

    //Call placeFigure method with random values between 1 and 3
    private static void cpuRandomLogic(Player cpu) {
        int width;
        int height;

        while (true) {
            width = random.nextInt(4 - 1) + 1;
            height = random.nextInt(4 - 1) + 1;
            if (placeFigure(width, height, cpu.getFigure())) {
                break;
            }
        }
    }

    //Checks is user's input correct
    private static boolean checkValue(String s) {
        int value;

        if (s.length() != 1)
            return false;

        if (!Character.isDigit(s.charAt(0)))
            return false;

        value = Integer.valueOf(s);

        if (value > 3 || value < 1)
            return false;

        return true;
    }

    //Outputs to the console 2d array
    private static void drawBoard() {
        for (int i = 0; i <= 2; i++) {
            System.out.println();
            for (int j = 0; j <= 2; j++) {
                if (board[i][j] != null) {
                    System.out.print(board[i][j].getDisplay() + " ");
                } else {
                    System.out.print("- ");
                }
            }
        }
    }

    //Starting method of the game. Setting parameters in "One Player" mode.
    //Closing Scanner after game end.
    static void start() {
        String line;
        int answer;

        while (true) {
            System.out.println("\nTIC TAC TOE:" +
                    "\n1. One player" +
                    "\n2. Two players" +
                    "\n3. Exit");
            line = scanner.nextLine();
            if (!checkValue(line)) {
                System.out.println("Wrong input. Try again.");
                continue;
            }
            answer = Integer.valueOf(line);
            break;
        }

        switch (answer) {
            case 1:
                random = new Random();
                player2.setCPU(true);
                System.out.println("One player game");
                runGameProcess();
                break;

            case 2:
                System.out.println("Two players game");
                runGameProcess();
                break;

            case 3:

                break;
        }
        scanner.close();
    }

    //Main loop of the game process
    private static void runGameProcess() {
        Player activePlayer = player1;
        int width;
        int height;
        String line;

        while (true) {
            drawBoard();
            System.out.println("\n\nPlayer's " + activePlayer.getNumber() + " turn");

            //Asking for input if player is not a cpu
            if (!activePlayer.isCPU()) {
                System.out.println("Enter width (from 1 to 3): ");
                line = scanner.nextLine();
                if (!checkValue(line)) {
                    System.out.println("Wrong coordinates. Try again.");
                    continue;
                }
                height = Integer.valueOf(line);

                System.out.println("Enter height (from 1 to 3): ");
                line = scanner.nextLine();
                if (!checkValue(line)) {
                    System.out.println("Wrong coordinates. Try again.");
                    continue;
                }
                width = Integer.valueOf(line);

                if (!placeFigure(width, height, activePlayer.getFigure())) {
                    System.out.println("This coordinates are already used. Try again.");
                    continue;
                }
                //If player is CPU call method with CPU move
            } else {
                cpuRandomLogic(activePlayer);
            }

            //Checks for wiener and tie
            if (checkWin()) {
                drawBoard();
                System.out.println("\nPlayer " + activePlayer.getNumber() + " wins!");
                break;
            }

            if (checkTie()) {
                drawBoard();
                System.out.println("\nTie!");
                scanner.close();
                break;
            }

            //Switch between players
            if (activePlayer == player1) {
                activePlayer = player2;
            } else {
                activePlayer = player1;
            }

        }

    }

}