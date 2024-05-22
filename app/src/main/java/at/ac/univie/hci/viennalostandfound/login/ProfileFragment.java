package at.ac.univie.hci.viennalostandfound.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import at.ac.univie.hci.viennalostandfound.MainActivity;
import at.ac.univie.hci.viennalostandfound.R;
import at.ac.univie.hci.viennalostandfound.home.HomeActivity;
import at.ac.univie.hci.viennalostandfound.login.LostActivity;
import at.ac.univie.hci.viennalostandfound.user.LoggedInUser;
import at.ac.univie.hci.viennalostandfound.user.User;

public class ProfileFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Get the loggedInUser
        User loggedInUser = LoggedInUser.getLoggedInUser();

        // Update the profile name and email
        TextView profileName = view.findViewById(R.id.profile_name);
        TextView profileEmail = view.findViewById(R.id.profile_email);

        // Display the changes
        if (loggedInUser != null) {
            profileName.setText(loggedInUser.getName());
            profileEmail.setText(loggedInUser.getEmailAddress());
        }

        Button lostButton = view.findViewById(R.id.lost_list);
        lostButton.setOnClickListener(v -> {
            FragmentActivity fragmentActivity = getActivity();
            Intent intent = new Intent(fragmentActivity, LostActivity.class);
            startActivity(intent);
        });

        Button findButton = view.findViewById(R.id.found_list);
        findButton.setOnClickListener(v -> {
            FragmentActivity fragmentActivity = getActivity();
            Intent intent = new Intent(fragmentActivity, FindActivity.class);
            startActivity(intent);
        });

        return view;
    }
}
