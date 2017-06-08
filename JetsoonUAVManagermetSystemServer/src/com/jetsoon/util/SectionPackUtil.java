package com.jetsoon.util;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



/**
 * 
* @ClassName: SectionPackUtil
* @Description: TODO(����MavLink����������)
* @author nipengge
* @date 2017-6-7 ����12:02:03
*
*/
public class SectionPackUtil {
	//˼·��
	//1.>��ȡ���������������±�
    //2.>�����±�λ�ýض���Ӧ�İ��� ���浽List����
	
	/**
	* 
	* @Title: packetInterception
	* @Description: TODO(��ȡ��������)
	* @param pack ���յ������ݰ�
	* @param len  ���յ������ݰ�����
	* @return    �趨�ļ�
	* @return List<byte[]>    ��������
	* @throws
	 */
	public  List<byte[]> packetInterception(byte pack[],int len){
		
		int mavlinkLen = pack[1]+8;//Ĭ�ϵ�һ��������,pack[1]�ǵ�һ�������ȵ�λ�ü��� 8λ �̶����� ����������ĳ���
		
		List<byte[]> packs = new ArrayList<byte[]>();//�洢ÿ��mavLink�İ�
		
		if(mavlinkLen == len){//���ݵ�һ�� ���ĳ��� �� ���յ������ݳ���ƥ��
			
			packs.add(pack);
			
		}else{
			
			//
			List<Integer> sectionPackIndex = new ArrayList<Integer>(); //�洢��ȡÿ��mavLink�����±�
			//��һ������λ���Ѿ�������浽List
			sectionPackIndex.add(0);
			sectionPackIndex.add(mavlinkLen);
			
			int mavLinkCount = mavlinkLen; //��ǰ��⵽���ܳ���
			int i = 2;//��ǰ�����ǹ̶������Դ�2��ʼ
			
			/**
			 * ѭ�������ص������±�λ��
			 * @ mavlinkCount != len  
			 * @ i<7 iѭ����2��ʼҲ�������ѭ��5��  ��ֹ��ѭ�� ����������������3������һ��
			 */
			while(mavLinkCount != len && i<7 ){
				
				mavlinkLen = pack[mavLinkCount+1]+8; //������һ�����ĳ���
				mavLinkCount += mavlinkLen;//�ۼ�ÿ�����ĳ��Ⱦ�����һ���Ľ�ȡλ��
				sectionPackIndex.add(mavLinkCount);//����ذ���λ��
				i++;//����ѭ��
			}
			
			/**
			 * �����ϴ�ѭ����ȡ���Ľذ�λ�ý�ȡһ�����ݰ������浽List������
			 */
			for (int j = 0; j < sectionPackIndex.size(); j++) {
				
				byte[] packet;//��ȡ��һ����
				
				if(j == sectionPackIndex.size() -1){
					//���һ�εĽ�ȡβ�����ܳ���
					packet = Arrays.copyOfRange(pack, sectionPackIndex.get(j), len);
					
				}else{
					//��һ���ذ����±������һ���ذ��Ľ�β
					packet = Arrays.copyOfRange(pack, sectionPackIndex.get(j), sectionPackIndex.get(j+1));
				}
				//����ÿ����ȡ���İ�
				packs.add(packet);
				
			}
			
		}	
			return packs;
	}	
	
}
