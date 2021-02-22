package com.example.bankproject2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {
    String [] serviceName;
    Context context;
    LayoutInflater inflater;

    public CustomAdapter(String[] serviceName, Context context) {
        this.serviceName = serviceName;
        this.context = context;
    }

    @Override
    public int getCount() {
        return serviceName.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.samplelayout,parent,false);
            }

        TextView servicname = convertView.findViewById(R.id.sampleTextId);
        servicname.setText(serviceName[position]);

        return convertView;
    }
}
