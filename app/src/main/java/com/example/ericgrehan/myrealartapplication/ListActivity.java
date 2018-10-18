package com.example.ericgrehan.myrealartapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class ListActivity extends AppCompatActivity {

//    ArrayList<ArtPlace> artPlaces;
//    private FirebaseDatabase mFirebaseDatabase;
//    private DatabaseReference mDatabaseReference;
//    private ChildEventListener mChildListner;

    //Currently entry level of the app, opens the list of art places
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //Check Location Permission
        //ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION );
    }

    //This method opens the option to Add a new art place at the top of the app
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.list_activity_menu, menu);
        MenuItem insertMenu = menu.findItem(R.id.insert_menu);
        if (FirebaseUtil.isAdmin == true) {
            insertMenu.setVisible(true);
        }
        else {
            insertMenu.setVisible(false);
        }
        return true;
    }

    //click on the art place list item, opens the intent and passes the art place info into the edit activity and populates the sections
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.insert_menu:
                Intent intent = new Intent(this, ArtActivity.class); //start the art activity
                startActivity(intent);
                return true;
            case R.id.logout_menu:
                AuthUI.getInstance()
                        .signOut(this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            public void onComplete(@NonNull Task<Void> task) {
                                Log.d("Logout", "User Logged Out");
                                FirebaseUtil.attachListner();
                            }
                        });
                FirebaseUtil.detachListner();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        FirebaseUtil.detachListner();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        FirebaseUtil.openFBReference("artitems",this);
        //Set up the recyclerView. Call an instance of the art adapter. Set the adapter as the art adapter.
        RecyclerView rvArtPlaces = (RecyclerView) findViewById(R.id.artListPlaces);
        final ArtAdapter adapter = new ArtAdapter();
        rvArtPlaces.setAdapter(adapter);
        LinearLayoutManager artLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,
        false);
        rvArtPlaces.setLayoutManager(artLayoutManager);
        FirebaseUtil.attachListner();
    }

    public void showMenu() {
        invalidateOptionsMenu();
    }
}
