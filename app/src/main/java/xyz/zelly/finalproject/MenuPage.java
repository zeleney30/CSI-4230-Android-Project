package xyz.zelly.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MenuPage extends AppCompatActivity {
    private Button enterGoal;
    private Button finishedButton;

    private EditText winLoss;
    private EditText goalsAgainst;
    private EditText savePercentage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.menu_page);

        enterGoal = findViewById(R.id.buttonEnterGoal);
        finishedButton = findViewById(R.id.buttonFinished);

        winLoss = findViewById(R.id.textWinLoss);
        goalsAgainst = findViewById(R.id.textGA);
        savePercentage = findViewById(R.id.textSavePercentage);

        enterGoal.setOnClickListener(e -> {
            Intent intent = new Intent(this, EnterGoal.class);
            startActivity(intent);
        });

        finishedButton.setOnClickListener(e -> {
            //If W/L, GA, and Save% are not default values ->
            Intent intent = new Intent(this, ReviewGame.class);
            intent.putExtra("winLoss", winLoss.getText().toString());
            intent.putExtra("goalsAgainst", goalsAgainst.getText().toString());
            intent.putExtra("savePercentage", savePercentage.getText().toString());
            startActivity(intent);
            //Else ->
            //show pop up that these must be completed first
        });
    }
}
