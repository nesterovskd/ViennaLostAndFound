package at.ac.univie.hci.viennalostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import at.ac.univie.hci.viennalostandfound.login.LoginRegistrationFragment;
import at.ac.univie.hci.viennalostandfound.user.LoggedInUser;
import at.ac.univie.hci.viennalostandfound.user.User;

public class LoginRequestFragment extends Fragment {

    public LoginRequestFragment() {
        // default constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login_request, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button loginButton = view.findViewById(R.id.request_login_button);

        loginButton.setOnClickListener(v -> {
            // Navigate to Profile (Login/Registration section)
            if (getActivity() instanceof MainActivity) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.navigateToProfile();
            }

            // TODO Login as User
            User loggedInUser = new User("Lisi Knorr", "lisi_knorr@gmail.com", R.drawable.profile_girl_with_cat);
            LoggedInUser.setLoggedInUser(loggedInUser);
        });
    }

}
