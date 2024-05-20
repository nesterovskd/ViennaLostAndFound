package at.ac.univie.hci.viennalostandfound.upload;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import at.ac.univie.hci.viennalostandfound.R;

public class UploadActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_upload);

        imageView = findViewById(R.id.upload_add_picture);

        Spinner categorySpinner = findViewById(R.id.upload_add_category);
        Spinner locationSpinner = findViewById(R.id.upload_add_location);

        String[] categories = {"Category 1", "Category 2", "Category 3", "Category 4"};
        String[] locations = {"Location 1", "Location 2", "Location 3", "Location 4"};

        // Adapter für Kategorien
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryAdapter);

        // Adapter für Orte
        ArrayAdapter<String> locationAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, locations);
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(locationAdapter);

    }


}

