package at.ac.univie.hci.viennalostandfound.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

import at.ac.univie.hci.viennalostandfound.R;
import at.ac.univie.hci.viennalostandfound.user.User;

public class ChatMessageAdapter extends BaseAdapter {
    private final Context context;
    private final List<String> messages;
    private final User loggedInUser;

    public ChatMessageAdapter(Context context, User loggedInUser, List<String> messages) {
        this.context = context;
        this.loggedInUser = loggedInUser;
        this.messages = messages;
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_message, parent, false);
        }

        ShapeableImageView loggedInUserProfilePicture = convertView.findViewById(R.id.message_profile_picture);
        TextView messageText = convertView.findViewById(R.id.message_text);

        String message = messages.get(position);
        messageText.setText(message);
        loggedInUserProfilePicture.setImageResource(loggedInUser.getProfilePictureId());

        return convertView;
    }
}
