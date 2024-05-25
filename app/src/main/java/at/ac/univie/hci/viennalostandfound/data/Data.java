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
    public void addItemLostItems(ResultItem item){myLostItems.add(item);}
    public void addItemFoundItems(ResultItem item){myFoundItems.add(item);}

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
       // lost items
        itemsDatenbank.add(new ResultItem(R.drawable.lost_item_1, "Lost Wallet near Stadtpark.", "Lost Wallet", "Wallets", "1\u02e2\u1d57 District", "2024-04-23", true, usersList.get(0)));
        itemsDatenbank.add(new ResultItem(R.drawable.lost_item_3, "Lost phone at campus", "Lost Phone", "Electronics", "9\u1d57\u02b0 District", "2024-04-30", true, usersList.get(2)));
        itemsDatenbank.add(new ResultItem(R.drawable.lost_item_5, "Lost umbrella", "Lost Umbrella", "Umbrellas", "7\u1d57\u02b0 District", "2024-05-23", true, usersList.get(1)));
        itemsDatenbank.add(new ResultItem(R.drawable.lost_glasses, "Lost glasses on bus", "Lost Glasses", "Jewelry", "1\u02e2\u1d57 District", "2024-04-19", true, usersList.get(1)));

//        // found items
        itemsDatenbank.add(new ResultItem(R.drawable.found_bottle, "Found keys near the Burggarten Park", "Found Keys", "Keys", "2\u207f\u1d48", "2024-05-20", false, usersList.get(1)));
        itemsDatenbank.add(new ResultItem(R.drawable.lost_item_7, "Found necklace at the FitInn gym", "Found Necklace", "Necklace", "3\u02b3\u1d48", "2024-05-05", false, usersList.get(1)));
//        // profile lost and found items
        myFoundItems.add(new ResultItem(R.drawable.found_ring, "Found Ring in the Long Hall pub", "Found Ring", "Jewelry", "9\u1d57\u02b0", "2024-05-23", false, usersList.get(2)));
        myLostItems.add(new ResultItem(R.drawable.lost_phone, "I lost my Phone near Westbahnhof Station", "Lost Phone", "Electronics", "6\u1d57\u02b0", "2024-05-04", true, usersList.get(0)));

    }

}