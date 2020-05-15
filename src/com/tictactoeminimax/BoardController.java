package com.tictactoeminimax;

import java.util.Random;
import java.util.Scanner;

public class BoardController {

    private char turn;
    private Scanner scanner = new Scanner(System.in);
    private BoardView view = new BoardView();
    private Ai aiX = new Ai(PlayerCharEnum.X);
    private Ai aiO = new Ai(PlayerCharEnum.O);
    private Random ran = new Random();

    public void playGame() {
        if(ran.nextInt(2) == 0) {
            turn = 'X';
        }
        else {
            turn = 'O';
        }
        TicTacToeBoard board = new TicTacToeBoard();
        System.out.println("starting a game... starting with"+turn);

        while(!board.isGameComplete()) {
            playTurn(board);
            view.printBoard(board);
        }

        System.out.println("winner is : "+( board.getWinner().isPresent() ? board.getWinner().get() : "draw"));
    }

    public void playTurn(TicTacToeBoard board) {
        if(turn == 'X') {
            aiX.bestMove(board);
        }
        else {
            aiO.bestMove(board);
            //uncomment off to use player for O
//            String input = scanner.nextLine();
//            input.trim();
//            String[] args = input.split(",");
//            int x = Integer.parseInt(args[0]);
//            int y = Integer.parseInt(args[1]);
//            board.place(x, y, 'O');
        }
        this.turn = this.turn == 'X' ? 'O' : 'X'; //change turn
    }


}
