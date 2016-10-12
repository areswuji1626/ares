package com.neusoft.test;

import javax.servlet.http.HttpServletRequest;

public class Encrypter
{
	private Encrypter()
	{
		Dec2Bin[0] = "0000";
		Dec2Bin[1] = "0001";
		Dec2Bin[2] = "0010";
		Dec2Bin[3] = "0011";
		Dec2Bin[4] = "0100";
		Dec2Bin[5] = "0101";
		Dec2Bin[6] = "0110";
		Dec2Bin[7] = "0111";
		Dec2Bin[8] = "1000";
		Dec2Bin[9] = "1001";
		Dec2Bin[10] = "1010";
		Dec2Bin[11] = "1011";
		Dec2Bin[12] = "1100";
		Dec2Bin[13] = "1101";
		Dec2Bin[14] = "1110";
		Dec2Bin[15] = "1111";
	}

	public String encode(String pwd)
	{
		return trans(pwd);
	}

	public String unencode(String pwd)
	{
		return unTrans(pwd);
	}
	private String[] Dec2Bin = new String[16];

	private final String PrivateKey = "6102453";

	private String bin2Dec(String BinStr)
	{
		String bs1 = "";
		String bs2 = "";
		String ds1 = "";
		String ds2 = "";
		String bin2Dec;

		bs1 = "0" + BinStr.substring(0, 3);
		bs2 = BinStr.substring(3, 7);
		for (int i = 0; i <= 15; i++)
		{

			if (Dec2Bin[i].compareTo(bs1) == 0)
			{
				ds1 = Integer.toHexString(i);
			}
			if (Dec2Bin[i].compareTo(bs2) == 0)
			{
				ds2 = Integer.toHexString(i);
			}
		}
		bin2Dec = ds1 + ds2;

		return bin2Dec;
	}

	private String displayChr(String Kstr, boolean jj)
	{
		int PassNum;
		String DC = "";
		String OC = "";

		PassNum = Kstr.length() / 7;
		for (int i = 0; i <= PassNum - 1; i++)
		{

			OC = Kstr.substring(i * 7, (i + 1) * 7);
			if (jj == true)
			{
				DC = DC + bin2Dec(OC);
			}
			else
			{
				DC = DC + (char) (Integer.decode("0x" + bin2Dec(OC)).byteValue());
			}

		}

		return DC;
	}

	private String move(String str, String key, boolean jj)
	{
		String TmpSBin = "";
		String TmpDBin = "";
		int Sn;
		int PassNum;
		String BMove = "";

		TmpSBin = str2Bin(str, jj);
		PassNum = TmpSBin.length() / 7;

		for (int i = 1; i <= 7; i++)
		{

			Sn = Integer.valueOf(key.substring(i - 1, i)).intValue();
			TmpDBin = TmpDBin + TmpSBin.substring(Sn * PassNum, (Sn + 1) * PassNum);

		}
		BMove = displayChr(TmpDBin, jj);
		return BMove;
	}

	private String str2Bin(String sStr, boolean S2B)
	{
		String Adata = "";
		String Bdata = "";
		String Tmp16 = "";
		String S16 = "";
		String SBin = "";
		String Str2Bin;

		if (S2B == true)
		{
			for (int i = 1; i <= sStr.length(); i++)
			{
				Adata = sStr.substring(i - 1, i);
				Tmp16 = Integer.toHexString((int) (Adata.charAt(0))).toUpperCase();
				if (Tmp16.length() == 1)
				{
					Tmp16 = "0" + Tmp16;
				}
				S16 = S16 + Tmp16;

			}
		}
		else
		{
			S16 = sStr;

		}

		for (int i = 1; i <= S16.length(); i = i + 2)
		{
			Bdata = S16.substring(i - 1, i);
			SBin = SBin + Dec2Bin[Integer.decode("0x" + Bdata).intValue()].substring(1, 4);
			Bdata = S16.substring(i, i + 1);
			SBin = SBin + Dec2Bin[Integer.decode("0x" + Bdata).intValue()];
		}

		Str2Bin = SBin;
		return Str2Bin;
	}

	private String trans(String str)
	{	
		String TranStr;
		String Trans = "";
		int n = 0;
		int[] m = new int[3];

		if (str.length() <= 6)
		{
			n = 1;
			m[0] = str.length();

		}
		if (str.length() > 6 && str.length() <= 12)
		{
			n = 2;
			m[0] = 6;
			m[1] = str.length();
		}
		if (str.length() > 12)
		{
			n = 3;
			m[0] = 6;
			m[1] = 12;
			m[2] = str.length();
		}

		for (int i = 1; i <= n; i++)
		{

			TranStr = transStr(str.substring((i - 1) * 6, m[i - 1]));
			Trans = Trans + TranStr;

		}

		return Trans;
	}

	private String transStr(String SData)
	{
		String tansStr = "";
		tansStr = move(SData, PrivateKey, true);
		return tansStr;
	}

	private String reverseKey(String key)
	{
		String UnKey = "";
		for (int i = 0; i <= 6; i++)
		{

			UnKey = UnKey + String.valueOf(key.indexOf(String.valueOf(i)));

		}
		
		return UnKey;
	}

	private String unTrans(String str)
	{
		String TranStr;
		String UnTrans = "";
		int n = 0;
		int[] m = new int[3];

		if (str.length() <= 12)
		{
			n = 1;
			m[0] = str.length();

		}
		if (str.length() > 12 && str.length() <= 24)
		{
			n = 2;
			m[0] = 12;
			m[1] = str.length();
		}
		if (str.length() > 24)
		{
			n = 3;
			m[0] = 12;
			m[1] = 24;
			m[2] = str.length();
		}

		for (int i = 1; i <= n; i++)
		{
			TranStr = unTransStr(str.substring((i - 1) * 12, m[i - 1]));
			UnTrans = UnTrans + TranStr;

		}

		return UnTrans;
	}

	private String unTransStr(String SData)
	{
		String untansStr = "";
		untansStr = move(SData, reverseKey(PrivateKey), false);
		return untansStr;
	}

	public static void main(String[] args)
	{
		Encrypter En = new Encrypter();
		String pass = En.trans("1234");
		String unpass = En.unTrans("211b1339");
		System.out.println(pass);
		System.out.println(unpass);
	}
}