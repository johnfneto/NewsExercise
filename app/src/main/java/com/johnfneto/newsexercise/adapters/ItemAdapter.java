package com.johnfneto.newsexercise.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.johnfneto.newsexercise.MainActivity;
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
        View vi=convertView;
        if (convertView == null) {
            vi = inflater.inflate(R.layout.list_item, null);
        }
        TextView title = (TextView) vi.findViewById(R.id.title);
        TextView description = (TextView) vi.findViewById(R.id.description);
        ImageView image = (ImageView) vi.findViewById(R.id.image);


        title.setText(itemsList.get(position).getTitle());
        description.setText(itemsList.get(position).getDescription());

        int w = MainActivity.width / 5;


        if (itemsList.get(position).getImageId() != 0) {
            image.setVisibility(View.VISIBLE);
            //Picasso.with(context).load(itemsList.get(position).getImageId()).into(image);
            Picasso.with(context).load(itemsList.get(position).getImageId()).resize(w, 0).into(image);

        }
        else
            image.setVisibility(View.GONE);


        return vi;
    }
}