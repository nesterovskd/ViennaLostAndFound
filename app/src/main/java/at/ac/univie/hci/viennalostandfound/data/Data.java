package at.ac.univie.hci.viennalostandfound.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import at.ac.univie.hci.viennalostandfound.R;
import at.ac.univie.hci.viennalostandfound.search.ResultItem;
import at.ac.univie.hci.viennalostandfound.user.User;

public class Data {
    private static Data singleInstance = null;
    public List<ResultItem> getItemsDatenbank() {
        return itemsDatenbank;
    }
    public void addItemDatenbank(ResultItem item) {
        itemsDatenbank.add(item);
    }

    List<ResultItem> itemsDatenbank = new ArrayList<ResultItem>();

    private final List<User> usersList = new ArrayList<>();

    public Serializable getUsersList() {
        return (Serializable) usersList;
    }

    public static Data getSingleInstance() {
        if(singleInstance ==null)
        {
            singleInstance = new Data();
        }
        return singleInstance;
    }

    private Data() {
        createDummyData();
        generateDummyUsers();
    }

    private void generateDummyUsers() {
        usersList.add(new User("Francesca Marino", "francesca.marino@gmail.com", R.drawable.profile_picture_1));
        usersList.add(new User("Marcel Meyer", "marcel.meyer@gmail.com", R.drawable.profile_picture_2));
        usersList.add(new User("David Sacks", "david.sacks@gmail.com", R.drawable.profile_picture_3));
    }

    private void createDummyData() {


        //    itemsDatenbank.add(new ResultItem(R.drawable.lost_item_1, "I lost my Wallet", "Wallet"... ));
       // itemsDatenbank.add(new ResultItem(R.drawable.found_bottle, "Result 2"));
        //itemsDatenbank.add(new ResultItem(R.drawable.scarf, "Result 3"));

        itemsDatenbank.add(new ResultItem(R.drawable.lost_item_1, "I lost my Wallet", "Lost Wallet", "Wallet", "Main Street", "2024-05-23", true, "User1"));

        itemsDatenbank.add(new ResultItem(R.drawable.found_bottle, "Found keys near the park", "Found Keys", "Keys", "Park", "2024-05-23", false, "User2"));

        itemsDatenbank.add(new ResultItem(R.drawable.lost_item_3, "Lost phone in the cafeteria", "Lost Phone", "Phone", "Cafeteria", "2024-05-23", true, "User3"));


      //  itemsDatenbank.add(new ResultItem(R.drawable.lost_item_4, "Found backpack on the bus", "Found Backpack", "Backpack", "Bus", "2024-05-23", false, "User4"));

        itemsDatenbank.add(new ResultItem(R.drawable.lost_item_5, "Lost umbrella at the cinema", "Lost Umbrella", "Umbrella", "Cinema", "2024-05-23", true, "User5"));


      //  itemsDatenbank.add(new ResultItem(R.drawable.lost_item_6, "Found glasses near the library", "Found Glasses", "Glasses", "Library", "2024-05-23", false, "User6"));

        itemsDatenbank.add(new ResultItem(R.drawable.lost_item_7, "Lost necklace at the gym", "Lost Necklace", "Necklace", "Gym", "2024-05-23", true, "User7"));
    }

}
