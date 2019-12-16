package ru.job4j.tictactoy;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.util.Pair;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    SwitchCompat switchEnemy;
    boolean ai = true;
    Logic logic = new Logic();
    int[] cells = {R.id.btn0, R.id.btn1, R.id.btn2,
            R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6,
            R.id.btn7, R.id.btn8};
    private static String LOG = "Log";
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
        Button button = findViewById(v.getId());
        for (Map.Entry<Integer, Pair<Integer, Integer>> entry : marks.entrySet()) {
            if (entry.getKey() == v.getId()) {
                Log.d(LOG, "entry.getKey() = " + entry.getKey() + " v.getId() = " + v.getId());
                String tempForCurrentMark = "";
                if (logic.currentMark()) {
                    tempForCurrentMark = logic.MARK_X;
                    button.setText(logic.MARK_X);
                } else {
                    tempForCurrentMark = logic.MARK_O;
                    button.setText(logic.MARK_O);
                }
                button.setEnabled(false);
                Log.d(LOG, "button.getText() = " + button.getText());
                logic.mark(entry.getValue().first, entry.getValue().second, tempForCurrentMark);
                Log.d(LOG, "arrayOfChoices = " + logic.checkArray());
            }

        }

    }

}





