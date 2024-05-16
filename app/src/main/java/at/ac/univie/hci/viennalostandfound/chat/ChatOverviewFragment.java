package at.ac.univie.hci.viennalostandfound.chat;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import at.ac.univie.hci.viennalostandfound.MainActivity;
import at.ac.univie.hci.viennalostandfound.R;
import at.ac.univie.hci.viennalostandfound.user.User;
import at.ac.univie.hci.viennalostandfound.user.UserAdapter;

public class ChatOverviewFragment extends Fragment {
    private List<User> usersList;

    public ChatOverviewFragment() {
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat_overview, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ListView chatsList = view.findViewById(R.id.chats_list);

        // Get User Data
        if (getArguments() != null) {
            usersList = (List<User>) getArguments().getSerializable("usersList");
        } else {
            usersList = new ArrayList<>();
        }

        UserAdapter adapter = new UserAdapter(requireContext(), usersList);
        chatsList.setAdapter(adapter);

        chatsList.setOnItemClickListener((parent, view1, position, id) -> {
            Intent intent = new Intent(getActivity(), ChatActivity.class);

            // Pass chat ID
            intent.putExtra("chatId", String.valueOf(position));

            // Pass name of the chat
            intent.putExtra("chatName", usersList.get(position).getName());

            // Pass the profile picture ID
            intent.putExtra("profilePictureId", usersList.get(position).getProfilePictureId());

            startActivity(intent);
        });
    }
}