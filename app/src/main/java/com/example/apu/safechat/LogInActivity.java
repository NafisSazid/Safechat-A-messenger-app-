package com.example.apu.safechat;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogInActivity extends AppCompatActivity {

    protected EditText emailEditText;
    protected EditText passwordEditText;
    protected Button logInButton;
    protected Button signUpButton;
    private FirebaseAuth mFirebaseAuth;


    public String username(String Email)
    {
        final String TAG="SignUpActivity";
        //MainActivity
        //String email = ContactActivity.mAuth.getCurrentUser().getEmail();
        Log.i(TAG, "usernameOfCurrentUser(): "+Email);
        if (Email.contains("@")) {
            return Email.split("@")[0];
        } else {
            return Email;
        }
    }

    Button resetPass;
    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        // Initialize FirebaseAuth
        mFirebaseAuth = FirebaseAuth.getInstance();

        signUpButton = (Button) findViewById(R.id.signUpText);
        emailEditText = (EditText) findViewById(R.id.emailField);
        passwordEditText = (EditText) findViewById(R.id.passwordField);
        logInButton = (Button) findViewById(R.id.loginButton);
        resetPass = (Button) findViewById(R.id.resetPasswordButton);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });


        resetPass.setOnClickListener(new View.OnClickListener() {

                                         public void onClick(View v)
                                         {
                                             Intent intent = new Intent(LogInActivity.this, Reset1PasswordActivity.class);
                                             startActivity(intent);
                                         }
                                     }
        );

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                email = email.trim();
                password = password.trim();

                if (email.isEmpty() || password.isEmpty()) {
                    //
                    Context context = getApplicationContext();
                    CharSequence text = "Password or Email empty!!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    //

                    AlertDialog.Builder builder = new AlertDialog.Builder(LogInActivity.this);
                    builder.setMessage(R.string.login_error_message)
                            .setTitle(R.string.login_error_title)
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else {
                    final String finalEmail = email;
                    mFirebaseAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(LogInActivity.this, new OnCompleteListener<AuthResult>() {

                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        //
                                        Context context = getApplicationContext();
                                        CharSequence text = "Success ";
                                        int duration = Toast.LENGTH_SHORT;
                                        Toast toast = Toast.makeText(context, text, duration);
                                        toast.show();
                                        //
                                        //Intent intent = new Intent(LogInActivity.this, MainActivity.class);
                                        Intent intent = new Intent(LogInActivity.this, NavigationActivity.class);
                                        intent.putExtra("SignUpEmail",username(finalEmail));
                                       // intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                       // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                    } else {
                                        //
                                        Context context = getApplicationContext();
                                        CharSequence text = "Someting Error!!";
                                        int duration = Toast.LENGTH_SHORT;
                                        Toast toast = Toast.makeText(context, text, duration);
                                        toast.show();
                                        //
                                        AlertDialog.Builder builder = new AlertDialog.Builder(LogInActivity.this);
                                        builder.setMessage(task.getException().getMessage())
                                                .setTitle(R.string.login_error_title)
                                                .setPositiveButton(android.R.string.ok, null);
                                        AlertDialog dialog = builder.create();
                                        dialog.show();
                                    }
                                }

                               /* @Override
                                public void onComplete(@NonNull Task</AuthResult><AuthResult>task) {
                                    if (task.isSuccessful()) {
                                        Intent intent = new Intent(LogInActivity.this, MainActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                    } else {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(LogInActivity.this);
                                        builder.setMessage(task.getException().getMessage())
                                                .setTitle(R.string.login_error_title)
                                                .setPositiveButton(android.R.string.ok, null);
                                        AlertDialog dialog = builder.create();
                                        dialog.show();
                                    }
                                }*/
                            });
                }
            }
        });
    }
}
