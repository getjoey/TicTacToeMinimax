package com.tictactoeminimax;

import java.awt.*;

public class Ai {

    private final char c;
    private final char otherC;
    private Point moveToDo;
    private final int max_depth = 6; //how many moves ahead to look

    public Ai(PlayerCharEnum playerCharEnum) {
        this.c = playerCharEnum.toString().charAt(0);
        this.otherC = (c == 'X') ? 'O' : 'X';
    }

    public void bestMove(TicTacToeBoard board) {
        minimax(board, 0, true);
        board.place(moveToDo.x, moveToDo.y, c);
        moveToDo = null;
    }

    public int minimax(TicTacToeBoard boardPos, int depth, boolean isMaximizingPlayer) {

        //evaluation
        if(boardPos.isGameComplete() || depth == max_depth) {
            if(boardPos.getWinner().isPresent()) {
                if(boardPos.getWinner().get() == c) {
                    return 1;
                }
                else {
                    return -1;
                }
            }
            return 0; //tie
        }

        //minmaxing
        if(isMaximizingPlayer) {
            int maxEval = -1000000;
            for(int i=0; i<3; i++) {
                for(int j=0; j<3; j++) {
                    if(boardPos.getData()[i][j] == ' ') {
                        boardPos.getData()[i][j] = c;
                        int score = minimax(boardPos, depth + 1, false);
                        boardPos.getData()[i][j] = ' ';
                        if(score > maxEval) {
                            maxEval = score;
                            if(depth == 0) {
                                moveToDo = new Point(i,j); //moveToDo is only set at begining, and changed if a depth=0 value is superior
                            }
                        }
                    }
                }
            }
            return maxEval;
        }
        else {
            int minEval = 1000000;

            for(int i=0; i<3; i++) {
                for(int j=0; j<3; j++) {
                    if(boardPos.getData()[i][j] == ' ') {
                        boardPos.getData()[i][j] = otherC;

                        int score = minimax(boardPos, depth + 1, true);
                        boardPos.getData()[i][j] = ' ';
                        if(score < minEval) {
                            minEval = score;
                            if(depth == 0) {
                                moveToDo = new Point(i,j);
                            }
                        }
                    }
                }
            }
            return minEval;
        }

    }
}
