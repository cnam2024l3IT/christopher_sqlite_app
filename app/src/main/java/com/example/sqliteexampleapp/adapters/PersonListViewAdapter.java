package com.example.sqliteexampleapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sqliteexampleapp.R;
import com.example.sqliteexampleapp.models.Person;

import java.util.ArrayList;

public class PersonListViewAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<Person> persons;

    public PersonListViewAdapter(Context context, ArrayList<Person> persons) {
        this.context = context;
        this.persons = persons;
        this.inflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return persons.size();
    }

    @Override
    public Person getItem(int position) {
        return persons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return persons.get(position).get_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.activity_person_list_view, null);
        TextView firstNameView = convertView.findViewById(R.id.personFirstNameItemListView);
        TextView lastNameView = convertView.findViewById(R.id.personLastNameItemListView);

        Person person = getItem(position);

        firstNameView.setText(person.getFirstName());
        lastNameView.setText(person.getLastName());
        return convertView;
    }
}
