package com.example.sqlite_examplev2;

import java.util.List;

import com.example.adapter.CustomArrayAdapter;
import com.example.helper.DatabaseHelper;
import com.example.model.Person;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class PersonListActivity extends Activity {

	DatabaseHelper db;
	ListView listview;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_person_list);

		db = DatabaseHelper.getInstance(getApplicationContext());
		listview = (ListView)findViewById(R.id.listview);
		
		List<Person> people = db.getAllPeople();
		
		CustomArrayAdapter adapter = new CustomArrayAdapter(this, people);
		listview.setAdapter(adapter);
		
		listview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(getApplicationContext(), "position = " + position, Toast.LENGTH_LONG).show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.person_list, menu);
		return true;
	}

}
