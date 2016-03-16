package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;

import person.Person;

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
			
			String keyword = (String) ois.readObject();
			SearchType searchType = (SearchType) ois.readObject();
			
			CSVDataReader csvDataReader = new CSVDataReader(System.getProperty("user.dir")+"\\src\\socket\\persons.csv");
			Set<Person> persons = csvDataReader.getPersons(keyword, searchType);
			
			oos.writeObject(persons);
			
			oos.close();
			ois.close();
			os.close();
			is.close();
			socket.close();
			ss.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}
}
