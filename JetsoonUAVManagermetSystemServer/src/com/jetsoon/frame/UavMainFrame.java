package com.jetsoon.frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import javax.swing.WindowConstants;


import com.jetsoon.clientconn.UAVSocketCilent;
import com.jetsoon.serverconn.UAVSocketServer;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class UavMainFrame extends javax.swing.JFrame implements ActionListener {
	private static JTextArea jTextArea1;
	private JPanel jPanel1;
	private JButton jButton3;
	private JButton jButton1;
	private JScrollPane jScrollPane1;
	private JTextField jTextField1;

	private static String context;
	
	private UAVSocketServer uavSocketServer = null;

	/**
	* Auto-generated main method to display this JFrame
	*/
/*	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				UavMainFrame inst = new UavMainFrame();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
		UAVSocketServer uavSocketServer = new UAVSocketServer();
		uavSocketServer.start();
	}*/
	public UavMainFrame() {
		super("[JETSOON无人机管理调度系统服务器]");
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			{
				jButton3 = new JButton();/*清空缓存区*/
				getContentPane().add(jButton3);
				jButton3.setText("\u6e05\u7a7a\u7f13\u51b2\u533a");
				jButton3.setBounds(656, 21, 100, 41);
				jButton3.addActionListener(this);
			}
			{
				jPanel1 = new JPanel();
				getContentPane().add(jPanel1);
				jPanel1.setBounds(6, 81, 762, 464);
				{
					jScrollPane1 = new JScrollPane();
					jPanel1.add(jScrollPane1);
					jScrollPane1.setPreferredSize(new java.awt.Dimension(739, 457));
					{
						jTextArea1 = new JTextArea();
						jScrollPane1.setViewportView(jTextArea1);
					}
				}
			}
			{
				jButton1 = new JButton();
				getContentPane().add(jButton1);
				jButton1.setText("\u53d1\u9001\u547d\u4ee4");
				jButton1.setBounds(525, 21, 103, 41);
				jButton1.addActionListener(this);
			}
			{
				jTextField1 = new JTextField();
				getContentPane().add(jTextField1);
				jTextField1.setBounds(18, 29, 451, 33);
			}
			pack();
			this.setSize(789, 596);
			uavSocketServer = new UAVSocketServer();
			uavSocketServer.start();
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	public static void setMessage(String msg) {
		// TODO Auto-generated method stub
		context = msg+"\n";
		jTextArea1.append(context);
		jTextArea1.setCaretPosition(jTextArea1.getText().length());
	}
	
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		if(event.getSource() == jButton3){
			jTextArea1.setText("");
		}else if(event.getSource()== jButton1){
			UAVSocketCilent.sendMessageMapAll(jTextField1.getText().trim());
			//UAVSocketCilent.sendMessageList(jTextField1.getText().trim());
		}
	}

}
