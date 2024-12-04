package xyz.zelly.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class EnterGoal extends AppCompatActivity {
    private Button menuButton;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.goal_page);
        
        menuButton = findViewById(R.id.buttonMenu1);
        
        menuButton.setOnClickListener(e -> {
            Intent intent = new Intent(this, MenuPage.class);
            startActivity(intent);
        });
    }
}
