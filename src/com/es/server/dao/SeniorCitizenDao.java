package com.es.server.dao;

import com.es.model.SeniorCitizen;

public interface SeniorCitizenDao {
	public int add(SeniorCitizen sc);
	
	public void remove(String name);
	
	public void remove(int id);
	
	public void update(SeniorCitizen sc);
	
	public SeniorCitizen[] query(int page, int rows);
}
