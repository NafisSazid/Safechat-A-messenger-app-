package com.example.apu.safechat;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SendMessages extends AppCompatActivity {
    //
    DBHelper mydb;
    //

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference mDatabase;
    private String mUserId;

    private String userName;//current userName
    private String choosenContact;//the contact that i send messages

    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_messages);

        // Initialize Firebase Auth and Database Reference
        mFirebaseAuth = FirebaseAuth.getInstance();
        //mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        mDatabase = FirebaseDatabase.getInstance().getReference();

        //
        mUserId = mFirebaseUser.getUid();
        // Add items via the Button and EditText at the bottom of the view.
        final EditText text = (EditText) findViewById(R.id.todoTextSend);
        final Button button = (Button) findViewById(R.id.addButtonSend);
        final String email = "";

        //getEmailfrom signup

        Intent i = getIntent();
        //The second parameter below is the default string returned if the value is not there.
        userName = i.getExtras().getString("userName");
        choosenContact = i.getExtras().getString("choosenContact");
        //
        final String ContactMessages = commonString(userName, choosenContact);
        //
        //mydb = new DBHelper(this);
        //my adapter
        ArrayList<User> arrayOfUsers = new ArrayList<User>();
        // Create the adapter to convert the array to views
        final UserAdapter adapter = new UserAdapter(this, arrayOfUsers);
        // Attach the adapter to a ListView
        final ListView listView = (ListView) findViewById(R.id.listViewSend);
        listView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mDatabase.child("users").child(ContactMessages)
                        .child("messages").push().child("title")
                        .setValue("  "+userName+"  "+"orkahcitrob"+"  " +text.getText().toString());

                text.setText("");

            }
        });

        //mDatabase.child("users").child(userName).child("messages").addChildEventListener(new ChildEventListener() {
        mDatabase.child("users").child(ContactMessages).child("messages").addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                //String message = (String) dataSnapshot.child("title").getValue();
                //String senderName = (String) dataSnapshot.child("userName").getValue();
                String str=(String) dataSnapshot.child("title").getValue();
                String[] mss=str.split("orkahcitrob");

              //String  userName1=message.substring(0,userName.length());
              //String  messages=message.substring(userName.length());

                if(mss[0]!=null &&mss[1]!=null ){
                    User newUser = new User(mss[1]+" ",mss[0]+" ");
                    adapter.add(newUser);
                }


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                //adapter.remove((String) dataSnapshot.child("title").getValue());

                String message = (String) dataSnapshot.child("title").getValue();

                String  userName1=message.substring(userName.length());
                String  messages=message.substring(userName.length()+1,message.length());

                //adapter.remove(messages,userName1);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        // Delete items when clicked
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mDatabase.child("users").child(ContactMessages).child("messages").orderByChild("title")
                        .equalTo((String) listView.getItemAtPosition(position)).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChildren()) {
                            DataSnapshot firstChild = dataSnapshot.getChildren().iterator().next();
                            firstChild.getRef().removeValue();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
        //
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public String commonString(String email1, String email2) {
        String str = email1 + email2;
        char[] charArray = str.toCharArray();
        Arrays.sort(charArray);
        return new String(charArray);
    }

    public String username(String Email) {
        final String TAG = "SignUpActivity";
        Log.i(TAG, "usernameOfCurrentUser(): " + Email);
        if (Email.contains("@")) {
            return Email.split("@")[0];
        } else {
            return Email;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            mFirebaseAuth.signOut();
            startActivity(new Intent(SendMessages.this,LogInActivity.class));
            return true;
        }
        //noinspection SimplifiableIfStatement
        return super.onOptionsItemSelected(item);
    }

}
