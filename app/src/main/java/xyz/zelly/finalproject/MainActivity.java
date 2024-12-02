package xyz.zelly.finalproject;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Button loginButton;
    private EditText email;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        loginButton = findViewById(R.id.buttonLogin);
        email = findViewById(R.id.inputEmail);
        password = findViewById(R.id.inputPassword);

        loginButton.setOnClickListener(e -> {
            if (email.getText().toString().trim().isEmpty() || password.getText().toString().trim().isEmpty()) {
                new AlertDialog.Builder(this).setTitle("Error").setMessage("The above fields cannot be empty!").setPositiveButton("OK", null).show();
            } else {
                if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString().trim()).matches()) {
                    new AlertDialog.Builder(this).setTitle("Error").setMessage("Please enter a valid email address!").setPositiveButton("OK", null).show();
                } else {
                    //If email and password do not match a database element (aka user is not found)
                    //new AlertDialog.Builder -> username and/or password not found!
                    Intent intent = new Intent(MainActivity.this, LandingPage.class);
                    startActivity(intent);
                }
            }
        });
    }
}