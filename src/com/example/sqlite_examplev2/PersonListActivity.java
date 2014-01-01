package com.example.sqlite_examplev2;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adapter.CustomArrayAdapter;
import com.example.helper.DatabaseHelper;
import com.example.model.Person;

public class PersonListActivity extends Activity {

	DatabaseHelper db;
	ListView listview;

	List<Person> people;
	
	private void init() {
		db = DatabaseHelper.getInstance(getApplicationContext());
		listview = (ListView)findViewById(R.id.listview);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_person_list);

		init();

		// get all people from database
		people = db.getAllPeople();

		// set custom array adapter
		CustomArrayAdapter adapter = new CustomArrayAdapter(this, people);
		listview.setAdapter(adapter);

		// set on item long click listener
		listview.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
				AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(
						PersonListActivity.this);

				// Setting Dialog Title
				alertDialog2.setTitle("Confirm Delete...");

				// Setting Dialog Message
				alertDialog2.setMessage("Are you sure you want delete this file?");

				// Setting Icon to Dialog
				//alertDialog2.setIcon(R.drawable.delete);

				// Setting Positive "Delete" Button
				alertDialog2.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// Write your code here to execute after dialog
						Person toBeDeletedPerson = people.get(position);
						db.deletePerson(toBeDeletedPerson);
						
						// update listview
						people = db.getAllPeople();
						listview.setAdapter(new CustomArrayAdapter(PersonListActivity.this, people));
					}
				});
				
				// Setting Negative "Update" Button
				alertDialog2.setNegativeButton("Update", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// Write your code here to execute after dialog
						
					}
				});

				// Showing Alert Dialog
				alertDialog2.show();
				return false;
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
