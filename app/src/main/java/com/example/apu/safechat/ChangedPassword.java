package com.example.apu.safechat;

/**
 * Created by apuchakroborti on 11/11/2016.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChangedPassword extends AppCompatActivity {

    private DatabaseReference mDatabase;
    //private String mUserId;
    //private EditText oldPassword;
    private EditText newPassword;
    private EditText newConfrimPassword;
    private Button changeButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_password);

        //mDatabase = FirebaseDatabase.getInstance().getReference();

        //oldPassword = (EditText)findViewById(R.id.oldPasswordField);
        newPassword = (EditText)findViewById(R.id.newPasswordField);
        newConfrimPassword= (EditText)findViewById(R.id.newConfirmPasswordField);
        changeButton = (Button)findViewById(R.id.changePassword);


        //FirebaseAuth auth=FirebaseAuth.getInstance();
        //FirebaseUser user=auth.getCurrentUser();
        //String pass=user.getEmail();

        changeButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v)
            {
                String pass1=newPassword.getText().toString();
                String pass2=newConfrimPassword.getText().toString();

                if(pass1.equals(pass2)){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    user.updatePassword(newPassword.getText().toString().trim())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(ChangedPassword.this, "Password is updated!", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(ChangedPassword.this,LogInActivity.class));
                                    } else {
                                        Toast.makeText(ChangedPassword.this, "Failed to update password!", Toast.LENGTH_SHORT).show();
                                        //progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                }
                else{
                    Toast.makeText(ChangedPassword.this, "Failed to update password!\nPassword Don't Match", Toast.LENGTH_SHORT).show();

                }
                //String oldP = oldPassword.getText().toString().trim();
                //String newP = newPassword.getText().toString().trim();

            }
        });
    }
}