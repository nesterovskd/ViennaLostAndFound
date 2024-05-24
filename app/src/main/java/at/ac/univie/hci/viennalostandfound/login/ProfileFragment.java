package at.ac.univie.hci.viennalostandfound.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import at.ac.univie.hci.viennalostandfound.R;
import at.ac.univie.hci.viennalostandfound.user.User;

public class ProfileFragment extends Fragment {
    private User loggedInUser;

    public ProfileFragment() {

    }

    public ProfileFragment(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        ImageView profilePicture = view.findViewById(R.id.profile_profile_picture);

        TextView profileName = view.findViewById(R.id.profile_name);
        TextView profileEmail = view.findViewById(R.id.profile_email);
        TextView profilePhoneNumber = view.findViewById(R.id.profile_phone_number);
        TextView profileHomeAddress = view.findViewById(R.id.profile_home_address);

        TextView profileFoundCount = view.findViewById(R.id.profile_found_count);
        TextView profileLostCount = view.findViewById(R.id.profile_lost_count);
        TextView profileAppreciationCount = view.findViewById(R.id.profile_appreciation_count);

        // Display the user
        profilePicture.setImageResource(loggedInUser.getProfilePictureId());

        profileName.setText(loggedInUser.getName());

        profileEmail.setText(loggedInUser.getEmailAddress());
        profilePhoneNumber.setText(loggedInUser.getPhoneNumber());
        profileHomeAddress.setText(loggedInUser.getHomeAddress());

        profileFoundCount.setText(loggedInUser.getFoundCount());
        profileLostCount.setText(loggedInUser.getLostCount());
        profileAppreciationCount.setText(loggedInUser.getAppreciationCount());

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
