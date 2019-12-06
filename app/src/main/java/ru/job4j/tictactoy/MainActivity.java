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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    SwitchCompat switchEnemy;
    boolean ai = true;
    Logic logic = new Logic();
    int[] cells = {R.id.btn0, R.id.btn1, R.id.btn2,
            R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6,
            R.id.btn7, R.id.btn8};
    static int checkedButton;
    int countOfCheck = 0;
    private static String LOG = "Log";

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
    }

    CompoundButton.OnCheckedChangeListener checked = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                ai = false;
                Toast.makeText(getApplicationContext(), "Your opponent is a human",
                        Toast.LENGTH_SHORT).show();
            } else {
                ai = true;
                Toast.makeText(getApplicationContext(), "Your opponent is a computer",
                        Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    public void onClick(View v) {
        Log.d(LOG, "ai: " + ai);
        if (ai == false) {
            for (int index = 0; index < cells.length; index++) {

                Button button = findViewById(v.getId());
                int buttonId = v.getId();
                if (buttonId == cells[index]) {
                    checkedButton = index;
                    Log.d(LOG, "checkedButton: " + checkedButton
                            + " checkArray: " + logic.checkArray());
                    countOfCheck++;
                    logic.playGame();
                    button.setText(logic.draw());
                    button.setEnabled(false);
                    messageToPlayer(checkedButton);
                }
            }
        } else if (ai) {
            int random = logic.randomNumberFrom0To9();
            for (int i = 0; i < cells.length; i++) {
                Log.d(LOG, " checkArray: " + logic.checkArray()
                        + " random = " + random);
                if (logic.getArrayOfChoice()[i] != 0) {
                    continue;
                } else {
                    if (i == random) {
                        Button button = findViewById(cells[i]);
                        checkedButton = i;
                        Log.d(LOG, "checkedButton: " + checkedButton
                                + " checkArray: " + logic.checkArray());
                        countOfCheck++;
                        logic.playGame();
                        button.setText(logic.draw());
                        button.setEnabled(false);
                        messageToPlayer(i);

                    }
                }
            }
        }
    }
    void messageToPlayer(int positionNumber) {
        if (logic.isGameOver(positionNumber) == true) {
            Toast.makeText(getApplicationContext(),
                    "Игра закончена! Победитель: игрок "
                            + logic.draw(), Toast.LENGTH_SHORT).show();
            logic.clear();
            clearButtonsText();
            countOfCheck = 0;
        } else if (countOfCheck == 9) {
            Toast.makeText(getApplicationContext(),
                    "Ничья", Toast.LENGTH_SHORT).show();
            logic.clear();
            clearButtonsText();
            countOfCheck = 0;
        }
    }

    public void clearButtonsText() {
        int[] buttonIDs = {R.id.btn0, R.id.btn1, R.id.btn2,
                R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6,
                R.id.btn7, R.id.btn8};

        for (int i = 0; i < buttonIDs.length; i++) {
            Button currentButton = findViewById(buttonIDs[i]);
            currentButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.alpha));
            currentButton.setText("");
            currentButton.setEnabled(true);
        }
    }
}





