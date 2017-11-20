package com.sqllite;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.*;

public class readFile {
	String fname = "D://CODE/JavaWeb/Sqlite/src/com/sqllite/max.txt";
	DBOP dbop = new DBOP();//�������ݿ�

	
	
	void test() throws IOException
	{
		char c =  ' ';
		@SuppressWarnings("resource")
		BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(fname),"UTF-8"));

		
		int ch;
		while((ch=br.read())!=-1)
		{	
			c = (char)ch;
//			System.out.print(ch);
//			System.out.print(" ");
			//System.out.print(c);
//			System.out.print(" ");
	
		}
	}
	
	void create() throws SQLException
	{
		String sql = "CREATE TABLE examSelect " +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " question TEXT, " + 
                " A TEXT," + 
                " B TEXT," + 
                " C TEXT," + 
                " D TEXT," + 
                " E TEXT," +
                "answer varchar(5))"; 
		dbop.excute(sql);
		System.out.println("�����ɹ���");
	}
	
	
	/*��ȡѡ��*/
	void readItem() throws IOException, SQLException
	{
		String sql;
		
		@SuppressWarnings("resource")
		BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(fname),"UTF-8"));
		char c =  ' ';
		List<String> list = new ArrayList<String>();
		int ch;
		String str = "";
		String s = "";
		while((ch=br.read())!=-1)
		{	
			c = (char)ch;
			if(ch>=65&&ch<=69)
			{
				ch = br.read();
				if(ch==65294||ch==46||ch==12289)//65294�ǡ�.����UTF-8����
				{
					str = "";
					s = "";
					while((ch=br.read())!=10)
					{
						c = (char)ch;
						s = String.valueOf(c);
						str = str+s;
						
					}
					list.add(str);
					//System.out.println(str);
				}
			}
		}
		Iterator<String> it = list.iterator();
		int flag = 1;
		String item = "";
		while(it.hasNext())
		{
			item = it.next();
			System.out.println(item);
			
			
		}

		
		
	}
	
	/*��ȡ��ɺ�ѡ��*/
	void readData() throws IOException, SQLException
	{	
		//��ȡ�ļ�����
		BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(fname),"UTF-8"));
		String sql;
		char c =  ' ';//��ʱ����������ĵ����ַ�
		String str = "";//��ʱ������Ŀ������ַ���
		String s = "";//��ʱ������Ŀ�ĵ����ַ�
		
		int flag = 1;//�жϺ�����ַ��Ƿ�����ɵı�־�����������flag=2����������flag=1
		
		
		int ch;//��ʱ������������ַ����루��������
		while((ch=br.read())!=-1)
		{	
			str="";
			s="";
			if(flag!=2)
			{
				if(ch>=48&&ch<=57)//��ȡ��������
				{
					ch = br.read();
					if(ch>=48&&ch<=57)//��ȡ��������
					{
						ch = br.read();
						if(ch==65294||ch==46||ch==12289)//��ȡ���˵�(65294�ǡ�.����UTF-8����)˵���������ַ��������
						{
							flag=2;
						}
					}
					else if(ch==65294||ch==46||ch==12289)//65294�ǡ�.����UTF-8����
					{
						flag=2;
					}
				}
			}
			while(true)//65289�ǡ�������UTF-8����
			{
				
				if(ch==41) 
				{
					c = (char)ch;
					s = String.valueOf(c);
					str = str+s;
					break;
				}
				if(ch==65289) 
				{
					c = (char)ch;
					s = String.valueOf(c);
					str = str+s;
					break;
				}
				c = (char)ch;
				s = String.valueOf(c);
				str = str+s;
				ch = br.read();
			}

			sql = "INSERT INTO examSelect (question) " +
	                   "VALUES ('" + str + "');"; 
	     	dbop.excute(sql);
			//System.out.println("Insert successfully!");
			//if(str!="") System.out.println(str);
			String id = dbop.selectId("SELECT ID FROM examSelect WHERE question = '"+str+"'");
			
			/*�˴��Զ�����ɣ�������Ӧ�ö�ѡ��*/
			
			s="";
			str="";
			flag=1;//flag=4��ʾ��һ���ַ�����ѡ���е�����
					//flag=5��ʾ��һ���ַ��е���������ѡ��
			
			int insert = 1;
			while(flag!=2)//flag=2��ʾ��һ���ַ��е������Ѿ����������
			{
				ch=br.read();
				
				switch(ch)
				{
				case 48:
				case 49:
				case 50:
				case 51:
				case 52:
				case 53:
				case 54:
				case 55:
				case 56:
				case 57:
					int temp = ch;
					char tempc = (char)temp;
					String temps = String.valueOf(tempc);
					
					ch = br.read();
					if(ch>=48&&ch<=57)
					{
						int temp2 = ch;
						char tempc2 = (char)temp2;
						String temps2 = String.valueOf(tempc2);
						
						ch = br.read();
						if(ch==65294||ch==46||ch==12289)
						{
							if(insert==1)
							{
								sql = "UPDATE examSelect SET D =  '" + str +
						                   "' WHERE ID = "+id; 
						     	dbop.excute(sql);
							}
							else if(insert==2)
							{
								sql = "UPDATE examSelect SET E =  '" + str +
						                   "' WHERE ID = "+id; 
						     	dbop.excute(sql);
							}
							//System.out.println(str);
							
							flag = 2;
						}
						else
						{
							str = str +temps;
							str = str+temps2;
						}
					}
					else if(ch==65294||ch==46||ch==12289)
					{
						if(insert==1)
						{
							sql = "UPDATE examSelect SET D =  '" + str +
					                   "' WHERE ID = "+id; 
					     	dbop.excute(sql);
						}
						else if(insert==2)
						{
							sql = "UPDATE examSelect SET E =  '" + str +
					                   "' WHERE ID = "+id; 
					     	dbop.excute(sql);
						}
						//System.out.println(str);
						
						flag =2;
					}
					else
					{
						str = str+temps;
						c = (char)ch;
						s = String.valueOf(c);
						str = str+s;
						
					}
					break;
				case 65:
					//��ȡ��A�ַ�
					ch=br.read();
					if(ch==65294||ch==46||ch==12289)
					{
						str="";
						s="";
					}
					else
					{
						str = str+"A";
					}
						
					break;
				case 66:
					//��ȡ��B�ַ�
					ch=br.read();
					if(ch==65294||ch==46||ch==12289)
					{
						sql = "UPDATE examSelect SET A =  '" + str +
				                   "' WHERE ID = "+id; 
				     	dbop.excute(sql);
						//System.out.println(str);
						str="";
						s="";
					}
					else
					{
						str = str+"B";
					}
					break;
				case 67:
					//��ȡ��C�ַ�
					ch=br.read();
					if(ch==65294||ch==46||ch==12289)
					{	sql = "UPDATE examSelect SET B =  '" + str +
	                   "' WHERE ID = "+id; 
	     	dbop.excute(sql);
						//System.out.println(str);
						str="";
						s="";
					}
					else
					{
						//System.out.println(str);
						str = str+"C";
					}
					break;
				case 68:
					//��ȡ��D�ַ�
					ch=br.read();
					if(ch==65294||ch==46||ch==12289)
					{
						insert = 1;
						sql = "UPDATE examSelect SET C =  '" + str +
				                   "' WHERE ID = "+id; 
				     	dbop.excute(sql);
						//System.out.println(str);
						str="";
						s="";
					}
					else
					{
						str = str+"D";
					}
					break;
				case 69:
					//��ȡ��E�ַ�
					ch=br.read();
					if(ch==65294||ch==46||ch==12289)
					{
						insert = 2;
						sql = "UPDATE examSelect SET D =  '" + str +
				                   "' WHERE ID = "+id; 
				     	dbop.excute(sql);
						//System.out.println(str);
						str="";
						s="";
					}
					else
					{
						str = str+"E";
					}
					break;
				default:
					c = (char)ch;
					s = String.valueOf(c);
					str = str+s;
						
				}
			
			}

				
		}
	}
	
	void select( String sql) throws SQLException
	{
		dbop.select(sql);
	}



}
