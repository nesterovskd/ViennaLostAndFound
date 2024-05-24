package at.ac.univie.hci.viennalostandfound.search;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import at.ac.univie.hci.viennalostandfound.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {
private String[] categories = {
            "ID",
            "Documents",
            "Plastic Cards",
            "Wallets",
            "Money",
            "Security Instruments",
            "Vehicles",
            "Motorbikes",
            "Boats",
            "Trailers",
            "Clothing",
            "Footwear",
            "Glasses",
            "Contact Lenses",
            "Optical Equipment",
            "Electronics",
            "Cameras",
            "Cellphones",
            "Bicycles",
            "Scooters",
            "Pushchairs",
            "Household",
            "Tools",
            "Cases",
            "Backpacks",
            "Bags",
            "Medical Devices And Aids",
            "Medicines",
            "Cosmetic Products",
            "Musical Instruments",
            "Food",
            "Drink",
            "Tobacco",
            "Umbrellas",
            "Keys",
            "Jewelry",
            "Watches",
            "Stationery",
            "Books",
            "Photos",
            "Toy",
            "Sports",
            "Leisure Items",
            "Animals",
            "Animal Accessories"
};
    String[] bezirke = new String[23];
AutoCompleteTextView autoCompleteTextViewStandort;
ArrayAdapter<String> adapterItemsStandort;
AutoCompleteTextView autoCompleteTextViewCategory;
ArrayAdapter<String> adapterItemsCategory;

    private TextInputEditText editTextDate;
    private SwitchCompat switchToggle;
    private MaterialButton searchButton;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for (int i = 0; i < 23; i++) {

            if (i == 0 || i==20) {
                bezirke[i] = String.valueOf(i + 1) + "\u02e2\u1d57  District"; // 1st
            } else if (i == 1 || i==21) {
                bezirke[i] = String.valueOf(i + 1) + "\u207f\u1d48  District"; // 2nd
            } else if (i == 2 || i==22) {
                bezirke[i] = String.valueOf(i + 1) + "\u02b3\u1d48  District"; // 3rd
            } else {
                bezirke[i] = String.valueOf(i + 1) + "\u1d57\u02b0  District"; // nth
            }
        }

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        editTextDate = view.findViewById(R.id.editTextDate);
        switchToggle = view.findViewById(R.id.switchToggle);
        searchButton = view.findViewById(R.id.search_button);

        editTextDate.setOnClickListener(v -> {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    getContext(),
                    (view1, year1, monthOfYear, dayOfMonth) -> {
                        calendar.set(year1, monthOfYear, dayOfMonth);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
                        editTextDate.setText(sdf.format(calendar.getTime()));
                    },
                    year, month, day
            );
            datePickerDialog.show();
        });

        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize standort dropdown
        autoCompleteTextViewStandort = view.findViewById(R.id.autoCompleteStandort);
        adapterItemsStandort = new ArrayAdapter<>(requireContext(), R.layout.search_dropdown_item, bezirke);
        autoCompleteTextViewStandort.setAdapter(adapterItemsStandort);
        // Initialize categories dropdown
        autoCompleteTextViewCategory = view.findViewById(R.id.autoCompleteCategory);
        adapterItemsCategory = new ArrayAdapter<>(requireContext(), R.layout.search_dropdown_item, categories);
        autoCompleteTextViewCategory.setAdapter(adapterItemsCategory);

        SwitchCompat switchToggle = view.findViewById(R.id.switchToggle);
        switchToggle.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                switchToggle.setText("Found");
            } else {
                switchToggle.setText("Lost");
            }
        });

        Button search = view.findViewById(R.id.search_button);
        search.setOnClickListener(v -> {
            String searchText = ((EditText) view.findViewById(R.id.textSearch)).getText().toString();
            boolean found = switchToggle.isChecked();;
            String place = autoCompleteTextViewStandort.getText().toString() ;
            String category = autoCompleteTextViewCategory.getText().toString();
            String dateStr = editTextDate.getText().toString();
            SearchItem item = new SearchItem(searchText, found, place, category, dateStr);

                // Load the ProfileFragment
                loadFragment(new ResultItemFragment(item));

        });

    }

    public void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.flFragment, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}