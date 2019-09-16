package com.helloworld.robar;

// https://appsandbiscuits.com/listview-tutorial-android-12-ccef4ead27cc

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

    // Amount of drinks ordered
    private final int[] amountArray;

    private int totalAmount;

    // Constructor
    public ListAdapter (Activity context, String[] orderArrayParam, String[] infoArrayParam, Integer[] imgArrayParam, int[] amountArrayParam) {
        super (context, R.layout.listview_row, orderArrayParam);
        this.context = context;
        this.imgArray = imgArrayParam;
        this.orderArray = orderArrayParam;
        this.infoArray = infoArrayParam;
        this.amountArray = amountArrayParam;
    }

    public View getView (final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        final View rowView = inflater.inflate(R.layout.listview_row, null, true);

        // gets references to objects in the listview_row.xml file
        TextView orderTextField = rowView.findViewById(R.id.txtOrder);
        TextView infoTextField = rowView.findViewById(R.id.txtOrderInfo);
        ImageView imgView = rowView.findViewById(R.id.imgOrder);

        final TextView amountTextField = rowView.findViewById(R.id.txtAmount);
        amountTextField.setText(Integer.toString(amountArray[position]));

        final Button addButton = rowView.findViewById(R.id.btnAdd);
        final Button removeButton = rowView.findViewById(R.id.btnRemove);

        final LinearLayout infoLayout = rowView.findViewById(R.id.pnlInfo);

        addButton.setEnabled(totalAmount < 6);
        removeButton.setEnabled(amountArray[position] > 0);

        infoLayout.setVisibility(infoLayout.GONE);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalAmount + 1 == 6) {
                    notifyDataSetChanged();
                }
                if (amountArray[position] == 0) {
                    removeButton.setEnabled(true);
                }
                totalAmount++;
                amountArray[position]++;
                amountTextField.setText(Integer.toString(amountArray[position]));
            }
        });

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalAmount == 6) {
                    notifyDataSetChanged();
                }
                if (amountArray[position] - 1 == 0) {
                    removeButton.setEnabled(false);
                }
                totalAmount--;
                amountArray[position]--;
                amountTextField.setText(Integer.toString(amountArray[position]));
            }
        });

        // Sets values of objects to values of arrays
        orderTextField.setText(orderArray[position]);
        infoTextField.setText(infoArray[position]);
        imgView.setImageResource(imgArray[position]);

        return rowView;
    }
}
