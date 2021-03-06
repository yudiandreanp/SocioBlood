package com.example.yudiandrean.socioblood;

/**
 * Created by yudiandrean on 15/7/2015.
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 or more contributor license agreements.  See the NOTICE file
 distributed with this work for additional information
 regarding copyright ownership.  The ASF licenses this file
 to you under the Apache License, Version 2.0 (the
 "License"); you may not use this file except in compliance
 with the License.  You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing,
 software distributed under the License is distributed on an
 "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 KIND, either express or implied.  See the License for the
 specific language governing permissions and limitations
 under the License.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yudiandrean.socioblood.Twitter.TwitterActivity;
import com.example.yudiandrean.socioblood.databases.SessionManager;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import android.content.pm.Signature;


import com.example.yudiandrean.socioblood.databases.DatabaseHandler;
import com.example.yudiandrean.socioblood.databases.UserFunctions;
//import com.facebook.AccessToken;
//import com.facebook.AccessTokenTracker;
//import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
//import com.facebook.Profile;
//import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
//import com.facebook.login.widget.LoginButton;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.AppSession;
import com.twitter.sdk.android.core.TwitterCore;

import io.fabric.sdk.android.Fabric;


/**
 * A login screen that offers login via email/password.
 * <p/>
 * ************ IMPORTANT SETUP NOTES: ************
 * In order for Google+ sign in to work with your app, you must first go to:
 * https://developers.google.com/+/mobile/android/getting-started#step_1_enable_the_google_api
 * and follow the steps in "Step 1" to create an OAuth 2.0 client for your package.
 */


public class LoginActivity extends Activity{

    Button btnLogin;
    Button Btnregister;
    Button passreset;
    EditText inputEmail;
    EditText inputPassword;
  //  LoginButton facebookLogin;





    /**
     * Called when the activity is first created.
     */

    private static String KEY_SUCCESS = "success";
    private static String KEY_UID = "uid";
    private static String KEY_USERNAME = "username";
    private static String KEY_FIRSTNAME = "firstname";
    private static String KEY_LASTNAME = "lastname";
    private static String KEY_EMAIL = "email";
    private static String KEY_CREATED_AT = "created_at";
    private static String KEY_BLOOD_TYPE = "blood_type";
    private static String KEY_RHESUS = "rhesus";
    private static String KEY_GENDER = "gender";
    private static final String TWITTER_KEY = "uLY62iV2Gyc0FYin6RMede2ph";
    private static final String TWITTER_SECRET = "ixs5d15ExnJageamXjIw787Cw7iOnOy34y5Vjb3uTfmYfPC3ZD";
    //private static AccessTokenTracker accessTokenTracker;
    //private static ProfileTracker profileTracker;
    private static final String TAG = Register.class.getSimpleName();
    private static ProgressDialog pd;
    //private static CallbackManager mCallbackManager;
    private SessionManager session;
    List<String> permissionNeeds= Arrays.asList("email", "user_birthday", "user_friends", "public_profile");



    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);



        //FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.activity_login);

        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:",
                        Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
        } catch (NoSuchAlgorithmException e) {
        }

        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        //instantiate callbacks manager
        //mCallbackManager = CallbackManager.Factory.create();

        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        Btnregister = (Button) findViewById(R.id.registerbtn);
        btnLogin = (Button) findViewById(R.id.login);
        passreset = (Button) findViewById(R.id.passres);
//        facebookLogin = (LoginButton) findViewById(R.id.facebook_login);
        //facebookLogin.setReadPermissions(permissionNeeds);

        session = new SessionManager(getApplicationContext());

        // Check if user is already logged in or not
        if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(LoginActivity.this, FeedActivity.class);
            startActivity(intent);
            finish();
        }

//        accessTokenTracker = new AccessTokenTracker() {
//            @Override
//            protected void onCurrentAccessTokenChanged(
//                    AccessToken oldAccessToken,
//                    AccessToken currentAccessToken) {
//                // App code
//            }
//        };
//
//        profileTracker = new ProfileTracker() {
//            @Override
//            protected void onCurrentProfileChanged(
//                    Profile oldProfile,
//                    Profile currentProfile) {
//                // App code
//            }
//        };



        passreset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), PasswordReset.class);
                startActivityForResult(myIntent, 0);
                finish();
            }
        });

        Btnregister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Register.class);
                startActivityForResult(myIntent, 0);
                finish();
            }
        });



        /**
         * Login button click event
         * A Toast is set to alert when the Email and Password field is empty
         **/
        btnLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                if ((!inputEmail.getText().toString().equals("")) && (!inputPassword.getText().toString().equals(""))) {
                    NetAsync(view);
                } else if ((!inputEmail.getText().toString().equals(""))) {
                    Toast.makeText(getApplicationContext(),
                            "Password field empty", Toast.LENGTH_SHORT).show();
                } else if ((!inputPassword.getText().toString().equals(""))) {
                    Toast.makeText(getApplicationContext(),
                            "Email field empty", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Email and Password field are empty", Toast.LENGTH_SHORT).show();
                }
            }
        });


