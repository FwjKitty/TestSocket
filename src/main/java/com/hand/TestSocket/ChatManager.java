package com.hand.TestSocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;

import com.hand.TestSocket.ChatClient.MainWindow;

public class ChatManager {
	
	private ChatManager(){}
	private static final ChatManager cm = new ChatManager();
	public static ChatManager getChatManager(){
		return cm;
	}
	
	Vector<ChatSocket> vector = new Vector<ChatSocket>();
	public void add(ChatSocket cs){
		vector.add(cs);
	}
	public void publish(ChatSocket cs,String out){
		for(int i=0; i<vector.size(); i++){
			ChatSocket cs2 = vector.get(i);
			if(!cs.equals(cs2)){
				cs2.out(out);
			}
		}
	}
	
	MainWindow window;
	String IP;
	Socket s;
	BufferedReader br;
	PrintWriter pw;
	public void setWindow(MainWindow window){
		this.window = window;
		//window.appendText("文本框已经和ChatManager绑定了。");
	}
	
	public void connect(String ip){
		this.IP = ip;
		new Thread(){
			@Override
			public void run(){
				try {
					s = new Socket(IP, 12345);
					pw = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
					br = new BufferedReader(new InputStreamReader(s.getInputStream()));
					
					String line;
					while((line = br.readLine()) != null){
						window.appendText("收到："+line);
					}
					
					pw.close();
					br.close();
					pw = null;
					br = null;
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
	
	public void send(String out){
		if(pw != null){
			pw.write(out+"\n");
			pw.flush();
		}else{
			window.appendText("当前的链接已经中断");
		}
	}
}