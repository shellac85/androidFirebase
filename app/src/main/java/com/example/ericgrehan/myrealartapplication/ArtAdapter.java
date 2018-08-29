package com.example.ericgrehan.myrealartapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class ArtAdapter extends RecyclerView.Adapter<ArtAdapter.ArtViewHolder> { //ArtViewHolder here is the ViewHolder we created at the bottom of this class

    ArrayList<ArtPlace> artPlaces;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildListner;

    public ArtAdapter(){
        FirebaseUtil.openFBReference("artitems");
        mFirebaseDatabase = FirebaseUtil.mFirebaseDatabase;
        mDatabaseReference = FirebaseUtil.mdatabaseReference;
        artPlaces = FirebaseUtil.martPlaces;
        mChildListner = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                ArtPlace ap = dataSnapshot.getValue(ArtPlace.class);
                Log.d("Art place ", ap.getName());
                Log.d("Art place ", ap.getLocation());
                Log.d("Art place ", ap.getDescription());
                ap.setId(dataSnapshot.getKey());
                artPlaces.add(ap);
                notifyItemInserted(artPlaces.size() - 1);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        mDatabaseReference.addChildEventListener(mChildListner);
    }


    @Override
    public ArtViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.art_row, parent, false);
        return new ArtViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtViewHolder artViewHolder, int position) {
        ArtPlace artPlace = artPlaces.get(position);
        artViewHolder.bind(artPlace);
    }

    @Override
    public int getItemCount() {
        return artPlaces.size();
    }

    //ViewHolder - -- - extends the Recycler View
    public class ArtViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvTitle;
        TextView tvDescription;
        TextView tvPrice;


        public ArtViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvDescription = (TextView) itemView.findViewById(R.id.tvDescription);
            tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);
        }

        //Viewholder needs to bind the data to our layout of our row
        public void bind(ArtPlace place)
        {
            tvTitle.setText(place.getName()); //Takes an Art Place as a parameter and puts it into the TextView set above in the constructor
            tvDescription.setText(place.getLocation());
            tvPrice.setText(place.getDescription());

        }
    }

}
