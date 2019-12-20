package ru.job4j.tictactoy;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class LogicTest {
    @Test
    public void whenVerticalFirstLineWon() {
        Logic logic = new Logic();
        Logic.Mark[][] testBoard = logic.getBoard();
        logic.setBoard(testBoard);
        logic.mark(0, 0, Logic.Mark.X);
        logic.mark(1, 0, Logic.Mark.O);
        logic.mark(2, 0, Logic.Mark.X);
        boolean result = logic.isWin(Logic.Mark.O);
        assertThat(result, is(false));
    }

    @Test
    public void whenVerticalSecondLineWon() {
        Logic logic = new Logic();
        Logic.Mark[][] testBoard = logic.getBoard();
        logic.setBoard(testBoard);
        logic.mark(0, 1, Logic.Mark.X);
        logic.mark(1, 1, Logic.Mark.O);
        logic.mark(2, 1, Logic.Mark.X);
        boolean result = logic.isWin(Logic.Mark.O);
        assertThat(result, is(false));
    }

    @Test
    public void whenVerticalThirdLineWon() {
        Logic logic = new Logic();
        Logic.Mark[][] testBoard = logic.getBoard();
        logic.setBoard(testBoard);
        logic.mark(0, 2, Logic.Mark.X);
        logic.mark(1, 2, Logic.Mark.O);
        logic.mark(2, 2, Logic.Mark.X);
        boolean result = logic.isWin(Logic.Mark.X);
        assertThat(result, is(false));
    }

    @Test
    public void whenHorizontalFirstLineWon() {
        Logic logic = new Logic();
        Logic.Mark[][] testBoard = logic.getBoard();
        logic.setBoard(testBoard);
        logic.mark(0, 0, Logic.Mark.X);
        logic.mark(0, 1, Logic.Mark.O);
        logic.mark(0, 2, Logic.Mark.X);
        boolean result = logic.isWin(Logic.Mark.X);
        assertThat(result, is(false));
    }

    @Test
    public void whenHorizontalSecondLineWon() {
        Logic logic = new Logic();
        Logic.Mark[][] testBoard = logic.getBoard();
        logic.setBoard(testBoard);
        logic.mark(1, 0, Logic.Mark.X);
        logic.mark(1, 1, Logic.Mark.O);
        logic.mark(1, 2, Logic.Mark.X);
        boolean result = logic.isWin(Logic.Mark.O);
        assertThat(result, is(false));
    }

    @Test
    public void whenHorizontalThirdLineWon() {
        Logic logic = new Logic();
        Logic.Mark[][] testBoard = logic.getBoard();
        logic.setBoard(testBoard);
        logic.mark(2, 0, Logic.Mark.X);
        logic.mark(2, 1, Logic.Mark.O);
        logic.mark(2, 2, Logic.Mark.X);
        boolean result = logic.isWin(Logic.Mark.X);
        assertThat(result, is(false));
    }

    @Test
    public void whenFromLeftToRightLineWon() {
        Logic logic = new Logic();
        Logic.Mark[][] testBoard = logic.getBoard();
        logic.setBoard(testBoard);
        logic.mark(0, 0, Logic.Mark.X);
        logic.mark(1, 1, Logic.Mark.O);
        logic.mark(2, 2, Logic.Mark.X);
        boolean result = logic.isWin(Logic.Mark.O);
        assertThat(result, is(false));
    }

    @Test
    public void whenFromRightToLeftLineWon() {
        Logic logic = new Logic();
        Logic.Mark[][] testBoard = logic.getBoard();
        logic.setBoard(testBoard);
        logic.mark(0, 2, Logic.Mark.X);
        logic.mark(1, 1, Logic.Mark.O);
        logic.mark(2, 0, Logic.Mark.X);
        boolean result = logic.isWin(Logic.Mark.O);
        assertThat(result, is(false));
    }

    @Test
    public void hasGapsWhenNoGaps() {
        Logic logic = new Logic();
        Logic.Mark[][] testBoard = logic.getBoard();
        logic.setBoard(testBoard);
        logic.mark(0, 0, Logic.Mark.X);
        logic.mark(0, 1, Logic.Mark.X);
        logic.mark(0, 2, Logic.Mark.X);
        logic.mark(1, 0, Logic.Mark.X);
        logic.mark(1, 1, Logic.Mark.X);
        logic.mark(1, 2, Logic.Mark.X);
        logic.mark(2, 0, Logic.Mark.X);
        logic.mark(2, 1, Logic.Mark.X);
        logic.mark(2, 2, Logic.Mark.X);
        boolean result = logic.hasGaps();
        assertThat(result, is(false));
    }

    public void hasGapsWhenThereAreGaps() {
        Logic logic = new Logic();
        Logic.Mark[][] testBoard = logic.getBoard();
        logic.setBoard(testBoard);
        logic.mark(0, 0, Logic.Mark.X);
        logic.mark(0, 1, Logic.Mark.X);
        logic.mark(0, 2, Logic.Mark.X);
        logic.mark(1, 0, Logic.Mark.X);
        boolean result = logic.hasGaps();
        assertThat(result, is(true));
    }
}
