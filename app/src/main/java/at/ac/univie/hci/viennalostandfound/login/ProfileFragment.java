package at.ac.univie.hci.viennalostandfound.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import at.ac.univie.hci.viennalostandfound.MainActivity;
import at.ac.univie.hci.viennalostandfound.R;
import at.ac.univie.hci.viennalostandfound.home.HomeActivity;
import at.ac.univie.hci.viennalostandfound.login.LostActivity;
public class ProfileFragment extends Fragment {

    private Button lostButton;
    private Button findButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        lostButton = view.findViewById(R.id.lost_list);
        findButton = view.findViewById(R.id.found_list);
        lostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentActivity fragmentActivity = getActivity();
                Intent intent = new Intent(fragmentActivity, LostActivity.class);
                startActivity(intent);
            }
        });
        findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentActivity fragmentActivity = getActivity();
                Intent intent = new Intent(fragmentActivity, FindActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
