package at.ac.univie.hci.viennalostandfound.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import at.ac.univie.hci.viennalostandfound.R;
import at.ac.univie.hci.viennalostandfound.data.ResultItem;

public class ResultAdapter extends BaseAdapter implements View.OnClickListener {

    private Context context;
    private List<ResultItem> items;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public ResultAdapter(Context context, List<ResultItem> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setItems(List<ResultItem> items) {
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.result_list_item, parent, false);
        }

        ResultItem item = (ResultItem) getItem(position);

        ImageView imageView = convertView.findViewById(R.id.item_image);
        TextView textView = convertView.findViewById(R.id.list_item_text);
        TextView userView = convertView.findViewById(R.id.list_item_user);
        TextView dateView = convertView.findViewById(R.id.list_item_date);
        TextView categoryView = convertView.findViewById(R.id.list_item_category);

        imageView.setImageResource(item.getImageResId());
        textView.setText(item.getTitle());
        userView.setText(item.getUserString());
        dateView.setText(item.getDate());
        categoryView.setText(item.getCategory());

        convertView.setOnClickListener(this);
        convertView.setTag(position);

        imageView.setOnClickListener(this);
        imageView.setTag(position);

        return convertView;
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        if (listener != null) {
            listener.onItemClick(position);
        }
    }


}
