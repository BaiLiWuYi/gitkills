package com.cabinet.boxctl;

import java.io.IOException;
import java.net.Socket;

import com.infrastructure.utils.SocketUtils;
import com.infrastructure.utils.TimeRoutine;

public class PhyCabinet {

	private static final int DEF_CABINET_PORT = 50000;
	
	private String ip;
	private int cabNo;
	private int boxNum;
	private boolean isOpenIng = false;
	
	public PhyCabinet(String ip, int cabNo, int boxNum) {
		this.ip = ip;
		this.cabNo = cabNo;
		this.boxNum = boxNum;
	}
	
	public boolean open(int boxNo) {
		if (boxNo<=0 || boxNo>boxNum) {
			return false;
		}
		if (isOpenIng) {
			return false;
		}
		isOpenIng = true;
		
		byte[] data = new byte[1];
		data[0] = (byte) boxNo;
		Pdu pdu = new Pdu(Pdu.CMD_OPEN, data);
		Pdu retPdu = sendAndRecvPdu(pdu);
		isOpenIng = false;//don't forget.
		return retPduAckOkCheck(retPdu);
	}
	
	public boolean openAllBox() {
		return open(Pdu.THE_FST_LUCKY_CODE);
	}

	public boolean bind(int boxNo, String strCardNo) {
		if (strCardNo.length() != Pdu.CARD_NO_LEN) {
			return false;
		}
		byte[] cardNo = strCardNo.getBytes();
		for (int i = 0; i < cardNo.length; i++) {
			cardNo[i] = Pdu.ansic2dec(cardNo[i]);
		}
		byte[] data = new byte[11];
		data[0] = (byte) boxNo;
		for (int i = 0; i < Pdu.CARD_NO_LEN; i++) {
			data[i+1] = cardNo[i];
		}
		
		Pdu pdu = new Pdu(Pdu.CMD_BIND, data);
		Pdu retPdu = sendAndRecvPdu(pdu);
		return retPduAckOkCheck(retPdu);
	}
	
	public boolean unbind(int boxNo) {
		byte[] data = new byte[1];
		data[0] = (byte) boxNo;
		Pdu pdu = new Pdu(Pdu.CMD_UNBIND, data);		
		Pdu retPdu = sendAndRecvPdu(pdu);
		return retPduAckOkCheck(retPdu);
	}
	
	public boolean unbindAll() {
		return unbind(Pdu.THE_FST_LUCKY_CODE);
	}
	
	public boolean bindCover(int boxNo, String cardNo) {
		if (cardNo.length() != Pdu.CARD_NO_LEN) {
			return false;
		}
		byte[] bTmp = cardNo.getBytes();	
		byte[] data = new byte[11];
		data[0] = (byte) boxNo;
		for (int i = 0; i < Pdu.CARD_NO_LEN; i++) {
			data[i+1] = Pdu.ansic2dec(bTmp[i]);
		}		
		Pdu pdu = new Pdu(Pdu.CMD_BINDCOVER, data);
		Pdu retPdu = sendAndRecvPdu(pdu);
		return retPduAckOkCheck(retPdu);
	}
	
	public boolean lock(int boxNo) {
		byte[] data = new byte[1];
		data[0] = (byte) boxNo;
		Pdu pdu = new Pdu(Pdu.CMD_LOCK, data);
		Pdu retPdu = sendAndRecvPdu(pdu);
		return retPduAckOkCheck(retPdu);
	}
	
	public boolean unlock(int boxNo) {
		byte[] data = new byte[1];
		data[0] = (byte) boxNo;
		Pdu pdu = new Pdu(Pdu.CMD_UNLOCK, data);
		Pdu retPdu = sendAndRecvPdu(pdu);
		return retPduAckOkCheck(retPdu);
	}
	
