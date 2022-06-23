

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GuiManager {
	private JFrame frame;
	GuiManager gui;

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
		
		
		JLabel sql = new JLabel("SQL語法輸入:");    //
		sql.setBounds(180,40,160,25);                       
		frame.getContentPane().add(sql);
		
		
		JLabel s = new JLabel("查詢結果:");    //
		s.setBounds(180,400,160,25);                       
		frame.getContentPane().add(s);
	
		
		JTextArea SQL = new JTextArea() ;     //SQL輸入區
		SQL.setLineWrap(false);                //不自動換行
		SQL.setBounds(280,40,300,200);
		
		JScrollPane sq1 = new JScrollPane(SQL);   //將多行文字框，加滾輪卷軸
		sq1.setBounds(280,40,300,200);
		frame.getContentPane().add(sq1);
		
		JTextArea output = new JTextArea() ;     //結果輸出區
		output.setEditable(false);               //輸出區無法編輯
		output.setBounds(280,400,800,300);
		frame.getContentPane().add(output);
		
		JScrollPane out = new JScrollPane(output);   //將多行文字框，加滾輪卷軸
		out.setBounds(280,400,800,300);
		frame.getContentPane().add(out);

		JButton confirm = new JButton("Confirm");    //確認
		confirm.setBounds(700, 100, 100, 30);
		frame.getContentPane().add(confirm);
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 //顯示查詢結果
				String[] line = SQL.getText().split("\n");
				output.append("(查詢結果)\n");
				for(int i=0; i<line.length; i++) {
					output.append(line[i]+"\n");
				}
				
			}
		});
	}
}
