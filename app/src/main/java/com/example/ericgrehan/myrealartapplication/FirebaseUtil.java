package com.example.ericgrehan.myrealartapplication;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

//Class to Initalise the Firebase Stuff
public class FirebaseUtil {
    public static FirebaseDatabase mFirebaseDatabase;
    public static DatabaseReference mdatabaseReference;
    private static FirebaseUtil firebaseUtil;
    public static ArrayList<ArtPlace> martPlaces;


    private FirebaseUtil(){};

    public static void openFBReference(String ref)
    {
        if(firebaseUtil == null)
        {
            firebaseUtil = new FirebaseUtil();
            mFirebaseDatabase =  FirebaseDatabase.getInstance();

        }
        martPlaces =  new ArrayList<ArtPlace>();
        mdatabaseReference =  mFirebaseDatabase.getReference().child(ref);

    }
}
