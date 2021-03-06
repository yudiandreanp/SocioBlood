package com.example.yudiandrean.socioblood.Twitter;

/**
 * Created by yudiandrean on 14/7/15.
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
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import twitter4j.User;

public class Tweet{


	public Tweet() {
	}

	@SerializedName("created_at")
	private String DateCreated;

	@SerializedName("id")
	private String Id;

	@SerializedName("text")
	private String Text;

	@SerializedName("in_reply_to_status_id")
	private String InReplyToStatusId;

	@SerializedName("in_reply_to_user_id")
	private String InReplyToUserId;

	@SerializedName("in_reply_to_screen_name")
	private String InReplyToScreenName;

	@SerializedName("user")
	private User User;

	@SerializedName("screen_name")
	private String name;

	private String urlImagemPerfil;


	public String getDateCreated() {
		return DateCreated;
	}
	
	public String getId() {
		return Id;
	}

	public String getInReplyToScreenName() {
		return InReplyToScreenName;
	}

	public String getInReplyToStatusId() {
		return InReplyToStatusId;
	}

	public String getInReplyToUserId() {
		return InReplyToUserId;
	}

	public String getText() {
		return Text;
	}

	public void setDateCreated(String dateCreated) {
		DateCreated = dateCreated;
	}

	public void setId(String id) {
		Id = id;
	}

	public void setInReplyToScreenName(String inReplyToScreenName) {
		InReplyToScreenName = inReplyToScreenName;
	}
	
	public void setInReplyToStatusId(String inReplyToStatusId) {
		InReplyToStatusId = inReplyToStatusId;
	}
	
	public void setInReplyToUserId(String inReplyToUserId) {
		InReplyToUserId = inReplyToUserId;
	}

	public void setUrlImagemPerfil(String url) {
		this.urlImagemPerfil = url;
	}

	public String getUrlImagemPerfil() {
		return urlImagemPerfil;
	}

	public void setText(String text) {
		Text = text;
	}

	public void setUser(User user) {
		User = user;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {
		return User;
	}

	public String getName() {return name;}

	@Override
	public String toString(){
		return getText();
	}
}
