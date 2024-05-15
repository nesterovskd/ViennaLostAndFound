package at.ac.univie.hci.viennalostandfound.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

import at.ac.univie.hci.viennalostandfound.R;

public class UserAdapter extends ArrayAdapter<User> {
    private final Context context;
    private final List<User> usersList;
    public UserAdapter(@NonNull Context context, List<User> usersList) {
        super(context, R.layout.item_user, usersList);
        this.context = context;
        this.usersList = usersList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false);
        }

        User user = getItem(position);
        ImageView profilePicture = convertView.findViewById(R.id.profile_picture);
        TextView chatName = convertView.findViewById(R.id.chat_name);

        if (user != null) {
            profilePicture.setImageResource(user.getProfilePictureId());
            chatName.setText(user.getName());
        }

        return convertView;
    }
}
