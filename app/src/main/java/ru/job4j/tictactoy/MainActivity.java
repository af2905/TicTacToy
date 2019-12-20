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

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    SwitchCompat switchEnemy;
    boolean ai = true;
    Logic logic = new Logic();
    int[] cells = {R.id.btn0, R.id.btn1, R.id.btn2,
            R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6,
            R.id.btn7, R.id.btn8};
    private static String TAG = "Log";
    private Map<Integer, Pair<Integer, Integer>> marks = new HashMap<>();

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
        for (int id : cells) {
            findViewById(id).setOnClickListener(this);
        }
        switchEnemy = findViewById(R.id.switchEnemy);
        if (switchEnemy != null) {
            switchEnemy.setOnCheckedChangeListener(checked);
        }

        if (savedInstanceState != null) {
            ai = savedInstanceState.getBoolean("ai");
            cells = savedInstanceState.getIntArray("cells");
            marks = (Map<Integer, Pair<Integer, Integer>>) savedInstanceState
                    .getSerializable("marks");

            for (int i = 0; i < cells.length; i++) {
                Button button = findViewById(cells[i]);
                String text = savedInstanceState.getString("buttonsText");
                button.setText(text);
            }
        }
    }

    CompoundButton.OnCheckedChangeListener checked = (buttonView, isChecked) -> {
        if (isChecked) {
            ai = false;
            Toast.makeText(getApplicationContext(), "Your opponent is a human",
                    Toast.LENGTH_SHORT).show();
        } else {
            ai = true;
            Toast.makeText(getApplicationContext(), "Your opponent is a computer",
                    Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void onClick(View v) {
        Button button;
        if (ai) {
            button = randomChoiceFromAI();
        } else button = findViewById(v.getId());

        for (Map.Entry<Integer, Pair<Integer, Integer>> entry : marks.entrySet()) {
            Logic.Mark currentMark;
            if (entry.getKey() == v.getId()) {
                if (logic.currentMarkIsX()) {
                    currentMark = Logic.Mark.X;
                    button.setText("X");
                } else {
                    currentMark = Logic.Mark.O;
                    button.setText("O");
                }
                button.setEnabled(false);
                Log.d(TAG, "button.getText() = " + button.getText());
                logic.mark(entry.getValue().first, entry.getValue().second, currentMark);
                Log.d(TAG, "checkArray(): " + logic.checkArray() + " currentMark: " + currentMark);
                if (logic.isWin(currentMark)) {
                    Toast.makeText(getApplicationContext(),
                            "Congratulations! Winner is " + currentMark, Toast.LENGTH_SHORT).show();
                    logic.clear();
                    Log.d(TAG, "checkArray(): " + logic.checkArray());
                    clearButtonsText();
                }
                if (!logic.hasGaps()) {
                    logic.clear();
                    clearButtonsText();
                    Toast.makeText(getApplicationContext(),
                            "Oh! Friendship won!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void clearButtonsText() {
        for (int i = 0; i < cells.length; i++) {
            Button currentButton = findViewById(cells[i]);
            currentButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.alpha));
            currentButton.setText("");
            currentButton.setEnabled(true);
        }
    }

    public Button randomChoiceFromAI() {
        return findViewById(cells[logic.randomNumberFrom0To8()]);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean("ai", ai);
        outState.putIntArray("cells", cells);
        outState.putSerializable("marks", (Serializable) marks);

        for (int i = 0; i < cells.length; i++) {
            Button buttonText = findViewById(cells[i]);
            outState.putString("buttonText", buttonText.getText().toString());
        }
        super.onSaveInstanceState(outState);
    }
}










