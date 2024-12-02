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


        menuButton = findViewById(R.id.menuButton);

        menuButton.setOnClickListener(e -> {
            Intent intentNew = new Intent(this, LandingPage.class);
            startActivity(intentNew);
        });
    }
}
