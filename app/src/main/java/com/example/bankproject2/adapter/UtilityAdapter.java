package com.example.bankproject2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bankproject2.R;

public class UtilityAdapter extends BaseAdapter {

    String[] utilityService;
    int[] utilityimage;
    Context context;
    LayoutInflater inflater;

    public UtilityAdapter(String[] utilityService, int[] utilityimage, Context context) {
        this.utilityService = utilityService;
        this.utilityimage = utilityimage;
        this.context = context;
    }

    @Override
    public int getCount() {
        return utilityService.length;
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
            convertView = inflater.inflate(R.layout.utility_sample_layout,parent,false);
        }
        ImageView imageView = convertView.findViewById(R.id.utilityImageId);
        TextView textView = convertView.findViewById(R.id.utilityTextId);

        imageView.setImageResource(utilityimage[position]);
        textView.setText(utilityService[position]);
        return convertView;
    }
}
