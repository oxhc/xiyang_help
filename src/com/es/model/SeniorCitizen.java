package com.es.model;

import java.util.Date;

public class SeniorCitizen  {

	int id;
	String name;
	String info;
	Date birth;
	String vts;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getInfo() {
		return info;
	}

	public Date getBirth() {
		return birth;
	}

	public SeniorCitizen () {
		this(-1, null, null, null);
	}
	
	
	public String getVts() {
		return vts;
	}

	public void setVts(String vts) {
		this.vts = vts;
	}

	public SeniorCitizen(int id, String name, String info, Date birth) {
		this.id = id;
		this.name = name;
		this.info = info;
		this.birth = birth;
		this.vts = "";
	}
	
	
	public SeniorCitizen(int id, String name, String info, Date birth,
			String vts) {
		super();
		this.id = id;
		this.name = name;
		this.info = info;
		this.birth = birth;
		this.vts = vts;
	}

	public SeniorCitizen(String name, String info, Date birth) {
		this(-1, name, info, birth);
	}
	

	@Override
	public String toString() {
		return "SeniorCitizen [id=" + id + ", name=" + name + ", info=" + info
				+ ", birth=" + birth + ", vts=" + vts + "]";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
