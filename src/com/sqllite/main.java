package com.sqllite;

import java.io.IOException;
import java.sql.SQLException;

public class main {

	public static void main(String[] args) throws IOException, SQLException {
		// TODO Auto-generated method stub
		readFile rf = new readFile();
		rf.create();
		rf.readData();
		//String sql = "select * from examSelect";
		//rf.select(sql);
//		rf.test();
		//rf.readItem();
	}

}
