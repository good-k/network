package network.test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

public class TCPServer {
	private static final int PORT = 5000;
	
	public static void main(String[] args) {
		ServerSocket serverSocket = null;

		try {
			//1. 서버소켓 생성
			serverSocket = new ServerSocket();
			
			//2. binding(소켓에 소켓주소(IP + Port)를 바인딩)
			InetAddress inetAddress = InetAddress.getLocalHost();
			String hostAddress = inetAddress.getHostAddress();
			serverSocket.bind(new InetSocketAddress(hostAddress, PORT));
			System.out.println("[server] binding " + hostAddress + ":" + PORT);
			
			//3. accept
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if( serverSocket != null && serverSocket.isClosed() == false) {
					serverSocket.close();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		

	}

}
