package ru.job4j.tictactoy;

import java.util.Arrays;
import java.util.Objects;

class Logic {
    private boolean isCurrentX;
    private Mark[][] board = new Mark[3][3];

    boolean currentMarkIsX() {
        if (!isCurrentX) {
            isCurrentX = true;
        } else {
            isCurrentX = false;
        }
        return isCurrentX;
    }

    public Mark[][] getBoard() {
        return board;
    }

    public void setBoard(Mark[][] board) {
        this.board = board;
    }

    void mark(int x, int y, Mark mark) {
        board[x][y] = mark;
    }

    boolean hasGaps() {
        return Arrays.stream(board).flatMap(Arrays::stream).anyMatch(Objects::isNull);
    }

    boolean isWin(Mark mark) {
        boolean win = false;
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != null && board[i][0].equals(board[i][1])
                    && board[i][1].equals(board[i][2]) && board[i][0].equals(mark)) {
                win = true;
                break;
            }
        }
        if (!win) {
            for (int i = 0; i < 3; i++) {
                if (board[0][i] != null && board[0][i].equals(board[1][i])
                        && board[1][i].equals(board[2][i]) && board[0][i].equals(mark)) {
                    win = true;
                    break;
                }
            }
        }
        if (!win) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[0][0] != null && board[0][0] == board[1][1]
                            && board[0][0] == board[2][2] && board[0][0].equals(mark)) {
                        win = true;
                    }
                }
            }
        }
        if (!win) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[0][2] != null && board[0][2] == board[1][1]
                            && board[0][2] == board[2][0] && board[0][2].equals(mark)) {
                        win = true;
                    }
                }
            }
        }
        return win;
    }

    enum Mark {
        X, O,
    }

    void clear() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = null;
            }
        }
    }

    int randomNumberFrom0To8() {
        return (int) (Math.random() * 9);
    }

    String checkArray() {
        String s = "";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                s += board[i][j];
            }
        }
        return s;
    }
}