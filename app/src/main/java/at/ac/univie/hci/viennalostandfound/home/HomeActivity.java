package at.ac.univie.hci.viennalostandfound.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import at.ac.univie.hci.viennalostandfound.R;
import at.ac.univie.hci.viennalostandfound.user.LoggedInUser;
import at.ac.univie.hci.viennalostandfound.user.User;

public class HomeActivity extends AppCompatActivity {
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView greetingTextView = findViewById(R.id.textView_Homescreen_Greeting);
        Button loginButton = findViewById(R.id.button_Login_Homescreen);

        // TODO idk what the correct way of passing the logged in User is :)
        /*
        User loggedInUser = LoggedInUser.getLoggedInUser();
        String loggedInUserName = loggedInUser.getName();
        greetingTextView.setText("Hello, " + loggedInUserName);
        */

       // String loggedInUserName = getIntent().getStringExtra("loggedInUserName");
/*
        if (loggedInUserName != null) {
            greetingTextView.setText("Hello, " + loggedInUserName);
            loginButton.setVisibility(View.GONE);
        }
        else {
            greetingTextView.setText("Hello, stranger");
            loginButton.setVisibility(View.VISIBLE);
        }

 */

        /*
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        */
    }
}
