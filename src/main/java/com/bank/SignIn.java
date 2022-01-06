package com.bank;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/SignIn")
public class SignIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static int count=0;
    private String result;
       
    public SignIn() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		//PrintWriter out = response.getWriter();
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		
		
		UseractivityDAO dao = new UseractivityDAO();
		result = dao.checkl(userid,password,count);
		
		if(Boolean.valueOf(result) == true) {
			
			HttpSession session=request.getSession();  
			session.setAttribute("Userid",userid);
			session.setAttribute("balance", "null");
			RequestDispatcher rd=request.getRequestDispatcher("Userdetail.jsp");  
	        rd.forward(request,response);
		}
		
		else 
		{
			count++; 
			//out.print(count);
			request.setAttribute("loginresult", result);
			RequestDispatcher rd=request.getRequestDispatcher("SignIn.jsp");  
            rd.include(request,response); 
         }
		
	}

}
