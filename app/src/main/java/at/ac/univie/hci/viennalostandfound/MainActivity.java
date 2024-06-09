package at.ac.univie.hci.viennalostandfound;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import at.ac.univie.hci.viennalostandfound.chat.ChatOverviewFragment;
import at.ac.univie.hci.viennalostandfound.data.Data;
import at.ac.univie.hci.viennalostandfound.home.HomeFragment;
import at.ac.univie.hci.viennalostandfound.login.LoginRegistrationFragment;
import at.ac.univie.hci.viennalostandfound.login.LoginRequestFragment;
import at.ac.univie.hci.viennalostandfound.upload.UploadFragment;
import at.ac.univie.hci.viennalostandfound.search.SearchFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    public static boolean LOGIN_STATUS = false;
    private BottomNavigationView bottomNavigationView;
    private final Data data = Data.getSingleInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        // Set start-Screen
        bottomNavigationView.setSelectedItemId(R.id.home);
    }

    // Initialize fragments
    HomeFragment homeFragment = new HomeFragment();
    ChatOverviewFragment chatOverviewFragment = new ChatOverviewFragment();
    LoginRequestFragment loginRequestFragment = new LoginRequestFragment();
    LoginRegistrationFragment loginRegistrationFragment = new LoginRegistrationFragment();

    public SearchFragment getSearchFragment() {
        return searchFragment;
    }

    SearchFragment searchFragment = new SearchFragment();
    UploadFragment uploadFragment = new UploadFragment();

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                Bundle bundleHome = new Bundle();
                bundleHome.putSerializable("loggedInUser", data.getLoggedInUser());
                homeFragment.setArguments(bundleHome);

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, homeFragment)
                        .commit();
                return true;

            case R.id.search:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, searchFragment)
                        .commit();
                return true;

            case R.id.insert_item:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, uploadFragment)
                        .commit();
                return true;

            case R.id.chat:
                if (data.getLoggedInUser() != null) {
                    // Pass usersList to chatOverviewScreen
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("usersList", Data.getSingleInstance().getUsersList());
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
                data.createDummyLoginUser();
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
}