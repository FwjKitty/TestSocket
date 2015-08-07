package com.hand.TestSocket.ChatClient;

import java.awt.EventQueue;

import com.hand.TestSocket.ChatManager;

public class StartClient {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable(){
			public void run() {
				try{
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
					ChatManager.getChatManager().setWindow(frame);
				}catch(Exception e){
					e.printStackTrace();
				}
			}

		});
	}
}
