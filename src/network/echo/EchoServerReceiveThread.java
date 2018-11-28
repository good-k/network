package network.echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

public class EchoServerReceiveThread extends Thread {
	private Socket socket = null;
	
	public EchoServerReceiveThread(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		InetSocketAddress inetSocketAddress = 
				(InetSocketAddress)socket.getRemoteSocketAddress();
		String remoteHostAddress = inetSocketAddress.getAddress().getHostAddress();
		int remoteHostPort = inetSocketAddress.getPort();
		System.out.println("[server] connected by client[" + remoteHostAddress + ":" + remoteHostPort + "]");
		
		try {
			//4. IOStream 생성(받아오기)
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"utf-8"), true); 

			while(true) {
				//5. 데이터 읽기
				String data = br.readLine(); //blocking
				if( data == null ) {
					System.out.println("[server] closed by client");
					break;
				}
				
				System.out.println("[Server] received:" + data);
				pw.println( data );
			}
		} catch(SocketException e) {
			System.out.println("[server] abnormal closed by client");
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				//8. 자원정리
				socket.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
}
