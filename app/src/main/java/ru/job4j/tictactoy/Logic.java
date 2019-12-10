package ru.job4j.tictactoy;

class Logic {
    Character[][] arrayOfChoices = new Character[3][3];
    final Character MARK_X = 'X';
    final Character MARK_O = 'O';


    void mark(int x, int y, Character z) {
        if (z.equals(MARK_X)) {
            arrayOfChoices[x][y] = MARK_X;
        } else if (z.equals(MARK_O)) {
            arrayOfChoices[x][y] = MARK_O;
        }
    }

    boolean hasGaps() {
        boolean hasGapsInArray = true;
        for (int i = 0; i < arrayOfChoices.length; i++) {
            for (int j = 0; j < arrayOfChoices[i].length; j++) {
                if (arrayOfChoices[i][j] != null) {
                    hasGapsInArray = false;
                }
            }
        }
        return hasGapsInArray;
    }

    boolean isWin(int player) {
        boolean win = false;
        for (int i = 0; i < 3; i++) {
            if (arrayOfChoices[i][0] != null && arrayOfChoices[i][0].equals(arrayOfChoices[i][1])
                    && arrayOfChoices[i][1].equals(arrayOfChoices[i][2])) {
                win = true;
                break;
            }
        }
        if (win == false) {
            for (int i = 0; i < 3; i++) {
                if (arrayOfChoices[0][i] != null && arrayOfChoices[0][i].equals(arrayOfChoices[1][i])
                        && arrayOfChoices[1][i].equals(arrayOfChoices[2][i])) {
                    win = true;
                    break;
                }
            }
        }
        if (win == false) {
            for (int i = 0; i < arrayOfChoices.length; i++) {
                for (int j = 0; j < arrayOfChoices[i].length; j++) {
                    if (i == j) {
                        win = true;
                    }
                }
            }
        }
        if (win == false) {
            for (int i = 0; i < arrayOfChoices.length; i++) {
                for (int j = 0; j < arrayOfChoices[i].length; j++) {
                    if (i == 2 - j) {
                        win = true;
                    }
                }
            }
        }
        return win;
    }
}

