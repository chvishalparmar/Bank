package com.bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UseractivityDAO {
	private String dbUrl = "jdbc:mysql://localhost:3306/bank";

	private String dbUname = "root";

	private String dbPassword = "vishal";

	private String dbDriver = "com.mysql.cj.jdbc.Driver";

	

	public void loadDriver(String dbDriver)

	{

		try {

			Class.forName(dbDriver);

		} catch (ClassNotFoundException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

	}

	

	public Connection getConnection()

	{

		Connection con = null;

		try {

			con = DriverManager.getConnection(dbUrl, dbUname, dbPassword);

		} catch (SQLException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

		return con;

	}
// -----------------> for SignIn <--------------------------------//
	protected String checkl(String userid ,String password,int count)

	{
		Boolean id=false,pass=false,lock=null;
        String result="success";
		loadDriver(dbDriver);

		Connection con = getConnection();

		String sql1 = "select userid from memberinfo where userid=?;";
        String sql2 ="select password from memberinfo where password=?;";
		String sql3 ="select locked from memberinfo where userid=?;";
		String sql4 ="update memberinfo set locked='true' where userid=?;";
		
		//Query 1 for userid to check user is vaild or not
		PreparedStatement ps;
		try {
		ps = con.prepareStatement(sql1);		
		ps.setString(1, userid);
		ResultSet rs=ps.executeQuery();  
		id=rs.next();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = "User id not checked by Dao";
			return result;
		}
		
		//Query 2 for password to check user password is right
		PreparedStatement ps1;
		try {
		ps1 = con.prepareStatement(sql2);		
		ps1.setString(1, password);
		ResultSet rs=ps1.executeQuery();  
		pass=rs.next();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result="Password not checked by Dao";
			return result;
		}
		
		//Query 3 for checking for user id lock
		PreparedStatement ps2;
		try{
			ps2 = con.prepareStatement(sql3);
			ps2.setString(1, userid);
			ResultSet rs=ps2.executeQuery();  
			while(rs.next())
			{
				
				String l =rs.getString("locked");
				lock = Boolean.valueOf(l);
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result="lock not checked by Dao";
			return result;
		}
		//System.out.print("userid---------->"+id);
		//System.out.print("password-------->"+pass);
		//System.out.print("lock-------->"+lock);
		//System.out.print("count-------->"+count);
		//System.out.println("completed");
		//condtion
		if(id == true && pass == true && lock==false)
			{
			    result ="true";
			    return result;
			}
		else if(id == true && pass == false)
			{
			   
			       if(count>2 && lock==false)  //if user enter wrong password 3 time then it will be locked
			       {
			    	   PreparedStatement ps3;
			   		try{
			   			ps3 = con.prepareStatement(sql4);
			   			ps3.setString(1, userid);
			   			ps3.executeUpdate();  
			   		}catch (SQLException e) {
			   			// TODO Auto-generated catch block
			   			e.printStackTrace();
			   			result="lock not enter by Dao";
			   			return result;
			   		}
			   		result="Userid locked due to due to 3 successive erroneous passwords";
			   		return result;
			       }
			       result = "Wrong Password";
			       return result;
			}
		else if(id == true && pass == true && lock==true)
		{
			  result ="User Locked";
			  return result;
		}
		else
		    return "Wrong Userid and Password";
	}
//--------------------> End of SignIn <----------------------//
	
//----------------------> Check admin login <-----------------------//
	
	protected boolean checkadmin(String userid ,String password)

	{
		Boolean status =true;  

		loadDriver(dbDriver);

		Connection con = getConnection();


		String sql = "select id,password from adminid where id=? and password=?";

		
		PreparedStatement ps;
		try {

		ps = con.prepareStatement(sql);		

		ps.setString(1, userid);
		
		ps.setString(2, password);
		
		ResultSet rs=ps.executeQuery();  
		status=rs.next();

		} catch (SQLException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();
          System.out.println("Data not check"+e);
			status =false ;

		}
		
		return status;
		
	}

	
//------------------> SignUp <--------------------------------------------//
	protected boolean insert(Member member)

	{

		loadDriver(dbDriver);

		Connection con = getConnection();

		boolean result = true;

		String sql = "insert into bank.memberinfo(userid, password, fname, lname, addressline1, addressline2, state, city, pincode, phone, email, type) values(?,?,?,?,?,?,?,?,?,?,?,?)";

		PreparedStatement ps;
		try {

		ps = con.prepareStatement(sql);		

		ps.setString(1, member.userid());
		
		ps.setString(2, member.getPassword());
		
		ps.setString(3, member.getFname());

		ps.setString(4, member.getLname());

		ps.setString(5, member.getAddress1());
		
		ps.setString(6, member.getAddress2());
		
		ps.setString(7, member.getState());
		
		ps.setString(8, member.getCity());
		
		ps.setString(9, member.getPincode());
		
		ps.setString(10, member.getPhone());
		
		ps.setString(11, member.getEmail());
		
		ps.setString(12, member.getType());
		
		
		
		ps.executeUpdate();

		} catch (SQLException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();
          System.out.println(e);
			result = false;

		}

		return result;

	}
//------------------------> End of SignUp <------------------------------//
	
//------------------------> Account Number <---------------------------//
	protected String accountnumber(String userid)
	{
		loadDriver(dbDriver);

		Connection con = getConnection();
		
		String sql="Select accountnumber from memberinfo where userid=?;";
		String accountnumber = "not checked";
		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);	
			ps.setString(1, userid);
			ResultSet rs = ps.executeQuery();
		     while(rs.next())
		     {
		    	accountnumber =rs.getString("accountnumber");
		     }
		}catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
         System.out.println(e);
		}
		
		return accountnumber;
	}
	
	
//-------------------> Balance <--------------------------------------//
	protected double checkbal(String bal)
	{
		loadDriver(dbDriver);

		Connection con = getConnection();
		
		String sql="Select balance from memberinfo where userid=?;";
		
		double balance=0;
		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);	
			ps.setString(1, bal);
			ResultSet rs = ps.executeQuery();
		     while(rs.next())
		     {
		    	 balance=rs.getDouble("balance");
		     }
		}catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
          System.out.println(e);
		}
		
		return balance;
		
	}
