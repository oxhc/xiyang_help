package com.es.server.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerCore {

    private static ServerSocket SERVER_SOCKET =null;;
    
    static{
        try {
            SERVER_SOCKET = new ServerSocket(9999);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println("******���������������ȴ��ͻ�������*****");
            Socket socket = null;
            while(true){
                //ѭ�������ͻ��˵�����
                socket = SERVER_SOCKET.accept();
                //�½�һ���߳�ServerSocket��������
                new ServerThread(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}