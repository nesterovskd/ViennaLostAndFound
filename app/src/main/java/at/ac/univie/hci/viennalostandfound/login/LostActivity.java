package at.ac.univie.hci.viennalostandfound.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import at.ac.univie.hci.viennalostandfound.R;
import at.ac.univie.hci.viennalostandfound.data.Data;
import at.ac.univie.hci.viennalostandfound.data.ResultItem;

public class LostActivity extends AppCompatActivity implements LostItemAdapter.OnItemClickListener {

    private ListView lost_items_lv;
    private Data data = Data.getSingleInstance();
    private LostItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        lost_items_lv = findViewById(R.id.found_list_view);
        ImageButton exitButton = findViewById(R.id.exit_button_from_found);
        adapter = new LostItemAdapter(this, data.getMyLostItems());
        adapter.setOnItemClickListener(this);
        lost_items_lv.setAdapter(adapter);
        exitButton.setOnClickListener(v -> finish());
    }

    @Override
    public void onItemClick(int position) {
        ResultItem clickedItem = data.getMyLostItems().get(position);

        Intent intent = new Intent(this, LostItemDetails.class);
        intent.putExtra(LostItemDetails.EXTRA_ITEM, clickedItem);
        startActivity(intent);
    }


}