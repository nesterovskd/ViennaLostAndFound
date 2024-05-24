package at.ac.univie.hci.viennalostandfound.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import at.ac.univie.hci.viennalostandfound.MainActivity;
import at.ac.univie.hci.viennalostandfound.R;
import at.ac.univie.hci.viennalostandfound.user.User;

public class HomeFragment extends Fragment {
    private User loggedInUser;

    public HomeFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            loggedInUser = (User) getArguments().getSerializable("loggedInUser");
        }

        TextView welcomeTextView = view.findViewById(R.id.textView_Homescreen_Greeting);
        TextView welcomeTextViewLoggedIn = view.findViewById(R.id.textView_Homescreen_Greeting_Loggedin);
        if (loggedInUser != null) {
            welcomeTextView.setText("Hello, " + loggedInUser.getName());
            Button loginButton = view.findViewById(R.id.button_Login_Homescreen);
            loginButton.setVisibility(View.GONE);
            LoggedInFragment homeLoggedIn = new LoggedInFragment();
            Bundle bundleHome = new Bundle();
            bundleHome.putSerializable("loggedInUser", loggedInUser);
            homeLoggedIn.setArguments(bundleHome);
            loadFragment(homeLoggedIn);
        }
        else{
            welcomeTextView.setText("Hello, stranger");
            Button loginButton = view.findViewById(R.id.button_Login_Homescreen);
            loginButton.setVisibility(View.VISIBLE);
        }

        Button loginButton = view.findViewById(R.id.button_Login_Homescreen);
        loginButton.setOnClickListener(v -> switchToLogin());
    }

    private void switchToLogin() {
        if (getActivity() instanceof MainActivity) {
            MainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.navigateToProfile();
        }
    }
    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


}

