package com.es.server.dao;

import java.sql.ResultSet;

import com.es.model.SeniorCitizen;
import com.es.model.Volunteer;
import com.es.util.BaseDao;
import com.es.util.MD5Utils;

public class VolunteerImpl extends BaseDao implements VolunteerDao {

	public boolean signUp(Volunteer vt) {
		return addVolunteer(vt)!=0?true:false;
	}
	
	public Volunteer query(int id) {
		ResultSet rs = VolunteerQuery(id);
		int i = 0;
		try {
			if (rs.next()) {
				return new Volunteer(rs.getInt("id"),rs.getString("user"),
						rs.getString("name"), rs.getString("info"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Volunteer query(String user) {
		ResultSet rs = VolunteerQuery(user);
		try {
			if (rs.next()) {
				return new Volunteer(rs.getInt("id"),rs.getString("user"),
						rs.getString("name"), rs.getString("info"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean signIn(String user, String passwd) {
		ResultSet rs = VolunteerQuery(user);
		try {
			if(rs.next() && rs.getString("passwd").equals(passwd)) {
				System.out.println(rs.getString("passwd")+"==");
				System.out.println(passwd+"++");
				return true;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public int addVolunteer(Volunteer vt) {
		if(this.query(vt.getUser()) != null) {
			return 0;
		}
		String sql = "insert into vt values(?,?,?,?,?)";
		Object[] params = new Object[]{null,vt.getUser(), vt.getName(), vt.getInfo(), vt.getPassword()};
		int i = 0;
		try {
			i = executeUpdate(sql, params);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	public void close() {
		super.close();
	}
	
	public static void main(String[] args) {
		VolunteerImpl vil = new VolunteerImpl();
		vil.addVolunteer(new Volunteer("qq", "ff", "ee", "tt"));
	}
	
	
	
	
}
