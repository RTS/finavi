package com.finavi.ejb.thirdparty;

import java.util.Date;

public class Finance {
	public static final int AT_END_OF_PERIOD = 0;
	public static final int AT_BEGINNING_OF_PERIOD = 1;
	private static final double rateStart[] = { 0.001D, 0.0050000000000000001D,
			0.002D, 0.0070000000000000001D, 0.02D, 0.10000000000000001D,
			-0.01D, -0.001D, -0.0050000000000000001D, -0.002D,
			-0.0070000000000000001D, -0.02D, -0.10000000000000001D,
			0.29999999999999999D, -0.29999999999999999D, 0.5D, -0.5D };
	private static final double ms_per_year = 31536000000D;

	private Finance() {
	}

	public static double cumipmt(double d, int i, double d1, int j, int k, int l) {
		checkRate(d);
		checkWhen(l);
		if (i <= 0 || d1 <= 0.0D || j < 1 || k < 1 || j > k)
			return (0.0D / 0.0D);
		double d2 = 0.0D;
		for (int i1 = j; i1 <= k; i1++)
			d2 += ipmt(d, i1, i, d1, 0.0D, l);

		return d2;
	}

	public static double cumprinc(double d, int i, double d1, int j, int k,
			int l) {
		checkRate(d);
		checkWhen(l);
		if (i <= 0 || d1 <= 0.0D || j < 1 || k < 1 || j > k)
			return (0.0D / 0.0D);
		double d2 = 0.0D;
		for (int i1 = j; i1 <= k; i1++)
			d2 += ppmt(d, i1, i, d1, 0.0D, l);

		return d2;
	}

	public static double db(double d, double d1, int i, int j, int k) {
		double d11 = (0.0D / 0.0D);
		double d8 = d1 / d;
		double d9 = 1.0D / (double) i;
		double d10 = 1.0D - Math.pow(d8, d9);
		d10 *= 1000D;
		d10 = Math.round(d10);
		d10 /= 1000D;
		double d2 = (d * (double) k * d10) / 12D;
		double d6 = d2;
		if (j == 1)
			d11 = d2;
		else if (1 < j && j < i + 1) {
			double d3 = 0.0D;
			for (int l = 1; l < j; l++) {
				d3 = (d - d6) * d10;
				d6 += d3;
			}

			d11 = d3;
		} else if (j == i + 1) {
			double d4 = 0.0D;
			double d7 = d2;
			for (int i1 = 1; i1 < j - 1; i1++) {
				double d5 = (d - d7) * d10;
				d7 += d5;
			}

			d11 = ((d - d7) * d10 * (double) (12 - k)) / 12D;
		}
		return d11;
	}

	public static double ddb(double d, double d1, int i, int j, double d2) {
		double d3 = 0.0D;
		double d4 = (0.0D / 0.0D);
		if (d < 0.0D || d1 < 0.0D || i <= 0 || j <= 0 || d2 <= 0.0D)
			return (0.0D / 0.0D);
		for (int k = 0; k < j; k++) {
			d4 = ((d - d3) * d2) / (double) i;
			double d5 = d3 + d4;
			double d6 = d - d3;
			if (d6 - d4 < d1)
				d4 = d6 - d1;
			d3 += d4;
		}

		return d4;
	}

	public static double dollarde(double d, int i) {
		if (i <= 0)
			return (0.0D / 0.0D);
		int j = 1;
		for (int k = i / 10; k > 0; k /= 10)
			j++;

		double d1 = Math.pow(10D, j);
		double d2 = Math.abs(d);
		double d3 = Math.floor(d2);
		d3 += (d2 - d3) * (d1 / (double) i);
		return d >= 0.0D ? d3 : -d3;
	}

	public static double dollarfr(double d, int i) {
		if (i <= 0)
			return (0.0D / 0.0D);
		int j = 1;
		for (int k = i / 10; k > 0; k /= 10)
			j++;

		double d1 = Math.pow(10D, j);
		double d2 = Math.abs(d);
		double d3 = Math.floor(d2);
		d3 += (d2 - d3) * ((double) i / d1);
		return d >= 0.0D ? d3 : -d3;
	}

