package xyz.zelly.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Profile extends AppCompatActivity {
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.profile_page);

        backButton = findViewById(R.id.buttonBack);

        backButton.setOnClickListener(e -> {
            Intent intent = new Intent(this, LandingPage.class);
            startActivity(intent);
            finish();
        });
    }
}
