import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class Signin extends HttpServlet
{
   @Override
   public void doPost(HttpServletRequest req, HttpServletResponse res)
         throws IOException, ServletException
	{
 
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
 
      // Write the response message, in an HTML page
   try 
	{
			String username = req.getParameter("userid");
			String password = req.getParameter("userpwd");
			int flag = 0;
			int return_flag;
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
			
			String strSelect = "select username, password, name from entry where username = '" + username + "';";
			ResultSet rset = stmt.executeQuery(strSelect);

         if (rset.next())
			{
				String user_db = rset.getString("username");
				String pass_db = rset.getString("password");
				if (user_db.equals(username) && pass_db.equals(password))
				{
				    String name_db = rset.getString("name");
				    out.println("Welcome : " + name_db + ".");
					 res.sendRedirect("disclaimer.html");
					 flag = 1;
			   }
				if (flag == 0)
				{
					out.println("<html><head><title>Isopharma</title>");
					out.println("<style>");
					out.println("p{	font-family:georgia;	font-size:18px;}");
					out.println("</style>");
					out.println("<link href = \"menu_assets/styles.css\" rel = \"stylesheet\" type = \"text/css\">");
					out.println("</head><body>");
					out.println("<div class = \"logo\" align = \"left\">");
					out.println("<a href = \"\"><img src = \"Logo.png\" alt = \"Logo\" style = \"float:left;padding:10\"/></a>");
					out.println("</div><br /><br />");
					out.println("<div id='cssmenu'>");
					out.println("<ul>		<li class = 'active'><a href=\"home.html\"><span>Home</span></a></li>");
					out.println("<li><a href=\"isop.html\"><span>IsoPharma</span></a></li>");
					out.println("<li><a href = \"about.html\"><span>About Us</span></a></li>");
					out.println("<li class='last'><a href = \"contact.html\"><span>Contact Us</span></a></li>");
					out.println("</ul>	</div>");
					out.println("<div style = \"position:relative;float:left;width:65%;\">");
					out.println("<br />	<img src = \"Images/pills.jpg\" alt = \"medicine\" width = \"85%\" height = \"70%\"/>");
					out.println("</div><div style=\"position:relative;float:right;width:30%;\">");
					out.println("<div class = \"sign align\">");
					out.println("<p id=\"signin\">Sign In</p>");
					out.println("<form action=\"http://localhost:8080/isopharma/signin\" method=\"POST\">");
					out.println("<span style = \"color:red\">Incorrect username/password</span><br/>");
					out.println("<span style = \"font-family:Arial;font-weight:bold;\">Username</span><br/>");
					out.println("<input type = \"text\" id = \"userid\" name = \"userid\" size = \"40\" style = \"font-size:16px\"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>");
					out.println("<span style = \"font-family:Arial;font-weight:bold;\">Password</span><br/>");
					out.println("<input type = \"password\" id = \"userpwd\" name = \"userpwd\" size = \"40\" style = \"font-size:16px\"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br />");
					out.println("<span style = \"float:right;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><input type = \"submit\" style = \"float:right;\" class = \"but1\" value = \"Sign in\">");
					out.println("</form> </div>");
					out.println("<div class = \"register\"><em style = \"font-family:georgia;\"> It is free and Verified </em> <img src = \"tick.jpg\" alt = \"Tick\" height = \"4%\" width =\"6%\"/>");
					out.println("<br/><span id=\"new_user\" style = \"font-family:georgia;\"> New User? Register </span>");
					out.println("&nbsp; <button class = \"but2\" onClick = \"location.href='register.html'\">SIGN UP</button>");
 					out.println("</div></div></body></html>");
				}
			}
			else
			{
					out.println("<html><head><title>Isopharma</title>");
					out.println("<style>");
					out.println("p{	font-family:georgia;	font-size:18px;}");
					out.println("</style>");
					out.println("<link href = \"menu_assets/styles.css\" rel = \"stylesheet\" type = \"text/css\">");
					out.println("</head><body>");
					out.println("<div class = \"logo\" align = \"left\">");
					out.println("<a href = \"\"><img src = \"Logo.png\" alt = \"Logo\" style = \"float:left;padding:10\"/></a>");
					out.println("</div><br /><br />");
					out.println("<div id='cssmenu'>");
					out.println("<ul>		<li class = 'active'><a href=\"home.html\"><span>Home</span></a></li>");
					out.println("<li><a href=\"isop.html\"><span>IsoPharma</span></a></li>");
					out.println("<li><a href = \"about.html\"><span>About Us</span></a></li>");
					out.println("<li class='last'><a href = \"contact.html\"><span>Contact Us</span></a></li>");
					out.println("</ul>	</div>");
					out.println("<div style = \"position:relative;float:left;width:65%;\">");
					out.println("<br /><img src = \"Images/pills.jpg\" alt = \"medicine\" width = \"85%\" height = \"70%\"/>");
					out.println("</div><div style=\"position:relative;float:right;width:30%;\">");
					out.println("<div class = \"sign align\">");
					out.println("<p id=\"signin\">Sign In</p>");
					out.println("<form action=\"http://localhost:8080/isopharma/signin\" method=\"POST\">");
					out.println("<span style = \"color:red\">Incorrect username/password</span><br/>");
					out.println("<span style = \"font-family:Arial;font-weight:bold;\">Username</span><br/>");
					out.println("<input type = \"text\" id = \"userid\" name = \"userid\" size = \"40\" style = \"font-size:16px\"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>");
					out.println("<span style = \"font-family:Arial;font-weight:bold;\">Password</span><br/>");
					out.println("<input type = \"password\" id = \"userpwd\" name = \"userpwd\" size = \"40\" style = \"font-size:16px\"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br />");
					out.println("<span style = \"float:right;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><input type = \"submit\" style = \"float:right;\" class = \"but1\" value = \"Sign in\">");
					out.println("</form> </div>");
					out.println("<div class = \"register\"><em style = \"font-family:georgia;\"> It is free and Verified </em> <img src = \"tick.jpg\" alt = \"Tick\" height = \"4%\" width =\"6%\"/>");
					out.println("<br/><span id=\"new_user\" style = \"font-family:georgia;\"> New User? Register </span>");
					out.println("&nbsp; <button class = \"but2\" onClick = \"location.href='register.html'\">SIGN UP</button>");
 					out.println("</div></div></body></html>");
			}
      }
		catch(SQLException ex)
		{
         ex.printStackTrace();
			out.println(ex);
		}
   }
}