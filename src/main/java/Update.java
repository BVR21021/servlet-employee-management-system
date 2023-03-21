
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Update() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)

			throws ServletException, IOException {

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		String Name = request.getParameter("name");

		String Password = request.getParameter("pwd");

		String Email = request.getParameter("mail");

		long MobileNumber = Long.parseLong(request.getParameter("mobile"));

		String Address = request.getParameter("uadd");

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "venky", "venky");

			PreparedStatement ps = con
					.prepareStatement("update Employee set Password=?,Email=?,MobileNumber=?,Address=? where Name=?");

			ps.setString(1, Password);

			ps.setString(2, Email);

			ps.setLong(3, MobileNumber);

			ps.setString(4, Address);

			ps.setString(5, Name);

			int i = ps.executeUpdate();

			out.println(i + "Your Record is Updated.");

			con.close();

		} catch (Exception e) {

			out.println(e);
		}
	}

}