	public static double effect(double d, int i) {
		if (d <= 0.0D || i < 1)
			return (0.0D / 0.0D);
		else
			return Math.pow(1.0D + d / (double) i, i) - 1.0D;
	}

	public static double fv(double d, int i, double d1, double d2, int j) {
		checkWhen(j);
		double d3 = (0.0D / 0.0D);
		switch (j) {
		default:
			break;

		case 0: // '\0'
			d3 = d2 * Math.pow(d + 1.0D, i);
			for (int k = 1; k <= i; k++)
				d3 += d1 * Math.pow(d + 1.0D, i - k);

			break;

		case 1: // '\001'
			if (i == 0)
				d3 = d2;
			else
				d3 = (d1 + d2) * Math.pow(d + 1.0D, i);
			for (int l = 1; l <= i - 1; l++)
				d3 += d1 * Math.pow(d + 1.0D, i - l);

			break;
		}
		return 0.0D - d3;
	}

	public static double fvschedule(double d, double ad[]) {
		if (ad.length == 0)
			return (0.0D / 0.0D);
		double d1 = d;
		for (int i = 0; i < ad.length; i++)
			d1 *= 1.0D + ad[i];

		return d1;
	}

	public static double ipmt(double d, int i, int j, double d1, double d2,
			int k) {
		checkWhen(k);
		if (i < 1 || j < i)
			return (0.0D / 0.0D);
		if (d == 0.0D)
			return 0.0D;
		double d4 = pmt(d, j, d1, d2, k);
		double d5 = 1.0D + d;
		double d6 = Math.pow(d5, (j - i) + 1);
		double d7 = (d4 * (d6 - 1.0D)) / d;
		double d3;
		switch (k) {
		case 0: // '\0'
			d3 = (d2 + d7) / d6;
			break;

		case 1: // '\001'
			if (i == 1)
				return 0.0D;
			d3 = (d2 / d5 + d7) / d6;
			break;

		default:
			throw new IllegalArgumentException("when=" + k);
		}
		return d3 * d;
	}

	public static double irr(double ad[]) {
		return irr(ad, rateStart);
	}

	public static double irr(double ad[], double d) {
		double ad1[] = { d };
		return irr(ad, ad1);
	}

	private static double irr(final double pmt[], double ad[]) {
		double d = 9.9999999999999998E-013D;
		ZeroFunction zerofunction = new ZeroFunction();
		zerofunction.setAbsoluteError(d);
		zerofunction.setRelativeError(d);
		ZeroFunction.Function function = new ZeroFunction.Function() {

			public double f(double d3) {
				return Finance.npv(d3, pmt);
			}

		};
		double ad1[] = zerofunction.computeZeros(function, ad);
		double d1 = ad1[0];
		for (int i = 1; i < ad1.length; i++) {
			double d2 = Math.abs(npv(ad1[i], pmt));
			if (d2 <= 100D * d && Math.abs(ad1[i]) < Math.abs(d1))
				d1 = ad1[i];
		}

		return d1;
	}

	public static double mirr(double ad[], double d, double d1) {
		int i = ad.length;
		double ad1[] = new double[i];
		double ad2[] = new double[i];
		for (int j = 0; j < i; j++) {
			if (ad[j] >= 0.0D) {
				ad1[j] = ad[j];
				ad2[j] = 0.0D;
				continue;
			}
			if (ad[j] < 0.0D) {
				ad1[j] = 0.0D;
				ad2[j] = ad[j];
			}
		}

		double d2 = 1.0D + d1;
		double d3 = 1.0D + d;
		double d4 = 0.0D;
		double d5 = 0.0D;
		for (int k = 0; k < i; k++) {
			d4 += ad1[k] / Math.pow(d2, k + 1);
			d5 += ad2[k] / Math.pow(d3, k + 1);
		}

		d4 *= Math.pow(d2, i);
		d5 *= Math.pow(d3, 1.0D);
		double d6 = 1.0D / ((double) i - 1.0D);
		return Math.pow(-d4 / d5, d6) - 1.0D;
	}

