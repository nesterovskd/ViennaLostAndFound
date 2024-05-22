package at.ac.univie.hci.viennalostandfound.search;

import java.util.Date;

public class SearchItem {
    String searchText;
    boolean found;
    String place;
    String category;
    String date;

    public SearchItem(String searchText, boolean found, String place, String category, String date) {
        this.searchText = searchText;
        this.found = found;
        this.place = place;
        this.category = category;
        this.date = date;
    }

}
