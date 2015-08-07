package com.hand.TestSocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

public class ChatSocket extends Thread{

	Socket s;
	public ChatSocket(Socket s){
		this.s = s;
	}
	
	public void out(String out){
		try {
			s.getOutputStream().write(out.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run(){
		/*try {
			BufferedWriter bw = 
					new BufferedWriter(
							new OutputStreamWriter(
									s.getOutputStream()));
			int count = 0;
			while(true){
				bw.write("loop:"+count);
				sleep(1000);
				count++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		
		try {
			InputStream is = s.getInputStream();
			InputStreamReader isr = new InputStreamReader(is,"UTF-8");
			BufferedReader br = new BufferedReader(isr);
			String line;
			while((line = br.readLine()) != null){
				ChatManager.getChatManager().publish(this, line);
			}
			br.close();
			isr.close();
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}