package com.uchumi.shopify.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.uchumi.shopify.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.backHomeButton) Button mBackHomeButton;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.userLoginEmailEditText) EditText mUserLoginEmailEditText;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.userLoginPasswordEditText) EditText mUserLoginPasswordEditText;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.logInButton) Button mLoginButton;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.textViewTitle) TextView mTextViewTitle;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.createAccountTextView) TextView mCreateAccountTextView;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.googleButton) FloatingActionButton mGoogleButton;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.facebookButton) FloatingActionButton mFacebookButton;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.twitterButton) FloatingActionButton mTwitterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mBackHomeButton.setOnClickListener(this);
        mCreateAccountTextView.setOnClickListener(this);
        mLoginButton.setOnClickListener(this);
        mGoogleButton.setOnClickListener(this);
        mTwitterButton.setOnClickListener(this);
        mFacebookButton.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };
    }

    @Override
    public void onClick(View v) {
        if (v == mCreateAccountTextView) {
            Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);
            startActivity(intent);
            finish();
        }
        if (v == mLoginButton) {
            loginWithRegisteredAccount();
        }
        if(v == mGoogleButton){
            SignInGoogle();
        }
        if(v == mTwitterButton){
            SignInTwitter();
        }
        if (v == mFacebookButton){
            SignInFaceBook();
        }
    }

    private void loginWithRegisteredAccount() {
        String email = mUserLoginEmailEditText.getText().toString().trim();
        String password = mUserLoginPasswordEditText.getText().toString().trim();
        if (email.equals("")) {
            mUserLoginEmailEditText.setError("Please enter your email");
            return;
        }
        if (password.equals("")) {
            mUserLoginPasswordEditText.setError("Password cannot be blank");
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void SignInGoogle() {
        Toast.makeText(LoginActivity.this, "Under construction", Toast.LENGTH_SHORT).show();
    }

    private void SignInTwitter() {
        Toast.makeText(LoginActivity.this, "Under construction", Toast.LENGTH_SHORT).show();
    }

    private void SignInFaceBook() {
        Toast.makeText(LoginActivity.this, "Under construction", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}