package com.hand.TestSocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListener extends Thread{

	@Override
	public void run(){
		try {
			ServerSocket sc = new ServerSocket(12345);
			while(true){
				//监听客户端的链接，阻塞当前的线程
				Socket s = sc.accept();
				//建立链接
				BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
				PrintWriter pw = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
				//JOptionPane.showMessageDialog(null, "有客户端链接到了本机的1234端口");
				//将socket传递给新的线程
				//ChatSocket cs = new ChatSocket(s);
				//cs.start();
				//ChatManager.getChatManager().add(cs);
			}
			} catch (IOException e) {
			e.printStackTrace();
		}
	}
}