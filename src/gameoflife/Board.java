package gameoflife;

import java.util.Random;

/** Created by Giuseppe on 25/09/2015. */
public class Board {

    private int[][] board;

    public Board(int size) {
        board = new int[size][size];
    }

    public void setField(int x, int y, int value) {
        board[x][y] = value;
    }

    public int getField(int x, int y) {
        return board[x][y];
    }

    public int getSize() {
        return board.length;
    }

    public void initBoard(int[][] newBoard) {
        board = newBoard;
    }

    public void initBoard(double density) {
        Random random = new Random();
        for(int x=0; x<board.length;x++) {
            for(int y=0; y<board.length;y++) {
                if(random.nextDouble()>density) {
                    board[x][y] = 1;
                }
            }
        }
    }

    public void nextPopulation() {
        int[][] newBoard = new int[board.length][board.length];
        for(int x=0; x<board.length;x++) {
            for(int y=0; y<board.length;y++) {
                newBoard[x][y] = getField(x, y); //Copy value into new board
                checkBoard(x, y, newBoard);
            }
        }
        board = newBoard;
    }

    int[] indexX = {-1, 0, 1 ,-1, 1 ,-1, 0, 1};
    int[] indexY = {1, 1, 1, 0, 0, -1, -1, -1};
    public void checkBoard(int x, int y, int[][] newBoard) {

        int fieldValue = board[x][y];

        int neighbours = 0;
        for(int i=0; i<8; i++) {
            if(x+indexX[i]>=0 && y+indexY[i]>=0 && x+indexX[i]<board.length && y+indexY[i]<board.length) {
                neighbours = neighbours + getField(x+ indexX[i], y+indexY[i]);
            }
        }

        if(fieldValue==0 && neighbours==3) {
            //setField(x, y, 1); // Reborn with three alive neighbours
            newBoard[x][y] = 1;
            return;
        }

        if(fieldValue==1 && neighbours<2) {
            //setField(x, y, 0); //Less than two alive neightours die
            newBoard[x][y] = 0;
            return;
        }

        if(fieldValue==1 && neighbours>3) {
            //setField(x, y, 0); //Die if more than three alive neighbours
            newBoard[x][y] = 0;
        }

    }

}
