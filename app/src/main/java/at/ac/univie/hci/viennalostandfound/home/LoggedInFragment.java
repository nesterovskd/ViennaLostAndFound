package at.ac.univie.hci.viennalostandfound.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import at.ac.univie.hci.viennalostandfound.R;
import at.ac.univie.hci.viennalostandfound.user.User;

public class LoggedInFragment extends Fragment {
    private User loggedInUser;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_loggedin_home, container, false);
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        TextView welcomeTextViewLoggedIn = view.findViewById(R.id.textView_Homescreen_Greeting_Loggedin);
        if (getArguments() != null) {
            this.loggedInUser = (User) getArguments().getSerializable("loggedInUser");
        }
        welcomeTextViewLoggedIn.setText("Hello, " + loggedInUser.getName());

    }
}
