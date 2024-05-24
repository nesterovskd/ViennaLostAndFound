package at.ac.univie.hci.viennalostandfound.login;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import at.ac.univie.hci.viennalostandfound.R;

public class FindActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        ImageButton exitButton = findViewById(R.id.exit_buttom_from_found);
        exitButton.setOnClickListener(v -> finish());
    }

}