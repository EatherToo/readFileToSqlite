package com.sqllite;

import java.sql.*;

public class DBOP {

	Connection con = null;
	Statement stmt;
	DBOP()
	{

		try 
		{
		      Class.forName("org.sqlite.JDBC");
		      con = DriverManager.getConnection("jdbc:sqlite:test.db");
		      stmt = con.createStatement();
		    
		}
		catch(Exception e)
		{
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		}
		    System.out.println("Opened database successfully");
	}

	void excute(String sql) throws SQLException
	{
		stmt.executeUpdate(sql);
	}
	
	void close() throws SQLException
	{
		stmt.close();
		con.close();
		System.out.println("Closed database successfully!");
	}
	void select(String sql) throws SQLException
	{
		ResultSet rs = stmt.executeQuery(sql);
	      while ( rs.next() ) {
	         String question = rs.getString("question");
	         System.out.println(question);
	      }

	}
	String selectId(String sql) throws SQLException
	{
		ResultSet rs = stmt.executeQuery(sql);
		rs.next();
		String id = rs.getString("ID");
		return id;
		
	}
}

	
