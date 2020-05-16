package com.tictactoeminimax;


import java.util.Optional;

public class TicTacToeBoard {
    
    private Character[][] data = new Character[3][3];

    public TicTacToeBoard() {
        clearBoard();
    }

    public void place(int i, int j, char c) {
        data[i][j] = c;
    }

    public void clearBoard() {
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                data[i][j] = ' ';
            }
        }

        data[0][0] = 'X';
        data[1][1] = 'O';
        data[0][2] = 'X';
        data[0][1] = 'O';
        data[1][0] = 'X';
        data[2][0] = 'O';
    }

    public Character[][] getData() {
        return data;
    }

    public boolean isGameComplete() {
        //if all pieces are not null... and no winner = draw.
        boolean isThereEmptyPiece = false;
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                if(data[j][i] == ' ') {
                    isThereEmptyPiece = true;
                }
            }
        }

        if(getWinner().isPresent()) {
            return true;
        }

        return !isThereEmptyPiece;
    }

    public Optional<Character> getWinner() {

        Optional<Character> winner = Optional.empty();

        //hori
        winner = winner.isPresent() ? winner : checkLine(data[0][0], data[1][0], data[2][0]);
        winner = winner.isPresent() ? winner : checkLine(data[0][1], data[1][1], data[2][1]);
        winner = winner.isPresent() ? winner : checkLine(data[0][2], data[1][2], data[2][2]);

        //vert
        winner = winner.isPresent() ? winner : checkLine(data[0][0], data[0][1], data[0][2]);
        winner = winner.isPresent() ? winner : checkLine(data[1][0], data[1][1], data[1][2]);
        winner = winner.isPresent() ? winner : checkLine(data[2][0], data[2][1], data[2][2]);

        //diag
        winner = winner.isPresent() ? winner : checkLine(data[0][0], data[1][1], data[2][2]);
        winner = winner.isPresent() ? winner : checkLine(data[0][2], data[1][1], data[2][0]);

        return winner;
    }

    public Optional<Character> checkLine(char p1, char p2, char p3) {

        if(p1 == p2 && p1 == p3 && p1 != ' ') {
            return Optional.of(p1);
        }

        return Optional.empty();
    }
    
}
