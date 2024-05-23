package at.ac.univie.hci.viennalostandfound.search;

public class ResultItem {
    private int img_ID;
    private String description;
    private String title;
    private String category;
    private String location;
    private String date;
    private boolean found;

    private String creatorUsername;


    public ResultItem(int result_img_ID, String result_item_txt) {
        this.img_ID = result_img_ID;
        this.description = result_item_txt;
    }

    public int getImageResId() {
        return img_ID;
    }

    public String getText() {
        return description;
    }
}

