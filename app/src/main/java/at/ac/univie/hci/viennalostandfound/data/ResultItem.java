package at.ac.univie.hci.viennalostandfound.data;

import java.io.Serializable;

import at.ac.univie.hci.viennalostandfound.user.User;

public class ResultItem implements Serializable {
    private int img_ID;
    private String description;
    private String title;
    private String category;
    private String location;
    private String date;
    private boolean found;

    private User user;

    public String getUserString() {
        return user.getName();
    }

    public ResultItem(int result_img_ID, String result_item_txt) {
        this.img_ID = result_img_ID;
        this.description = result_item_txt;
    }

    public int getImg_ID() {
        return img_ID;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public User getUser() {
        return user;
    }

    public String getCategory() {
        return category;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }

    public boolean isFound() {
        return found;
    }


    public ResultItem(int img_ID, String description, String title, String category, String location, String date, boolean found, User user) {
        this.img_ID = img_ID;
        this.description = description;
        this.title = title;
        this.category = category;
        this.location = location;
        this.date = date;
        this.found = found;
        this.user=user;
    }

    public int getImageResId() {
        return img_ID;
    }

    public String getText() {
        return description;
    }
}

