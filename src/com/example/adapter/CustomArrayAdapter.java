package com.example.adapter;

import java.util.ArrayList;
import java.util.List;

import com.example.model.Person;
import com.example.sqlite_examplev2.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomArrayAdapter extends ArrayAdapter<Person> {
	private final Activity context;
	private final List<Person> people;

	public CustomArrayAdapter(Activity context, List<Person> people) {
		super(context, R.layout.rowlayout, people);
		this.context = context;
		this.people = people;
	}

	public View getView(int pos, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
		
		// set the view with the object's data
		TextView textView = (TextView) rowView.findViewById(R.id.secondLine);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
		
		textView.setText(people.get(pos).toString());
		imageView.setImageResource(R.drawable.ic_launcher);
		
		return rowView;
	}
}