	public static double nominal(double d, int i) {
		if (d <= 0.0D || i < 1) {
			return (0.0D / 0.0D);
		} else {
			double d1 = Math.pow(1.0D + d, 1.0D / (double) i);
			return (d1 - 1.0D) * (double) i;
		}
	}

	public static double nper(double d, double d1, double d2, double d3, int i) {
		checkWhen(i);
		if (d == 0.0D)
			return -(d2 + d3) / d1;
		double d4 = d1 / d;
		if (i == 1)
			d4 *= d + 1.0D;
		double d5 = (d4 - d3) / (d4 + d2);
		return Math.log(d5) / Math.log(d + 1.0D);
	}

	public static double npv(double d, double ad[]) {
		double d1 = 0.0D;
		double d2 = 1.0D + d;
		double d3 = 1.0D;
		for (int i = 0; i < ad.length; i++) {
			d3 *= d2;
			d1 += ad[i] / d3;
		}

		return d1;
	}

	public static double pmt(double d, int i, double d1, double d2, int j) {
		checkWhen(j);
		double d3;
		if (d == 0.0D) {
			d3 = (d1 + d2) / (double) i;
		} else {
			double d4 = 1.0D + d;
			double d5 = Math.pow(d4, i);
			d3 = ((d1 * d5 + d2) * d) / (d5 - 1.0D);
			if (j == 1)
				d3 /= d4;
		}
		return 0.0D - d3;
	}

	public static double ppmt(double d, int i, int j, double d1, double d2,
			int k) {
		return pmt(d, j, d1, d2, k) - ipmt(d, i, j, d1, d2, k);
	}

	public static double pv(double d, int i, double d1, double d2, int j) {
		checkWhen(j);
		double d3;
		if (d == 0.0D) {
			d3 = d2 + d1 * (double) i;
		} else {
			double d4 = Math.pow(d + 1.0D, i);
			d3 = 1.0D - 1.0D / d4;
			if (j == 1)
				d3 *= d + 1.0D;
			d3 *= d1 / d;
			d3 += d2 / d4;
		}
		return 0.0D - d3;
	}

	public static double rate(int nper, double pmt, double pv, double fv, int j) {
		return rate(nper, pmt, pv, fv, j, rateStart);
	}

	public static double rate(int nper, double pmt, double pv, double fv,
			int j, double d3) {
		double ad[] = { d3 };
		return rate(nper, pmt, pv, fv, j, ad);
	}

	private static double rate(final int nper, final double pmt,
			final double pv, final double fv, final int when, double ad[]) {
		double d = 1.0000000000000001E-015D;
		ZeroFunction zerofunction = new ZeroFunction();
		ZeroFunction.Function function = new ZeroFunction.Function() {

			public double f(double d3) {
				return Finance.pv(d3, nper, pmt, fv, when) - pv;
			}

		};
		zerofunction.setAbsoluteError(d);
		zerofunction.setRelativeError(d);
		double ad1[] = zerofunction.computeZeros(function, ad);
		double d1 = ad1[0];
		for (int i = 1; i < ad1.length; i++) {
			double d2 = Math.abs(function.f(ad1[i]));
			if (d2 <= 100D * d && Math.abs(ad1[i]) < Math.abs(d1))
				d1 = ad1[i];
		}

		return d1;
	}

	public static double sln(double d, double d1, int i) {
		return (d - d1) / (double) i;
	}

	public static double syd(double d, double d1, int i, int j) {
		if (d1 < 0.0D || i <= 0 || j > i || j <= 0)
			return (0.0D / 0.0D);
		else
			return ((d - d1) * (double) ((i - j) + 1) * 2D)
					/ (double) (i * (i + 1));
	}

