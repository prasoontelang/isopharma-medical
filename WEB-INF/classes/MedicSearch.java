import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class MedicSearch extends HttpServlet
{
   int i = 0, j = 0;
   @Override
   public void doGet(HttpServletRequest req, HttpServletResponse res)
         throws IOException, ServletException
	{
 
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
 
      // Write the response message, in an HTML page
   try {
			String medical = req.getParameter("med_name").toLowerCase();
			String medtype = req.getParameter("name_type").toLowerCase();
			String name = medical;
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch (ClassNotFoundException exp)
		{
			out.println(exp);
		}
			Connection conn = null;
			Statement stmt = null;

			conn = DriverManager.getConnection("jdbc:mysql://localhost:8888/medical", "root", "");
			stmt = conn.createStatement();
         conn.setReadOnly(false);
			String strSelect;

			if (medtype.equals("brand_name"))
			{
				 strSelect = "select medical_name from medicine where brand_name = '" + medical + "';";
				 ResultSet rs = stmt.executeQuery(strSelect);
				 if(rs.next())
				 {
				 	medical = rs.getString("medical_name");
					strSelect = "select company_name, brand_name, medical_name, price from medicine where medical_name = '" + medical + "'order by price;";
				 }
			}
			else
			{
   	   	strSelect = "select company_name, brand_name, medical_name, price from medicine where medical_name = '" + medical + "'order by price;";
			}
			ResultSet rset = stmt.executeQuery(strSelect);
		 	
			/*********GUI**********/
			
			out.println("<html><head><title>Isopharma</title>");
			out.println("<link href=\"menu_assets/styles.css\" rel=\"stylesheet\" type=\"text/css\">");
			out.println("<style>");
			out.println("#cssmenu	{		margin-top: 10px;		margin-left: 20px;	}");
			out.println("div	{		margin-left: 20px;	}");
			out.println(".entry	{		background-color:#E8E8E8;	}");
			out.println("</style></head>");
			out.println("<body>");
			out.println("<div class = \"logo\" align = \"left\">");
			out.println("<a href = \"search.html\"><img src = \"Logo.png\" alt = \"Logo\" style = \"float:left;padding:10\"/></a>");
			out.println("</div>	<br />	<br />	<div id='cssmenu'>");
			out.println("<ul>");
			out.println("<li class='active'><a href='search.html'><span>Home</span></a></li>");
			out.println("<li><a href='isop2.html'><span>IsoPharma</span></a></li>");
			out.println("<li><a href='about2.html'><span>About Us</span></a></li>");
			out.println("<li class='last'><a href='contact2.html'><span>Contact Us</span></a></li>");
			out.println("<li style = \"position:relative;float:right;\"><span><a href = \"home.html\">Logout</a></span></li>	</ul>	</div>");
			out.println("<br/>	<div class = \"entry\" style = \"box-shadow: 2px 2px 2px #888888;\">");
			out.println("<center>	<form action=\"http://localhost:8080/isopharma/medicsearch\" method=\"GET\">");
			out.println("<span style = \"margin-left:7px;font-size:18px;font-family:georgia;\">Enter medicine name</span>");
			out.println("<table border=0>");
	    	out.println("<tr>		<center>");
			out.println("<td colspan = \"2\"><input type=\"text\" name = \"med_name\" id = \"med_name\" size = \"50\" style = \"font-size:20px;\"/><br/></td>");
			out.println("</center>");
			out.println("</tr>	<tr>");
			out.println("<td  style = \"font-family:Arial;\"><center><label title = \"This is the common name of the medicine\"><input type = \"radio\" name = \"name_type\" title = \"This is the common name of the medicine\" id = \"name_type\" value = \"brand_name\" size = \"30px\" checked = \"checked\"/>Brand name</label></center></td>");
			out.println("<td style = \"font-family:Arial;\"><center><label title = \"This is the scientific name of the medicine\"><input type = \"radio\" name = \"name_type\" title = \"This is the scientific name of the medicine\" id = \"name_type\" value = \"medical_name\" size = \"30px\"/>Medical name</label></center></td>");
			out.println("</tr>	<tr>");
			out.println("<td colspan = \"2\"><input type = \"submit\"  value = \"Search\" style = \"float:right\"></td>	</tr>");
			out.println("</table></form><center></div></body></html>");
			if (medical.equals(""))
			{
					out.println("<p style = \"font-family:georgia;font-size:25px;font-weight:bold;\">No medicine name entered</p>");
					out.println("</table></center></body></html>");
			}/******************/
			else
			{
			   if (rset.next())
				{
					out.print("<br/><br/><center>The medicine name entered is <u>" + name + "</u><br/><br/><br/><center>");
					out.print("<table border=0 cellpadding = 5>");
					out.print("<tr><th>Brand Name</th><th>Medical Name</th><th>Company Name</th><th>Price</th></tr>");
					if (i == 0)
					{
						out.print("<tr><td bgcolor = \"#AFDCEC\"><center>" + rset.getString("brand_name")+ "</center></td><td bgcolor = \"#AFDCEC\"><center>" + rset.getString("medical_name")+ "</center></td><td bgcolor = \"#AFDCEC\"><center>" + rset.getString("company_name") + "</center></td><td bgcolor = \"#AFDCEC\"><center>" + rset.getFloat("price") +"</center></td></tr>");
						i = 1;
					}
					else
					{
						out.print("<tr><td bgcolor = \"#CFECEC\"><center>" + rset.getString("brand_name")+ "</center></td><td bgcolor = \"#CFECEC\"><center>" + rset.getString("medical_name")+ "</center></td><td bgcolor = \"#CFECEC\"><center>" + rset.getString("company_name") + "</center></td><td bgcolor = \"#CFECEC\"><center>" + rset.getFloat("price") +"</center></td></tr>");
						i = 0;					
					}
					while (rset.next())
					{
						 if (i == 0)
						 {
							 out.print("<tr><td bgcolor = \"#AFDCEC\"><center>" + rset.getString("brand_name")+ "</center></td><td bgcolor = \"#AFDCEC\"><center>" + rset.getString("medical_name")+ "</center></td><td bgcolor = \"#AFDCEC\"><center>" + rset.getString("company_name") + "</center></td><td bgcolor = \"#AFDCEC\"><center>" + rset.getFloat("price") +"</center></td></tr>");
							 i = 1;
						 }
						 else
						 {
						 	out.print("<tr><td bgcolor = \"#CFECEC\"><center>" + rset.getString("brand_name")+ "</center></td><td bgcolor = \"#CFECEC\"><center>" + rset.getString("medical_name")+ "</center></td><td bgcolor = \"#CFECEC\"><center>" + rset.getString("company_name") + "</center></td><td bgcolor = \"#CFECEC\"><center>" + rset.getFloat("price") +"</center></td></tr>");
							i = 0;
						 }
					}
					out.println("</table></center></body></html>");
				}
				else
				{
					out.println("<center><p style = \"font-family:Arial;font-weight:bold;font-size:25px;color:red;\">Medicine not found in database</p>");
					out.println("<p style =\"font-family:Georgia;font-size:15px;\"><u>Following could be one of the reasons</u></p>");
					out.println("<p style =\"font-family:Georgia;\">1.) Check the spelling of the medicine</p>");
					out.println("<p style =\"font-family:Georgia;\">2.) We are sorry, the medicine may not be available in our database</p>");
					out.println("<p style =\"font-family:Georgia;\">3.) Check if the type of medicine (brand/medical) is rightly selected</p>");
				}
			}
	    }catch(SQLException ex){
	         ex.printStackTrace();
				out.println(ex);
				}
	   }
}