package xyz.zelly.finalproject;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
            if (winLoss.getText().toString().trim().isEmpty() && goalsAgainst.getText().toString().trim().isEmpty() && savePercentage.getText().toString().trim().isEmpty()) {
                new AlertDialog.Builder(this).setTitle("Error").setMessage("The above fields cannot be empty!").setPositiveButton("OK", null).show();
            } else {
                if (!winLoss.getText().toString().equalsIgnoreCase("W") || !winLoss.getText().toString().equalsIgnoreCase("L")) {
                    new AlertDialog.Builder(this).setTitle("Error").setMessage("The Win/Loss field must be either W or L!").setPositiveButton("OK", null).show();
                } else {
                    if (Integer.parseInt(goalsAgainst.getText().toString()) < 0) {
                        new AlertDialog.Builder(this).setTitle("Error").setMessage("The Goals Against field cannot be negative!").setPositiveButton("OK", null).show();
                    } else {
                        if (Integer.parseInt(savePercentage.getText().toString()) < 0) {
                            new AlertDialog.Builder(this).setTitle("Error").setMessage("The Save Percentage field cannot be less than 0!").setPositiveButton("OK", null).show();
                        } else if (Integer.parseInt(savePercentage.getText().toString()) > 100) {
                            new AlertDialog.Builder(this).setTitle("Error").setMessage("The Save Percentage field cannot be greater than 100!").setPositiveButton("OK", null).show();
                        } else {
                            Intent intent = new Intent(this, ReviewGame.class);
                            intent.putExtra("winLoss", winLoss.getText().toString());
                            intent.putExtra("goalsAgainst", goalsAgainst.getText().toString());
                            intent.putExtra("savePercentage", savePercentage.getText().toString());
                            startActivity(intent);
                        }
                    }
                }
            }
        });
    }
}
