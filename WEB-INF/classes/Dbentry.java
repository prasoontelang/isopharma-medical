import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class Dbentry extends HttpServlet
{
   @Override
   public void doGet(HttpServletRequest req, HttpServletResponse res)
         throws IOException, ServletException{
 
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
 
      // Write the response message, in an HTML page
   try 
	{
	      String strSelect, strInsert;
			String brand = req.getParameter("brand_name");
			String medical = req.getParameter("medic_name");
			String company = req.getParameter("company_name");
			float price = Float.valueOf(req.getParameter("price_value")).floatValue();
			int flag = 0;
			int return_flag;
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch (ClassNotFoundException exp){
			out.println(exp);
		}
			Connection conn = null;
			Statement stmt = null;

			conn = DriverManager.getConnection("jdbc:mysql://localhost:8888/medical", "root", "");
			stmt = conn.createStatement();
         conn.setReadOnly(false);
		
			strInsert = "insert into medicine values ('" + medical + "','" + brand + "','" + company + "','" + price + "');";
			return_flag = stmt.executeUpdate(strInsert);
         System.out.println(return_flag + "records affected");
			strSelect = "select brand_name, medical_name, company_name, price from medicine";
			ResultSet rset = stmt.executeQuery(strSelect);
			while (rset.next())
			{
				 out.println("<p>" + rset.getString("brand_name")+ ", " + rset.getString("medical_name")+ ", " + rset.getString("company_name") + ", $" + rset.getFloat("price") +"</p>");
		   }
	} catch(SQLException ex) {
         ex.printStackTrace();
			out.println(ex);
   }
   }
}