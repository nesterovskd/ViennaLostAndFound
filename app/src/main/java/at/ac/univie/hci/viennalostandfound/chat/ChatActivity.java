package at.ac.univie.hci.viennalostandfound.chat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.List;

import at.ac.univie.hci.viennalostandfound.R;

public class ChatActivity extends AppCompatActivity {
    private List<String> messages;
    private ArrayAdapter<String> adapter;
    private SharedPreferences sharedPreferences;
    private String chatId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        TextView headerTextView = findViewById(R.id.chat_header);
        ListView messagesList = findViewById(R.id.messages_list);
        EditText messageInput = findViewById(R.id.message_input);
        ImageView profilePicture = findViewById(R.id.profile_picture);

        Button sendButton = findViewById(R.id.send_button);
        Button exitButton = findViewById(R.id.exit_button);

        messages = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, messages);
        messagesList.setAdapter(adapter);

        // Set header text
        String chatName = getIntent().getStringExtra("chatName");
        headerTextView.setText(chatName);

        // Set profile picture
        int profilePictureId = getIntent().getIntExtra("profilePictureId", R.drawable.ic_profile_foreground);
        profilePicture.setImageResource(profilePictureId);

        // Load saved messages from specific chatId
        chatId = getIntent().getStringExtra("chatId");
        sharedPreferences = getSharedPreferences("MessagePrefs", MODE_PRIVATE);
        loadMessages();

        // Handle sendButton click
        sendButton.setOnClickListener(v -> {
            String message = messageInput.getText().toString();
            if (!message.isEmpty()) {
                messages.add(message);
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
        editor.remove("MessageStorageSet_" + chatId);
        editor.apply();
    }

    private void loadMessages() {
        Set<String> messageStorageSet = sharedPreferences.getStringSet("MessageStorageSet_" + chatId, new HashSet<>());
        messages.addAll(messageStorageSet);
        adapter.notifyDataSetChanged();
    }

    private void saveMessages() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Set<String> messageSet = new HashSet<>(messages);
        editor.putStringSet("MessageStorageSet_" + chatId, messageSet);
        editor.apply();
    }
}