package com.es.client.core;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPSocket {
	Socket socket = null;
	OutputStream out = null;
	InputStream in = null;
	DataOutputStream dout = null;
	BufferedReader buf = null;

	public TCPSocket() throws UnknownHostException, IOException {
		socket = new Socket("127.0.0.1", 9999);
		out = socket.getOutputStream();
		in = socket.getInputStream();
		dout = new DataOutputStream(out);
		buf = new BufferedReader(new InputStreamReader(in));
	}

	public boolean sendMessage(String args) {
		try {
			args = args + "@\n";
			dout.write(args.getBytes());
			return true;
		} catch (IOException e) {
			return false;
		} finally {

		}
	}

	public String getReply() {
		try {
			return buf.readLine();
		} catch (IOException e) {
			return null;
		}
	}

	public void close() {

		try {
			if (out != null)
				out.close();
			if (in != null)
				in.close();
			if (socket != null)
				socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
