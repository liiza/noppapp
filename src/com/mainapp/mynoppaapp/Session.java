package com.mainapp.mynoppaapp;

import java.io.FileOutputStream;
import java.io.Serializable;

import android.app.Application;
import android.content.Context;
import android.view.View;

import datastructures.User;

public class Session extends Application {
	private User user;
	public Session(){
		
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
