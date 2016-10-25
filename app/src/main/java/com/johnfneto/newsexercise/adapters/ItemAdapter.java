package com.johnfneto.newsexercise.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.johnfneto.newsexercise.views.MainActivity;
import com.johnfneto.newsexercise.R;
import com.johnfneto.newsexercise.models.Items;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by johnneto on 23/10/16.
 */

public class ItemAdapter extends BaseAdapter {
    public static final String TAG = "ItemAdapter";
    private List<Items.Item> itemsList;
    private static LayoutInflater inflater = null;
    private final Context context;

    public ItemAdapter(Context context, List<Items.Item> itemsList) {
        this.context = context;
        this.itemsList = itemsList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if (itemsList == null)
            return 0;
        else
            return itemsList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        View vi = convertView;

        if (vi == null) {
            // inflate the layout
            vi = inflater.inflate(R.layout.list_item, parent, false);

            // set up the ViewHolder
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) vi.findViewById(R.id.title);
            viewHolder.description = (TextView) vi.findViewById(R.id.description);
            viewHolder.image = (ImageView) vi.findViewById(R.id.image);

            // store the holder with the view.
            vi.setTag(viewHolder);
        }
        else {
            // instead of calling findViewById()
            // used viewHolder
            viewHolder = (ViewHolder) vi.getTag();
        }

        Items.Item item = itemsList.get(position);

        viewHolder.title.setText(item.getTitle());
        viewHolder.description.setText(item.getDescription());

        int w = MainActivity.width / 5;

        Picasso.with(context).load(item.getImage()).resize(w, 0).into(viewHolder.image);

        return vi;
    }

    static class ViewHolder {
        TextView title;
        TextView description;
        ImageView image;
    }
}