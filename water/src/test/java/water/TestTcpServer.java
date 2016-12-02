package water;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TestTcpServer {

	public static void main(String[] args) {
		byte[] buff = new byte[65536];
		ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(50000);
            while (true) {
                Socket sock = serverSocket.accept();
                int ret = sock.getInputStream().read(buff);
                System.out.println("ret:"+ ret);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }          
            return;
        }
	}
	
}
