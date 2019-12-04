package ru.job4j.tictactoy;

class Logic {
    private static int[] arrayOfChoice = {
            0, 0, 0,
            0, 0, 0,
            0, 0, 0
    };
    private static boolean isCurrentX;

    static void playGame() {
        arrayOfChoice[MainActivity.checkedButton] = currentMark() ? 1 : 2;
    }

    private static boolean currentMark() {
        if (!isCurrentX) {
            isCurrentX = true;
        } else {
            isCurrentX = false;
        }
        return isCurrentX;
    }

    static String draw() {
        String s = "";
        if (arrayOfChoice[MainActivity.checkedButton] == 1) {
            s = "X";
        } else if (arrayOfChoice[MainActivity.checkedButton] == 2) {
            s = "O";
        }
        return s;
    }

    static boolean isGameOver(int positionNumber) {

        int row = positionNumber - positionNumber % 3;
        int column = positionNumber % 3;

        if (arrayOfChoice[row] == arrayOfChoice[row + 1])
            if (arrayOfChoice[row] == arrayOfChoice[row + 2]) {
                return true;
            }

        if (arrayOfChoice[column] == arrayOfChoice[column + 3])
            if (arrayOfChoice[column] == arrayOfChoice[column + 6]) {
                return true;
            }

        if (positionNumber % 2 != 0) {
            return false;
        }

        if (positionNumber % 4 == 0) {
            if (arrayOfChoice[0] == arrayOfChoice[4] &&
                    arrayOfChoice[0] == arrayOfChoice[8]) {
                return true;
            }
            if (positionNumber != 4) {
                return false;
            }
        }
        return arrayOfChoice[2] == arrayOfChoice[4] &&
                arrayOfChoice[2] == arrayOfChoice[6];
    }

    static String checkArray() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < arrayOfChoice.length - 1; i++) {
            s.append(arrayOfChoice[i]);
        }
        return s.toString();
    }

    static void clear() {
        for (int i = 0; i < arrayOfChoice.length; i++) {
            arrayOfChoice[i] = 0;
        }
    }

    static int randomNumberFrom0To9(int number) {
        int first = 0;
        return first + (int) (Math.random() * number);
    }
}
