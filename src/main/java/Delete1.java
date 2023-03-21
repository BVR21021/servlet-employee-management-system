
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

public class Delete1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Delete1() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String Name = request.getParameter("name");

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "venky", "venky");
			PreparedStatement ps = con.prepareStatement("Delete from employee where Name=?");
			ps.setString(1, Name);
			int i = ps.executeUpdate();
			out.print(i + "your Record Deleted.....");
			con.close();
		} catch (Exception e) {
			out.println(e);
		}
	}

}
