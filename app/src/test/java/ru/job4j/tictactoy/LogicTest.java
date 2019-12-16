package ru.job4j.tictactoy;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class LogicTest {
    @Test
    public void whenVerticalFirstLineWon() {
        Logic logic = new Logic();
        logic.arrayOfChoices = new String[3][3];
        logic.mark(0, 0, "X");
        logic.mark(1, 0, "X");
        logic.mark(2, 0, "X");
        boolean result = logic.isWin(0);
        assertThat(result, is(true));
    }

    @Test
    public void whenVerticalSecondLineWon() {
        Logic logic = new Logic();
        logic.arrayOfChoices = new String[3][3];
        logic.mark(0, 1, "X");
        logic.mark(1, 1, "X");
        logic.mark(2, 1, "X");
        boolean result = logic.isWin(0);
        assertThat(result, is(true));
    }

    @Test
    public void whenVerticalThirdLineWon() {
        Logic logic = new Logic();
        logic.arrayOfChoices = new String[3][3];
        logic.mark(0, 2, "X");
        logic.mark(1, 2, "X");
        logic.mark(2, 2, "X");
        boolean result = logic.isWin(0);
        assertThat(result, is(true));
    }

    @Test
    public void whenHorizontalFirstLineWon() {
        Logic logic = new Logic();
        logic.arrayOfChoices = new String[3][3];
        logic.mark(0, 0, "X");
        logic.mark(0, 1, "X");
        logic.mark(0, 2, "X");
        boolean result = logic.isWin(0);
        assertThat(result, is(true));
    }

    @Test
    public void whenHorizontalSecondLineWon() {
        Logic logic = new Logic();
        logic.arrayOfChoices = new String[3][3];
        logic.mark(1, 0, "X");
        logic.mark(1, 1, "X");
        logic.mark(1, 2, "X");
        boolean result = logic.isWin(0);
        assertThat(result, is(true));
    }

    @Test
    public void whenHorizontalThirdLineWon() {
        Logic logic = new Logic();
        logic.arrayOfChoices = new String[3][3];
        logic.mark(2, 0, "X");
        logic.mark(2, 1, "X");
        logic.mark(2, 2, "X");
        boolean result = logic.isWin(0);
        assertThat(result, is(true));
    }

    @Test
    public void whenFromLeftToRightLineWon() {
        Logic logic = new Logic();
        logic.arrayOfChoices = new String[3][3];
        logic.mark(0, 0, "X");
        logic.mark(1, 1, "X");
        logic.mark(2, 2, "X");
        boolean result = logic.isWin(0);
        assertThat(result, is(true));
    }

    @Test
    public void whenFromRightToLeftLineWon() {
        Logic logic = new Logic();
        logic.arrayOfChoices = new String[3][3];
        logic.mark(0, 2, "X");
        logic.mark(1, 1, "X");
        logic.mark(2, 0, "X");
        boolean result = logic.isWin(0);
        assertThat(result, is(true));
    }

    @Test
    public void hasGapsWhenNoGaps() {
        Logic logic = new Logic();
        logic.arrayOfChoices = new String[3][3];
        logic.mark(0, 0, "X");
        logic.mark(0, 1, "X");
        logic.mark(0, 2, "X");
        logic.mark(1, 0, "X");
        logic.mark(1, 1, "X");
        logic.mark(1, 2, "X");
        logic.mark(2, 0, "X");
        logic.mark(2, 1, "X");
        logic.mark(2, 2, "X");
        boolean result = logic.hasGaps();
        assertThat(result, is(false));
    }

    public void hasGapsWhenThereAreGaps() {
        Logic logic = new Logic();
        logic.arrayOfChoices = new String[3][3];
        logic.mark(0, 0, "X");
        logic.mark(0, 1, "X");
        logic.mark(0, 2, "X");
        logic.mark(1, 0, "X");
        boolean result = logic.hasGaps();
        assertThat(result, is(true));
    }


}
