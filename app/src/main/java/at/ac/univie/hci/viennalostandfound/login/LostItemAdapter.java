package at.ac.univie.hci.viennalostandfound.login;

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

public class LostItemAdapter extends BaseAdapter implements View.OnClickListener{
    Context context;
    List<ResultItem> resultItemList;
    LayoutInflater inflater;
    private LostItemAdapter.OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(LostItemAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    public LostItemAdapter(Context context, List<ResultItem> resultItemList) {
        this.context = context;
        this.resultItemList = resultItemList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return resultItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return resultItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.lost_item, parent, false);
        }

        ResultItem item = (ResultItem) getItem(position);

        ImageView imageView = convertView.findViewById(R.id.lost_item_image);
        TextView textView = convertView.findViewById(R.id.lost_item_text);
        TextView dateView = convertView.findViewById(R.id.lost_item_date);
        TextView categoryView = convertView.findViewById(R.id.lost_item_category);

        imageView.setImageResource(item.getImageResId());
        textView.setText(item.getTitle());
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
