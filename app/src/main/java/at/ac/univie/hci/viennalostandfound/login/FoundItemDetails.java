package at.ac.univie.hci.viennalostandfound.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import at.ac.univie.hci.viennalostandfound.R;
import at.ac.univie.hci.viennalostandfound.data.ResultItem;

public class FoundItemDetails extends AppCompatActivity {

    public static final String EXTRA_ITEM = "at.ac.univie.hci.viennalostandfound.ITEM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.found_item_details);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        ResultItem clickedItem = (ResultItem) intent.getSerializableExtra(EXTRA_ITEM);

        if (clickedItem != null) {
            ImageView imageView = findViewById(R.id.item_img);
            imageView.setImageResource(clickedItem.getImageResId());

            TextView titleTextView = findViewById(R.id.item_title);
            titleTextView.setText(clickedItem.getTitle());

            TextView locationTextView = findViewById(R.id.item_location);
            locationTextView.setText(clickedItem.getLocation());

            TextView dateTextView = findViewById(R.id.item_date);
            dateTextView.setText(clickedItem.getDate());

            TextView categoryTextView = findViewById(R.id.item_category);
            categoryTextView.setText(clickedItem.getCategory());

            TextView descriptionTextView = findViewById(R.id.item_description);
            descriptionTextView.setText(clickedItem.getDescription());
        }

        ImageView exitButton = findViewById(R.id.exit_details_btn);
        exitButton.setOnClickListener(v -> {
            finish(); // Close the activity and return to the previous activity (the list)
        });
    }
}
