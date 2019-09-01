package com.helloworld.robar;

// https://appsandbiscuits.com/listview-tutorial-android-12-ccef4ead27cc

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListAdapter extends ArrayAdapter {

    // To reference the activity
    private final Activity context;

    // To store the order images
    private final Integer[] imgArray;

    // To store the order names
    private final String[] orderArray;

    // To store the info appearing underneath each order
    private final String[] infoArray;

    // Constructor
    public ListAdapter (Activity context, String[] orderArrayParam, String[] infoArrayParam, Integer[] imgArrayParam) {
        super (context, R.layout.listview_row, orderArrayParam);
        this.context = context;
        this.imgArray = imgArrayParam;
        this.orderArray = orderArrayParam;
        this.infoArray = infoArrayParam;
    }

    public View getView (int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.listview_row, null, true);

        // gets references to objects in the listview_row.xml file
        TextView orderTextField = (TextView) rowView.findViewById(R.id.txtOrder);
        TextView infoTextField = (TextView) rowView.findViewById(R.id.txtOrderInfo);
        ImageView imgView = (ImageView) rowView.findViewById(R.id.imgOrder);

        // Sets values of objects to values of arrays
        orderTextField.setText(orderArray[position]);
        infoTextField.setText(infoArray[position]);
        imgView.setImageResource(imgArray[position]);

        return rowView;
    }
}
