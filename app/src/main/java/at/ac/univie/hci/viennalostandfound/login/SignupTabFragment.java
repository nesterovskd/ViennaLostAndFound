package at.ac.univie.hci.viennalostandfound.login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import at.ac.univie.hci.viennalostandfound.R;
import at.ac.univie.hci.viennalostandfound.user.LoggedInUser;
import at.ac.univie.hci.viennalostandfound.user.User;

public class SignupTabFragment extends Fragment {
    private User loggedInUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup_tab, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText signUpNameEditText = view.findViewById(R.id.signup_name);
        EditText signUpEmailEditText = view.findViewById(R.id.signup_email);

        // TODO
        //  this will still create an error
        //  I will fix it later
        loggedInUser = LoggedInUser.getLoggedInUser();

        Button logIn = view.findViewById(R.id.signup_button);
        logIn.setOnClickListener(v -> {
            // Get the name and email of the new User
            String signUpName = signUpNameEditText.getText().toString().trim();
            String signUpEmail = signUpEmailEditText.getText().toString().trim();

            if (!signUpName.isEmpty() && !signUpEmail.isEmpty()) {
                if (loggedInUser != null) {
                    loggedInUser.resetUser();
                }
                // Create a new User
                loggedInUser = new User(signUpName, signUpEmail);
                // Login with this User
                LoggedInUser.setLoggedInUser(loggedInUser);

                // Load the ProfileFragment
                loadFragment(new ProfileFragment());
            } else {
                Toast.makeText(getActivity(), "Please fill in all the fields", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}