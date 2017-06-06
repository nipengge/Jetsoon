package com.jetsoon.serverconn;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.jetsoon.clientconn.UAVSocketCilent;
import com.jetsoon.config.ServerConfig;
import com.jetsoon.frame.UavMainFrame;
/**
 * ���������������
 * �������ӷ������ͻ��˵ķַ�
 * @author nipengge
 * ����:������
 * ʱ��:2017-2-26 ����08:05:38
 */
public class UAVSocketServer extends Thread{
	
	private ServerSocket serverSocket;
	
	private List<UAVSocketCilent> mlist_click = new ArrayList<UAVSocketCilent>();
	
	
	public void run(){
		
		super.run();
		
		try {
			
			serverSocket = new ServerSocket(ServerConfig.PORT);
			
			UavMainFrame.setMessage("�������ѿ����˿�:"+ServerConfig.PORT);
			System.out.println("�������ѿ����˿�:"+ServerConfig.PORT);
			//��ѭ�������˶˿��пͻ�������
			while (true) {
				//�����˶˿�
				Socket clientSocket = serverSocket.accept();
				//�����ͻ������ӷ���һ���߳�
				UAVSocketCilent uavSocketCilent = new UAVSocketCilent(this,clientSocket);
				//����
				uavSocketCilent.start();
				
				UavMainFrame.setMessage("�ͻ������ӣ�"+clientSocket);
				
				mlist_click.add(uavSocketCilent);
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
		}
		
	}
	
	/**
	 * �ͻ��˶Ͽ����� ɾ��������
	 * @param uavSocketCilent
	 */
	public void removeClient(UAVSocketCilent uavSocketCilent){
		
		if(uavSocketCilent != null){
			
			synchronized (mlist_click) {//����ͬ���̰߳�ȫ
				
					if(mlist_click.contains(uavSocketCilent)){//�����Ӵ���
						 mlist_click.remove(uavSocketCilent);
						 UavMainFrame.setMessage("�豸"+uavSocketCilent+"�Ͽ�����");
					}
			}
		}
		
		
	}

}
