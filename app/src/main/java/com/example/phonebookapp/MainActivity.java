package com.example.phonebookapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.customadapter.Contact;
import com.example.customadapter.CustomAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Contact> contacts;
    ListView listView;
    TextView textView;
    public static final String STRING_KEY = "tempItem";
    public static final String STRING_POSITION_KEY = "position";
    public static final String STRING_AL_KEY = "contactsList";
    Contact temp;
    Button removeButton;
    public static int p = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contacts = new ArrayList<>();
        {
            contacts.add(new Contact("Gumball Watterson", R.drawable.gumballl, "1433987231", "numberonealanhater@gmail.com", "1026 York Street", "Online"));
            contacts.add(new Contact("Darwin Watterson", R.drawable.darwinnnn, "2547143834", "fishwithlegs@gmail.com", "1026 York Street", "Online"));
            contacts.add(new Contact("Anais Watterson", R.drawable.anaissss, "3438015925", "anaisnotanias@gmail.com", "1026 York Street", "Do Not Disturb"));
            contacts.add(new Contact("Nicole Watterson", R.drawable.nicoleee, "6017235866", "squeezelemonsinlifeseyes@gmail.com", "1026 York Street", "Do Not Disturb"));
            contacts.add(new Contact("Richard Watterson", R.drawable.richarddd, "5606396727", "finalbosswhenemployed@gmail.com", "1026 York Street", "Idle"));
            contacts.add(new Contact("Penny Fitzgerald", R.drawable.pennyyy, "6783572878", "emopeanutwithantlers@gmail.com", "123 Maple Street", "Idle"));
            contacts.add(new Contact("Gaylord Robinson", R.drawable.gaylordr, "7892648769", "gaylordrobinson@gmail.com", "1027 York Street", "Do Not Disturb"));
            contacts.add(new Contact("Margaret Robinson", R.drawable.margarett, "8957957830", "mehmehmeh@gmail.com", "1027 York Street", "Invisible"));
            contacts.add(new Contact("Leslie", R.drawable.leslie, "5316128494", "gaguponmyeleganza@gmail.com", "101 Sunshine Avenue", "Online"));
            contacts.add(new Contact("Tobias Wilson", R.drawable.tobias, "6745730188", "t0b1a5i5th3c00135t@gmail.com", "205 Rainbow Street", "Online"));
            contacts.add(new Contact("Alan Keane", R.drawable.alan, "7328975532", "peaceandsunshine@gmail.com", "701 Serenity Avenue", "Invisible"));
            contacts.add(new Contact("Banana Joe", R.drawable.bananaaa, "8908848947", "banananaaa@gmail.com", "317 Fruit Street", "Online"));
        }

        listView = findViewById(R.id.listView);
        textView = findViewById(R.id.textView2);
        removeButton = findViewById(R.id.removeButton);

        CustomAdapter customAdapter = new CustomAdapter(this, R.layout.customadapterlayout, contacts);
        listView.setAdapter(customAdapter);

        if (savedInstanceState != null) {
            temp = (Contact) savedInstanceState.getSerializable(STRING_KEY);
            p = savedInstanceState.getInt(STRING_POSITION_KEY);
            contacts = (ArrayList<Contact>) savedInstanceState.getSerializable(STRING_AL_KEY);
            customAdapter = new CustomAdapter(this, R.layout.customadapterlayout, contacts);
            listView.setAdapter(customAdapter);

            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE && contacts.contains(temp))
                textView.setText("Contact Info:\n\n\nName: " + temp.getName() + "\n\nPhone Number: " + temp.getPhoneNumber() + "\n\nEmail: " + temp.getEmail() + "\n\nAddress: " + temp.getAddress() +"\n\nStatus: " + temp.getStatus());

            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT && contacts.contains(temp))
            {
                Toast message = Toast.makeText(MainActivity.this, temp.getName() + " is selected.", Toast.LENGTH_LONG);
                message.show();
            }
        }

        CustomAdapter finalCustomAdapter = customAdapter;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                p = position + 1;
                temp = contacts.get(position);

                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    textView.setText("Contact Info:\n\n\nName: " + temp.getName() + "\n\nPhone Number: " + temp.getPhoneNumber() + "\n\nEmail: " + temp.getEmail() + "\n\nAddress: " + temp.getAddress() +"\n\nStatus: " + temp.getStatus());
                }

                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    Toast message = Toast.makeText(MainActivity.this, temp.getName() + " is selected.", Toast.LENGTH_LONG);
                    message.show();
                }
            }
        });

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast removeMessage;

                if(p > 0 && contacts.size() > 0)
                {
                    removeMessage = Toast.makeText(MainActivity.this, temp.getName() + " is removed.", Toast.LENGTH_LONG);
                    removeMessage.show();

                    contacts.remove(p - 1);
                    finalCustomAdapter.notifyDataSetChanged();

                    if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                        textView.setText("Contact Info: ");
                    }
                    p = 0;
                }

                else if (p == 0 && contacts.size() > 0)
                {
                    removeMessage = Toast.makeText(MainActivity.this, "No contact is selected.", Toast.LENGTH_LONG);
                    removeMessage.show();
                }

                else if (contacts.size() == 0)
                {
                    removeMessage = Toast.makeText(MainActivity.this, "No contacts to remove.", Toast.LENGTH_LONG);
                    removeMessage.show();
                    if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                        textView.setText("Contact Info: ");
                    }
                    p = 0;
                }
                listView.setAdapter(finalCustomAdapter);
            }
        });
        customAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(STRING_KEY, temp);
        outState.putInt(STRING_POSITION_KEY, p);
        outState.putSerializable(STRING_AL_KEY, contacts);
    }
}