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
    String [] infoArray = {"Apple juice: 3 parts \nOrange juice: 3 parts \nGuava juice: 1 part",
            "Apple juice: 2 parts \nGuava juice: 3 parts \nGrenadine: 1 part",
            "Grenadine: 2 parts \nFruit base: 2 parts \nApple juice: 2 parts",
            "Apple juice: 2 parts \nGrenadine: 1 part \nFruit base: 1 part \nGuava juice: 2 parts",
            "Apple juice: 2 parts \nFruit base: 2 parts \nGinger ale: 2 parts",
            "Fruit base: 2 parts \nGrenadine: 1 part \nGinger ale: 3 parts",
            "Apple juice: 2 parts \nOrange juice: 2 parts \nGuava juice: 1 part \nFruit base: 2 parts"};

//        String [] infoArray = {"3 parts apple juice, 3 parts orange juice, 1 part guava juice",
//            "2 parts apple juice, 3 parts guava juice, 1 part grenadine",
//            "2 parts grenadine, 2 parts fruit base, 2 parts apple juice",
//            "2 parts apple juice, 1 part grenadine, 1 part fruit base, 2 parts guava juice",
//            "2 parts apple juice, 2 parts fruit base, 2 parts ginger ale",
//            "2 parts fruit base, 1 part grenadine, 3 parts ginger ale",
//            "2 parts apple juice, 2 parts orange juice, 1 part guava juice, 2 parts fruit base"};

    // Array of images to go with each order
    Integer[] imgArray = {R.drawable.tropicano1,
            R.drawable.guava_crush2,
            R.drawable.magenta_galaxy3,
            R.drawable.sunset_elixir4,
            R.drawable.sparkling_sunrise5,
            R.drawable.ginger_revenge6,
            R.drawable.fruit_splash7};

    int[] amountArray = new int[imgArray.length];

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Globals.prevPos = -1;

        final ListAdapter orderListAdapter = new ListAdapter (this, orderArray, infoArray, imgArray, amountArray);

        listView = findViewById(R.id.lvOrders);
        listView.setAdapter(orderListAdapter);

        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,  int position, long id){
                LinearLayout infoLayout = findViewById(R.id.pnlInfo);
                orderListAdapter.notifyDataSetChanged();
                infoLayout.setVisibility(infoLayout.VISIBLE);
            }
        });
    }

}
