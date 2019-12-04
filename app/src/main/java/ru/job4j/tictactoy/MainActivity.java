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

public class MainActivity extends AppCompatActivity {
    SwitchCompat switchEnemy;
    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8;
    static int checkedButton;
    int countOfCheck = 0;
    private static String LOG = "Log";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);

        btn0.setOnClickListener(clickListener);
        btn1.setOnClickListener(clickListener);
        btn2.setOnClickListener(clickListener);
        btn3.setOnClickListener(clickListener);
        btn4.setOnClickListener(clickListener);
        btn5.setOnClickListener(clickListener);
        btn6.setOnClickListener(clickListener);
        btn7.setOnClickListener(clickListener);
        btn8.setOnClickListener(clickListener);

        switchEnemy = findViewById(R.id.switchEnemy);
        if (switchEnemy != null) {
            switchEnemy.setOnCheckedChangeListener(checked);
        }
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn0:
                    checkedButton = 0;
                    countOfCheck++;
                    Log.d(LOG, "countOfCheck = " + countOfCheck + " checkArray(): "
                            + Logic.checkArray() + " " + Logic.isGameOver(0));
                    Logic.playGame();
                    btn0.setText(Logic.draw());
                    btn0.setEnabled(false);
                    messageToPlayer(0);
                    break;

                case R.id.btn1:
                    checkedButton = 1;
                    countOfCheck++;
                    Log.d(LOG, "countOfCheck = " + countOfCheck + " checkArray(): "
                            + Logic.checkArray() + " " + Logic.isGameOver(1));
                    Logic.playGame();
                    btn1.setText(Logic.draw());
                    btn1.setEnabled(false);
                    messageToPlayer(1);
                    break;

                case R.id.btn2:
                    checkedButton = 2;
                    countOfCheck++;
                    Log.d(LOG, "countOfCheck = " + countOfCheck + " checkArray(): "
                            + Logic.checkArray() + " " + Logic.isGameOver(2));
                    Logic.playGame();
                    btn2.setText(Logic.draw());
                    btn2.setEnabled(false);
                    messageToPlayer(2);
                    break;

                case R.id.btn3:
                    checkedButton = 3;
                    countOfCheck++;
                    Log.d(LOG, "countOfCheck = " + countOfCheck + " checkArray(): "
                            + Logic.checkArray() + " " + Logic.isGameOver(3));
                    Logic.playGame();
                    btn3.setText(Logic.draw());
                    btn3.setEnabled(false);
                    messageToPlayer(3);
                    break;

                case R.id.btn4:
                    checkedButton = 4;
                    countOfCheck++;
                    Log.d(LOG, "countOfCheck = " + countOfCheck + " checkArray(): "
                            + Logic.checkArray() + " " + Logic.isGameOver(4));
                    Logic.playGame();
                    btn4.setText(Logic.draw());
                    btn4.setEnabled(false);
                    messageToPlayer(4);
                    break;

                case R.id.btn5:
                    checkedButton = 5;
                    countOfCheck++;
                    Log.d(LOG, "countOfCheck = " + countOfCheck + " checkArray(): "
                            + Logic.checkArray() + " " + Logic.isGameOver(5));
                    Logic.playGame();
                    btn5.setText(Logic.draw());
                    btn5.setEnabled(false);
                    messageToPlayer(5);
                    break;

                case R.id.btn6:
                    checkedButton = 6;
                    countOfCheck++;
                    Log.d(LOG, "countOfCheck = " + countOfCheck + " checkArray(): "
                            + Logic.checkArray() + " " + Logic.isGameOver(6));
                    Logic.playGame();
                    btn6.setText(Logic.draw());
                    btn6.setEnabled(false);
                    messageToPlayer(6);
                    break;

                case R.id.btn7:
                    checkedButton = 7;
                    countOfCheck++;
                    Log.d(LOG, "countOfCheck = " + countOfCheck + " checkArray(): "
                            + Logic.checkArray() + " " + Logic.isGameOver(7));
                    Logic.playGame();
                    btn7.setText(Logic.draw());
                    btn7.setEnabled(false);
                    messageToPlayer(7);
                    break;

                case R.id.btn8:
                    checkedButton = 8;
                    countOfCheck++;
                    Log.d(LOG, "countOfCheck = " + countOfCheck + " checkArray(): "
                            + Logic.checkArray() + " " + Logic.isGameOver(8));
                    Logic.playGame();
                    btn8.setText(Logic.draw());
                    btn8.setEnabled(false);
                    messageToPlayer(8);
                    break;
            }
        }
    };

    CompoundButton.OnCheckedChangeListener checked = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                Toast.makeText(getApplicationContext(), "Your opponent is a human",
                        Toast.LENGTH_SHORT).show();
            } else Toast.makeText(getApplicationContext(), "Your opponent is a computer",
                    Toast.LENGTH_SHORT).show();
        }
    };

    void messageToPlayer(int positionNumber) {
        if (Logic.isGameOver(positionNumber) == true) {
            Toast.makeText(getApplicationContext(),
                    "Игра закончена! Победитель: игрок "
                            + Logic.draw(), Toast.LENGTH_SHORT).show();
            Logic.clear();
            clearButtonsText();
            countOfCheck = 0;
        } else if (countOfCheck == 9) {
            Toast.makeText(getApplicationContext(),
                    "Ничья", Toast.LENGTH_SHORT).show();
            Logic.clear();
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

    public Button randomButtonFromComputer() {
        Button currentButton = null;
        int[] buttonIDs = {R.id.btn0, R.id.btn1, R.id.btn2,
                R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6,
                R.id.btn7, R.id.btn8};

        for (int i = 0; i < buttonIDs.length; i++) {
            if (buttonIDs[i] == Logic.randomNumberFrom0To9(checkedButton)) {
                currentButton = findViewById(buttonIDs[i]);
            }
        }
        return currentButton;
    }

    public int randomButtonFromComputerID() {
        int id = 0;
        int[] buttonIDs = {R.id.btn0, R.id.btn1, R.id.btn2,
                R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6,
                R.id.btn7, R.id.btn8};

        for (int i = 0; i < buttonIDs.length; i++) {
            if (buttonIDs[i] == Logic.randomNumberFrom0To9(checkedButton)) {
                id = buttonIDs[i];
            }
        }
        return id;
    }
}





