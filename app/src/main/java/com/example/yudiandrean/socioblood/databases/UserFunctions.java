package com.example.yudiandrean.socioblood.databases;

/**
 * Created by yudiandrean on 10/7/15.
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

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import android.content.Context;


public class UserFunctions {

    private JSONParser jsonParser;

    //URL of the PHP API
    private static String loginURL = "http://www.socioblood.com/socioblood/index.php";
    private static String registerURL = "http://www.socioblood.com/socioblood/index.php";
    private static String forpassURL = "http://www.socioblood.com/socioblood/index.php";
    private static String chgpassURL = "http://www.socioblood.com/socioblood/index.php";
    private static String postURL = "http://www.socioblood.com/socioblood/index.php";


    private static String login_tag = "login";
    private static String register_tag = "register";
    private static String forpass_tag = "forpass";
    private static String chgpass_tag = "chgpass";
    private static String post_tag = "postreq";


    // constructor
    public UserFunctions(){
        jsonParser = new JSONParser();
    }

    /**
     * Function to Login
     **/

    public JSONObject loginUser(String email, String password){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("tag", login_tag));
        params.add(new BasicNameValuePair("email", email));
        params.add(new BasicNameValuePair("password", password));
        JSONObject json = jsonParser.getJSONFromUrl(loginURL, params);
        return json;
    }

    /**
     * Function to change password
     **/

    public JSONObject chgPass(String newpas, String email){
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("tag", chgpass_tag));
        params.add(new BasicNameValuePair("newpas", newpas));
        params.add(new BasicNameValuePair("email", email));
        JSONObject json = jsonParser.getJSONFromUrl(chgpassURL, params);
        return json;
    }





    /**
     * Function to reset the password
     **/

    public JSONObject forPass(String forgotpassword){
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("tag", forpass_tag));
        params.add(new BasicNameValuePair("forgotpassword", forgotpassword));
        JSONObject json = jsonParser.getJSONFromUrl(forpassURL, params);
        return json;
    }






     /**
      * Function to  Register
      **/
    public JSONObject registerUser(String firstname,String lastname, String username, String email, String password, String gender, String blood_type, String rhesus){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("tag", register_tag));
        params.add(new BasicNameValuePair("firstname", firstname));
        params.add(new BasicNameValuePair("lastname", lastname));
        params.add(new BasicNameValuePair("username", username));
        params.add(new BasicNameValuePair("email", email));
        params.add(new BasicNameValuePair("password", password));
        params.add(new BasicNameValuePair("gender", gender));
        params.add(new BasicNameValuePair("blood_type", blood_type));
        params.add(new BasicNameValuePair("rhesus", rhesus));
        JSONObject json = jsonParser.getJSONFromUrl(registerURL,params);
        return json;
    }

    public JSONObject addPost(int uid, String message, String post_bloodtype, String post_rhesus){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("tag", post_tag));
        params.add(new BasicNameValuePair("uid", Integer.toString(uid)));
        params.add(new BasicNameValuePair("message", message));
        params.add(new BasicNameValuePair("post_bloodtype", post_bloodtype));
        params.add(new BasicNameValuePair("post_rhesus", post_rhesus));
        JSONObject json = jsonParser.getJSONFromUrl(postURL,params);
        return json;
    }

    /**
     * Function to logout user
     * Resets the temporary data stored in SQLite Database
     * */
    public boolean logoutUser(Context context){
        DatabaseHandler db = new DatabaseHandler(context);
        db.resetTables();
        return true;
    }

}

