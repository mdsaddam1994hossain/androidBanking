package com.example.bankproject2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bankproject2.R;

public class RechargeCustomAdapter extends BaseAdapter {
    int[] rechargeImage;
    String[] operatorName;
    Context context;
    LayoutInflater inflater;

    public RechargeCustomAdapter(int[] rechargeImage, String[] operatorName, Context context) {
        this.rechargeImage = rechargeImage;
        this.operatorName = operatorName;
        this.context = context;
    }

    @Override
    public int getCount() {
        return operatorName.length;
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
            convertView = inflater.inflate(R.layout.recharge_sample_layout,parent,false);
        }
        ImageView imageView = convertView.findViewById(R.id.rechargeImageId);
        TextView textView = convertView.findViewById(R.id.rechargeTextId);

        imageView.setImageResource(rechargeImage[position]);
        textView.setText(operatorName[position]);
        return convertView;
    }
}
