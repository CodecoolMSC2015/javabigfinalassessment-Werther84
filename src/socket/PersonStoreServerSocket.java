package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class PersonStoreServerSocket {

	private DataReader store;
	
	public void start() {
		try {
			ServerSocket ss = new ServerSocket(4422);
			Socket socket = ss.accept();
			
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			
			ObjectInputStream ois = new ObjectInputStream(is);
			ObjectOutputStream oos = new ObjectOutputStream(os);
			
			oos.close();
			ois.close();
			os.close();
			is.close();
			socket.close();
			ss.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
