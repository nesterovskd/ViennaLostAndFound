package at.ac.univie.hci.viennalostandfound.verification;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import at.ac.univie.hci.viennalostandfound.R;
import at.ac.univie.hci.viennalostandfound.chat.ChatActivity;
import at.ac.univie.hci.viennalostandfound.data.Data;
import at.ac.univie.hci.viennalostandfound.data.ResultItem;
import at.ac.univie.hci.viennalostandfound.user.User;

public class VerificationActivity extends AppCompatActivity {
    private String chatId;
    private final Data data = Data.getSingleInstance();
    private User loggedInUser;
    private String currentItemUserId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        loggedInUser = data.getLoggedInUser();

        ListView questionsList = findViewById(R.id.questions_list);
        Button submitButton = findViewById(R.id.submit_button);
        ImageButton exitButton = findViewById(R.id.exit_button);

        chatId = getIntent().getStringExtra("chatId");

        // Get the verification questions of the item
        List<String> verificationQuestions = new ArrayList<>();
        for (ResultItem item : data.getItemsDatenbank()) {
            if (Objects.equals(item.getUser().getId(), chatId)) {
                verificationQuestions.addAll(item.getVerificationQuestions());

                currentItemUserId = item.getUser().getId();
            }
        }

        List<EditText> answerFields = new ArrayList<>();
        VerificationAdapter adapter = new VerificationAdapter(this, verificationQuestions, answerFields);
        questionsList.setAdapter(adapter);

        submitButton.setOnClickListener(v -> {
            if (checkVerificationAnswers()) {
                loggedInUser.addVerificationId(currentItemUserId);

                Intent intent = new Intent(VerificationActivity.this, ChatActivity.class);
                intent.putExtra("chatId", chatId);
                intent.putExtra("chatName", getIntent().getStringExtra("chatName"));
                intent.putExtra("profilePictureId", getIntent().getIntExtra("profilePictureId", R.drawable.ic_profile_foreground));
                startActivity(intent);
                finish();
            }
        });

        exitButton.setOnClickListener(v -> finish());
    }

    private boolean checkVerificationAnswers() {
        // for simplicity always true
        return true;
    }
}

