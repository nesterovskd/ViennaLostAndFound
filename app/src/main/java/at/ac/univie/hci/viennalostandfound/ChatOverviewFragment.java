package at.ac.univie.hci.viennalostandfound;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChatOverviewFragment extends Fragment {

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
        List<String> dummyChats = new ArrayList<>(Arrays.asList("Chat 1", "Chat 2", "Chat 3"));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, dummyChats);
        chatsList.setAdapter(adapter);

        chatsList.setOnItemClickListener((parent, view1, position, id) -> {
            Intent intent = new Intent(getActivity(), ChatActivity.class);

            // Set chatId
            intent.putExtra("chatId", "Chat " + position+1);

            // Set chatName
            intent.putExtra("chatName", dummyChats.get(position));

            startActivity(intent);
        });
    }
}