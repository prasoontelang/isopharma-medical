import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class Register extends HttpServlet
{
   @Override
   public void doPost(HttpServletRequest req, HttpServletResponse res)
         throws IOException, ServletException
	{
 
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		int flag = 0;
		int return_flag;

 
      // Write the response message, in an HTML page
   try {
			try{
				Class.forName("com.mysql.jdbc.Driver");
			}catch (ClassNotFoundException exp)
			{
				out.println(exp);
			}
			Connection conn = null;
			Statement stmt = null;

			conn = DriverManager.getConnection("jdbc:mysql://localhost:8888/login", "root", "");
			stmt = conn.createStatement();
			conn.setReadOnly(false);

			int age = 0;
			String name = req.getParameter("name");
			String username = req.getParameter("user_name");
			String password1 = req.getParameter("pass1_name");
			String password2 = req.getParameter("pass2_name");
			if (!req.getParameter("age").equals(""))
				age = Integer.parseInt(req.getParameter("age"));
			String occup = req.getParameter("occup");

			out.println("<html><head><title>Isopharma Registration</title>");
			out.println("<link href=\"menu_assets/styles.css\" rel=\"stylesheet\" type=\"text/css\">");
			out.println("<style>");
			out.println("td	{		font-family:georgia;		font-weight:normal;		size:12px;	}");
			out.println("</style>");
			out.println("</head><body>");
			out.println("<div class = \"logo\" align = \"left\">");
			out.println("<a href = \"\"><img src = \"Logo.png\" alt = \"Logo\" style = \"float:left;padding:10\"/></a>");
			out.println("</div>	<br />	<br />");
			out.println("<div id='cssmenu'>");
			out.println("<ul>");
			out.println("<li class = 'active'><a href=\"home.html\"><span>Home</span></a></li>");
			out.println("<li><a href=\"isop.html\"><span>IsoPharma</span></a></li>");
			out.println("<li><a href=\"about.html\"><span>About Us</span></a></li>");
			out.println("<li class='last'><a href=\"contact.html\"><span>Contact Us</span></a></li>");
			out.println("</ul></div>");
			out.println("<div>  <br />");
			out.println("&nbsp;&nbsp;&nbsp;<label style = \"font-family:Arial;font-weight:bold;\"><u>ISOPHARMA REGISTRATION</u></label>");
			out.println("<form action=\"http://localhost:8080/isopharma/registration\" method=\"POST\">");
			
			if (name.equals("") || username.equals("") || password1.equals("") || password2.equals("") || req.getParameter("age").equals("") || occup.equals(""))
			{
					//fields are missing
					out.println("<span style = \"color:red;\">Fields are missing</span><br/>");
			}
			else if (age < 1)
			{
					out.println("<span style = \"color:red;\">Invalid age</span><br/>");
			}
			else if (username.length() < 6 || password1.length() < 6)
			{
					//string length less than 6
					out.println("<span style = \"color:red;\">Username or password should be atleast 6 characters long</span><br/>");
			}
			else
			{
				String strSelect = "select username from entry where username = '" + username + "';";
				ResultSet rset = stmt.executeQuery(strSelect);
				if (rset.next())
				{
					if (rset.getString("username").equals(username))
					{
						//display username already taken.
						out.println("<span style = \"color:red;\">Username already taken</span><br/>");
					}
				}
				else
				{
						if (password1.equals(password2))
						{
						    String strInsert = "insert into entry values ('"+username+"','"+password1+"','"+name+"','"+age+"','"+occup+"');";
							 return_flag = stmt.executeUpdate(strInsert);
							 if (return_flag > 0)
							 {
							 	//registration successful
								out.println("<h3>Welcome " + name + "</h3>");
								out.println("Registration successful. Please <a href = \"home.html\">click here</a> to login");
								flag = 1;
						 	}
						}
						else
						{
							//passwords do not match
							out.println("<span style = \"color:red;\">Password mis-match</span><br/>");
						}
					}
				}
			if (flag != 1)
			{
				out.println("<table>");
				out.println("<tr>		<td>Name:</td><td><input type = \"text\" name = \"name\" value = \""+name+"\"></td>");
				out.println("</tr><tr>");
				out.println("<td>Username:</td><td><input type=\"text\" name= \"user_name\" value = \""+username+"\"></td>");
				out.println("</tr><tr>");
				out.println("<td>Password:</td><td><input type=\"password\" name= \"pass1_name\"></td>");
				out.println("</tr><tr>");
				out.println("<td>Confirm Password:</td><td><input type=\"password\" name= \"pass2_name\"></td>");
				out.println("</tr><tr>");
				out.println("<td>Age:</td><td><input type=\"text\" name= \"age\" value = \""+age+"\"></td>");
				out.println("</tr><tr>");
				out.println("<td>Occupation:</td><td><label><input type=\"radio\" id = \"occup\" name= \"occup\" value = \"Doctor\">Doctor</label>");
				out.println("<label><input type=\"radio\" id = \"occup\" name= \"occup\" value = \"Pharmacist\">Pharmacist</label>");
				out.println("<label><input type=\"radio\" id = \"occup\" name= \"occup\" value = \"Other\">Other</td></label>");
				out.println("</tr><tr>");
				out.println("<td></td><td><center><input type = \"submit\" style = \"position:relative;\" value = \"Submit\"></center></td>");
				out.println("</tr></table></form></div></body></html>");
			}
	
		}					
		catch(SQLException ex)
		{
		 	ex.printStackTrace();
			out.println(ex);
		}
	}
}