package at.ac.univie.hci.viennalostandfound.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import at.ac.univie.hci.viennalostandfound.R;
import at.ac.univie.hci.viennalostandfound.user.User;

public class Data {
    private static Data singleInstance = null;
    List<ResultItem> itemsDatenbank = new ArrayList<ResultItem>();
    List<ResultItem> myLostItems= new ArrayList<ResultItem>();

    List<ResultItem> myFoundItems= new ArrayList<ResultItem>();
    private final List<User> usersList = new ArrayList<>();
    private static User loggedInUser;
    public List<ResultItem> getItemsDatenbank() {
        return itemsDatenbank;
    }
    public void addItemDatenbank(ResultItem item) {
        itemsDatenbank.add(item);
    }

    public List<ResultItem> getMyLostItems() {
        return myLostItems;
    }
    public List<ResultItem> getMyFoundItems() {
        return myFoundItems;
    }
    public static User getLoggedInUser() {
        return loggedInUser;
    }
    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }


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
        generateDummyUsers();
        createDummyData();
    }

    private void generateDummyUsers() {
        usersList.add(new User("Francesca Marino", "francesca.marino@gmail.com", R.drawable.profile_picture_1, "0"));
        usersList.add(new User("Marcel Meyer", "marcel.meyer@gmail.com", R.drawable.profile_picture_2,"1"));
        usersList.add(new User("David Sacks", "david.sacks@gmail.com", R.drawable.profile_picture_3,"3"));
    }
    public void createDummyLoginUser() {
        this.loggedInUser = new User("Lisi Knorr","lisi.knorr@gmail.com", R.drawable.profile_girl_with_cat, "10");
        this.loggedInUser.setPhoneNumber("+43 6667778495");
        this.loggedInUser.setHomeAddress("Gersthofer Strasse 100/1/2");

        this.loggedInUser.setFoundCount("15");
        this.loggedInUser.setLostCount("9");
        this.loggedInUser.setAppreciationCount("12");
    }
    private void createDummyData() {
// itemsDatenbank.add(new ResultItem(R.drawable.lost_item_1, "I lost my Wallet", "Wallet"... ));
// itemsDatenbank.add(new ResultItem(R.drawable.found_bottle, "Result 2"));
//itemsDatenbank.add(new ResultItem(R.drawable.scarf, "Result 3"));

        itemsDatenbank.add(new ResultItem(R.drawable.lost_item_1, "I lost my Wallet", "Lost Wallet", "Wallet", "Main Street", "2024-05-23", true, usersList.get(0)));

        itemsDatenbank.add(new ResultItem(R.drawable.found_bottle, "Found keys near the park", "Found Keys", "Keys", "Park", "2024-05-23", false, usersList.get(1)));

        itemsDatenbank.add(new ResultItem(R.drawable.lost_item_3, "Lost phone in the cafeteria", "Lost Phone", "Phone", "Cafeteria", "2024-05-23", true, usersList.get(2)));

        myFoundItems.add(new ResultItem(R.drawable.lost_item_3, "Lost phone in the cafeteria", "Lost Phone", "Phone", "Cafeteria", "2024-05-23", true, usersList.get(2)));
        myLostItems.add(new ResultItem(R.drawable.lost_item_1, "I lost my Wallet", "Lost Wallet", "Wallet", "Main Street", "2024-05-23", true, usersList.get(0)));
// itemsDatenbank.add(new ResultItem(R.drawable.lost_item_4, "Found backpack on the bus", "Found Backpack", "Backpack", "Bus", "2024-05-23", false, "User4"));

        itemsDatenbank.add(new ResultItem(R.drawable.lost_item_5, "Lost umbrella at the cinema", "Lost Umbrella", "Umbrella", "Cinema", "2024-05-23", true, usersList.get(1)));


// itemsDatenbank.add(new ResultItem(R.drawable.lost_item_6, "Found glasses near the library", "Found Glasses", "Glasses", "Library", "2024-05-23", false, "User6"));

        itemsDatenbank.add(new ResultItem(R.drawable.lost_item_7, "Lost necklace at the gym", "Lost Necklace", "Necklace", "Gym", "2024-05-23", true, usersList.get(1)));
    }

}