//        //facebook login
//        facebookLogin.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
//
//
//            @Override
//            public void onSuccess(LoginResult loginResults) {
//                //login ok  get access token
//                AccessToken.getCurrentAccessToken();
//
//
//            }
//
//            @Override
//            public void onCancel() {
//
//                Toast.makeText(getApplicationContext(),
//                        "Login Cancelled", Toast.LENGTH_SHORT).show();
//            }
//
//
//            @Override
//            public void onError(FacebookException e) {
//                Toast.makeText(getApplicationContext(),
//                        "Login Failed!", Toast.LENGTH_SHORT).show();
//            }
//        });




        //Session Manager

//        TwitterSession session =
//                Twitter.getSessionManager().getActiveSession();
//        TwitterAuthToken authToken = session.getAuthToken();
//        String token = authToken.token;
//        String secret = authToken.secret;

        //Guest Authentication
        TwitterCore.getInstance().logInGuest(new Callback<AppSession>() {
            @Override
            public void success(Result<AppSession> result) {
                AppSession guestAppSession = result.data;
            }

            @Override
            public void failure(TwitterException exception) {
                // unable to get an AppSession with guest auth
            }
        });

    }




    @Override
    public void onDestroy() {
        super.onDestroy();
      //  accessTokenTracker.stopTracking();
       // profileTracker.stopTracking();
    }
    /**
     * Async Task to check whether internet connection is working.
     **/

    private class NetCheck extends AsyncTask<String, String, Boolean>
    {
        private ProgressDialog nDialog;

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            nDialog = new ProgressDialog(LoginActivity.this);
            nDialog.setTitle("Checking Network");
            nDialog.setMessage("Loading..");
            nDialog.setIndeterminate(false);
            nDialog.setCancelable(true);
            nDialog.show();
        }

        @Override
        protected Boolean doInBackground(String... args){


        /**
         * Gets current device state and checks for working internet connection by trying Google.
         **/
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
        try {
            URL url = new URL("https://www.google.co.id");
            HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
            urlc.setConnectTimeout(3000);
            urlc.connect();
            if (urlc.getResponseCode() == 200) {
                return true;
            }
        } catch (MalformedURLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
        return false;

    }

    @Override
    protected void onPostExecute(Boolean th){

        if(th){
            nDialog.dismiss();
            new ProcessLogin().execute();
        }
        else{
            nDialog.dismiss();
            Toast.makeText(getApplicationContext(),
                    "Error in internet connection!", Toast.LENGTH_SHORT).show();
        }
    }
}

    /**
     * Async Task to get and send data to My Sql database through JSON respone.
     **/
    private class ProcessLogin extends AsyncTask<String, String, JSONObject> {


        private ProgressDialog pDialog;

        String email,password;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            inputEmail = (EditText) findViewById(R.id.email);
            inputPassword = (EditText) findViewById(R.id.password);
            email = inputEmail.getText().toString();
            password = inputPassword.getText().toString();
            pDialog = new ProgressDialog(LoginActivity.this);
            pDialog.setTitle("Contacting Servers");
            pDialog.setMessage("Logging in ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... args) {

            UserFunctions userFunction = new UserFunctions();
            JSONObject json = userFunction.loginUser(email, password);
            return json;
        }

        @Override
        protected void onPostExecute(JSONObject json) {
            try {
                if (json.getString(KEY_SUCCESS) != null) {

                    String res = json.getString(KEY_SUCCESS);

                    if(Integer.parseInt(res) == 1) {
                        pDialog.setMessage("Loading User Space");
                        pDialog.setTitle("Getting Data");
                        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                        JSONObject json_user = json.getJSONObject("user");
                        /**
                         * Clear all previous data in SQlite database.
                         **/
                        UserFunctions logout = new UserFunctions();
                        logout.logoutUser(getApplicationContext());
                        db.addUser(json_user.getString(KEY_FIRSTNAME),json_user.getString(KEY_LASTNAME), json_user.getString(KEY_EMAIL), json_user.getString(KEY_USERNAME), json_user.getString(KEY_UID), json_user.getString(KEY_CREATED_AT), json_user.getString(KEY_GENDER), json_user.getString(KEY_BLOOD_TYPE), json_user.getString(KEY_RHESUS));
                        /**
                         *If JSON array details are stored in SQlite it launches the User Panel.
                         **/
                        session.setLogin(true);
                        session.setCurrentUser(Integer.parseInt(json_user.getString(KEY_UID)));

//                        session.setCurrentUser(Integer.parseInt(json_user.getString(KEY_UID)));
                        Intent feed = new Intent(getApplicationContext(), FeedActivity.class);
                        feed.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        pDialog.dismiss();
                        startActivity(feed);
                        /**
                         * Close Login Screen
                         **/
                        finish();

                    }
                    else{

                        pDialog.dismiss();
                        Toast.makeText(getApplicationContext(),
                                "Incorrect username/password", Toast.LENGTH_SHORT).show();
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void NetAsync(View view){
        new NetCheck().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_twitter:


                    Intent intentTweet = new Intent(this, TwitterActivity.class);
                    startActivity(intentTweet);
                    return true;


            case R.id.action_about:
                Intent intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                return true;
            case R.id.user_panel:
                Intent intentUserPanel = new Intent(this, UserPanel.class);
                startActivity(intentUserPanel);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    }

