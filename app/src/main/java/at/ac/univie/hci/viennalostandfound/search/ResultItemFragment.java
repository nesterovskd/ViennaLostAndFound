package at.ac.univie.hci.viennalostandfound.search;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

import at.ac.univie.hci.viennalostandfound.R;

public class ResultItemFragment extends Fragment{
    private ListView listView;
    private ResultAdapter adapter;

    public ResultItemFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_result_list, container, false);
        listView = view.findViewById(R.id.result_list_view);
        List<ResultItem> items = createDummyData();
        adapter = new ResultAdapter(requireContext(), items);
        listView.setAdapter(adapter);

        Button editFiltersButton = view.findViewById(R.id.edit_filters_btn);
        editFiltersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToSearchFragment();
            }
        });

        return view;
    }

    private void navigateToSearchFragment() {
        SearchFragment searchFragment = new SearchFragment();

        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.flFragment, searchFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private List<ResultItem> createDummyData() {
        List<ResultItem> items = new ArrayList<>();

        items.add(new ResultItem(R.drawable.lost_item_1, "Result 1"));
        items.add(new ResultItem(R.drawable.lost_item_1, "Result 2"));
        items.add(new ResultItem(R.drawable.lost_item_1, "Result 3"));
        return items;
    }
}
