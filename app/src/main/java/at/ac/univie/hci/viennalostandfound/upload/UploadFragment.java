package at.ac.univie.hci.viennalostandfound.upload;

import static at.ac.univie.hci.viennalostandfound.MainActivity.LOGIN_STATUS;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AutoCompleteTextView;
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

import java.time.LocalDateTime;

import at.ac.univie.hci.viennalostandfound.MainActivity;
import at.ac.univie.hci.viennalostandfound.R;
import at.ac.univie.hci.viennalostandfound.data.Data;
import at.ac.univie.hci.viennalostandfound.data.ResultItem;

public class UploadFragment extends Fragment {
    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView imageView;

    public UploadFragment() {

    }
private Data data= Data.getSingleInstance();

    @Nullable
   // @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_upload, container, false);


        String[] categories = {"ID",
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

        String[] locations = {"1. District", "2. District", "3. District", "4. District", "5. District", "6. District", "7. District", "8. District", "9. District", "10. District",
                "11. District", "12. District", "13. District", "14. District", "15. District", "16. District", "17. District", "18. District", "19. District", "20. District", "21. District", "22. District", "23. District",};

        // Adapter für Filter
        AutoCompleteTextView autoCompleteTextViewCategory;
        ArrayAdapter<String> adapterItemsCategory;

        autoCompleteTextViewCategory = view.findViewById(R.id.uploadCategory);
        adapterItemsCategory = new ArrayAdapter<>(requireContext(), R.layout.search_dropdown_item, categories);
        autoCompleteTextViewCategory.setAdapter(adapterItemsCategory);

        // Adapter für Orte
        AutoCompleteTextView autoCompleteTextViewStandort;
        ArrayAdapter<String> adapterItemsStandort;

        autoCompleteTextViewStandort = view.findViewById(R.id.uploadLocation);
        adapterItemsStandort = new ArrayAdapter<>(requireContext(), R.layout.search_dropdown_item, locations);
        autoCompleteTextViewStandort.setAdapter(adapterItemsStandort);


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
            TextInputEditText description = view.findViewById(R.id.upload_add_description);
            AutoCompleteTextView autoCompleteTextViewCategory;
            autoCompleteTextViewCategory = view.findViewById(R.id.uploadCategory);
            AutoCompleteTextView autoCompleteTextViewStandort;
            autoCompleteTextViewStandort = view.findViewById(R.id.uploadLocation);

            String filterCategory = String.valueOf(autoCompleteTextViewCategory.getText());
            String titleText = String.valueOf(title.getText());
            String filterLocation = String.valueOf(autoCompleteTextViewStandort.getText());
            String descriptionText = String.valueOf(description.getText());
            Boolean isLost = lostChip.isChecked();
            Boolean isFound = foundChip.isChecked();

            if (titleText.isEmpty()) {
                Toast.makeText(getContext(), "Please fill in the title", Toast.LENGTH_SHORT).show();
                return;
            }
            if (filterCategory.isEmpty()) {
                Toast.makeText(getContext(), "Please enter category", Toast.LENGTH_SHORT).show();
                return;
            }
            if (filterLocation.isEmpty()) {
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

            data.addItemDatenbank(new ResultItem(R.drawable.lost_item_2, descriptionText, titleText, filterCategory,filterLocation, LocalDateTime.now().toString(),isFound, data.getLoggedInUser()));
            Toast.makeText(getContext(), "Item was uploaded", Toast.LENGTH_SHORT).show();

            title.setText("");
            description.setText("");
            return;
        }

    }


}
