package com.es.client.core;

import java.io.*;
import java.net.UnknownHostException;

import com.es.model.SeniorCitizen;
import com.es.model.Volunteer;
import com.google.gson.GsonBuilder;
import com.es.util.MD5Utils;
import com.google.gson.Gson;

public class ClientMain {
	private TCPSocket so;
	private Gson gson;
	public boolean isLogin = false;
	int vtID;
	
	
	public ClientMain() throws UnknownHostException, IOException {
		so = new TCPSocket();
		gson = new Gson();
	}
	
	public void setVtID(int id) {
		this.vtID = id;
	}
	
	public int getVtID() {
		return this.vtID;
	}
	
	public boolean helpSeniorCitizen(int vt, int sc, String info) {
		String query = String.format("helpSeniorCitizen@%d@%d@%s", vt, sc, info);
		so.sendMessage(query);
		String reply = so.getReply();
		System.out.println("reply--"+reply+"--");
		if("null".equals(reply)) {
			return false;
		} else return true;
	}
	
	public boolean signUp(Volunteer vt) {
		String query = "signUp@"+gson.toJson(vt);
		so.sendMessage(query);
		String reply = so.getReply();
		System.out.println("reply--"+reply+"--");
		if("ok".equals(reply)) {
			return true;
		} else return false;
	}
	
	public boolean signIn(String user, String passwd) {
		String query = String.format("signIn@%s@%s", user, MD5Utils.MD5Encode(passwd, "utf8"));
		so.sendMessage(query);
		String reply = so.getReply();
		System.out.println("reply--"+reply+"--");
		if("null".equals(reply)) {
			this.isLogin = false;
			return false;
		} else {
			this.isLogin = true;
			this.vtID = Integer.parseInt(reply);
			return true;
		}
	}
	
	public SeniorCitizen[] getSeniorCitizenByPage(int page, int row) {
		SeniorCitizen[] scs = new SeniorCitizen[row+1];
		String query = String.format("getSeniorCitizenByPage@%d@%d", page, row);
		so.sendMessage(query);
		String reply = so.getReply();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String[] tt = reply.split("@");
		int i = 0;
		for(String t : tt) {
			scs[i++] = (SeniorCitizen)gson.fromJson(t,SeniorCitizen.class);
		}
		return scs;
	}
	
	public void close() {
		so.close();
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		ClientMain cm = new ClientMain();
        boolean flag = true;
        while (flag) {
            String str = input.readLine();

            if(str.equals("signUp")) {
            	if(cm.signUp(new Volunteer("asd", "dsfa", "fds", "fsdf"))) {
            		System.out.println("suc");
            	} else {
            		System.out.println("no");
            	}
            }
            else if(str.equals("signIn")) {
            	if(cm.signIn("asd", MD5Utils.MD5Encode("fsdf", "utf8"))) {
            		System.out.println("suc");
            	} else {
            		System.out.println("no");
            	}
            }
            else if("show".equals(str)) {
            	SeniorCitizen[] sc = new SeniorCitizen[21];
            	sc = cm.getSeniorCitizenByPage(0, 3);
            	for(SeniorCitizen i:sc) {
            		if(i!=null)
            			System.out.println(i);
            	}
            }
            
            else if ("bye".equals(str)) {
                flag = false;
            } else {
            	cm.so.sendMessage(str);
                System.out.println("++"+cm.so.getReply());
            }
        }
        input.close();
		cm.close();

	}

}
