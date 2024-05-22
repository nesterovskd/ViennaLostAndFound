package at.ac.univie.hci.viennalostandfound.search;

public class ResultItem {
    private int result_img_ID;
    private String result_item_txt;

    public ResultItem(int result_img_ID, String result_item_txt) {
        this.result_img_ID = result_img_ID;
        this.result_item_txt = result_item_txt;
    }

    public int getImageResId() {
        return result_img_ID;
    }

    public String getText() {
        return result_item_txt;
    }
}

