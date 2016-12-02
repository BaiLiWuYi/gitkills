package water;

import java.io.IOException;
import java.net.Socket;

import com.infrastructure.utils.SocketUtils;

public class TestTcp {

	public static void main(String[] args) {
		Socket sock = SocketUtils.buildConnection("127.0.0.1", 50000);
		if (sock == null) {
			System.out.println("连接失败！");
			return;
		}
		byte[] buff = new byte[65536];
		for (int i = 0; i < buff.length; i++) {
			buff[i] = 0x03;
		}		
		try {
			sock.getOutputStream().write(buff);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SocketUtils.closeConnection(sock);
	}
	
}
