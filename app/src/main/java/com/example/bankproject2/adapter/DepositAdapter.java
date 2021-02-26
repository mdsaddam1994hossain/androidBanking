package com.example.bankproject2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bankproject2.R;

public class DepositAdapter extends BaseAdapter {

    String[] depositService;
    int[] methodimage;
    Context context;
    LayoutInflater inflater;

    public DepositAdapter(String[] depositService, int[] methodimage, Context context) {
        this.depositService = depositService;
        this.methodimage = methodimage;
        this.context = context;
    }

    @Override
    public int getCount() {
        return depositService.length;
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
            convertView = inflater.inflate(R.layout.deposit_method_list_layout,parent,false);
        }
        ImageView imageView = convertView.findViewById(R.id.depoImageId);
        TextView textView = convertView.findViewById(R.id.depoTextId);

        imageView.setImageResource(methodimage[position]);
        textView.setText(depositService[position]);
        return convertView;
    }
}
