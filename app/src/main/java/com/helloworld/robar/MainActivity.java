package com.helloworld.robar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    // Array of order names
    String[] orderArray = {"Order 1", "Order 2", "Order 3", "Order 4", "Order 5", "Order 6", "Order 7"};

    // Array of information about the orders
    String [] infoArray = {"Order 1 info", "Order 2 info", "Order 3 info", "Order 4 info", "Order 5 info", "Order 6 info", "Order 7 info"};

    // Array of images to go with each order
    Integer[] imgArray = {R.drawable.drink1,
            R.drawable.drink2,
            R.drawable.drink3,
            R.drawable.drink4,
            R.drawable.drink5,
            R.drawable.drink6,
            R.drawable.drink7};

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListAdapter orderListAdapter = new ListAdapter (this, orderArray, infoArray, imgArray);

        listView = (ListView) findViewById(R.id.lvOrders);
        listView.setAdapter(orderListAdapter);
    }

}
