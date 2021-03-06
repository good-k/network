package network.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NSLookup {

	public static void main(String[] args) {
		Scanner scanner = null;
		
		try {
			scanner = new Scanner(System.in);
			
			while(true) {
				System.out.print(">>");
				String line = scanner.nextLine();
				
				if( "exit".equals(line) ) {
					break;
				}
				
				InetAddress[] inetAddresses = InetAddress.getAllByName(line);
				for( InetAddress inetAddress : inetAddresses ) {
					System.out.println(line + ":" + inetAddress.getHostAddress());
				}
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} finally {
			if(scanner != null) {
				scanner.close();
			}
		}
		

	}

}
