package at.ac.univie.hci.viennalostandfound.search;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import at.ac.univie.hci.viennalostandfound.R;

public class ResultItemFragment extends Fragment{
    private ListView listView;
    private ResultAdapter adapter;
    private SearchItem item;

    public ResultItemFragment() {
        // Required empty public constructor
    }
    public ResultItemFragment(SearchItem item) {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_result_list, container, false);

        // Initialize ListView
        listView = view.findViewById(R.id.result_list_view);

        // Set up adapter
        List<ResultItem> items = createDummyData(); // You need to implement this method
        adapter = new ResultAdapter(requireContext(), items);
        listView.setAdapter(adapter);

        return view;
    }

    private List<ResultItem> createDummyData() {
        List<ResultItem> items = new ArrayList<>();

        items.add(new ResultItem(R.drawable.lost_item_1, "Result 1"));
        items.add(new ResultItem(R.drawable.lost_item_1, "Result 2"));
        items.add(new ResultItem(R.drawable.lost_item_1, "Result 3"));
        return items;
    }
}
