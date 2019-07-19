package com.es.server.dao;

import com.es.model.Volunteer;

public interface VolunteerDao {
	public boolean signUp(Volunteer vt);
	
	public boolean signIn(String user, String passwd);
	
	public int addVolunteer(Volunteer vt);
	
}