//-------------------> End of balance <------------------------------//
	
//------------------> Update Address <-----------------------------//
	
	protected String updateaddress(String userid,String ad1,String ad2)
	{
		loadDriver(dbDriver);
		Connection con = getConnection();
        String sql ="update memberinfo set addressline1=?,addressline2=? where userid=?;";
		boolean Status = true;
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);	
			ps.setString(1, ad1);
			ps.setString(2, ad2);
			ps.setString(3, userid);
			ps.executeUpdate();
		}catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
          System.out.println(e);
          Status = false;
		}
		if(Status == true)
		return "Address Successful Updated";
		else
			return "Address Not Updated";
    }
	
//----------------> End of Update Address <--------------------------//
	
//----------------> Money Deposit <-------------------------------//	
	protected boolean deposit(String userid,String amount)
	{
		loadDriver(dbDriver);

		Connection con = getConnection();

		boolean result=true;
		
		String sql="update memberinfo set deposit=?,balance=balance+? where userid=?;";
		String sql2="insert into bank.log(userid,deposit,date) values(?,?,?)";
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date1 = new Date();
		String date = formatter.format(date1);
		
		PreparedStatement ps;
		try {

		ps = con.prepareStatement(sql);		
		ps.setString(1, amount);
		ps.setString(2, amount);
		ps.setString(3, userid);
		ps.executeUpdate();

		} catch (SQLException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();
          System.out.println("due to update statement in deposit dao");
			result = false;

		}
		
		PreparedStatement ps1;
		try {

		ps1 = con.prepareStatement(sql2);		

		ps1.setString(1, userid);
		
		ps1.setString(2, amount);
		
		ps1.setString(3, date);
		
		//ps1.setString(3, balance);

		ps1.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
          System.out.println("Deposit log not create by dao");
          result =false;
		}

		return result;
	}
//------------------> End of Deposit <----------------------------//
	
//--------------------> withdrawal <------------------------------//
	
	protected boolean withdrawal(String userid,String withdrawal)
	{
		loadDriver(dbDriver);

		Connection con = getConnection();

		boolean result = true;
		
		String sql="update memberinfo set withdrawal=?,balance=balance-? where userid=?;";
		String sql2="insert into bank.log(userid,withdrawal,date) values(?,?,?)";
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date1 = new Date();
		String date = formatter.format(date1);
		 
		PreparedStatement ps;
		try {

		ps = con.prepareStatement(sql);		
		ps.setString(1, withdrawal);
		ps.setString(2, withdrawal);
		ps.setString(3, userid);
		ps.executeUpdate();

		} catch (SQLException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();
          System.out.println("due dao not entering with drawal");
			result =false;

		}
		
		PreparedStatement ps1;
		try {

		ps1 = con.prepareStatement(sql2);		

		ps1.setString(1, userid);
		
		ps1.setString(2, withdrawal);
		
		ps1.setString(3, date);
		
		//ps1.setString(3, balance);

		ps1.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
          System.out.println(e);
          result =false;
		}
		return result;
	}
//----------------> End of Withdrawal <------------------------------//
	
