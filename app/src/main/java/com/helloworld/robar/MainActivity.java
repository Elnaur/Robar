
package com.helloworld.robar;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.bluetooth.BluetoothAdapter;
import android.widget.Toast;
import android.util.Log;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Set;
import java.util.UUID;

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

    private final UUID PORT_UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");//Serial Port Service ID
    private BluetoothDevice device;
    private BluetoothSocket socket;
    private OutputStream outputStream;
    boolean deviceConnected = false;
    boolean stopThread;

    public boolean BTinit()
    {
        boolean found = false;
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            Toast.makeText(getApplicationContext(),"Device doesn't support Bluetooth",Toast.LENGTH_SHORT).show();
        }
        if(!bluetoothAdapter.isEnabled())
        {
            Intent enableAdapter = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableAdapter, 0);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Set<BluetoothDevice> bondedDevices = bluetoothAdapter.getBondedDevices();
        if(bondedDevices.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Please pair the with robot first",Toast.LENGTH_SHORT).show();
        }
        else
        {
            for (BluetoothDevice iterator : bondedDevices)
            {
                String DEVICE_ADDRESS = "00:18:E4:35:38:D6";
                if(iterator.getAddress().equals(DEVICE_ADDRESS))
                {
                    device = iterator;
                    found = true;
                    break;
                }
            }
        }
        return found;
    }

    public boolean BTconnect()
    {
        boolean connected = true;
        try {
            socket = device.createRfcommSocketToServiceRecord(PORT_UUID);
            socket.connect();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"Failed to connect to ROBAR",Toast.LENGTH_SHORT).show();
            connected = false;
        }
        if(connected)
        {
            try {
                outputStream = socket.getOutputStream();
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(),"Failed to connect to ROBAR",Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
        return connected;
    }

    public void sendOrders() {
        try {
            byte[] out = new byte[amountArray.length+1];
            for (int i = 0; i < amountArray.length; i++) {
                out[i] = (byte)(amountArray[i] + (int)'0');
            }
            out[out.length-1] = '\n';
            outputStream.write(out);
            outputStream.flush();
            Toast.makeText(getApplicationContext(),"Orders sent to ROBAR. Processing...",Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"Failed to send orders to ROBAR",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListAdapter orderListAdapter = new ListAdapter (this, orderArray, infoArray, imgArray, amountArray);

        listView = findViewById(R.id.lvOrders);
        listView.setAdapter(orderListAdapter);

        final Button connectButton = findViewById(R.id.btnConnect);
        final Button disconnectButton = findViewById(R.id.btnDisconnect);
        final Button submitButton = findViewById(R.id.btnSubmit);

        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!deviceConnected) {
                    if (BTinit()) {
                        if (BTconnect()) {
                            deviceConnected = true;
                            Toast.makeText(getApplicationContext(), "Connected to ROBAR", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Failed to connect to ROBAR", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        disconnectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (deviceConnected) {
                    stopThread = true;
                    try {
                        outputStream.close();
                        socket.close();
                        Toast.makeText(getApplicationContext(), "Disconnected from ROBAR", Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Failed to disconnect from ROBAR", Toast.LENGTH_SHORT).show();
                    }
                    deviceConnected = false;
                }
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(deviceConnected)
                {
                    sendOrders();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Not connected to ROBAR",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

