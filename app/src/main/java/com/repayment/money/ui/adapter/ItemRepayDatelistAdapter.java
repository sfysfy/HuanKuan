package com.repayment.money.ui.adapter;


import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.repayment.money.R;

public class ItemRepayDatelistAdapter extends BaseAdapter {

    private List<String> mEntities;

    private Context context;
    private LayoutInflater layoutInflater;

    public ItemRepayDatelistAdapter(Context context,List<String> entities) {
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
            convertView = layoutInflater.inflate(R.layout.item_repaydatelist, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((String)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    List<String> mStrings;
      private  ArrayAdapter<String>  mArrayAdapter;
    private void initializeViews(String entities, ViewHolder holder) {
        //TODO implement
        for (int i = 0; i < 31; i++) {
            mStrings.add(i + 1 + "");
        }
        mArrayAdapter=new ArrayAdapter<String>(context ,android.R.layout.simple_selectable_list_item ,mStrings);
        holder.lvDate.setAdapter(mArrayAdapter);
    }

    protected class ViewHolder {
        private ListView lvDate;

        public ViewHolder(View view) {
            lvDate = (ListView) view.findViewById(R.id.lv_date);
        }
    }
}
