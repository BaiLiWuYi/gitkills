/**
 * 
 */
package com.cabinet.boxctl;

import com.infrastructure.utils.NumTrans;

/*
 * @author Administrator
 * @version 20160512
 */
class Pdu {
	
	public static final int CARD_NO_LEN = 10;
	public static final int ADMIN_CODE_LEN = 4;
	public static final int MAX_BOX_NUM_PER_CAB = 48;
	public static final int THE_FST_LUCKY_CODE = 88;
	public static final int RECV_DATA_SIZE = 200;	
	public static final int CMD_OPEN = 0x01, CMD_BIND = 0x02, CMD_GET_OPTRECORD = 0x0e, CMD_GET_BOXSTATUS = 0x0f,
			CMD_UNBIND = 0x03, CMD_BOXSTATUS = 0x13, CMD_ALL_BOXSTATUS = 0x14, CMD_GET_ALL_STATUS = 0X17,
			CMD_BINDCOVER = 0x15, CMD_LOCK = 0X09, CMD_UNLOCK = 0X0A, CMD_GRANT = 0X04, CMD_UNGRANT = 0X05,
			CMD_REMOTE_CALL = 0X18, CMD_MAX_NUM = 0X20, CMD_SET_PASSWD = 0X0B, CMD_SET_TIME = 0X0D,
			CMD_DOWN_KEYS = 0X19, CMD_GET_LEFTTIME = 0X20, CMD_SET_HOST = 0X16;
	public static final int CMD_CARD_INVALID = 0X58, CMD_DEPOSIT = 0x5B, CMD_FETCH = 0x5C;
	public static final int ACK_OK = 0x32, ACK_ERR = 0x33, ACK_NONE_OPTRECORD = 0x36, 
			ACK_OPTRECORD = 0x51, ACK_GOT_OPTRECORD = 0x38, ACK_BINDED = 0x34, 
			ACK_LOCKED = 0x35, ACK_UNBIND = 0x37, ACK_CARD_NONEXIST = 0x3b,
			ACK_CARD_ON_OTHER = 0x3c,ACK_BOXSTATUS = 0x59, ACK_ALL_BOXSTATUS = 0x5a,
			ACK_TMTOOSHT = 0X3F, ACK_ADMIN = 0x40, ACK_CLEANER = 0x41, ACK_RELEASED = 0x42,
			ACK_LOST = 0x43, ACK_UNUSED = 0x44;
	public static final int BOX_FREE = 0x01, BOX_USE = 0x02, BOX_LOCK = 0x03;
	//public static final int MCU_OPTTYPE_USER =  
	
	private static final int HEAD_PC2MCU = 0x01, HEAD_MCU2PC = 0x02,
			END_PC2MCU = 0x06, END_MCU2PC = 0x07, HEAD_END_CS_SIZE = 4,
			CMD_SIZE = 2;
	
	private int cmd;
	private byte[] data;
	private String clientIp;//用来保存反馈pdu的ip
	
	public Pdu() {
	}

	public Pdu(int cmd, byte[] data) {
		this.cmd = cmd;
		this.data = data;
	}	
	
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public String getClientIp() {
		return clientIp;
	}

	public int getCmd() {
		return cmd;
	}
	
	public byte[] getData() {
		return data;
	}
	
	public byte[] encode() {
		int len;
		if (this.data == null) {
			len = HEAD_END_CS_SIZE+CMD_SIZE;
		} else {
			len = HEAD_END_CS_SIZE+CMD_SIZE+(this.data.length<<1);
		}			
		byte[] data = new byte[len];
		data[0] = HEAD_PC2MCU;
		data[1] = dec2ansic((byte)(cmd>>4));
		data[2] = dec2ansic((byte)(cmd&0x0f));
		if (this.data != null) {
			for (int i = 0; i < this.data.length; i++) {
				data[(i<<1)+3] = dec2ansic((byte)(this.data[i]>>4));
				data[(i<<1)+4] = dec2ansic((byte)(this.data[i]&0x0f));
			}
		}					
		data[len-3] = END_PC2MCU;
		data[len-2] = 0;
		data[len-1] = 0;
		
		return data;				
	}
	
	public boolean decode(byte[] recv, int len) {
		if (len<HEAD_END_CS_SIZE+CMD_SIZE ||recv[0]!=HEAD_MCU2PC ||
				recv[len-3]!=END_MCU2PC) {
			return false;
		}
		this.cmd = (ansic2dec(recv[1])<<4)| (ansic2dec(recv[2]));
		int dataLen = len-HEAD_END_CS_SIZE-CMD_SIZE;
		if (dataLen == 0) {
			return true;
		}
		byte[] data = new byte[dataLen>>1];		
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) ((ansic2dec(recv[(i<<1)+3])<<4)| (ansic2dec(recv[(i<<1)+1+3])));
		}
		this.data = data;
		return true;
	}		
	
	public static byte ansic2dec(byte ansic) {
		return NumTrans.ansic2dec(ansic);
	}
	public static byte dec2ansic(byte dec) {
		return NumTrans.dec2ansic(dec);
	}		
	
}
