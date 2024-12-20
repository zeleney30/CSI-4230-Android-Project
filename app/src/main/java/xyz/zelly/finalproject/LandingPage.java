package xyz.zelly.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class LandingPage extends AppCompatActivity {
    private Button previousGames;
    private Button enterGame;
    private Button profileButton;
    private Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.landing_page);

        previousGames = findViewById(R.id.buttonPreviousGames);
        enterGame = findViewById(R.id.buttonEnterGame);
        profileButton = findViewById(R.id.buttonProfile);
        logoutButton = findViewById(R.id.buttonLogout);

        enterGame.setOnClickListener(e -> {
            Intent intent = new Intent(this, MenuPage.class);
            startActivity(intent);
            finish();
        });

        profileButton.setOnClickListener(e -> {
            Intent intent = new Intent(this, Profile.class);
            startActivity(intent);
            finish();
        });

        logoutButton.setOnClickListener(e -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
