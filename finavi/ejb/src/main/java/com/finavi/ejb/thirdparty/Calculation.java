package com.finavi.ejb.thirdparty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public abstract class Calculation {
	public String sChrome = "<script>\n//function toggleChrome() {\n  var is_chrome = navigator.userAgent.toLowerCase().indexOf('chrome') > -1;  \n  if (is_chrome) {\n    thisDiv = document.getElementById(\"GraphCalculator1\");\n    if (thisDiv) {thisDiv.style.display =\"show\";}\n    thisDiv = document.getElementById(\"GraphCalculator2\");\n    if (thisDiv) {thisDiv.style.display = \"show\";}\n  }\n//}\n</script>\n";
	public boolean bHT = false;
	public String MHI = "";
	public String sCurrency = "Dollars";
	public String sScaleLabel3 = "Billions of ";
	public String sScaleLabel2 = "Millions of ";
	public String sScaleLabel1 = "Thousands of ";
	public String MSG_YEARS_LBL = "years";
	public String MSG_YEAR_LBL = "year";
	public String MSG_MONTHS_LBL = "months";
	public String MSG_MONTH_LBL = "month";
	public String MSG_AND_LBL = "and";
	public String[] sReportCols;
	public boolean PAYMENTS_AT_START = false;
	public String _sGraphUnits = "";
	public String _sMessage = null;
	protected StringBuffer sRepeating = new StringBuffer();
	protected int nRepeatingCount;
	public boolean bWithSchedule = false;
	public String _sTableHeader = "<table border=0 cellpadding=0 cellspacing=0 width=100%>";
	public String _sTopRow = "<TR bgcolor=#CCCCCC>";
	public String _sEvenRow = "<TR>";
	public String _sOddRow = "<TR bgcolor=#CCCCCC>";
	public String _sTableFooter = "</table>";
	public String _sRowFooter = "</TR>";
	public String _sCell = "";
	public String _sCellFooter = "";
	public String _sCellFormat = "</TD><TD>";

	public static double APR(double numberOfpayments, double paramDouble2,
			double interestRate, double presentValue, double poplatky) {
		double payment = PMT(interestRate, numberOfpayments, presentValue + poplatky);
		//do payment zapocitam poplatok za vedenie uctu
		return RATE(numberOfpayments, payment , presentValue);
	}

	public static double APY_MONTH(double paramDouble) {
		return FV_AMT(paramDouble, 12.0D, 1.0D) - 1.0D;
	}

	public static double FV(double paramDouble1, double paramDouble2,
			double paramDouble3) {
		if (paramDouble1 == 0.0D)
			return paramDouble2 * paramDouble3;
		return paramDouble3 / paramDouble1
				* (Math.pow(1.0D + paramDouble1, paramDouble2) - 1.0D);
	}

	public static double FV(double paramDouble1, int paramInt,
			double paramDouble2) {
		if (paramDouble1 == 0.0D)
			return paramInt * paramDouble2;
		return paramDouble2 / paramDouble1
				* (Math.pow(1.0D + paramDouble1, paramInt) - 1.0D);
	}

	public static double FV_AMT(double paramDouble1, double paramDouble2,
			double paramDouble3) {
		if (paramDouble1 == 0.0D)
			return paramDouble3;
		return paramDouble3
				/ Math.pow(1.0D + paramDouble1, -1.0D * paramDouble2);
	}

	public static double FV_BEGIN(double paramDouble1, double paramDouble2,
			double paramDouble3) {
		if (paramDouble1 == 0.0D)
			return paramDouble2 * paramDouble3;
		return paramDouble3 / paramDouble1
				* (Math.pow(1.0D + paramDouble1, paramDouble2 + 1.0D) - 1.0D)
				- paramDouble3;
	}

	public String JavaScriptReport(String paramString1, String paramString2)
			throws CalculationException {
		this.bWithSchedule = true;
		recalculate();
		this.bWithSchedule = false;
		String str = replace(
				"&amp;NBSP;",
				"&nbsp;",
				replace("QQ_HEADER_QQ",
						this.MHI,
						replace("CURRENCY_LABEL",
								this.sCurrency,
								formatReport(replace(
										"&QCPQ;",
										"&copy;",
										replace("&NBSP;",
												"&nbsp;",
												replace("**Q**",
														"\"",
														replace("&QQ",
																"&#",
																replace("<START>",
																		"<HTML><HEAD>",
																		this._sMessage)))))))));
		str = replace("**GRAPH**", this.sChrome + " " + paramString1, str);
		str = replace("**GRAPH**", paramString1, str);
		str = replace("**GRAPH2**", paramString2, str);
		int i = str.indexOf("**REPEATING GROUP**");
		if (i == -1)
			return str;
		return replace("**REPEATING GROUP**", getRepeating(), str);
	}

	public static double NPV_AMT(double paramDouble1, double paramDouble2,
			double paramDouble3) {
		return paramDouble3 / Math.pow(1.0D + paramDouble1, paramDouble2);
	}

	public static double PERIODS(double paramDouble1, double paramDouble2,
			double paramDouble3) {
		double d1 = 3120.0D;
		double d2 = 1560.0D;
		double d3 = paramDouble2;
		for (int i = 1; i < 50; i++) {
			d3 = PMT(paramDouble1, d1, paramDouble3);
			if (d3 == paramDouble2)
				return d1;
			if (d3 < paramDouble2)
				d1 -= d2;
			else
				d1 += d2;
			d2 /= 2.0D;
		}
		return d1;
	}

	public static double PERIODS_FV(double paramDouble1, double paramDouble2,
			double paramDouble3) {
		double d1 = 720.0D;
		double d2 = 360.0D;
		double d3 = paramDouble2;
		for (int i = 1; i < 50; i++) {
			d3 = PMT(paramDouble1, d1, NPV_AMT(paramDouble1, d1, paramDouble3));
			if (d3 == paramDouble2)
				return d1;
			if (d3 < paramDouble2)
				d1 -= d2;
			else
				d1 += d2;
			d2 /= 2.0D;
		}
		return d1;
	}

	public static void main(String[] args) {
		//=(1+RATE(pocetsplatok;splatka plus popl;hypo-vstup))^12-1
		double pocetRokov = 2;
		double poplatok =3.32;
		double vstup =69.71;
		double pozicka =1327.76;
		double splatka = PMT(0.078/12,pocetRokov*12,pozicka);
		System.out.println("Splatka: "+splatka+", vratane poplatkov: "+(splatka+poplatok));
		
	}
	

	public static double PMT(double monthlyInterestRate, double numberOfMonthlyPayments,
			double loan) {
		if (numberOfMonthlyPayments <= 0.0D)
			return loan;
		if (numberOfMonthlyPayments <= 1.0D)
			return loan * (1.0D + monthlyInterestRate);
		if (monthlyInterestRate == 0.0D)
			return loan / numberOfMonthlyPayments;
		return loan
				* monthlyInterestRate
				/ (1.0D - Math.pow(1.0D + monthlyInterestRate, numberOfMonthlyPayments
						* -1.0D));
	}

	public static double PMT_BEGIN(double paramDouble1, double paramDouble2,
			double paramDouble3) {
		if (paramDouble2 <= 1.0D)
			return paramDouble3;
		double d1 = PMT(paramDouble1, paramDouble2, paramDouble3);
		double d2 = d1 / 2.0D;
		double d3 = 0.0D;
		for (int i = 1; i < 50; i++) {
			d3 = PMT(paramDouble1, paramDouble2 - 1.0D, paramDouble3 - d1);
			if (d3 == d1)
				return d1;
			if (d3 < d1)
				d1 -= d2;
			else
				d1 += d2;
			d2 /= 2.0D;
		}
		return d1;
	}

	public static double PV(double paramDouble1, double paramDouble2,
			double paramDouble3) {
		return NPV_AMT(paramDouble1, paramDouble2,
				FV(paramDouble1, paramDouble2, paramDouble3));
	}

	public static double RATE(double noOfpayments, double payment,
			double loan) {
		double interestRate = 0.0D;
		double d2 = 2.40D;
		double d3 = payment;
		for (int i = 1; i < 500; i++) {
			d3 = PMT(interestRate, noOfpayments, loan);
			if (d3 == payment)
				return interestRate;
			if (d3 < payment)
				interestRate += d2;
			else
				interestRate -= d2;
			d2 /= 2.0D;
		}
		return interestRate;
	}

	public static double ROR_MONTH(double paramDouble) {
		return Math.pow(1.0D + paramDouble, 0.08333333333333333D) - 1.0D;
	}

	public static double ROR_PERIOD(double paramDouble, int paramInt) {
		return Math.pow(1.0D + paramDouble, 1.0D / paramInt) - 1.0D;
	}

	public void addRepeat(String paramString) {
		if (this.nRepeatingCount == 0) {
			this.sRepeating.append(this._sTableHeader);
			this.sRepeating.append(this._sTopRow);
		} else if (this.nRepeatingCount % 2 == 0) {
			this.sRepeating.append(this._sOddRow);
		} else {
			this.sRepeating.append(this._sEvenRow);
		}
		this.sRepeating.append(paramString);
		this.sRepeating.append(this._sRowFooter);
		this.nRepeatingCount += 1;
	}

	protected abstract void calculate() throws CalculationException;

	protected abstract void clearCalculated();

	public abstract void clearInputed();

	protected abstract String formatReport(String paramString);

	public int getGraphUnits(double paramDouble) {
		return getGraphUnits(paramDouble, 0.0D);
	}

	public int getGraphUnits(double paramDouble1, double paramDouble2) {
		int i = 1;
		if ((paramDouble1 > 100000000000.0D)
				|| (paramDouble2 < -100000000000.0D)) {
			i = 1000000000;
			this._sGraphUnits = this.sScaleLabel3;
		} else if ((paramDouble1 > 100000000.0D)
				|| (paramDouble2 < -100000000.0D)) {
			i = 1000000;
			this._sGraphUnits = this.sScaleLabel2;
		} else if ((paramDouble1 > 100000.0D) || (paramDouble2 < -100000.0D)) {
			i = 1000;
			this._sGraphUnits = this.sScaleLabel1;
		} else {
			this._sGraphUnits = "";
			i = 1;
		}
		return i;
	}

	public static String getMessage(URL paramURL, String paramString) {
		StringBuffer localStringBuffer = new StringBuffer();
		try {
			InputStream localInputStream = paramURL.openStream();
			BufferedReader localBufferedReader = new BufferedReader(
					paramString == null ? new InputStreamReader(
							localInputStream) : new InputStreamReader(
							localInputStream, paramString));
			String str = "";
			while ((str = localBufferedReader.readLine()) != null)
				localStringBuffer.append(str + "\n");
			localInputStream.close();
		} catch (IOException localIOException) {
			return "<HTML><BODY>Financial Calculator Report not found<P>**REPEATING GROUP**</BODY></HTML>";
		}
		return localStringBuffer.toString();
	}

	public String getRepeating() {
		String str = this._sTableFooter;
		this.sRepeating = new StringBuffer();
		return str;
	}

	public String getTermLabel(int paramInt, boolean paramBoolean) {
		int i = paramInt / 12;
		int j = paramInt % 12;
		return ((paramBoolean) || (i > 0) ? i + " "
				+ (i == 1 ? this.MSG_YEAR_LBL : this.MSG_YEARS_LBL) : "")
				+ ((paramBoolean) || ((i > 0) && (j > 0)) ? " "
						+ this.MSG_AND_LBL + " " : "")
				+ ((paramBoolean) || (j > 0) ? j + " "
						+ (j == 1 ? this.MSG_MONTH_LBL : this.MSG_MONTHS_LBL)
						: "");
	}

	public void recalculate() throws CalculationException {
		this._sCellFormat = (this._sCellFooter + "</TD><TD>" + this._sCell);
		this.nRepeatingCount = 0;
		clearCalculated();
		calculate();
	}

	public static String replace(String paramString1, String paramString2,
			String paramString3) {
		int i = 0;
		if (paramString2.equals("-922,337,203,685,477,632.8%"))
			paramString2 = "0%";
		if (paramString2.equals("-92,233,720,368,547,760.08%"))
			paramString2 = "0%";
		int j = paramString1.length();
		int k = paramString3.indexOf(paramString1);
		StringBuffer localStringBuffer = new StringBuffer();
		while (k != -1) {
			localStringBuffer.append(paramString3.substring(i, k));
			localStringBuffer.append(paramString2);
			i = k + j;
			k = paramString3.indexOf(paramString1, i);
		}
		localStringBuffer.append(paramString3.substring(i));
		return localStringBuffer.toString();
	}

	public String sReportCol(String paramString, int paramInt) {
		String str = "";
		if (this.sReportCols == null)
			str = paramString;
		else if ((paramInt < 1) || (paramInt > this.sReportCols.length))
			str = paramString;
		else if (this.sReportCols[(paramInt - 1)] == null)
			str = paramString;
		else
			str = this.sReportCols[(paramInt - 1)];
		return str;
	}
}

/*
 * Location: /home/matus/Downloads/DinkyTown.jar Qualified Name:
 * KJEcalculation.Calculation JD-Core Version: 0.6.0
 */