	public boolean grant(int seqNo, String cardNo, String code) {
		if (cardNo.length()!=Pdu.CARD_NO_LEN || code.length()!=Pdu.ADMIN_CODE_LEN) {
			return false;
		}
		byte[] data = new byte[1+Pdu.CARD_NO_LEN+Pdu.ADMIN_CODE_LEN];
		data[0] = (byte) seqNo;
		byte[] bTmp;
		bTmp = cardNo.getBytes();
		for (int i = 0; i < Pdu.CARD_NO_LEN; i++) {
			data[1+i] = Pdu.ansic2dec(bTmp[i]);
		}
		bTmp = code.getBytes();
		for (int i = 0; i < Pdu.ADMIN_CODE_LEN; i++) {
			data[1+Pdu.CARD_NO_LEN+i] = Pdu.ansic2dec(bTmp[i]);
		}
		Pdu pdu = new Pdu(Pdu.CMD_GRANT, data);
		Pdu retPdu = sendAndRecvPdu(pdu);
		return retPduAckOkCheck(retPdu);
	}
	
	public boolean ungrant(int seqNo) {
		byte[] data = new byte[1];
		data[0] = (byte) seqNo;
		Pdu pdu = new Pdu(Pdu.CMD_UNGRANT, data);
		Pdu retPdu = sendAndRecvPdu(pdu);
		return retPduAckOkCheck(retPdu);
	}
	
	/**
	 * 
	 * @return
	 * 	null, if no OpenRecord.
	 */
	public OpenRecord getOpenRecord() {
		Pdu pdu = new Pdu(Pdu.CMD_GET_OPTRECORD, null);
		Pdu retPdu = sendAndRecvPdu(pdu, true);
		if (retPdu==null || retPdu.getCmd()!=Pdu.ACK_OPTRECORD) {
			return null;
		}
		byte[] data = retPdu.getData();
		OpenRecord.TYPE type;
		if (data[0] == 0x01) {
			type = OpenRecord.TYPE.USER;
		} else {
			type = OpenRecord.TYPE.ADMIN;
		}
		int boxNo = data[1];
		String cardNo = new String(data, 9, Pdu.CARD_NO_LEN);
		String time = TimeRoutine.takeTimeInFormat("yyyy-MM-dd HH:mm:ss", 
				data[2]+2000, data[3], data[4], data[6], data[7], data[8]);	
		return new OpenRecord(retPdu.getClientIp(), cardNo, boxNo, type, time);		
	}	
	
	public int getCabNo() {
		return cabNo;
	}
	
	
	/**
	 * 检测retPdu反馈是否为ACK_OK.
	 * @param retPdu
	 * @return
	 */
	private boolean retPduAckOkCheck(Pdu retPdu) {
		if (retPdu==null || retPdu.getCmd()!=Pdu.ACK_OK) {
			return false;
		}	
		return true;
	}
	
	private Pdu sendAndRecvPdu(Pdu pdu) {
		return sendAndRecvPdu(pdu, false);
	}
	
	/**
	 * 发送并接收反馈的Pdu
	 * @param pdu
	 * @return
	 * 	null, if err happen.
	 */
	private Pdu sendAndRecvPdu(Pdu pdu, boolean needIp) {
		if (pdu == null) {
			return null;
		}
		byte[] finalData = pdu.encode();
		if (finalData == null) {
			return null;
		}//encode pdu
		
		Socket sock = buildConnection();
		if (sock == null) {
			return null;
		}//建立链接
		
		try {
			sock.getOutputStream().write(finalData);
		} catch (IOException e) {			
			e.printStackTrace();
			closeConnection(sock);
			return null;
		}		
		
		int ret = 0;
		byte[] gotData = new byte[Pdu.RECV_DATA_SIZE];		
		try {						
			sock.setSoTimeout(SocketUtils.TMO_DATA_RECV);
			ret = sock.getInputStream().read(gotData);			
		} catch (IOException e) {			
			e.printStackTrace();
			closeConnection(sock);
			return null;
		}
		if (needIp) {
			pdu.setClientIp(getIp(sock));
		}		
		closeConnection(sock);
		
		if (ret <= 0) {
			return null;
		}		
		if (!pdu.decode(gotData, ret)) {
			return null;
		}
		return pdu;				
	}
	
	
	private String getIp(Socket sock) {
		if (sock == null) {
			return null;
		}
		String ipOrg = sock.getInetAddress().toString();
		if (ipOrg == null) {
			return null;
		}
		return ipOrg.substring(1);
	}

	private Socket buildConnection() {
		return SocketUtils.buildConnection(ip, DEF_CABINET_PORT);
	}
	
	private void closeConnection(Socket sock) {
		SocketUtils.closeConnection(sock);
	}
	
}
