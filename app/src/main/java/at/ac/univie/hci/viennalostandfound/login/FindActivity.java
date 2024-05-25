package at.ac.univie.hci.viennalostandfound.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import at.ac.univie.hci.viennalostandfound.R;
import at.ac.univie.hci.viennalostandfound.data.Data;
import at.ac.univie.hci.viennalostandfound.data.ResultItem;

public class FindActivity extends AppCompatActivity implements FoundItemAdapter.OnItemClickListener {

    private ListView found_items_lv;
    private Data data = Data.getSingleInstance();
    private FoundItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        found_items_lv = findViewById(R.id.found_list_view);
        ImageButton exitButton = findViewById(R.id.exit_button_from_found);
        adapter = new FoundItemAdapter(this, data.getMyFoundItems());
        adapter.setOnItemClickListener(this);
        found_items_lv.setAdapter(adapter);
        exitButton.setOnClickListener(v -> finish());
    }

    @Override
    public void onItemClick(int position) {
        ResultItem clickedItem = data.getMyFoundItems().get(position);

        Intent intent = new Intent(this, FoundItemDetails.class);
        intent.putExtra(FoundItemDetails.EXTRA_ITEM, clickedItem);
        startActivity(intent);
    }
}