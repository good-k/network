package network.test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class LocalHost {

	public static void main(String[] args) {
		try {
			InetAddress inetAddress = InetAddress.getLocalHost();
			
			String hostName = inetAddress.getHostName();
			String hostAddress = inetAddress.getHostAddress();
			System.out.println( hostName + ":" + hostAddress);

			byte[] ipAddress = inetAddress.getAddress();
			for( byte address : ipAddress ) {
				System.out.print(address & 0x000000ff);
				System.out.print(".");
			}
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		

	}

}
