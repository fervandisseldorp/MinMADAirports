package com.f.minmadairports;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CursorTreeAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ferdinand on 27-10-2017.
 */

public class AirportsExpAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<String> headers;
    private HashMap<String, List<Airport>> airports;



    public AirportsExpAdapter(Context context, List<String> headers, HashMap<String, List<Airport>> airports) {
        this.context = context;
        this.headers = headers;
        this.airports = airports;
    }


    @Override
    public int getGroupCount() {
        return this.headers.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        String headers = this.headers.get(groupPosition);
        System.out.println("children count: " + airports.get(headers).size() );
        return this.airports.get(headers).size();

    }

    @Override
    public Object getGroup(int groupPosition) {
        String header = this.headers.get(groupPosition);
        return header;
    }

    @Override
    public Airport getChild(int groupPosition, int childPosition) {
        String header = headers.get(groupPosition);
        List<Airport> childs = this.airports.get(header);
        Airport child = childs.get(childPosition);
        return child;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        String headerTitle = (String)getGroup(groupPosition);
        if(convertView == null) {
            LayoutInflater inflater =
                    (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_header, null);
        }
        TextView headerText = (TextView) convertView.findViewById(R.id.countryHeaderTV);
        headerText.setText(headerTitle);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if( convertView == null ){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_row,null);
        }

        TextView icao = (TextView) convertView.findViewById(R.id.airportIcaoTV);
        TextView name = (TextView) convertView.findViewById(R.id.airportNameTV);

        Airport airport = getChild(groupPosition, childPosition);

        icao.setText(airport.getIcao());
        name.setText(airport.getName());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
