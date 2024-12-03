package xyz.zelly.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private EditText emailInput, passwordInput;
    private Button signUpButton, logInButton;
    private DatabaseHelper dbHelper;
    private ExecutorService executorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        emailInput = findViewById(R.id.inputEmail);
        passwordInput = findViewById(R.id.inputPassword);
        signUpButton = findViewById(R.id.buttonSignup);
        logInButton = findViewById(R.id.buttonLogin);

        // Initialize the DatabaseHelper and ExecutorService
        dbHelper = new DatabaseHelper(this);
        executorService = Executors.newSingleThreadExecutor(); // Create a single background thread for DB operations

        // Sign Up Button Listener
        signUpButton.setOnClickListener(v -> {
            String email = emailInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            Log.d("MainActivity", "Sign Up Button clicked with Email: " + email + " and Password: " + password);

            // Execute the insert data operation on a background thread
            executorService.execute(() -> {
                boolean result = dbHelper.insertData(email, password);
                runOnUiThread(() -> {
                    if (result) {
                        Toast.makeText(MainActivity.this, "Sign Up Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, LandingPage.class);
                        startActivity(intent);
                        finish(); // Finish the current activity
                    } else {
                        Toast.makeText(MainActivity.this, "Email already exists", Toast.LENGTH_SHORT).show();
                    }
                });
            });
        });

        // Log In Button Listener
        logInButton.setOnClickListener(v -> {
            String email = emailInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            Log.d("MainActivity", "Log In Button clicked with Email: " + email + " and Password: " + password);

            // Execute the login check operation on a background thread
            executorService.execute(() -> {
                boolean valid = dbHelper.checkEmailPassword(email, password);
                runOnUiThread(() -> {
                    if (valid) {
                        Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, LandingPage.class);
                        startActivity(intent);
                        finish(); // Finish the current activity
                    } else {
                        Toast.makeText(MainActivity.this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
                    }
                });
            });
        });
    }
}
