package com.radvision.icm.service.test;


public class LicenseTester extends ServiceTester {

	public static void main(String[] args) {
		try {
			if (args.length != 1) {
				System.out.println("Usage: LicenseTester <testoption>");
				// System.out.println("<testoption>: 1 - ");
				return;
			}
			System.out.println("Welcome you into the iCM test center.");
			System.out.println("");
			int testoption = -1;
			try {
				testoption = Integer.parseInt(args[0]);
			} catch (Exception ex) {
				System.out.println("The test option is not a digit.");
				// System.out.println("<testoption>: 1 - ");
				return;
			}

			switch (testoption) {

			default:
				System.out.println("The test option is 1.");
				// System.out.println("<testoption>: 1 - ");
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
