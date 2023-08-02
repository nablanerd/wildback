package com.templateproject.api.controller.payload;

public class PlayerLogin {
	 private String username;
	    private String password;

	  

	    public PlayerLogin() {}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }
}
