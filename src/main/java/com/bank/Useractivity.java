package com.bank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Useractivity
 */
@WebServlet("/Useractivity")
public class Useractivity extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Useractivity() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("").append(request.getContextPath());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		double bal=0;
		
		HttpSession session=request.getSession(false);
		String userid = (String) session.getAttribute("Userid");
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		UseractivityDAO dao = new UseractivityDAO();
		useraccount ac = new useraccount(userid);
		
        
//-----------------> check balance <-------------------------// 
		if(userid!=null)
		{
			bal = dao.checkbal(userid);
			ac.setBalance(bal);	
		}

//-----------------> check balance for Userdetails <--------------------//
		String checkbal = request.getParameter("checkbal");
		if(userid!=null && checkbal!=null)
		{
			Double balanceforUserdetails = dao.checkbal(userid);
			String result = "Your Account Balance : "+ balanceforUserdetails;
			session.setAttribute("result",result);
			request.getRequestDispatcher("Userdetail.jsp").include(request, response);
		}
		
//-----------------> update address <--------------------------//
		String ad1 = request.getParameter("addressline1");
		String ad2 = request.getParameter("addressline2");
		if(ad1 != null && ad2 !=null)
		{
			String result = dao.updateaddress(userid, ad1, ad2);
			session.setAttribute("result",result);
			request.getRequestDispatcher("Userdetail.jsp").include(request, response);
		}
		
		
//----------------> deposit money <----------------------------//
		
		String deposit = request.getParameter("deposit");
		if(deposit != null && userid!= null)
		{
			boolean result = dao.deposit(userid,deposit);
			if(result==true)
			{
				//double de = Double.parseDouble(deposit);
				//ac.setDeposit(de);
				//out.print("<h2>"+deposit+", Deposit Successfully</h2>");
				//String resultm = ac.getDeposit()+",Deposit Successfully. New Balance :" + ac.getBalance();
				session.setAttribute("result", "Deposit Successfully");
				request.getRequestDispatcher("Userdetail.jsp").include(request, response);
			}
			else
				{
				String resultm ="Not Deposited";
				session.setAttribute("result",resultm);
			    request.getRequestDispatcher("Userdetail.jsp").include(request, response);
				//out.print("<h2>Not Deposited");
				}
		}
		
//-----------------> withdrawal <---------------------------------//
		String withdrawal = request.getParameter("withdrawal");
		if(withdrawal !=null && userid!=null )
		{   boolean checkforwithdrawal	= ac.setWithdrawal(withdrawal);
			if(checkforwithdrawal == true )
		   {
				boolean result = dao.withdrawal(userid,withdrawal);
			    if(result == true)
			    {
				//out.print("<h2>"+deposit+", Deposit Successfully</h2>");
				String resultm = ac.getWithdrawal()+",Withdrawal Successfully. New Balance :" + ac.getBalance();
				session.setAttribute("result",resultm);
				request.getRequestDispatcher("Userdetail.jsp").include(request, response);
			    }
		        else
			   {
				session.setAttribute("result","Not Withdrawal");
			    request.getRequestDispatcher("Userdetail.jsp").include(request, response);
			    }
			}
			else
			{
				session.setAttribute("result","Not Withdrawal Due to Low Balance");
			    request.getRequestDispatcher("Userdetail.jsp").include(request, response);
			}
		}
		
		
//--------------------> End of Withdrawal <-----------------------------//

//--------------------> PrintStatement <-------------------------------//
		String startdate = request.getParameter("startdate");
		String enddate = request.getParameter("enddate");
		System.out.print(startdate + "   "+ enddate);
		if(userid!=null && startdate!=null )
		{
			ResultSet rs=dao.printstmt(userid,startdate,enddate);
			 out.println("<table border=1 width=50% height=50%>");  
             out.println("<tr><th>Userid</th><th>Date</th><th>Deposit</th><th>Withdrawal</th><th>Transfer</th><th>TransferTo</th><th>transferfrom</th></tr>"); 
			if(rs!=null)
			{
				try{
					while(rs.next())
					{
						String useridd = rs.getString(1);
						String dated = rs.getString(2);
						String depositd =rs.getString(3);
						if(depositd==null) depositd =" ";
						String withdrawald =rs.getString(4);
						if(withdrawald==null) withdrawald =" ";
						String transferd = rs.getString(5);
						if(transferd==null) transferd =" ";
						String transfertod = rs.getString(6);
						if(transfertod == null) transfertod =" ";
						String transferfrom = rs.getString(7);
						if(transferfrom == null) transferfrom=" ";
						out.println("<tr><td>" + useridd + "</td><td>" + dated + "</td><td>" + depositd + "</td><td>"+withdrawald+"</td><td>"+transferd+"</td><td>"+transfertod+"</td><td>"+transferfrom+"</td></tr>");
					//out.print("<h2>hi</h2>");
					}
			    	}catch (SQLException e) {
			    		// TODO Auto-generated catch block
			    		e.printStackTrace();
			    		System.out.println("due to useractivity"+e);
			    	}
				out.print("<button style=\"background-color:#04AA6D;color:white;padding:14px 20px; margin: 8px 0;width:10%;\"><a href=\"Userdetail.jsp\">Back</a></button>");
			}
		}
//-------------------> End of PrintStatement <------------------------------//
//-------------------> transfer money <--------------------------------//
		
		String tranferto = request.getParameter("transferto");
		String acno = request.getParameter("acno");
		String amount = request.getParameter("amount");
		
		if(tranferto!=null && amount!=null && userid!=null )
		{
			boolean checkfortranfer = ac.setTransfer(amount);
			if(checkfortranfer == true)
			{
			  boolean result= dao.transfer(userid,amount,tranferto,acno,bal);
			if(result==true)
			{
			    String resultm = amount+",Transfer Successfully. New Balance :" + ac.getBalance();
				session.setAttribute("result",resultm);
				request.getRequestDispatcher("Userdetail.jsp").include(request, response);
				
			}
			else
				{
				String resultm ="Not Transfer";
				//out.print("<h2>Not Transfer</h2>");
				session.setAttribute("result",resultm);
			    request.getRequestDispatcher("SignIn.jsp").include(request, response);
				
				}
			}
			else
			{
				session.setAttribute("result","Not Transfer Due to Low Balance");
			    request.getRequestDispatcher("Userdetail.jsp").include(request, response);
			}
		}
		
		
//--------------------------> End of Transfer money <----------------------------//
		
//---------------------------> account close <-------------------------------------//
		String close = request.getParameter("close");
		if(close!=null && userid!=null)
		{
			if(bal>=0)
			{
				String result = dao.close(userid);
				session.setAttribute("result",result);
			    request.getRequestDispatcher("SignIn.jsp").include(request, response);
			    session.invalidate();
			}
			
			else
				//out.print("<h2>Please Deposit remaining Balance</h2>");
				session.setAttribute("result","Please Deposit remaining Balance");
		    request.getRequestDispatcher("Userdetail.jsp").include(request, response);
			
		}
		
//------------------------> logout <--------------------------------------------------//
		String lg = request.getParameter("lg");
		if(lg!=null && userid!=null)
		{
			String s1="You are successfully logged out!";
			session.setAttribute("result1",s1 );
			request.getRequestDispatcher("HOME.jsp").forward(request, response); 
            session.invalidate();   
            session.removeAttribute("result");
           //out.print("<article><h2>You are successfully logged out!</h2></article>");  
		}
	}

}
