package at.ac.univie.hci.viennalostandfound.upload;

import static at.ac.univie.hci.viennalostandfound.MainActivity.LOGIN_STATUS;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.textfield.TextInputEditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import at.ac.univie.hci.viennalostandfound.MainActivity;
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

        String[] categories = {"Please select category", "ID",
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
                "Animal Accessories"};

        String[] locations = {"Please select location", "1. Bezirk", "2. Bezirk", "3. Bezirk", "4. Bezirk", "5. Bezirk", "6. Bezirk", "7. Bezirk", "8. Bezirk", "9. Bezirk", "10. Bezirk",
                "11. Bezirk", "12. Bezirk", "13. Bezirk", "14. Bezirk", "15. Bezirk", "16. Bezirk", "17. Bezirk", "18. Bezirk", "19. Bezirk", "20. Bezirk", "21. Bezirk", "22. Bezirk", "23. Bezirk",};

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

        Button button = view.findViewById(R.id.upload_done);
        button.setOnClickListener((v -> uploadItem(view)));


        return view;
    }
    public void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    public void uploadItem(View view) {
        if(LOGIN_STATUS == false){
            if (getActivity() instanceof MainActivity) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.navigateToProfile();
            }
        }
        else {
            Chip lostChip = view.findViewById(R.id.lost_chip);
            Chip foundChip = view.findViewById(R.id.found_chip);
            TextInputEditText title = view.findViewById(R.id.upload_add_title);
            Spinner category = view.findViewById(R.id.upload_add_category);
            Spinner location = view.findViewById(R.id.upload_add_location);
            TextInputEditText description = view.findViewById(R.id.upload_add_description);

            String filterCategory = (String) category.getSelectedItem();
            String titleText = String.valueOf(title.getText());
            String filterLocation = (String) location.getSelectedItem();
            String descriptionText = String.valueOf(description.getText());
            Boolean isLost = lostChip.isChecked();
            Boolean isFound = foundChip.isChecked();

            if (titleText.isEmpty()) {
                Toast.makeText(getContext(), "Please fill in the title", Toast.LENGTH_SHORT).show();
                return;
            }
            if (filterCategory.equals("Please select category")) {
                Toast.makeText(getContext(), "Please enter category", Toast.LENGTH_SHORT).show();
                return;
            }
            if (filterLocation.equals("Please select location")) {
                Toast.makeText(getContext(), "Please enter location", Toast.LENGTH_SHORT).show();
                return;
            }
            if (descriptionText.isEmpty()) {
                Toast.makeText(getContext(), "Please enter Description", Toast.LENGTH_SHORT).show();
                return;
            }
            if(isLost == false && isFound == false){
                Toast.makeText(getContext(), "Please enter if found or lost", Toast.LENGTH_SHORT).show();
                return;
            }
            //Einfügen in Ergebnisliste - Methode
        }

    }


}
