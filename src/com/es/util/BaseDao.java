package com.es.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDao {
	Connection con;
	Statement stat;
	ResultSet rs;
	PreparedStatement pst;

	public boolean getConn() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.con = DriverManager
					.getConnection(
							"jdbc:mysql://localhost:3306/esbase?useUnicode=true&characterEncoding=UTF-8",
							"root", null);
			stat = con.createStatement();
			return true;
		} catch (Exception e) {
			con = null;
			return false;
		}
	}
	
	public ResultSet VolunteerQuery(String user) {
		String sql = String.format("select * from vt where user = '%s'",user);
		this.getConn();
		try {
			stat = this.con.createStatement();
			return stat.executeQuery(sql);
		} catch(Exception e) {
			return null;
		} finally {
		}
	}
	
	public ResultSet SeniorCitizenQuery() {
		String sql = String.format("select * from sc");
		this.getConn();
		try {
			stat = this.con.createStatement();
			return stat.executeQuery(sql);
		} catch(Exception e) {
			return null;
		} finally {
		}
	}
	
	public ResultSet SeniorCitizenQuery(int id) {
		String sql = String.format("select * from sc where id = %d", id);
		this.getConn();
		try {
			stat = this.con.createStatement();
			return stat.executeQuery(sql);
		} catch(Exception e) {
			return null;
		} finally {
		}
	}
	
	public ResultSet VolunteerQuery(int id) {
		String sql = String.format("select * from vt where id = %d", id);
		this.getConn();
		try {
			stat = this.con.createStatement();
			return stat.executeQuery(sql);
		} catch(Exception e) {
			return null;
		} finally {
		}
	}
	
	
	public ResultSet SeniorCitizenQuery(String name) {
		String sql = String.format("select * from sc where name = '%s'", name);
		this.getConn();
		try {
			stat = this.con.createStatement();
			return stat.executeQuery(sql);
		} catch(Exception e) {
			return null;
		} finally {
		}
	}
	
	public ResultSet queryByPage(int page, int row) {
		String sql = String.format("select * from sc  limit %d,%d", page, row);
		this.getConn();
		try {
			stat = this.con.createStatement();
			return stat.executeQuery(sql);
		} catch(Exception e) {
			return null;
		} 
	}
	
	public int executeUpdate(String sql, Object[] params)  {
		int num = 0;
		this.getConn();
		try{
			pst = this.con.prepareStatement(sql);
			if(params != null) {
				for(int i = 0; i < params.length; i++) {
					pst.setObject(i+1, params[i]);
				}
			}
			num = pst.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(pst!=null)
				try {
					pst.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		return num;
	}

	public void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stat != null) {
				stat.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
