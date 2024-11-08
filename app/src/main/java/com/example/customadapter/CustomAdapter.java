package com.example.customadapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phonebookapp.MainActivity;
import com.example.phonebookapp.R;

import java.util.List;
public class CustomAdapter extends ArrayAdapter<Contact> {

    Context mainContact;
    int adapterXML;
    List<Contact> item;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<Contact> objects) {
        super(context, resource, objects);
        mainContact = context;
        adapterXML = resource;
        item = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        LayoutInflater layoutInflater = (LayoutInflater) mainContact.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View adapterView = layoutInflater.inflate(adapterXML, null);

        ImageView imageView = adapterView.findViewById(R.id.imageView);
        TextView textView = adapterView.findViewById(R.id.textView);
        Button button = adapterView.findViewById(R.id.button2);

        imageView.setImageResource(item.get(position).getImageResource());
        textView.setText(item.get(position).getName());
        button.setBackgroundColor(item.get(position).getColor());
        button.setTextColor(item.get(position).getColor());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast messageStatus = Toast.makeText(mainContact, item.get(position).getStatus(), Toast.LENGTH_SHORT);
                messageStatus.show();
            }
        });

        return adapterView;
    }
}