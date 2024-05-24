package at.ac.univie.hci.viennalostandfound.search;

public class SearchItem {
    String searchText;
    boolean found;
    String location;

    public String getSearchText() {
        return searchText;
    }

    public boolean isFound() {
        return found;
    }

    public String getLocation() {
        return location;
    }

    public String getCategory() {
        return category;
    }

    public String getDate() {
        return date;
    }

    String category;
    String date;

    public SearchItem(String searchText, boolean found, String location, String category, String date) {
        this.searchText = searchText;
        this.found = found;
        this.location = location;
        this.category = category;
        this.date = date;
    }

}
