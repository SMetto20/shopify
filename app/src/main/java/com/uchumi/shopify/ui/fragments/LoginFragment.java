package com.uchumi.shopify.ui.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.uchumi.shopify.R;
import com.uchumi.shopify.ui.CreateAccountActivity;
import com.uchumi.shopify.ui.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LoginFragment extends Fragment implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    GoogleSignInOptions mGoogleSignOptions;
    GoogleSignInClient mGoogleSignClient;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);

        mGoogleSignOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignClient = GoogleSignIn.getClient(getActivity(), mGoogleSignOptions);

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
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }
        };
    }

    @Override
    public void onClick(View v) {
        if (v == mCreateAccountTextView) {
            Intent intent = new Intent(getActivity(), CreateAccountActivity.class);
            startActivity(intent);
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
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(getActivity(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void SignInGoogle() {
        Intent intent = mGoogleSignClient.getSignInIntent();
        startActivityForResult(intent, 100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                MainActivity();
            } catch (ApiException e) {
                Toast.makeText(getActivity(), "Unable to sign in", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void MainActivity(){
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }

    private void SignInTwitter() {
        Toast.makeText(getActivity(), "Under construction", Toast.LENGTH_SHORT).show();
    }

    private void SignInFaceBook() {
        Toast.makeText(getActivity(), "Under construction", Toast.LENGTH_SHORT).show();
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