	public static double vdb(double d, double d1, int i, int j, int k,
			double d2, boolean flag) {
		if (d < 0.0D || d1 < 0.0D || d2 < 0.0D || i < 0 || j < 0 || k < 0
				|| k > i || j > k)
			return (0.0D / 0.0D);
		int l = 0;
		double d3 = 0.0D;
		double d5 = 0.0D;
		double d7 = 0.0D;
		double d8 = 0.0D;
		if (flag) {
			if (d2 != 0.0D) {
				for (int i1 = j + 1; i1 <= k; i1++)
					d8 += ddb(d, d1, i, i1, d2);

			}
		} else if (d2 == 0.0D) {
			d8 = sln(d, d1, i) * (double) (k - j);
		} else {
			double d9 = 0.0D;
			for (int j1 = 1; j1 <= i; j1++) {
				if (l == 0) {
					double d6 = ddb(d, d1, i, j1, d2);
					double d4 = sln(d - d9, d1, (i - j1) + 1);
					d9 += d6;
					if (d4 > d6) {
						l = j1;
						d7 = d4;
					} else {
						d7 = d6;
					}
				}
				if (j1 >= j + 1 && j1 <= k)
					d8 += d7;
			}

		}
		return d8;
	}

	public static double xirr(double ad[], Date adate[]) {
		return xirr(ad, adate, rateStart);
	}

	public static double xirr(double ad[], Date adate[], double d) {
		double ad1[] = { d };
		return xirr(ad, adate, ad1);
	}

	private static double xirr(final double pmt[], Date adate[], double ad[]) {
		if (pmt.length != adate.length)
			throw new IllegalArgumentException();
		double ad1[] = new double[adate.length];
		ad1[0] = 0.0D;
		for (int i = 0; i < ad1.length; i++) {
			ad1[i] = adate[i].getTime();
			if (i > 0)
				ad1[i] = (ad1[i] - ad1[0]) / 31536000000D;
		}

		ad1[0] = 0.0D;
		final double doubleDate[] = ad1;
		double d = 9.9999999999999998E-013D;
		ZeroFunction zerofunction = new ZeroFunction();
		ZeroFunction.Function function = new ZeroFunction.Function() {

			public double f(double d3) {
				double d4 = 0.0D;
				for (int k = 0; k < pmt.length; k++)
					d4 += pmt[k] / Math.pow(1.0D + d3, doubleDate[k]);

				return d4;
			}

		};
		zerofunction.setAbsoluteError(d);
		zerofunction.setRelativeError(d);
		double ad2[] = zerofunction.computeZeros(function, ad);
		double d1 = ad2[0];
		for (int j = 1; j < ad2.length; j++) {
			double d2 = Math.abs(npv(ad2[j], pmt));
			if (d2 <= 100D * d && Math.abs(ad2[j]) < Math.abs(d1))
				d1 = ad2[j];
		}

		return d1;
	}

	public static double xnpv(double d, double ad[], Date adate[]) {
		checkRate(d);
		if (ad.length != adate.length)
			return (0.0D / 0.0D);
		int i = ad.length;
		for (int j = 0; j < i; j++)
			;
		double d1 = 1.0D + d;
		double d2 = 0.0D;
		double d4 = 0.0D;
		long l = adate[0].getTime();
		for (int k = 1; k < i; k++) {
			long l1 = adate[k].getTime();
			double d3 = (double) (l1 - l) / 31536000000D;
			d4 += ad[k] / Math.pow(d1, d3);
		}

		d4 += Math.floor(ad[0]);
		return d4;
	}

	private static void checkRate(double d) {
		if (d <= 0.0D) {
			Object aobj[] = { "rate", new Double(d) };
			throw new IllegalArgumentException("NotPositive" + aobj);
		}
	}

	private static void checkWhen(int i) {
		if (i != 0 && i != 1) {
			Object aobj[] = { new Integer(i) };
			throw new IllegalArgumentException("Finance.When" + aobj);
		}
	}

	public static void main(String[] args) {
	}

}