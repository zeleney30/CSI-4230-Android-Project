package xyz.zelly.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MenuPage extends AppCompatActivity {
    private Button enterGoal;
    private Button finishedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.menu_page);

        enterGoal = findViewById(R.id.buttonEnterGoal);
        finishedButton = findViewById(R.id.buttonFinished);

        enterGoal.setOnClickListener(e -> {
            Intent intent = new Intent(this, EnterGoal.class);
        });

        finishedButton.setOnClickListener(e -> {
            Intent intent = new Intent(this, ReviewGame.class);
        });
    }
}
