package network.echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class EchoClient {
	private static String SERVER_IP = "192.168.1.3";
	private static int SERVER_PORT = 5000;

	public static void main(String[] args) {
		Scanner scanner = null;
		Socket socket = null;
		
		try {
			// 스캐너 생성(표준입력과 연결)
			scanner = new Scanner(System.in);
			
			// 소켓 생성
			socket = new Socket();
			
			// 서버에 연결하기
			socket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT) );
		
			// IOStream 생성(받아오기)
			BufferedReader br 
				= new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8") );
			PrintWriter pw
				= new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
			
			while(true) {
				// 입력받기
				System.out.print(">>");
				String line = scanner.nextLine();
				if("quit".equals(line) == true) {
					break;
				}
				
				// 송신
				pw.println(line);
				
				// 수신
				String data = br.readLine();
				if( data == null ) {
					log("closed by server");
					break;
				}
				
				// 출력
				System.out.println("<<" + data);
			}
			
		} catch(SocketException e) {
			log("abanormal close by server");
		} catch(IOException e) {
			log("error:" + e);
		} finally {
			try {
				if(scanner != null) {
					scanner.close();
				}
				if(socket != null && socket.isClosed() == false) {
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void log(String message) {
		System.out.println("[Echo Client] " + message);
	}

}
