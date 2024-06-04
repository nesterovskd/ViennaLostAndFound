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
import java.util.Objects;

import at.ac.univie.hci.viennalostandfound.R;
import at.ac.univie.hci.viennalostandfound.data.Data;
import at.ac.univie.hci.viennalostandfound.data.ResultItem;

public class UserAdapter extends ArrayAdapter<User> {
    private final Data data = Data.getSingleInstance();
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

        // Set the user info for the chats overview
        User user = getItem(position);
        ImageView profilePicture = convertView.findViewById(R.id.chat_profile_picture);
        TextView chatName = convertView.findViewById(R.id.chat_name);
        ImageView itemPicture = convertView.findViewById(R.id.chat_item_picture);

        if (user != null) {
            profilePicture.setImageResource(user.getProfilePictureId());
            chatName.setText(user.getName());

            // Set item picture in chat overview
            ResultItem item = findResultItemByUserId(user.getId());
            if (item != null) {
                itemPicture.setImageResource(item.getImg_ID());
            } else {
                itemPicture.setImageResource(R.drawable.ic_search_foreground);
            }
        }
        return convertView;
    }

    private ResultItem findResultItemByUserId(String userId) {
        List<ResultItem> itemsDatenbank = data.getItemsDatenbank();
        for (ResultItem item : itemsDatenbank) {
            if (item.getUser().getId() == userId) {
                return item;
            }
        }
        return null;
    }
}
