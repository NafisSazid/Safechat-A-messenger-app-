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

public class copySendMessages1 extends AppCompatActivity {
    //
    DBHelper mydb;
    //

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference mDatabase;
    private String mUserId;

    private String userName;//current userName
    private String choosenContact;//the contact that i send messages
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_messages);

        //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // setSupportActionBar(toolbar);

        // Initialize Firebase Auth and Database Reference
        mFirebaseAuth = FirebaseAuth.getInstance();
        //mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        mDatabase = FirebaseDatabase.getInstance().getReference();

        //
        mUserId = mFirebaseUser.getUid();
        // Set up ListView
        //   final ListView listView = (ListView) findViewById(R.id.listViewSend);
        //final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1);
        // final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.chat_xml, R.id.textview1);//changed
        //listView.setAdapter(adapter);

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
                //mDatabase.child("users").child("ZHZf1SxGmBNHPD2UKBeeJGJmoZf2").child("email").push().setValue("apuchakroborti@gmail.com");
                //mDatabase.child("users").child("ZHZf1SxGmBNHPD2UKBeeJGJmoZf2").child("email").push().setValue("apuchakroborti@gmail.com");
                //mDatabase.child("users").child(mUserId).child("items").push().child("title").setValue(text.getText().toString());
                //mDatabase.child("users").child(userName).child("messages").push().child("title").setValue(text.getText().toString());
                mDatabase.child("users").child(ContactMessages)
                        .child("messages").push().child("title")
                        .setValue("  "+userName+"  "+"(((())))"+"  " +text.getText().toString());

                //mDatabase.child("users").child(ContactMessages)
                //      .child("messages").push().child("userName")
                //    .setValue(userName);
                //public boolean insertContact  (String directoyName, String messages,String senderName)

                //Swasti DataBase Code

               /*
                if(DataBase.getHistory(ContactMessages).equals("")==true)
                {
                    DataBase.Insert(ContactMessages,text.getText().toString());
                }
                else
                {
                    DataBase.addChat(ContactMessages,text.getText().toString());
                }*/

                //

                text.setText("");

            }
        });

        //
        /*Cursor res=mydb.getData(ContactMessages);
        ArrayList<String> mArrayList = new ArrayList<String>();
        if(res!=null){


            res.moveToFirst();
            while(!res.isAfterLast()) {
                mArrayList.add(res.getString(res.getColumnIndex(DBHelper.ALL_CHAT))); //add the item
                res.moveToNext();
            }


            String [] allChat = mArrayList.toArray(new String[mArrayList.size()]);

            for(int idx=0;idx<allChat.length;idx++){
                adapter.add(allChat[idx]);
            }
            Log.i("SendMessages","add success");
        }*/


  /*      List<String> list = new ArrayList<String>();

        list=mydb.getAllCotacts();
        String [] allChat = list.toArray(new String[list.size()]);

        for(int idx=0;idx<allChat.length;idx++){
            adapter.add(allChat[idx]);
        }
        Log.i("SendMessages","add success");
*/

  /*      Cursor rs = mydb.getData(Value);
        id_To_Update = Value;
        rs.moveToFirst();

        String nam = rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_NAME));
        String phon = rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_PHONE));
        String emai = rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_EMAIL));
        String stree = rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_STREET));
        String plac = rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_CITY));

        Log.i("SendMessages","mydb success");

*/
        //String[] messages=mydb.getData(ContactMessages);
        /*Cursor messages=mydb.getData(ContactMessages);


        if(messages!=null){

            //cursor.moveToFirst();
            messages.moveToFirst();
            ArrayList<String> names = new ArrayList<String>();
            while(!messages.isAfterLast()) {
                names.add(messages.getString(messages.getColumnIndex("chatMessages")));
                messages.moveToNext();
                Log.i("SendMessages","while loop ");
            }
            messages.close();
            String[] allChat=names.toArray(new String[names.size()]);
//String[] allChat=names.toArray(names.size());

            //
            for(int idx=0;idx<allChat.length;idx++){
                adapter.add(allChat[idx]);
            }
            Log.i("SendMessages","add success");

        }*/

        //mDatabase.child("users").child(ContactMessages).child("messages").push().child("title").setValue("");

        //

           /* String history = DataBase.getHistory(ContactMessages);
            String[] parts = history.split("\n");

            for(int idx=0;idx<parts.length;idx++)
            {
                Toast.makeText(SendMessages.this,"message fetch",Toast.LENGTH_LONG);
                adapter.add(parts[idx]);
            }*/


        //






        // Use Firebase to populate the list.
        //mDatabase.child("users").child(mUserId).child("items").addChildEventListener(new ChildEventListener() {
        //mDatabase.child("users").child(userName).child("messages").addChildEventListener(new ChildEventListener() {
        mDatabase.child("users").child(ContactMessages).child("messages").addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                //String message = (String) dataSnapshot.child("title").getValue();
                //String senderName = (String) dataSnapshot.child("userName").getValue();
                String str=(String) dataSnapshot.child("title").getValue();
                String[] mss=str.split("");

                //String  userName1=message.substring(0,userName.length());
                //String  messages=message.substring(userName.length());

                User newUser = new User(mss[1],mss[0]);
                adapter.add(newUser);

                /*TextView txt = (TextView) findViewById(R.id.textview1);
                txt.setText(message);
                if (message.startsWith("[" + userName + "]")) {
                    txt.setGravity(Gravity.RIGHT);

                    //txt.setBackgroundColor(android.R.color.holo_blue_dark);
                } else {
                    txt.setGravity(Gravity.LEFT);
                    //txt.setBackgroundColor(android.R.color.primary_text_dark);
                }*/

                //adapter.add(message);
                //mydb.insertContact(ContactMessages,(String) dataSnapshot.child("title").getValue(),userName);

                /*// Swasti DataBase Code
                if(DataBase.getHistory(ContactMessages).equals("")==true)
                {
                    DataBase.Insert(ContactMessages,(String) dataSnapshot.child("title").getValue());
                }
                else
                {
                    DataBase.addChat(ContactMessages,(String) dataSnapshot.child("title").getValue());
                }*/

                //

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


        //for contact selection
       /* FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("users").child(ContactMessages).child("messages");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                Query myQuery = myRef.orderByValue().equalTo((String) listView.getItemAtPosition(position));

                myQuery.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String contact=adapter.getItem(position).toString();
                        //mDatabase.child("users").child(contact).child("messages").push().child("title").setValue(text.getText().toString());
                        //mDatabase.child("users").child(commonString(contact,userName)).child("messages").push().child("title").setValue(text.getText().toString());

                        /*if (dataSnapshot.hasChildren()) {
                            DataSnapshot firstChild = dataSnapshot.getChildren().iterator().next();
                            firstChild.getRef().removeValue();
                        }*/
             /*       }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                })
                ;
            }
        });*/

        //



        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        //  client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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
        //MainActivity
        //String email = ContactActivity.mAuth.getCurrentUser().getEmail();
        Log.i(TAG, "usernameOfCurrentUser(): " + Email);
        if (Email.contains("@")) {
            return Email.split("@")[0];
        } else {
            return Email;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            mFirebaseAuth.signOut();
            //startActivity(new Intent(SendMessages.this,LogInActivity.class));
            return true;
        }
        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    /*public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("SendMessages Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }*/
}
