package xyz.zelly.finalproject;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "userDB";  // Database name
    private static final String TABLE_NAME = "allusers";  // Table name
    private static final String COLUMN_ID = "id";        // Column for ID
    private static final String COLUMN_EMAIL = "email";  // Column for email
    private static final String COLUMN_PASSWORD = "password";  // Column for password
    private static final int DATABASE_VERSION = 1;  // Initial database version

    public DatabaseHelper(Context context) {
        super(context, context.getDatabasePath(DATABASE_NAME).getAbsolutePath(), null, DATABASE_VERSION);
        // Use default location: app-specific directory
    }

    // Create table method
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_EMAIL + " TEXT UNIQUE, " +
                COLUMN_PASSWORD + " TEXT)";
        db.execSQL(createTable);
        Log.d("DatabaseHelper", "Table created: " + createTable);
    }

    // Upgrade method if database version changes
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);  // Recreate the table if needed
        }
    }

    // Insert user data into the database
    public boolean insertData(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d("DatabaseHelper", "Inserting data for email: " + email);

        // Check if the email already exists in the database
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_EMAIL + " = ?", new String[]{email});
        if (cursor != null && cursor.getCount() > 0) {
            cursor.close();  // Close cursor if email exists
            Log.d("DatabaseHelper", "Email already exists: " + email);
            return false;  // Return false if email exists
        }
        cursor.close();

        // Insert new email and password into database
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, password);

        long result = db.insert(TABLE_NAME, null, values);
        db.close();

        if (result != -1) {
            Log.d("DatabaseHelper", "Data inserted successfully for email: " + email);
        } else {
            Log.e("DatabaseHelper", "Failed to insert data for email: " + email);
        }

        return result != -1;  // Return true if insertion is successful, else false
    }

    // Check email and password for login
    public boolean checkEmailPassword(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_EMAIL + " = ? AND " + COLUMN_PASSWORD + " = ?", new String[]{email, password});

        boolean valid = cursor != null && cursor.getCount() > 0;
        cursor.close();

        if (valid) {
            Log.d("DatabaseHelper", "Login successful for email: " + email);
        } else {
            Log.e("DatabaseHelper", "Invalid credentials for email: " + email);
        }

        return valid;
    }
}
