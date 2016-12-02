package com.infrastructure.utils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class SocketUtils {

	public static int TMO_CONNECTTION = 1500;
	public static int TMO_DATA_RECV = 2000;
	
	/**
	 * 
	 * @param ip
	 * @param port
	 * @return
	 * 	null, if fail.
	 */
	public static Socket buildConnection(String ip, int port) {		
		if (ip==null || port<=0) {
			return null;
		}
		Socket sock = new Socket();
		try {
			sock.connect(new InetSocketAddress(ip, port), TMO_CONNECTTION);
			if (!sock.isConnected()) {
				sock.close();
				return null;
			}
		} catch (IOException e) {
			try {
				sock.close();
			} catch (IOException e1) {				
				e1.printStackTrace();
			}
			return null;
		}		
						        								
		return sock;
	} 
	
	public static void closeConnection(Socket sock) {
		if (sock == null) {
			return;
		}
		try {
			sock.close();
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	
}
