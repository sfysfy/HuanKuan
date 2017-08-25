package com.repayment.money.ui.adpter;


import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.repayment.money.R;

public class ItemTypelistAdapter extends BaseAdapter {

    private List<String> mEntities;

    private Context context;
    private LayoutInflater layoutInflater;

    public ItemTypelistAdapter(Context context,List<String> entities) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);        this.mEntities=entities;
    }

    @Override
    public int getCount() {
        return mEntities.size();
    }

    @Override
    public String getItem(int position) {
        return mEntities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_typelist, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((String)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(String entities, ViewHolder holder) {
        //TODO implement
    }

    protected class ViewHolder {
        private ListView lvType;

        public ViewHolder(View view) {
            lvType = (ListView) view.findViewById(R.id.lv_type);
        }
    }
}
