package com.LBD.app;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by quoc on 12/13/13.
 */
public class DataAdapter extends BaseAdapter {

    Context myContex;
    private LayoutInflater myInflater;
    private ArrayList<ArrayList<String>> myTable;

    public DataAdapter(Context c, ArrayList<ArrayList<String>> table) {
        myContex = c;
        myInflater = LayoutInflater.from(c);
        myTable = table;
    }

    public int getCount() {
        return myTable.size();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        LayoutInflater mInflater = (LayoutInflater) myContex.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(com.LBD.app.R.layout.fragment_customgrid, null);
            holder = new ViewHolder();

            holder.txtTime = (TextView) convertView.findViewById(com.LBD.app.R.id.txtTime);
            //holder.txtTime.setPadding(10, 10, 10, 10);
            holder.txtTime.setPadding(0,10,0,10);

            holder.txtFirstTeam = (TextView) convertView.findViewById(com.LBD.app.R.id.txtFirstTeam);
            //holder.txtFirstTeam.setPadding(10, 10, 10, 10);
            holder.txtFirstTeam.setPadding(0,10,0,10);

            holder.txtResult = (TextView) convertView.findViewById(com.LBD.app.R.id.txtResult);
            //holder.txtResult.setPadding(10, 10, 10, 10);
            holder.txtResult.setPadding(0,10,0,10);

            holder.txtSecondTeam = (TextView) convertView.findViewById(com.LBD.app.R.id.txtSecondTeam);
            //holder.txtSecondTeam.setPadding(10, 10, 10, 10);
            holder.txtSecondTeam.setPadding(0,10,0,10);

            convertView.setTag(holder);


        } else {
            holder = (ViewHolder) convertView.getTag();
        }
            if (myTable.get(position).get(0) == "0") {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.weight= 1;
                holder.txtTime.setText(myTable.get(position).get(1));
                holder.txtTime.setLayoutParams(params);
                holder.txtFirstTeam.setText("");
                holder.txtResult.setText("");
                holder.txtSecondTeam.setText("");
            } else {
                LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params1.weight= 3;
                holder.txtTime.setLayoutParams(params1);
                holder.txtTime.setText(myTable.get(position).get(1));
                holder.txtFirstTeam.setText(myTable.get(position).get(2));
                holder.txtResult.setText(myTable.get(position).get(3));
                holder.txtSecondTeam.setText(myTable.get(position).get(4));
            }
        return convertView;
    }

    public long getItemId(int position) {
        return position;
    }

    public Object getItem(int position) {
        return myTable.get(position);
    }

    static class ViewHolder {
        TextView txtTime;
        TextView txtFirstTeam;
        TextView txtResult;
        TextView txtSecondTeam;
    }
}
