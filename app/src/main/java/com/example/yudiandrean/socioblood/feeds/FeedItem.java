package com.example.yudiandrean.socioblood.feeds;

/**
 * Created by yudiandrean on 5/24/2015.
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
public class FeedItem {
    private int id,pid;
    private String name, status, image, profilePic, timeStamp, url, post_bloodtype, post_rhesus, username, blood_type, rhesus;

    public FeedItem() {
    }

    public FeedItem(int id, int pid, String name, String username, String image, String status,
                    String profilePic, String timeStamp, String url, String post_bloodtype, String post_rhesus, String blood_type, String rhesus) {
        super();
        this.id = id;
        this.name = name;
        this.image = image;
        this.status = status;
        this.profilePic = profilePic;
        this.timeStamp = timeStamp;
        this.url = url;
        this.post_bloodtype = post_bloodtype;
        this.post_rhesus = post_rhesus;
        this.pid= pid;
        this.username = username;
        this.blood_type = blood_type;
        this.rhesus = rhesus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getImge() {
        return image;
    }

    public void setImge(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBloodtype() {
        return post_bloodtype;
    }

    public void setBloodtype(String post_bloodtype) {
        this.post_bloodtype = post_bloodtype;
    }

    public String getRhesus() {
        return post_rhesus;
    }

    public void setRhesus(String post_rhesus) {
        this.post_rhesus = post_rhesus;
    }

    public String getUserBloodtype() {
        return blood_type;
    }

    public void setUserBloodtype(String blood_type) {
        this.blood_type = blood_type;
    }

    public String getUserRhesus() {
        return rhesus;
    }

    public void setUserRhesus(String rhesus) {
        this.rhesus = rhesus;
    }
}