package at.ac.univie.hci.viennalostandfound.upload;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import at.ac.univie.hci.viennalostandfound.R;

public class UploadFragment extends Fragment {

    public UploadFragment() {

    }


    @Nullable
   // @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_upload, container, false);
    }

}
