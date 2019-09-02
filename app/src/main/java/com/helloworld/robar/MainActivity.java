package com.helloworld.robar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends AppCompatActivity {

    // Array of order names
    String[] orderArray = {"Tropicano",
            "Guava Crush",
            "Magenta Galaxy",
            "Sunset Elixir",
            "Sparkling Sunrise",
            "Ginger Revenge",
            "Fruit Splash"};

    // Array of information about the orders
    String [] infoArray = {"Apple juice \nOrange juice \nGuava juice",
            "Apple juice \nGuava juice \nGrenadine",
            "Grenadine \nFruit base \nApple juice",
            "Apple juice \nGrenadine \nFruit base \nGuava juice",
            "Apple juice \nFruit base \nGinger ale",
            "Fruit base \nGrenadine \nGinger ale",
            "Apple juice \nOrange juice \nGuava juice \nFruit base"};

    // Array of images to go with each order
    Integer[] imgArray = {R.drawable.drink1,
            R.drawable.drink2,
            R.drawable.drink3,
            R.drawable.drink4,
            R.drawable.drink5,
            R.drawable.drink6,
            R.drawable.drink7};

    int[] amountArray = new int[imgArray.length];

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Globals.prevPos = -1;

        final ListAdapter orderListAdapter = new ListAdapter (this, orderArray, infoArray, imgArray, amountArray);

        listView = (ListView) findViewById(R.id.lvOrders);
        listView.setAdapter(orderListAdapter);
        /*
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,  int position, long id){
                LinearLayout infoLayout = (LinearLayout) findViewById(R.id.pnlInfo);

                infoLayout.setVisibility(infoLayout.VISIBLE);

                if (Globals.prevPos > -1) {

                    infoLayout.setVisibility(orderListAdapter.getItem(Globals.prevPos).GONE);
                }

                Globals.prevPos = position;
            }
        }); */
    }

}
