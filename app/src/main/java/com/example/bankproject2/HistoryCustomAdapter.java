package com.example.bankproject2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class HistoryCustomAdapter extends BaseAdapter {

    List<String > operationtype;
    List<String > method;
    List<Double > amount;
    Context context;
    LayoutInflater inflater;

    public HistoryCustomAdapter( Context context , List<String> operationtype, List<String> method, List<Double> amount) {
        this.operationtype = operationtype;
        this.method = method;
        this.amount = amount;
        this.context = context;
    }

    @Override
    public int getCount() {
        return operationtype.size();
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
            convertView = inflater.inflate(R.layout.history_sample_layout,parent,false);
        }

        TextView opetype = convertView.findViewById(R.id.operationTypeId);
        TextView opmethod = convertView.findViewById(R.id.historymethodId);
        TextView hisamount = convertView.findViewById(R.id.historyAmountIds);

            opetype.setText(operationtype.get(position));
            opmethod.setText(method.get(position));
            hisamount.setText(amount.get(position).toString());
          //  hisamount.setText(amount.get(position));


        return convertView;
    }
}
