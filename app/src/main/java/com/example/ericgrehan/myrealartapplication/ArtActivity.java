package com.example.ericgrehan.myrealartapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ArtActivity extends AppCompatActivity {

    //Firebase Initialisation
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    //End Firebase Initialisation
    EditText txtName;
    EditText txtLocation;
    EditText txtDescription;
    ArtPlace artPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        //Firebase Instance
        //FirebaseUtil.openFBReference(,); //("artitems",this);
        mFirebaseDatabase = FirebaseUtil.mFirebaseDatabase;
        mDatabaseReference = FirebaseUtil.mdatabaseReference;
        //
        txtName = (EditText) findViewById(R.id.editText);
        txtLocation = (EditText) findViewById(R.id.editLocation);
        txtDescription = (EditText) findViewById(R.id.txtDescription);
        //intent
        Intent intent = getIntent(); //Get the Intent from Item clicked in viewholder
        ArtPlace artPlace = (ArtPlace) intent.getSerializableExtra("ArtPlace");

        //this will then pre-populate our fields with the item that we have clicked in our list
        if(artPlace == null){
            artPlace = new ArtPlace();
        }
        //Sets the TextViews to our list Items
        this.artPlace = artPlace;
        txtName.setText(artPlace.getName());
        txtLocation.setText(artPlace.getLocation());
        txtDescription.setText(artPlace.getDescription());
    }

    //What Happens when the save button is Hit//
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.save_menu:
                saveArtPlace();
                Toast.makeText(this,"Item Added",Toast.LENGTH_LONG).show();
                clean();
                backToList();
                return true;
            case R.id.delete_menu:
                deleteArtPlace();
                Toast.makeText(this,"Art Place Deleted", Toast.LENGTH_LONG).show();
                backToList();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //How the item Is Saved
    private void saveArtPlace() {
        artPlace.setName(txtName.getText().toString());
        artPlace.setLocation(txtLocation.getText().toString());
        artPlace.setDescription(txtDescription.getText().toString());
        if(artPlace.getId() == null){
            mDatabaseReference.push().setValue(artPlace);
        }
        else {
            mDatabaseReference.child(artPlace.getId()).setValue(artPlace);
        }
        //mDatabaseReference.push().setValue(artPlace); //Take Strings from each editText, Call an Instance of our Art Place Class, push to the DB with our instance
    }

    private void deleteArtPlace()
    {
        if(artPlace == null)
        {
            Toast.makeText(this,"Please save the deal before deleting", Toast.LENGTH_LONG).show();
            return;
        }
        mDatabaseReference.child(artPlace.getId()).removeValue();
    }

    private void backToList()
    {
        Intent intent =  new Intent(this, ListActivity.class);
        startActivity(intent);
    }

    //Clear all EditTexts once Item is sent to DB
    private void clean() {
        txtName.setText(" ");
        txtLocation.setText(" ");
        txtDescription.setText(" ");
        txtName.requestFocus();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =  getMenuInflater();
        inflater.inflate(R.menu.save_menu, menu);
        if(FirebaseUtil.isAdmin){
            menu.findItem(R.id.delete_menu).setVisible(true);
            menu.findItem(R.id.save_menu).setVisible(true);
            //menu.findItem(R.id.insert_menu).setVisible(true);
            enableEditTexts(true);
        }
        else{
            menu.findItem(R.id.delete_menu).setVisible(false);
            menu.findItem(R.id.save_menu).setVisible(false);
            //menu.findItem(R.id.insert_menu).setVisible(false);
            enableEditTexts(false);
        }
        return true;
    }

    private void enableEditTexts(boolean isEnabled) {
        txtName.setEnabled(isEnabled);
        txtLocation.setEnabled(isEnabled);
        txtDescription.setEnabled(isEnabled);
    }
}
