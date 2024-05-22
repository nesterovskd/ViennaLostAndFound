package at.ac.univie.hci.viennalostandfound.upload;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import at.ac.univie.hci.viennalostandfound.R;

public class UploadFragment extends Fragment {
    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView imageView;

    public UploadFragment() {

    }


    @Nullable
   // @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_upload, container, false);

        Spinner spinnerFilterCategories = view.findViewById(R.id.upload_add_category);
        Spinner spinnerLocation = view.findViewById(R.id.upload_add_location);

        String[] categories = {"Please select category", "Backpacks", "Personal documents", "Electronics", "Clothes"};
        String[] locations = {"Please select location", "1. District", "2. District", "3. District", "4. District"};

        // Adapter für Filter
        ArrayAdapter<String> catAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, categories);
        catAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFilterCategories.setAdapter(catAdapter);

        // Adapter für Orte
        ArrayAdapter<String> locAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, locations);
        locAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLocation.setAdapter(locAdapter);

        // Add picture
        imageView = view.findViewById(R.id.upload_add_picture);
        // Set onClickListener for the imageView
        imageView.setOnClickListener(v -> openGallery());

        return view;
    }
    public void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }



}
