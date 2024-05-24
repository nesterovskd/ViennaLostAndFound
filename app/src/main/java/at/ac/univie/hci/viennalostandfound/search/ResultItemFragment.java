package at.ac.univie.hci.viennalostandfound.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

import at.ac.univie.hci.viennalostandfound.MainActivity;
import at.ac.univie.hci.viennalostandfound.R;
import at.ac.univie.hci.viennalostandfound.data.Data;

public class ResultItemFragment extends Fragment implements ResultAdapter.OnItemClickListener {
    private ListView listView;
    private ResultAdapter adapter;
    private SearchItem item;
    private EditText filterText;
    private EditText filterDate;
    private EditText filterCategory;
    private EditText filterLocation;
    //private List<ResultItem> items; // Define items as a class-level field
    private Data data = Data.getSingleInstance();
    List<ResultItem> filteredItemsDatenbank = new ArrayList<ResultItem>();
    public ResultItemFragment() {
    }

    public ResultItemFragment(SearchItem item) {
        this.item = item;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.filterItems(this.data);

        View view = inflater.inflate(R.layout.fragment_result_list, container, false);
        listView = view.findViewById(R.id.result_list_view);
        adapter = new ResultAdapter(requireContext(), filteredItemsDatenbank);
        adapter.setOnItemClickListener(this); // Set the click listener
        listView.setAdapter(adapter);

        Button editFiltersButton = view.findViewById(R.id.edit_filters_btn);
        editFiltersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToSearchFragment();
            }
        });

        filterCategory = view.findViewById(R.id.filter_category);
        filterLocation = view.findViewById(R.id.filter_location);
        filterDate = view.findViewById(R.id.filter_date);
        filterText = view.findViewById(R.id.filter_search);

        filterCategory.setText(item.category);
        filterText.setText(item.searchText);
        filterLocation.setText(item.location);
        filterDate.setText(item.date);

        return view;
    }

    private void filterItems(Data data){
        List<ResultItem> filtered_data = new ArrayList<>(data.getItemsDatenbank());
        filtered_data.clear();

//

        String location = item.getLocation();
        String date = item.getDate();
        String category = item.getCategory();

        // add boolean for lost and found - Isidora
        // boolean found = ;

        if(data == null){
            filteredItemsDatenbank = filtered_data;
        }else{
            for (ResultItem item : data.getItemsDatenbank()) {
                // Check if item's location, date, and category match the filter values
                if ((location.isEmpty() || item.getLocation().equalsIgnoreCase(location)) &&
                        (date.isEmpty() || item.getDate().equalsIgnoreCase(date)) &&
                        (category.isEmpty() || item.getCategory().equalsIgnoreCase(category))) {
                    filtered_data.add(item);
                }
            }
        }

        this.filteredItemsDatenbank = filtered_data;
    }

    private void navigateToSearchFragment() {
        SearchFragment searchFragment = ((MainActivity) getActivity()).getSearchFragment();

        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.flFragment, searchFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }



    @Override
    public void onItemClick(int position) {
        ResultItem clickedItem = filteredItemsDatenbank.get(position);
        ItemDetails itemDetailsFragment = new ItemDetails(clickedItem);

        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.flFragment, itemDetailsFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}