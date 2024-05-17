package at.ac.univie.hci.viennalostandfound.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import at.ac.univie.hci.viennalostandfound.R;
import at.ac.univie.hci.viennalostandfound.chat.ChatActivity;
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

    // The serialized User (loggedInUser) gets passed to MainActivity through this method
    // TODO maybe it need adjustments ?
    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            loggedInUser = (User) getArguments().getSerializable("loggedInUser");
        }

        if (loggedInUser != null) {
            Intent intent = new Intent(getActivity(), HomeActivity.class);

            // Pass the logged in User Data
            intent.putExtra("loggedInUserName", loggedInUser.getName());

            startActivity(intent);
        }
    }
}