package at.ac.univie.hci.viennalostandfound.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import at.ac.univie.hci.viennalostandfound.R;
import at.ac.univie.hci.viennalostandfound.login.LoginRegistrationFragment;

public class HomeActivity extends AppCompatActivity {
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView greetingTextView = findViewById(R.id.textView_Homescreen_Greeting);
        Button loginButton = findViewById(R.id.button_Login_Homescreen);

    }

}
