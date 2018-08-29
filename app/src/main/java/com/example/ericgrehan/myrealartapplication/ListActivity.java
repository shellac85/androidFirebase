package com.example.ericgrehan.myrealartapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class ListActivity extends AppCompatActivity {

//    ArrayList<ArtPlace> artPlaces;
//    private FirebaseDatabase mFirebaseDatabase;
//    private DatabaseReference mDatabaseReference;
//    private ChildEventListener mChildListner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        RecyclerView rvArtPlaces = (RecyclerView) findViewById(R.id.artListPlaces);
        final ArtAdapter adapter = new ArtAdapter();
        rvArtPlaces.setAdapter(adapter);
        LinearLayoutManager artLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,
        false);
        rvArtPlaces.setLayoutManager(artLayoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.list_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId())
        {
            case R.id.insert_menu:
                Intent intent = new Intent(this, ArtActivity.class);
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
