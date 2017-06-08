package com.jetsoon.util;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



/**
 * 
* @ClassName: SectionPackUtil
* @Description: TODO(处理MavLink叠包工具类)
* @author nipengge
* @date 2017-6-7 下午12:02:03
*
*/
public class SectionPackUtil {
	//思路：
	//1.>获取叠包数量和数组下标
    //2.>根据下标位置截断相应的包并 保存到List集合
	
	/**
	* 
	* @Title: packetInterception
	* @Description: TODO(截取叠包数据)
	* @param pack 接收到的数据包
	* @param len  接收到的数据包长度
	* @return    设定文件
	* @return List<byte[]>    返回类型
	* @throws
	 */
	public  List<byte[]> packetInterception(byte pack[],int len){
		
		int mavlinkLen = pack[1]+8;//默认第一个包长度,pack[1]是第一个包长度的位置加上 8位 固定数据 就是这个包的长度
		
		List<byte[]> packs = new ArrayList<byte[]>();//存储每个mavLink的包
		
		if(mavlinkLen == len){//根据第一个 包的长度 和 接收到的数据长度匹配
			
			packs.add(pack);
			
		}else{
			
			//
			List<Integer> sectionPackIndex = new ArrayList<Integer>(); //存储截取每个mavLink包的下标
			//第一个包的位置已经算出储存到List
			sectionPackIndex.add(0);
			sectionPackIndex.add(mavlinkLen);
			
			int mavLinkCount = mavlinkLen; //当前检测到的总长度
			int i = 2;//因前两个是固定的所以从2开始
			
			/**
			 * 循环计算重叠包的下标位置
			 * @ mavlinkCount != len  
			 * @ i<7 i循环从2开始也就是最多循环5次  防止死循环 ，真正叠包最多见过3个包叠一起
			 */
			while(mavLinkCount != len && i<7 ){
				
				mavlinkLen = pack[mavLinkCount+1]+8; //计算下一个包的长度
				mavLinkCount += mavlinkLen;//累加每个包的长度就是下一包的截取位置
				sectionPackIndex.add(mavLinkCount);//储存截包的位置
				i++;//控制循环
			}
			
			/**
			 * 根据上次循环获取到的截包位置截取一个数据包并储存到List集合中
			 */
			for (int j = 0; j < sectionPackIndex.size(); j++) {
				
				byte[] packet;//截取的一个包
				
				if(j == sectionPackIndex.size() -1){
					//最后一次的截取尾数是总长度
					packet = Arrays.copyOfRange(pack, sectionPackIndex.get(j), len);
					
				}else{
					//下一个截包的下标就是上一个截包的结尾
					packet = Arrays.copyOfRange(pack, sectionPackIndex.get(j), sectionPackIndex.get(j+1));
				}
				//储存每个截取到的包
				packs.add(packet);
				
			}
			
		}	
			return packs;
	}	
	
}
