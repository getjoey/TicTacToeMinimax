package com.tictactoeminimax;

public class BoardView {

    public void printBoard(TicTacToeBoard board) {
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                Character c = board.getData()[j][i];
                if(c == ' ') {
                    c = '-';
                }
                System.out.print(c+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
