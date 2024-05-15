package at.ac.univie.hci.viennalostandfound;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        // Set start-screen
        bottomNavigationView.setSelectedItemId(R.id.home);
    }

    // Create fragments
    ChatOverviewFragment chatOverviewScreen = new ChatOverviewFragment();

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                // Add Home Screen Fragment
                return true;

                // Add missing screens

            case R.id.chat:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, chatOverviewScreen)
                        .commit();
                return true;
        }
        return false;
    }
}