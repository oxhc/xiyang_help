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
            System.out.println("******服务器已启动，等待客户端连接*****");
            Socket socket = null;
            while(true){
                //循环监听客户端的连接
                socket = SERVER_SOCKET.accept();
                //新建一个线程ServerSocket，并开启
                new ServerThread(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}