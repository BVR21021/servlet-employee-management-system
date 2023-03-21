
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Search() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String Name = request.getParameter("name");

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "venky", "venky");
			PreparedStatement ps = con.prepareStatement("select * from Employee where Name=?");
			ps.setString(1, Name);
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			out.print("<table border='1'>");
			int n = rsmd.getColumnCount();
			for (int i = 1; i <= n; i++)
				out.print("<td><font color=blue size=3> " + "rsmd.getColomnName(i)");
			out.print("<tr>");
			while (rs.next()) {
				for (int i = 1; i <= n; i++)
					out.println("<td><br>" + rs.getString(i));
				out.println("<tr>");
			}
			out.println("</table> </body> </html>");
			con.close();
		} catch (Exception e) {
			out.println(e);
		}

	}

}
