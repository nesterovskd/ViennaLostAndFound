package at.ac.univie.hci.viennalostandfound.verification;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import at.ac.univie.hci.viennalostandfound.R;
import at.ac.univie.hci.viennalostandfound.chat.ChatActivity;
import at.ac.univie.hci.viennalostandfound.data.Data;
import at.ac.univie.hci.viennalostandfound.data.ResultItem;
import at.ac.univie.hci.viennalostandfound.user.User;

public class VerificationActivity extends AppCompatActivity {
    private String chatId;
    private final Data data = Data.getSingleInstance();
    User loggedInUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        loggedInUser = data.getLoggedInUser();

        ListView questionsList = findViewById(R.id.questions_list);
        Button submitButton = findViewById(R.id.submit_button);

        chatId = getIntent().getStringExtra("chatId");

        // Get the verification questions of the item
        List<String> verificationQuestions = new ArrayList<>();
        for (ResultItem item : data.getItemsDatenbank()) {
            if (item.getUser().getId() == chatId) {
                verificationQuestions = item.getVerificationQuestions();
            }
        }

        // TODO
        if (verificationQuestions.isEmpty()) {
            verificationQuestions.add("Question 1");
            verificationQuestions.add("Question 2");
            verificationQuestions.add("Question 3");
        }

        List<EditText> answerFields = new ArrayList<>();
        VerificationAdapter adapter = new VerificationAdapter(this, verificationQuestions, answerFields);
        questionsList.setAdapter(adapter);

        submitButton.setOnClickListener(v -> {
            if (checkVerificationAnswers()) {
                loggedInUser.setVerified(true);

                Intent intent = new Intent(VerificationActivity.this, ChatActivity.class);
                intent.putExtra("chatId", chatId);
                intent.putExtra("chatName", getIntent().getStringExtra("chatName"));
                intent.putExtra("profilePictureId", getIntent().getIntExtra("profilePictureId", R.drawable.ic_profile_foreground));
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Incorrect answers. Please try again.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean checkVerificationAnswers() {
        //TODO
        return true;
    }
}

