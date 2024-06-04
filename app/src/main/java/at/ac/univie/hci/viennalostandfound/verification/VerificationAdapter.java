package at.ac.univie.hci.viennalostandfound.verification;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

import at.ac.univie.hci.viennalostandfound.R;

public class VerificationAdapter extends BaseAdapter {
    private final Context context;
    private final List<String> questions;
    private final List<EditText> answerFields;

    public VerificationAdapter(Context context, List<String> questions, List<EditText> answerFields) {
        this.context = context;
        this.questions = questions;
        this.answerFields = answerFields;
    }

    @Override
    public int getCount() {
        return questions.size();
    }

    @Override
    public Object getItem(int position) {
        return questions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_verification_question, parent, false);
        }

        TextView questionText = convertView.findViewById(R.id.verification_question);
        EditText answerField = convertView.findViewById(R.id.verification_answer);

        questionText.setText("Question " + (position + 1) + ":\n" + questions.get(position));
        answerFields.add(answerField);

        return convertView;
    }
}
