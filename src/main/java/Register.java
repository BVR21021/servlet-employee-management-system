
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Register() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String Name = request.getParameter("name");
		String Password = request.getParameter("pwd");
		String Email = request.getParameter("mail");
		String Gender = request.getParameter("gender");
		long MobileNumber = Long.parseLong(request.getParameter("mobile"));
		String State = request.getParameter("state");
		String Country = request.getParameter("Country");
		String Address = request.getParameter("uadd");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "venky", "venky");
			PreparedStatement ps = con.prepareStatement("insert into Employee values(?,?,?,?,?,?,?,?)");
			ps.setString(1, Name);
			ps.setString(2, Password);
			ps.setString(3, Email);
			ps.setString(4, Gender);
			ps.setLong(5, MobileNumber);
			ps.setString(6, State);
			ps.setString(7, Country);
			ps.setString(8, Address);
			int i = ps.executeUpdate();
			out.println(i + "Thanks For Registretion...");
			con.close();
		} catch (Exception e) {
			out.println(e);
		}
	}

}
