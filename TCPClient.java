package socket;
/*
    編譯: javac TcpClient.java
    執行: java TcpClient serverIP serverPort
    例如: java TcpClient 10.120.102.33 8888 
    
    說明: 可以連線任何server
          輸入quit離開 
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClient {
	public static void main(String args[]) {
		String data;
		Socket socket;
		int port;
		InetAddress addr = null;
		BufferedReader key_in = new BufferedReader(new InputStreamReader(	System.in));
		BufferedReader s_in;
		PrintWriter s_out;
		if (args.length != 2) {
			System.out.println("java TcpClient serverIP serverPort");
			System.exit(0);
		}

		try {
			addr = InetAddress.getByName(args[0]);
		} catch (UnknownHostException uhe) {
			System.out.println("Server位址錯誤或未知...");
		}
		try {
			port = Integer.parseInt(args[1]);
			socket = new Socket(addr, port);
			s_in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			s_out = new PrintWriter(socket.getOutputStream());
			while (true) {
				System.out.println("Client>");
				data = key_in.readLine();
				s_out.println(data);
				s_out.flush();
				if (data.equals("quit"))
					break;
				System.out.println(s_in.readLine());
			}
			socket.close();
		} catch (IOException ioe) {
			System.out.println("無法連接主機...");
		}
	}
}
