package ru.job4j.tictactoy;

import android.os.Bundle;
import android.view.View;
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


    }

}





