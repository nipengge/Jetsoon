package com.MAVLink.common;

import com.MAVLink.MAVLinkPacket;
import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.Messages.MAVLinkPayload;

public class msg_test extends MAVLinkMessage {
	
	
	public msg_test(MAVLinkPacket mavLinkPacket){
		this.sysid = mavLinkPacket.sysid;
		this.compid = mavLinkPacket.compid;
		this.msgid = 11;
		
		unpack(mavLinkPacket.payload);
	}
	

	@Override
	public MAVLinkPacket pack() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void unpack(MAVLinkPayload payload) {
		// TODO Auto-generated method stub
		
	}

}
