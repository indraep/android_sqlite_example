package com.example.sqlite_examplev2;

import com.example.helper.DatabaseHelper;
import com.example.model.Person;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HomeActivity extends Activity {

	DatabaseHelper db;
	
	EditText name;
	EditText age;
	Button saveButton;
	Button seePeopleButton;
	
	private void init() {
		name = (EditText)findViewById(R.id.name);
		age = (EditText)findViewById(R.id.age);
		saveButton = (Button)findViewById(R.id.save_button);
		seePeopleButton = (Button)findViewById(R.id.see_people_button);
		
		db = DatabaseHelper.getInstance(getApplicationContext());
		
		saveButton.setOnClickListener(saveButtonListener);
		seePeopleButton.setOnClickListener(seePeopleButtonListener);
	}
	
	OnClickListener saveButtonListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String nameStr = name.getText().toString();
			String ageStr = age.getText().toString();
			try {
				if (nameStr.length() == 0 || ageStr.length() == 0)
					throw new Exception();
				
				Person person = new Person(nameStr, Integer.parseInt(ageStr));
				db.createPerson(person);
			}
			catch(Exception e) {
				Toast.makeText(getApplicationContext(), "Name and age must be filled.", Toast.LENGTH_LONG).show();
			}
		}
	};
	
	OnClickListener seePeopleButtonListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			startActivity(new Intent(HomeActivity.this, PersonListActivity.class));
		}
	};
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    
        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }
    
}
