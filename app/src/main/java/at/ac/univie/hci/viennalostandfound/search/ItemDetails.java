package at.ac.univie.hci.viennalostandfound.search;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.imageview.ShapeableImageView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import at.ac.univie.hci.viennalostandfound.R;
import at.ac.univie.hci.viennalostandfound.chat.ChatActivity;
import at.ac.univie.hci.viennalostandfound.data.Data;
import at.ac.univie.hci.viennalostandfound.data.ResultItem;
import at.ac.univie.hci.viennalostandfound.login.LoginRequestFragment;
import at.ac.univie.hci.viennalostandfound.verification.VerificationActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ItemDetails#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ItemDetails extends Fragment {
    private Data data = Data.getSingleInstance();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ResultItem item ;

    public ItemDetails() {
        // Required empty public constructor
    }
    public ItemDetails(ResultItem item) {
        this.item=item;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ItemDetails.
     */
    // TODO: Rename and change types and number of parameters
    public static ItemDetails newInstance(String param1, String param2) {
        ItemDetails fragment = new ItemDetails();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_item_details, container, false);

        AppCompatImageButton editFiltersButton = view.findViewById(R.id.exit_details_btn);
        editFiltersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToSearchResultFragment();
            }
        });

        ImageView image = view.findViewById(R.id.item_img);
        image.setImageResource(item.getImageResId());
        TextView title = view.findViewById(R.id.item_title);
        title.setText(item.getTitle());
        TextView item_location = view.findViewById(R.id.item_location);
        item_location.setText(item.getLocation());
        TextView item_date = view.findViewById(R.id.item_date);
        item_date.setText(item.getDate());
        TextView item_category = view.findViewById(R.id.item_category);
        item_category.setText(item.getCategory());

        TextView item_description = view.findViewById(R.id.item_description);
        item_description.setText(item.getDescription());

        TextView item_creator = view.findViewById(R.id.item_creator_username);
        ShapeableImageView item_creator_picture = view.findViewById(R.id.item_creator_pic);
        item_creator_picture.setImageResource(item.getUser().getProfilePictureId());
        item_creator.setText("Message finder:\n" + item.getUserString());
        LinearLayout user_info_layout = view.findViewById(R.id.user_info_layout);

            user_info_layout.setOnClickListener(v -> {
                if(data.getLoggedInUser() == null){
                    LoginRequestFragment loginRequestFragment = new LoginRequestFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.flFragment, loginRequestFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                } else {
                    Intent intent;
                    if (!data.getLoggedInUser().getVerificationIdsForItems().contains(item.getUser().getId())) {
                        intent = new Intent(getActivity(), VerificationActivity.class);
                    } else {
                        intent = new Intent(getActivity(), ChatActivity.class);
                    }
                    intent.putExtra("chatId", item.getUser().getId());
                    intent.putExtra("chatName", item.getUserString());
                    intent.putExtra("profilePictureId", item.getUser().getProfilePictureId());
                    startActivity(intent);
                }
            });
        return view;
    }
    private void navigateToSearchResultFragment() {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentManager.popBackStack();
    }



}