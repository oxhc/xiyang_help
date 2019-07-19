package com.es.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.es.model.SeniorCitizen;
import com.es.util.BaseDao;
import com.es.util.DateFormatUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SeniorCitizenImpl extends BaseDao implements SeniorCitizenDao {

	public int add(SeniorCitizen sc) {
		String sql = "insert into sc values(?,?,?,?,?)";
		Object[] params = new Object[] { null, sc.getName(), sc.getInfo(),
				DateFormatUtils.DateToString(sc.getBirth()), sc.getVts() };
		int i = 0;
		try {
			i = executeUpdate(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	public void remove(String name) {
		ResultSet rs = super.SeniorCitizenQuery();
		try {
			while (rs.next()) {
				if (rs.getString("name").equals(name)) {
					remove(rs.getInt("id"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void remove(int id) {
		String sql = "delete from sc where id = ?";
		Object[] params = new Object[] { id };
		try {
			executeUpdate(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(SeniorCitizen sc) {
		ResultSet rs = super.SeniorCitizenQuery(sc.getId());
		try {
			while (rs.next()) {
				if (rs.getInt("id") == sc.getId()) {
					String sql = "UPDATE sc SET name = ?, info = ?, birth = ?, vts = ? WHERE id = ?";
					Object[] params = new Object[] { sc.getName(),
							sc.getInfo(),
							DateFormatUtils.DateToString(sc.getBirth()),sc.getVts(),
							sc.getId() };
					executeUpdate(sql, params);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public SeniorCitizen[] query(int page, int rows) {
		ResultSet rs = queryByPage(page, rows);
		SeniorCitizen[] scList = new SeniorCitizen[rows+1];
		int i = 0;
		try {
			while (rs.next()) {
				scList[i++] = new SeniorCitizen(rs.getInt("id"),
						rs.getString("name"), rs.getString("info"),
						rs.getDate("birth"), rs.getString("vts"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return scList;

	}
	
	public SeniorCitizen query(int id) {
		ResultSet rs = SeniorCitizenQuery(id);
		try {
			if (rs.next()) {
				return new SeniorCitizen(rs.getInt("id"),
						rs.getString("name"), rs.getString("info"),
						rs.getDate("birth"), rs.getString("vts"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public SeniorCitizen query(String name) {
		ResultSet rs = SeniorCitizenQuery(name);
		try {
			if (rs.next()) {
				return new SeniorCitizen(rs.getInt("id"),
						rs.getString("name"), rs.getString("info"),
						rs.getDate("birth"), rs.getString("vts"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String queryString(int page, int rows) {
		SeniorCitizen[] scList = query(page, rows);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String temp = "";
		for (SeniorCitizen sc : scList) {
			if (sc != null)
				temp = temp + gson.toJson(sc) + "@";
		}
		return temp;
	}
	
	public void help(int vtID, int scID, String info) {
		ResultSet rs = SeniorCitizenQuery(scID);
		String vts = null;
		try {
			if(rs.next()) {
				String sql = "UPDATE sc SET vts = ? WHERE id = ?";
				System.out.println(rs.getString("name"));
				System.out.println("---"+rs.getString("vts")+"---");
				if(rs.getString("vts") == null || rs.getString("vts").equals("")) {
					vts = String.valueOf(vtID);
				} else  {
					vts = rs.getString("vts") + "," + vtID;
				}
				System.out.println(vts);
				Object[] params = new Object[] {
						vts, scID
				};
				
				executeUpdate(sql, params);
				
				
				sql = "insert into favor values(?, ?, ?, ?)";
				System.out.println(vts);
				params = new Object[] {
						null,vtID, scID, info
				};
				
				executeUpdate(sql, params);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void close() {
		super.close();
	}

	public static void main(String[] args) {
		SeniorCitizenImpl sd = new SeniorCitizenImpl();
		for(int i = 0; i <= 100; i++) {
			sd.add(new SeniorCitizen(String.valueOf(i), String.valueOf(i+1), new Date()));
		}
		sd.close();
	}

}
