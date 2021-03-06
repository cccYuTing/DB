//package NewDB;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GuiManager {
	private JFrame frame;
	GuiManager gui;

	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://140.127.74.186/410977033";
	static final String USER = "410977033";
	static final String PASS = "410977033";

	public String menagerSQL(int len,String[]line,String s) {
		ArrayList alist = new ArrayList();
		alist.clear();
		int count=0;
		String str = "<html><body>";
		
		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();

			String sql = "";
			sql = s;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				for(int j=0;j<len;j++)
					alist.add(rs.getString(line[j]));
				count++;
			}
			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		} // end try
		System.out.println("Goodbye!"+count);
		
		if(alist.isEmpty()) {
			str=str+"???";
		}
		for(int i=0;i<count;i++) {
			for(int j=0;j<len;j++)
				str=str+alist.get(i*len+j)+" ";
			str=str+"<br>";
			System.out.println(str);
		}
		str = str + "</body></html>";
		
		return str;
	}
	
	public GuiManager() {
		gui = this;
	}

	void setVisible(boolean visiable) {
		frame.setVisible(visiable);
	}

	void initialize() {     
		frame = new JFrame();
		frame.setTitle("Manager");
		frame.setBounds(0, 0, 1300, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JLabel sql = new JLabel("SQL????????????:");    //
		sql.setBounds(180,40,160,25);                       
		frame.getContentPane().add(sql);
		
		JLabel select1 = new JLabel("??????????????????:");    //
		select1.setBounds(180,260,160,25);                       
		frame.getContentPane().add(select1);
		
		JLabel s = new JLabel("????????????:");    //
		s.setBounds(180,400,160,25);                       
		frame.getContentPane().add(s);
	
		
		JTextArea SQL = new JTextArea() ;     //SQL?????????
		SQL.setLineWrap(false);                //???????????????
		SQL.setBounds(280,40,300,200);
		
		JScrollPane sq1 = new JScrollPane(SQL);   //????????????????????????????????????
		sq1.setBounds(280,40,300,200);
		frame.getContentPane().add(sq1);
		
		JTextArea select = new JTextArea() ;     //SQL?????????
		select.setLineWrap(false);                //???????????????
		select.setBounds(280,260,300,100);
		
		JScrollPane select2 = new JScrollPane(select);   //????????????????????????????????????
		select2.setBounds(280,260,300,100);
		frame.getContentPane().add(select2);
		
//		JTextArea output = new JTextArea() ;     //???????????????
//		output.setEditable(false);               //?????????????????????
//		output.setBounds(280,400,800,300);
//		frame.getContentPane().add(output);
//		
//		JScrollPane out = new JScrollPane(output);   //????????????????????????????????????
//		out.setBounds(280,400,800,300);
//		frame.getContentPane().add(out);
		
		JLabel output = new JLabel();  
		output.setBounds(280,400,800,300);
		frame.getContentPane().add(output);

		JButton confirm = new JButton("Confirm");    //??????
		confirm.setBounds(700, 100, 100, 30);
		frame.getContentPane().add(confirm);
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 //??????????????????
//				String[] line = SQL.getText().split("\n");
//				output.append("(????????????)\n");
//				for(int i=0; i<line.length; i++) {
//					output.append(line[i]+"\n");
//				}
				String s=SQL.getText();
				String[] line =select.getText().split(",");
				
				String str=menagerSQL(line.length,line,s);
				output.setText(str);
			}
		});
	}
}
