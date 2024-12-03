package xyz.zelly.finalproject;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
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
                new AlertDialog.Builder(this).setTitle("Error").setMessage("The above fields cannot be empty!").setPositiveButton("OK", null).show();
            } else {
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    new AlertDialog.Builder(this).setTitle("Error").setMessage("Please enter a valid email address!").setPositiveButton("OK", null).show();
                } else {
                    Log.d("MainActivity", "Sign Up Button clicked with Email: " + email + " and Password: " + password);

                    // Execute the insert data operation on a background thread
                    executorService.execute(() -> {
                        boolean result = dbHelper.insertData(email, password);
                        runOnUiThread(() -> {
                            if (result) {
                                new AlertDialog.Builder(this).setTitle("Sign Up").setMessage("Sign Up successfully completed!").setPositiveButton("OK", null).show();
                                Intent intent = new Intent(MainActivity.this, LandingPage.class);
                                startActivity(intent);
                                finish(); // Finish the current activity
                            } else {
                                new AlertDialog.Builder(this).setTitle("Error").setMessage("This email is already in use!").setPositiveButton("OK", null).show();
                            }
                        });
                    });
                }
            }
        });

        // Log In Button Listener
        logInButton.setOnClickListener(v -> {
            String email = emailInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                new AlertDialog.Builder(this).setTitle("Error").setMessage("The above fields cannot be empty!").setPositiveButton("OK", null).show();
            } else {
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    new AlertDialog.Builder(this).setTitle("Error").setMessage("Please enter a valid email address!").setPositiveButton("OK", null).show();
                } else {
                    Log.d("MainActivity", "Log In Button clicked with Email: " + email + " and Password: " + password);

                    // Execute the login check operation on a background thread
                    executorService.execute(() -> {
                        boolean valid = dbHelper.checkEmailPassword(email, password);
                        runOnUiThread(() -> {
                            if (valid) {
                                Intent intent = new Intent(MainActivity.this, LandingPage.class);
                                startActivity(intent);
                                finish(); // Finish the current activity
                            } else {
                                new AlertDialog.Builder(this).setTitle("Error").setMessage("Invalid Email or Password!").setPositiveButton("OK", null).show();
                            }
                        });
                    });
                }
            }
        });
    }
}
