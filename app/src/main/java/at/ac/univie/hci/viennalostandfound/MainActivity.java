package at.ac.univie.hci.viennalostandfound;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import at.ac.univie.hci.viennalostandfound.chat.ChatOverviewFragment;
import at.ac.univie.hci.viennalostandfound.user.LoggedInUser;
import at.ac.univie.hci.viennalostandfound.user.User;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private List<User> usersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        // Set start-screen
        bottomNavigationView.setSelectedItemId(R.id.home);

        // Create Dummy Users
        usersList = new ArrayList<>(
                Arrays.asList(
                        new User("Ethan Calloway", "ethan.colloway@gmail.com", R.drawable.user2),
                        new User("Aria Sterling", "aria.sterling@gmail.com", R.drawable.user1),
                        new User("Nolan Thorne", "nolan.thorne@gmail.com", R.drawable.user3)
                )
        );

        // Set logged-in User
        User loggedInUser = new User("Peter Griffin", "peter.griffin@gmail.com", R.drawable.loggedin_user);
        LoggedInUser.setLoggedInUser(loggedInUser);
    }

    // Initialize fragments
    ChatOverviewFragment chatOverviewScreen = new ChatOverviewFragment();

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                // Add Home Screen Fragment
                return true;

            // Add missing screens here

            case R.id.chat:
                // Pass usersList to chatOverviewScreen
                Bundle bundle = new Bundle();
                bundle.putSerializable("usersList", new ArrayList<>(usersList));
                chatOverviewScreen.setArguments(bundle);

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, chatOverviewScreen)
                        .commit();
                return true;
        }
        return false;
    }

    public List<User> getUsersList() {
        return usersList;
    }
}