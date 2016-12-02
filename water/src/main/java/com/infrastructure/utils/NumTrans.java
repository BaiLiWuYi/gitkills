package com.infrastructure.utils;

public class NumTrans {
	
	public static int getByteAbs(int b) {
		if (b >= 0) {
			return b;
		} else {
			return b + 256;
		}
	}

	public static long getLongAbs(long b) {
		long tmp = 4294967296l;

		if (b >= 0) {
			return b;
		} else {
			return b += tmp;
		}
	}

	public static boolean byteCpy(byte[] src, int startSrc,  byte[] dst, int startDst, int offset) {
		if (startSrc>=src.length || startDst>=dst.length) {
			return false;
		}
		for (int i = 0; i<(src.length-startSrc)&&i<(dst.length-startDst)&&i<offset; i++) {
			dst[startDst+i] = src[startSrc+i];
		}
		return true;
	}

	public static String genStrFromDecInByte(byte[] buff, int offset, int len) {
		if (buff.length < offset+len) {
			return null;
		}
		byte[] tmp = new byte[len];
		for (int i = 0; i < tmp.length; i++) {
			tmp[i] = NumTrans.dec2ansic(buff[offset+i]);
		}
		return new String(tmp);
	}

	public static byte ansic2dec(byte ansic) {
		if (ansic >= 0x41) {
			return (byte) (ansic-(byte)0x37);
		}
		return (byte) (ansic-(byte)0x30);
	}
	public static byte dec2ansic(byte dec) {
		dec = (byte) (dec&0x0f);
		if (dec >= 10) {
			return (byte) (dec+(byte)0x37);
		}
		return (byte) (dec+(byte)0x30);
	}

}
