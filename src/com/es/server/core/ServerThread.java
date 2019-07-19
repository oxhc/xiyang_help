package com.es.server.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import com.es.model.Volunteer;
import com.es.server.dao.SeniorCitizenImpl;
import com.es.server.dao.VolunteerImpl;
import com.google.gson.Gson;

public class ServerThread extends Thread {

	private Socket socket = null;
	private VolunteerImpl vil = null;

	public ServerThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		super.run();
		PrintStream out = null;
		BufferedReader buf = null;

		try {
			out = new PrintStream(socket.getOutputStream());
			buf = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			Gson gson = new Gson();
			boolean flag = true;
			while (flag) {
				out.flush();
				String str;
				try {
					str = buf.readLine();
				} catch (Exception e) {
					str = "quit@";
				}
				if (str == null) {
					break;
				}
				String[] cmd = str.split("@");

				if (cmd.length >= 1) {
					if (cmd[0].equals("getSeniorCitizenByPage")) {

						int left = Integer.parseInt(cmd[1]);
						int offlen = Integer.parseInt(cmd[2]);
						SeniorCitizenImpl sil = new SeniorCitizenImpl();
						out.println(sil.queryString(left, offlen));
						sil.close();

					} else if (cmd[0].equals("helpSeniorCitizen")) {

						int vtID = Integer.parseInt(cmd[1]);
						int scID = Integer.parseInt(cmd[2]);
						SeniorCitizenImpl sil = new SeniorCitizenImpl();
						out.println("yes");
						System.out.println(cmd[3]);
						sil.help(vtID, scID, cmd[3]);
						sil.close();

					} else if (cmd[0].equals("signUp")) { // ×¢²á

						Volunteer vt = gson.fromJson(cmd[1], Volunteer.class);
						vil = new VolunteerImpl();
						if (vil.signUp(vt)) {
							out.println("ok");
						} else {
							out.println("no");
						}
						vil.close();

					} else if (cmd[0].equals("signIn")) { // µÇÂ¼
						System.out.println("--" + str);
						String user = cmd[1];
						String passwd = cmd[2];
						System.out.println(user);
						vil = new VolunteerImpl();
						if (vil.signIn(user, passwd)) {
							Volunteer v = vil.query(user);
							out.println(v.getId());
						} else {
							out.println("null");
						}
						vil.close();
					}

					else if (cmd[0].equals("quit")) {
						flag = false;

					} else {
						out.println("your command is " + str);
					}
				} else {
					out.println("cannot find available c");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (out != null)
				out.close();
			if (buf != null)
				try {
					buf.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (socket != null)
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

	}

	public static void main(String[] args) throws IOException {

	}

}
