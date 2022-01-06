package com.bank;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SignUp
 */
@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUp() {
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
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String address1 = request.getParameter("addressline1");
		String address2 = request.getParameter("addressline2");
		String state = request.getParameter("state");
		String city = request.getParameter("city");
		String pincode = request.getParameter("pincode");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String type = request.getParameter("type");
		//out.println("<h3>Hello in html</h3>"+ fname+ "\n" + lname+"\n" +address + "\n"+phone+"\n"+email+" "+ password+"\n"+type+"\n");
	
		Member member = new Member(fname,lname,address1,address2,state,city,pincode,phone,email,password,type);
		
		//SignUpDAO rDao = new SignUpDAO();
		UseractivityDAO rDao = new UseractivityDAO();
		boolean result = rDao.insert(member);
		
        //out.print(member.getPincode());
		//response.getWriter().print(result);
		if(result == true) {
			//out.println("<h1>Successfully Regestered</h1>");
			//out.print("<h1>Your User I'd is ");
			String userid = member.userid();
			String acno = rDao.accountnumber(userid);
			//out.print(userid);
			String s = "Dear "+member.getFname()+" "+member.getLname()+",You are Successfully Regestered with Us.<br> Your User I'd : <b>"+userid+"</b> and <b>Account Number :"+acno+"</b> ,<br>You Can <b> <a href=\"SignIn.jsp\">SignIN</a> Now </b>";
			request.setAttribute("userid", s);
            RequestDispatcher rd=request.getRequestDispatcher("reg.jsp");  
            rd.include(request,response); 
			
		}
		else
		{
			String s ="Not Regestered";
			request.setAttribute("userid", s);
            RequestDispatcher rd=request.getRequestDispatcher("reg.jsp");  
            rd.include(request,response); 
			out.println("");
		}
			
	}

}
