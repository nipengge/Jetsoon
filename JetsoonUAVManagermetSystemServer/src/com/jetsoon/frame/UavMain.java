package com.jetsoon.frame;


import javax.swing.JFrame;


public class UavMain {
	
	public static void main(String[] args) {
		
	//	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		UavMainFrame uavMainFrame = new UavMainFrame();
		
		uavMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		  // serverFrame.setBounds((screenSize.width) / 6, (screenSize.height) / 6, 1000, 600);
			
		uavMainFrame.setVisible(true);
		
	}

}
