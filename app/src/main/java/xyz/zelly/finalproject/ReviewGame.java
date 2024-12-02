package xyz.zelly.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ReviewGame extends AppCompatActivity {
    private TextView gradeText;

    private Button menuButton;


    private String winLoss;
    private String goalsAgainst;
    private String savePercentage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.review_page);

        Intent intent = getIntent();

        winLoss = intent.getStringExtra("winLoss");
        goalsAgainst = intent.getStringExtra("goalsAgainst");
        savePercentage = intent.getStringExtra("savePercentage");

        Float GA = Float.parseFloat(goalsAgainst);
        Float SP = Float.parseFloat(savePercentage);

        if (winLoss == "W") {
            if (SP >= 90) {
                gradeText.setText("A");
            } else if (SP >= 80) {
                gradeText.setText("B");
            } else if (SP >= 70) {
                gradeText.setText("C");
            } else if (SP >= 60) {
                gradeText.setText("D");
            } else {
                gradeText.setText("F");
            }
        } else if (winLoss == "L") {
            if (GA >= 5) {
                if (SP >= 80) {
                    gradeText.setText("C");
                } else if (SP >= 70) {
                    gradeText.setText("D");
                } else {
                    gradeText.setText("F");
                }
            } else if (GA < 5) {
                if (SP >= 90) {
                    gradeText.setText("B");
                } else if (SP >= 80) {
                    gradeText.setText("C");
                } else if (SP >= 70) {
                    gradeText.setText("D");
                } else {
                    gradeText.setText("F");
                }
            }
        } else {
            gradeText.setText("?");
        }

        menuButton = findViewById(R.id.menuButton);

        menuButton.setOnClickListener(e -> {
            Intent intentNew = new Intent(this, LandingPage.class);
            startActivity(intentNew);
        });
    }
}
