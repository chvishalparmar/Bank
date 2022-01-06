package com.bank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Adminlogin
 */
@WebServlet("/Adminlogin")
public class Adminlogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Adminlogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		
		String id = request.getParameter("adminid");
		String pass = request.getParameter("password");
		
		UseractivityDAO dao = new UseractivityDAO();
		 boolean result = dao.checkadmin(id,pass);
		if(result == true && id!= null) {
			HttpSession session = request.getSession();
			System.out.println(result);
			session.setAttribute("id",id);
			UseractivityDAO dao1 = new UseractivityDAO();
			ResultSet rs=dao1.adminstmt();
			out.println("<table border=1 width=50% height=50%>");  
            out.println("<tr><th>Close Date</th><th>Userid</th><th>Fname</th><th>Lname</th><th>Address line 1</th><th>Address line 2</th><th>State</th><th>City</th><th>Pincode</th><th>Phone</th><th>Email</th><th>Account Type</th></tr>"); 
			if(rs!=null)
			{
				try{
					while(rs.next())
					{		
						out.println("<tr><td>" + rs.getString(1) + "</td><td>" + rs.getString(2)+ "</td><td>" +rs.getString(4)+ "</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(7)+"</td><td>"+rs.getString(8)+"</td><td>"+rs.getString(9)+"</td><td>"+rs.getString(10)+"</td><td>"+rs.getString(11)+"</td><td>"+rs.getString(12)+"</td><td>"+rs.getString(13)+"</td></tr>");
					//out.print("<h2>hi</h2>");
					}
			    	}catch (SQLException e) {
			    		// TODO Auto-generated catch block
			    		e.printStackTrace();
			    		System.out.println(e);
			    	}
				
				out.print("<button><a href=\"Adminlogin.jsp\">Logout</a></button>");
				session.invalidate();
				session.removeAttribute("id");
				
		   }
		}
			else
			{
				System.out.println("inside else "+result);
			 request.setAttribute("adminresult", "Invaild Username and Password!");
             RequestDispatcher rd=request.getRequestDispatcher("Adminlogin.jsp");  
             rd.include(request,response);
             
			}
		out.close();

	}
}
