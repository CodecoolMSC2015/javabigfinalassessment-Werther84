package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import person.Person;
import socket.SearchType;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String keyword = request.getParameter("keyword");
		String strSearchType = request.getParameter("searchtype");
		SearchType searchType;
		
		if (strSearchType.equals("Mandatory")) {
			searchType = SearchType.Mandatory;
		} else {
			searchType = SearchType.Optional;
		}
		try {
			Socket socket = new Socket("localhost", 4422);
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
		
			ObjectInputStream ois = new ObjectInputStream(is);
			ObjectOutputStream oos = new ObjectOutputStream(os);
		
			oos.writeObject(keyword);
			oos.writeObject(searchType);
		
			Object getObj = ois.readObject();
			Set<Person> persons = new HashSet<>();
			if (getObj instanceof Set<?>) {
				persons = (Set<Person>) getObj;
			}
			
			out.println("<html><head><title>Result Page</title></head><body>");
			out.println("<p>Results:</p><ul>");
			for (Person onePerson : persons) {
				out.print("<li>" + onePerson.getName() + "</li>");
			}
			
			out.println("</body></html>");
			
			oos.close();
			ois.close();
			os.close();
			is.close();
			socket.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
