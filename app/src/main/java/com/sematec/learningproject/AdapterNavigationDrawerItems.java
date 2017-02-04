package com.sematec.learningproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Farnoosh on 3/2/2016.
 */
public class AdapterNavigationDrawerItems extends BaseAdapter {
    private Context mContext;
    private String[] titles;
    private int[] icons;
    private PublicMethods publicMethods;
    public AdapterNavigationDrawerItems(Context mContext, String[] titles, int[] icons) {
        this.mContext = mContext;
        this.titles = titles;
        this.icons = icons;
        publicMethods = new PublicMethods(mContext);
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = LayoutInflater.from(mContext).inflate(R.layout.navigator_drawer_item, parent, false);

        TextView txt_title = (TextView) rowView.findViewById(R.id.txt_title);
        ImageView img_icon = (ImageView) rowView.findViewById(R.id.img_icon);

        txt_title.setText( titles[position]);
        img_icon.setImageResource( icons[position]);
        txt_title.setTypeface(publicMethods.getTypeFace());

        return rowView;
    }
}