//----------------> Print Statement <-------------------------------//
	
	protected ResultSet printstmt(String userid, String Startdate, String enddate)
	{

		loadDriver(dbDriver);

		Connection con = getConnection();
		
		//String sql="Select * from log where userid=?";
		String sql="SELECT * FROM log where userid=? and date>=? and date<=?;";
		ResultSet rs=null;
		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);	
			ps.setString(1, userid);
			ps.setString(2, Startdate);
			ps.setString(3, enddate);
			rs = ps.executeQuery();
		     //while(rs.next())
		    // {
		    	// balance=rs.getDouble("balance");
		    // }
		}catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
          System.out.println("due to print statement dao"+e);
		}
		
		return rs;
		
	}

//----------------------> End print Statement <----------------------------//	
	
//----------------------> tranfer money <-----------------------------//
	
	protected boolean transfer(String userid,String amount,String useridto,String acto,double bal )
	{
		loadDriver(dbDriver);

		Connection con = getConnection();

		boolean result=true;

		String sql="update memberinfo set transfer=?,balance=balance-? where userid=?;";
		String sql2="insert into bank.log(userid,transfer,transferto,date,balance) values(?,?,?,?,?);";
		String sql3="update memberinfo set balance=balance+? where userid=? and accountnumber=?;";
		String sql4="insert into bank.log(userid,transfer,transferfrom,date) values(?,?,?,?);";
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date1 = new Date();
		String date = formatter.format(date1);
		 
		PreparedStatement ps;
		try {

		ps = con.prepareStatement(sql);		
		ps.setString(1, amount);
		ps.setString(2, amount);
		ps.setString(3, userid);
		ps.executeUpdate();

		} catch (SQLException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();
          System.out.println("due to dao query 1");
			result = false;

		}
		PreparedStatement ps1;
		try {

		ps1 = con.prepareStatement(sql2);		

		ps1.setString(1, userid);
		
		ps1.setString(2, amount);
		
		ps1.setString(3, useridto);
		
		ps1.setString(4, date);
		
		ps1.setDouble(5, bal);

		ps1.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
          System.out.println("due to dao query 2");
          result = false;
		}
		
		PreparedStatement ps2;
		try {

		ps2 = con.prepareStatement(sql3);		

		ps2.setString(1, amount);
		
		ps2.setString(2, useridto);
		
		ps2.setString(3, acto);
		
		ps2.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
          System.out.println("due to dao query 3");
          result = false;
		}
		
		
		
		PreparedStatement ps3;
		try {

		ps3 = con.prepareStatement(sql4);		

		ps3.setString(1, useridto);
		
		ps3.setString(2, amount);
		
		ps3.setString(3, userid);
		
		ps3.setString(4, date);

		ps3.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
          System.out.println("due to dao query 4");
          result = false;
		}

		return result;
	}
	
//-------------------> End of Transfer Money <-----------------------------------//
	
//-------------------> close account <--------------------------------------//	
	protected String close(String userid)
	{
		loadDriver(dbDriver);

		Connection con = getConnection();

		String result;
		
		String sql ="insert into closeaccount(userid, password, fname, lname, addressline1, addressline2, state, city, pincode, phone, email, type,balance) select userid, password, fname, lname, addressline1, addressline2, state, city, pincode, phone, email, type,balance from memberinfo where userid=?;";
		String sql1="update closeaccount set closedate=? where userid=?;";
		String sql2="DELETE FROM bank.memberinfo WHERE (userid = ?);";
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date1 = new Date();
		String date = formatter.format(date1);
		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);		
			ps.setString(1, userid);
			ps.executeUpdate();
		} catch (SQLException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();
          System.out.println(e);
			result = "Failed";
		}
		
		PreparedStatement ps1;
		try {
			ps1 = con.prepareStatement(sql1);		
			ps1.setString(1, date);
			ps1.setString(2, userid);
			ps1.executeUpdate();
		} catch (SQLException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();
          System.out.println(e);
			result = "Failed";
		}
		
		PreparedStatement ps2;
		try {
			ps2 = con.prepareStatement(sql2);		
			ps2.setString(1, userid); 
			ps2.executeUpdate();
		} catch (SQLException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();
          System.out.println(e);
			result = "Failed";
		}
		result ="Account Deleted";
		return result;
		
	}
//----------------------> End of close account <------------------------------//
	
	
	protected ResultSet adminstmt()
	{
		loadDriver(dbDriver);

		Connection con = getConnection();
		
		String sql="Select * from closeaccount";
		ResultSet rs=null;
		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);	
			rs = ps.executeQuery();
		     //while(rs.next())
		    // {
		    	// balance=rs.getDouble("balance");
		    // }
		}catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
          System.out.println(e);
		}
		
		return rs;
		
	}
	
}
