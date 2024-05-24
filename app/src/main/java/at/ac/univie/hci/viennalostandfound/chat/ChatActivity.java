package at.ac.univie.hci.viennalostandfound.chat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.List;

import at.ac.univie.hci.viennalostandfound.R;
import at.ac.univie.hci.viennalostandfound.data.Data;
import at.ac.univie.hci.viennalostandfound.user.User;

public class ChatActivity extends AppCompatActivity {
    private List<String> messages;
    private ChatMessageAdapter adapter;
    private SharedPreferences sharedPreferences;
    private String chatId;
    private static final String MessageKey = "MessageStorageSet_";
    private Data data = Data.getSingleInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        TextView headerTextView = findViewById(R.id.chat_header);
        ListView messagesList = findViewById(R.id.messages_list);
        EditText messageInput = findViewById(R.id.message_input);
        ShapeableImageView otherUserprofilePicture = findViewById(R.id.user_profile_picture);

        Button sendButton = findViewById(R.id.send_button);
        ImageButton exitButton = findViewById(R.id.exit_button);

        messages = new ArrayList<>();
        User loggedInUser = data.getLoggedInUser();
        adapter = new ChatMessageAdapter(this, loggedInUser, messages);
        messagesList.setAdapter(adapter);

        // Set header text
        String chatName = getIntent().getStringExtra("chatName");
        headerTextView.setText(chatName);

        // Set other user profile picture
        int profilePictureId = getIntent().getIntExtra("profilePictureId", R.drawable.ic_profile_foreground);
        otherUserprofilePicture.setImageResource(profilePictureId);

        // Load saved messages from specific chatId
        chatId = getIntent().getStringExtra("chatId");
        sharedPreferences = getSharedPreferences("MessagePrefs", MODE_PRIVATE);
        loadMessages();

        // Handle sendButton click
        sendButton.setOnClickListener(v -> {
            String message = messageInput.getText().toString();
            if (!message.isEmpty()) {
                if(loggedInUser != null) {
                    String userMessage = loggedInUser.getName() + ": \n" + message;
                    messages.add(userMessage);
                } else {
                    messages.add(message);
                }
                adapter.notifyDataSetChanged();
                saveMessages();
                messageInput.setText("");
            }
        });

        // Handle exitButton click
        exitButton.setOnClickListener(v -> finish());
    }

    private void clearMessages() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(MessageKey + chatId);
        editor.apply();
    }

    private void loadMessages() {
        Set<String> messageStorageSet = sharedPreferences.getStringSet(MessageKey + chatId, new HashSet<>());
        messages.addAll(messageStorageSet);
        adapter.notifyDataSetChanged();
    }

    private void saveMessages() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Set<String> messageSet = new HashSet<>(messages);
        editor.putStringSet(MessageKey + chatId, messageSet);
        editor.apply();
    }
}