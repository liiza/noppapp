package DataStructures;

import java.io.FileOutputStream;
import java.io.Serializable;

import android.content.Context;
import android.view.View;

import com.mainapp.mynoppaapp.DisplayUserProfile;

public class Session implements Serializable {
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
