package com.radvision.icm.service.test;

import java.util.List;

import com.radvision.icm.service.ConferenceInfo;
import com.radvision.icm.service.RecurInstanceInfo;
import com.radvision.icm.service.RecurrenceInfo;
import com.radvision.icm.service.ScheduleResult;
import com.radvision.icm.service.ScheduleService;
import com.radvision.icm.service.ScheduleServicePortType;
import com.radvision.icm.service.vcm.ServiceType;

public class SchedulerTester extends ServiceTester {

	public static void main(String[] args) {
		try {
			if (args.length != 1) {
				System.out.println("Usage: SchedulerTester <testoption>");
				System.out.println("<testoption>: 1 - createConference");
				System.out.println("              2 - modifyConference");
				System.out.println("              3 - viewConference");
				System.out.println("              4 - createRecurrence");
				return;
			}
			System.out.println("Welcome you into the iCM test center.");
			System.out.println("");
			int testoption = -1;
			try {
				testoption = Integer.parseInt(args[0]);
			} catch (Exception ex) {
				System.out.println("The test option is not a digit.");
				System.out.println("<testoption>: 1 - createConference");
				System.out.println("              2 - modifyConference");
				System.out.println("              3 - viewConference");
				System.out.println("              4 - createRecurrence");
				return;
			}

			switch (testoption) {
			case 1:
				SchedulerTester.createConference();
				break;
			case 2:
				SchedulerTester.modifyConference();
				break;
			case 3:
				SchedulerTester.viewConference("6100");
				break;
			case 4:
				SchedulerTester.reateRecurrence();
				break;
			default:
				System.out.println("The test option is 1~4.");
				System.out.println("<testoption>: 1 - createConference");
				System.out.println("              2 - modifyConference");
				System.out.println("              3 - viewConference");
				System.out.println("              4 - createRecurrence");
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void createConference() throws Exception {
		ConferenceInfo cb = new ConferenceInfo();
		cb = new ConferenceInfo();
		cb.setConferenceId("6200");
//		cb.setDialableConferenceId("6300");
		cb.setMeetingTypeId("10005");
		cb.setStartTime(System.currentTimeMillis());
		cb.setEndTime(System.currentTimeMillis() + 10000000);
		cb.setSubject("Test Create Conferece 001");
		ScheduleResult sr = getScheduleServicePort().createConference(cb);
		if (sr.isSuccess()) {
			System.out.println("Create a new conference: success");
			TestUtil.printSuccessScheduleResult(sr);
		} else {
			System.out.println("Create a new conference: failure");
			TestUtil.printFailureScheduleResult(sr);
			throw new Exception("Fails to create a new conference");
		}
	}

	private static void modifyConference() throws Exception {
		ConferenceInfo cb = new ConferenceInfo();
		cb = new ConferenceInfo();
		cb.setConferenceId("6100");
		cb.setDialableConferenceId("8100");
		cb.setMeetingTypeId("10002");
		cb.setStartTime(System.currentTimeMillis());
		cb.setEndTime(System.currentTimeMillis() + 100000);
		cb.setSubject("Test Modify Conferece 001");
		ScheduleResult sr = getScheduleServicePort().modifyConference(cb);
		if (sr.isSuccess()) {
			System.out.println("Modify a scheduled conference: success");
			TestUtil.printSuccessScheduleResult(sr);
		} else {
			System.out.println("Modify a scheduled conference: failure");
			TestUtil.printFailureScheduleResult(sr);
			throw new Exception("Fails to modify a scheduled conference");
		}
	}

	private static void viewConference(String cid) throws Exception {
		ConferenceInfo cb = getScheduleServicePort().viewConference(cid);
		if (cb != null) {
			TestUtil.printConferenceInfo(cb);
		} else {
			System.out.println("Fail to view the conference.");
			System.out
					.println("***********************************************************");
			System.out.println("[Conference Id]: " + cid);
			System.out
					.println("***********************************************************");
		}
	}

	private static void reateRecurrence() throws Exception {
		ConferenceInfo cinfo = new ConferenceInfo();
		cinfo.setMeetingTypeId("10005");
		cinfo.setSubject("Test Create Recurrence 001");
		RecurrenceInfo rinfo = new RecurrenceInfo();
		rinfo.setConferenceInfoTemplate(cinfo);
		List<RecurInstanceInfo> times = rinfo.getRecurInstanceInfos();
		RecurInstanceInfo time = new RecurInstanceInfo();
		time.setStartTime(System.currentTimeMillis() + 100000);
		time.setEndTime(System.currentTimeMillis() + 200000);
		times.add(time);

		time = new RecurInstanceInfo();
		time.setStartTime(System.currentTimeMillis() + 300000);
		time.setEndTime(System.currentTimeMillis() + 400000);
		times.add(time);

		time = new RecurInstanceInfo();
		time.setStartTime(System.currentTimeMillis() + 500000);
		time.setEndTime(System.currentTimeMillis() + 600000);
		times.add(time);

		List<ScheduleResult> srs = getScheduleServicePort().createRecurrence(
				rinfo);
		if (srs != null) {
			System.out.println("Succeeds in creating recurrence with "
					+ srs.size() + " instances.");
			System.out
					.println("***********************************************************");
			System.out.println("");
			String[] iids = new String[srs.size()];
			for (int i = 0; i < srs.size(); i++) {
				ScheduleResult sr = srs.get(i);
				ConferenceInfo info = sr.getConferenceInfo();
				if (info != null) {
					iids[i] = info.getConferenceId();
				}
				if (sr.isSuccess()) {
					System.out
							.println("Succeeds in creating the new recurrence instance["
									+ i + "].");
					System.out
							.println("############################################################");
					System.out.println("[Recurrence Id]: "
							+ (info != null ? info.getRecurrenceId() : ""));
					TestUtil.printSuccessScheduleResult(sr);
					System.out
							.println("############################################################");
					System.out.println();
				} else {
					System.out
							.println("Fails to create the new recurrence instance["
									+ i + "].");
					TestUtil.printFailureScheduleResult(sr);
					System.out.println();
					throw new Exception(
							"Fails to create the new recurrence instance[" + i
									+ "]");
				}
			}
		} else {
			System.out
					.println("Fails to create recurrence due to null elements array.");
			throw new Exception(
					"Fails to create recurrence due to null elements array");
		}

	}

	private static ScheduleServicePortType getScheduleServicePort()
			throws Exception {
		return ((ScheduleService) getService(ServiceType.ScheduleService))
				.getScheduleServicePort();
	}
}
