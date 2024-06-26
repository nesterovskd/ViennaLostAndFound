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
import at.ac.univie.hci.viennalostandfound.data.Data;

public class LoginTabFragment extends Fragment {
    private final Data data = Data.getSingleInstance();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText loginEmailEditText = view.findViewById(R.id.login_email);

        Button logIn = view.findViewById(R.id.login_button);
        logIn.setOnClickListener(v -> {
            String inputEmailAddress = loginEmailEditText.getText().toString().trim();

            // Login as user
            // Only check if the email is equal, the password will be ignored
            if (inputEmailAddress.equals(data.getLoggedInUser().getEmailAddress())) {
                loadFragment(new ProfileFragment(data.getLoggedInUser()));
            } else {
                Toast.makeText(getActivity(), "This User does not exist. Please sign up first!", Toast.LENGTH_SHORT).show();
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