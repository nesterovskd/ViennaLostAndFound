package at.ac.univie.hci.viennalostandfound.login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import at.ac.univie.hci.viennalostandfound.MainActivity;
import at.ac.univie.hci.viennalostandfound.R;
import at.ac.univie.hci.viennalostandfound.data.Data;

public class LoginRegistrationFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private LoginAdapter adapter;
    private final Data data = Data.getSingleInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_registration, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (MainActivity.LOGIN_STATUS) {
            loadFragment(new ProfileFragment(data.getLoggedInUser()));
        } else {
            MainActivity.LOGIN_STATUS = true;
            tabLayout = view.findViewById(R.id.tab_layout);
            viewPager2 = view.findViewById(R.id.view_pager);

            tabLayout.addTab(tabLayout.newTab().setText("Login"));
            tabLayout.addTab(tabLayout.newTab().setText("Sign up"));

            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            adapter = new LoginAdapter(fragmentManager, getLifecycle());
            viewPager2.setAdapter(adapter);
            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    viewPager2.setCurrentItem(tab.getPosition());
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {
                }
            });

            viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageSelected(int position) {
                    tabLayout.selectTab(tabLayout.getTabAt(position));
                }
            });
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