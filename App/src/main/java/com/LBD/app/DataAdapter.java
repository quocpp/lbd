package com.LBD.app;

import android.content.Context;
import android.support.v7.appcompat.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by quoc on 12/13/13.
 */
public class DataAdapter extends BaseAdapter {

    Context myContex;
    private LayoutInflater myInflater;
    private ArrayList<ArrayList<String>> myTable;
    public DataAdapter(Context c, ArrayList<ArrayList<String>> table)
    {
        myContex = c;
        myInflater = LayoutInflater.from(c);
        myTable = table;
    }

    public int getCount()
    {
        return myTable.size();
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder=null;
        if(convertView == null)
        {
            convertView = myInflater.inflate(com.LBD.app.R.layout.fragment_customgrid,parent,false);
            holder = new ViewHolder();

            holder.txtTime = (TextView) convertView.findViewById(com.LBD.app.R.id.txtTime);
            holder.txtTime.setPadding(100, 10,10 , 10);

            holder.txtFirstTeam = (TextView) convertView.findViewById(com.LBD.app.R.id.txtFirstTeam);
            holder.txtFirstTeam.setPadding(100, 10, 10, 10);

            holder.txtResult = (TextView) convertView.findViewById(com.LBD.app.R.id.txtResult);
            holder.txtResult.setPadding(100, 10, 10, 10);

            holder.txtSecondTeam = (TextView) convertView.findViewById(com.LBD.app.R.id.txtSecondTeam);
            holder.txtSecondTeam.setPadding(100, 10, 10, 10);

            if (position == 0)
            {
                convertView.setTag(holder);
            }

        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        if(myTable.get(position).get(0) == "0")
        {
            holder.txtTime.setText(myTable.get(position).get(1));
            holder.txtFirstTeam.setText("");
            holder.txtResult.setText("");
            holder.txtSecondTeam.setText("");
        }
        else
        {
            holder.txtTime.setText(myTable.get(position).get(1));
            holder.txtFirstTeam.setText(myTable.get(position).get(2));
            holder.txtResult.setText(myTable.get(position).get(3));
            holder.txtSecondTeam.setText(myTable.get(position).get(4));
        }
        return convertView;
    }

    public long getItemId(int position)
    {
        return  position;
    }

    public Object getItem(int position)
    {
        return myTable.get(position);
    }

    static class ViewHolder
    {
        TextView txtTime;
        TextView txtFirstTeam;
        TextView txtResult;
        TextView txtSecondTeam;
    }
}
