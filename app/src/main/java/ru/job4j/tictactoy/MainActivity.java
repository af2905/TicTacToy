package ru.job4j.tictactoy;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.util.Pair;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    SwitchCompat switchEnemy;
    boolean ai;
    Logic logic = new Logic();
    int[] cells = {R.id.btn0, R.id.btn1, R.id.btn2,
            R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6,
            R.id.btn7, R.id.btn8};
    private static final String TAG = "Log";
    private Map<Integer, Pair<Integer, Integer>> marks = new HashMap<>();
    private static final String BUTTON_TEXT = "buttonText";
    private static final String BUTTON_ENABLED = "buttonEnabled";
    private static final String AI = "ai";
    private static final String BOARD = "board";

    {
        marks.put(R.id.btn0, new Pair<>(0, 0));
        marks.put(R.id.btn1, new Pair<>(0, 1));
        marks.put(R.id.btn2, new Pair<>(0, 2));
        marks.put(R.id.btn3, new Pair<>(1, 0));
        marks.put(R.id.btn4, new Pair<>(1, 1));
        marks.put(R.id.btn5, new Pair<>(1, 2));
        marks.put(R.id.btn6, new Pair<>(2, 0));
        marks.put(R.id.btn7, new Pair<>(2, 1));
        marks.put(R.id.btn8, new Pair<>(2, 2));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        switchEnemy = findViewById(R.id.switchEnemy);
        if (switchEnemy != null) {
            switchEnemy.setOnCheckedChangeListener(checked);
        }
        if (savedInstanceState != null) {
            ai = savedInstanceState.getBoolean(AI);
            String[] buttonsText = savedInstanceState.getStringArray(BUTTON_TEXT);
            boolean[] buttonsEnabled = savedInstanceState.getBooleanArray(BUTTON_ENABLED);
            for (int i = 0; i < cells.length; i++) {
                Button button = findViewById(cells[i]);
                button.setText(buttonsText[i]);
                button.setEnabled(buttonsEnabled[i]);
            }
            Logic.Mark[][] board = (Logic.Mark[][]) savedInstanceState.getSerializable(BOARD);
            logic.setBoard(board);
        }
    }

    CompoundButton.OnCheckedChangeListener checked = (buttonView, isChecked) -> {
        if (isChecked) {
            ai = true;
            Toast.makeText(getApplicationContext(), "Your opponent is a computer",
                    Toast.LENGTH_SHORT).show();
        } else {
            ai = false;
            Toast.makeText(getApplicationContext(), "Your opponent is a human",
                    Toast.LENGTH_SHORT).show();
        }
    };

    public void clearButtonsText() {
        for (int i = 0; i < cells.length; i++) {
            Button currentButton = findViewById(cells[i]);
            currentButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.alpha));
            currentButton.setText(" ");
            currentButton.setEnabled(true);
        }
    }

    public void answer(View v) {
        Button button;
        Logic.Mark currentMark;
        int id = v.getId();
        if (!ai) {
           button = findViewById(id);

        } else {
            id = cells[randomChoiceFromAI()];
            button = findViewById(id);

        }
        Log.d(TAG, "id: " + id);
        if (logic.currentMarkIsX()) {
            currentMark = Logic.Mark.X;
            button.setText("X");
        } else {
            currentMark = Logic.Mark.O;
            button.setText("O");
        }
        button.setEnabled(false);
        logic.mark(marks.get(id).first, marks.get(id).second, currentMark);
        Log.d(TAG, "button: " + button.getText().toString());
        Log.d(TAG, "checkArray(): " + logic.checkArray());
        if (logic.isWin(currentMark)) {
            Toast.makeText(getApplicationContext(),
                    "Congratulations! Winner is " + currentMark, Toast.LENGTH_SHORT).show();
            logic.clear();
            Log.d(TAG, "checkArray(): " + logic.checkArray());
            clearButtonsText();
            resetCheck();
        }
        if (!logic.hasGaps()) {
            logic.clear();
            clearButtonsText();
            Toast.makeText(getApplicationContext(),
                    "Oh! Friendship won!", Toast.LENGTH_SHORT).show();
            resetCheck();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(AI, ai);
        String[] buttonsText = new String[9];
        boolean[] buttonEnabled = new boolean[9];
        for (int i = 0; i < cells.length; i++) {
            Button button = findViewById(cells[i]);
            buttonsText[i] = button.getText().toString();
            if (button.isEnabled()) {
                buttonEnabled[i] = button.isEnabled();
            }
        }
        Logic.Mark[][] board = new Logic.Mark[3][3];
        for (int i = 0; i < logic.getBoard().length; i++) {
            for (int j = 0; j < logic.getBoard()[i].length; j++) {
                board[i][j] = logic.getBoard()[i][j];
            }
        }
        outState.putSerializable(BOARD, board);
        outState.putStringArray(BUTTON_TEXT, buttonsText);
        outState.putBooleanArray(BUTTON_ENABLED, buttonEnabled);
        super.onSaveInstanceState(outState);
    }

    public int randomChoiceFromAI() {
        int number;
        for (; ; ) {
            number = logic.randomNumberFrom0To8();
            Button button = findViewById(cells[number]);
            if (button.getText().equals(" ")) {
                break;
            }
        }
        return number;
    }

    void resetCheck (){
        switchEnemy.setChecked(false);
    }
}










