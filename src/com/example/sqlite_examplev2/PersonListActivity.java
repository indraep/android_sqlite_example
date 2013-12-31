package com.example.sqlite_examplev2;

import java.util.List;

import com.example.helper.DatabaseHelper;
import com.example.model.Person;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class PersonListActivity extends ListActivity {

	DatabaseHelper db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_person_list);

		db = DatabaseHelper.getInstance(getApplicationContext());
		
		List<Person> people = db.getAllPeople();
		Person[] values = new Person[people.size()];
		int id = 0;
		for(Person p: people) {
			values[id++] = p;
		}
		
		ArrayAdapter<Person> adapter = new ArrayAdapter<Person>(this,
				android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.person_list, menu);
		return true;
	}

}
