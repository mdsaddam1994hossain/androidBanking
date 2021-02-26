package com.example.bankproject2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bankproject2.R;

public class HomeCustomAdapter extends BaseAdapter {
    String [] serviceName;
    Context context;
    int[] serviceIcon;
    LayoutInflater inflater;

    public HomeCustomAdapter(String[] serviceName, Context context, int[] serviceIcon) {
        this.serviceName = serviceName;
        this.context = context;
        this.serviceIcon = serviceIcon;
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
                convertView = inflater.inflate(R.layout.home_samplelayout,parent,false);
            }

        TextView servicname = convertView.findViewById(R.id.sampleTextId);
        ImageView imageView = convertView.findViewById(R.id.imageViewId);
        servicname.setText(serviceName[position]);
        imageView.setImageResource(serviceIcon[position]);


        return convertView;
    }
}
