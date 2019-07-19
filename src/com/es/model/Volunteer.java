package com.es.model;

import com.es.util.MD5Utils;

public class Volunteer  {

	int id;
	String user;
	String name;
	String info;
	String password;
	
	

	public Volunteer(int id, String user, String name, String info) {
		super();
		this.id = id;
		this.user = user;
		this.name = name;
		this.info = info;
	}

	public Volunteer(String user, String name, String info, String passwd) {
		this.user = user;
		this.name = name;
		this.info = info;
		this.password = MD5Utils.MD5Encode(passwd,"utf8");
	}

	@Override
	public String toString() {
		return "Volunteer [id=" + id + ", user=" + user + ", name=" + name
				+ ", info=" + info +  "]";
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
