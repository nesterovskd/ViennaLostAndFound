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
import at.ac.univie.hci.viennalostandfound.home.HomeFragment;
import at.ac.univie.hci.viennalostandfound.user.LoggedInUser;
import at.ac.univie.hci.viennalostandfound.user.User;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private List<User> usersList;
    private User loggedInUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        // Set start-Screen
        bottomNavigationView.setSelectedItemId(R.id.home);

        // Create Dummy Users
        usersList = generateUsers();

        // TODO move this to the login screen
        // Set logged-in User
        loggedInUser = new User("Test User", "test.user@gmail.com"); // Default Profile Picture
        LoggedInUser.setLoggedInUser(loggedInUser);
    }

    private List<User> generateUsers() {
        return new ArrayList<>(
                Arrays.asList(
                        new User("Francesca Marino", "francesca.marino@gmail.com", R.drawable.profile_picture_1),
                        new User("Marcel Meyer", "marcel.meyer@gmail.com", R.drawable.profile_picture_2),
                        new User("David Sacks", "david.sacks@gmail.com", R.drawable.profile_picture_3)
                )
        );
    }

    // Initialize fragments
    HomeFragment homeFragment = new HomeFragment();
    ChatOverviewFragment chatOverviewFragment = new ChatOverviewFragment();

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:

                // TODO
                //  Uncomment this and the logged in user is correctly passed to the home screen
                //  But the bottom tabs disappear when the view updates ???
                Bundle bundleHome = new Bundle();
                bundleHome.putSerializable("loggedInUser", loggedInUser);
                homeFragment.setArguments(bundleHome);

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, homeFragment)
                        .commit();
                return true;

            case R.id.search:
                // Add Search Screen Fragment
                return true;

            case R.id.insert_item:
                // Add insert Item Screen Fragment
                return true;

            case R.id.chat:
                // Pass usersList to chatOverviewScreen
                Bundle bundle = new Bundle();
                bundle.putSerializable("usersList", new ArrayList<>(usersList));
                chatOverviewFragment.setArguments(bundle);

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, chatOverviewFragment)
                        .commit();
                return true;

            case R.id.profile:
                // Add Profile Screen Fragment
                return true;
        }
        return false;
    }

    public List<User> getUsersList() {
        return usersList;
    }
}