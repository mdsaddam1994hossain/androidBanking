package com.example.bankproject2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TransferAdapter extends BaseAdapter {

    String[] transferService;
    int[] methodimage;
    Context context;
    LayoutInflater inflater;

    public TransferAdapter(String[] transferService, int[] methodimage, Context context) {
        this.transferService = transferService;
        this.methodimage = methodimage;
        this.context = context;
    }

    @Override
    public int getCount() {
        return transferService.length;
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
            convertView = inflater.inflate(R.layout.transfer_method_list_layout,parent,false);
        }
        ImageView imageView = convertView.findViewById(R.id.transferImageId);
        TextView textView = convertView.findViewById(R.id.transferTextId);

        imageView.setImageResource(methodimage[position]);
        textView.setText(transferService[position]);
        return convertView;
    }
}
