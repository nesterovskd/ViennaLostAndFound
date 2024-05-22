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
import at.ac.univie.hci.viennalostandfound.login.LoginRegistrationFragment;
import at.ac.univie.hci.viennalostandfound.login.LoginTabFragment;
import at.ac.univie.hci.viennalostandfound.login.ProfileFragment;
import at.ac.univie.hci.viennalostandfound.upload.UploadFragment;
import at.ac.univie.hci.viennalostandfound.user.LoggedInUser;
import at.ac.univie.hci.viennalostandfound.user.User;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    public static boolean LOGIN_STATUS = false;
    private List<User> usersList;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        // Set start-Screen
        bottomNavigationView.setSelectedItemId(R.id.home);

        // Create Dummy Users
        usersList = generateDummyUsers();
    }

    private List<User> generateDummyUsers() {
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
    LoginRequestFragment loginRequestFragment = new LoginRequestFragment();
    LoginRegistrationFragment loginRegistrationFragment = new LoginRegistrationFragment();
    UploadFragment uploadFragment = new UploadFragment();

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                Bundle bundleHome = new Bundle();
                bundleHome.putSerializable("loggedInUser", LoggedInUser.getLoggedInUser());
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
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, uploadFragment)
                        .commit();
                return true;

            case R.id.chat:
                if (LoggedInUser.getLoggedInUser() != null) {
                    // Pass usersList to chatOverviewScreen
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("usersList", new ArrayList<>(usersList));
                    chatOverviewFragment.setArguments(bundle);

                    // View the Chat Fragment
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.flFragment, chatOverviewFragment)
                            .commit();
                    return true;
                } else {
                    // If no user is logged in show login request fragment
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.flFragment, loginRequestFragment)
                            .commit();
                    return false;
                }

            case R.id.profile:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, loginRegistrationFragment)
                        .commit();
                return true;
        }
        return false;
    }

    public void navigateToProfile() {
        bottomNavigationView.setSelectedItemId(R.id.profile);
    }

    public List<User> getUsersList() {
        return usersList;
    }
}