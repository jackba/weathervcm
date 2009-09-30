package com.radvision.icm.service.test;

import java.util.ArrayList;
import java.util.List;

import com.radvision.icm.service.DeviceControllerIpPort;
import com.radvision.icm.service.Layout;
import com.radvision.icm.service.MeetingType;
import com.radvision.icm.service.ResourceResult;
import com.radvision.icm.service.ResourceService;
import com.radvision.icm.service.ResourceServicePortType;
import com.radvision.icm.service.RoomResource;
import com.radvision.icm.service.TerminalResource;
import com.radvision.icm.service.View;
import com.radvision.icm.service.vcm.ServiceType;

public class ResourceTester extends ServiceTester {

	public static void main(String[] args) {
		try {
			if (args.length != 1) {
				System.out.println("Usage: ResourceTester <testoption>");
				System.out.println("<testoption>: 1 - getNetClassfications");
				System.out.println("              2 - getMeetingTypes");
				System.out.println("              3 - getTerminals");
				System.out.println("              4 - getRooms");
				System.out.println("              5 - setTerminals");
				return;
			}
			System.out.println("Welcome you into the iCM test center.");
			System.out.println("");
			int testoption = -1;
			try {
				testoption = Integer.parseInt(args[0]);
			} catch (Exception ex) {
				System.out.println("The test option is not a digit.");
				System.out.println("<testoption>: 1 - getNetClassfications");
				System.out.println("              2 - getMeetingTypes");
				System.out.println("              3 - getTerminals");
				System.out.println("              4 - getRooms");
				System.out.println("              5 - setTerminals");
				return;
			}

			switch (testoption) {
			case 1:
				ResourceTester.testIPPorts();
				break;
			case 2:
				ResourceTester.testMeetingTypes();
				break;
			case 3:
				ResourceTester.testTerminals("all");
				break;
			case 4:
				ResourceTester.testRooms();
				break;
			case 5:
				ResourceTester.testSetTerminals();
				break;
			default:
				System.out.println("The test option is 1~5.");
				System.out.println("<testoption>: 1 - getIPPorts");
				System.out.println("              2 - getMeetingTypes");
				System.out.println("              3 - getTerminals");
				System.out.println("              4 - getRooms");
				System.out.println("              5 - setTerminals");
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static ResourceServicePortType getResourceServicePort()
			throws Exception {
		return ((ResourceService) getService(ServiceType.ResourceService))
				.getResourceServicePort();
	}

	private static void testIPPorts() throws Exception {
		DeviceControllerIpPort ipport = getResourceServicePort()
				.getDeviceControllerIpPort();
		if (ipport != null) {
			System.out.println("IP:" + ipport.getIp());
			System.out.println("Port:" + ipport.getPort());
		} else {
			System.out.println("IP Port Error or empty");
		}
	}

	private static void testMeetingTypes() throws Exception {
		try {
			List<MeetingType> mts = getResourceServicePort().getMeetingTypes();
			if (mts != null) {
				for (int i = 0; i < mts.size(); i++) {
					MeetingType mt = mts.get(i);
					System.out.println("Meeting Type:" + mt.getServicePrefix());
					List<Layout> ls = mt.getLayouts();
					for (int j = 0; j < ls.size(); j++) {
						Layout l = ls.get(j);
						System.out
								.println("  Layout type:" + l.getLayoutType());
					}
					List<View> vs = mt.getViews();
					for (int j = 0; j < vs.size(); j++) {
						View v = vs.get(j);
						System.out.println("  InitialVideoLayout:"
								+ v.getInitialVideoLayout());
						System.out.println("  MaxVideoLayout:"
								+ v.getMaxVideoLayout());
					}
				}
			} else {
				System.out.println("Meeting Type Error or empty");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void testTerminals(String classificationId) throws Exception {
		List<TerminalResource> tbs = getResourceServicePort().getTerminals(
				classificationId);
		if (tbs != null) {
			if (tbs.size() == 0) {
				System.out.println("Terminal Error or empty");
				return;
			}
			for (int i = 0; i < tbs.size(); i++) {
				TerminalResource tr = tbs.get(i);
				System.out.println("Terminal Id:" + tr.getTerminalId());
				List<String> cids = tr.getClassificationNames();
				if (cids != null) {
					for (int j = 0; j < cids.size(); j++) {
						System.out.println("   Classification Name:"
								+ cids.get(j));
					}
				}
			}
		} else {
			System.out.println("Terminal Error or empty");
		}
	}

	private static void testRooms() throws Exception {
		List<RoomResource> rbs = getResourceServicePort().getRooms();
		if (rbs != null) {
			for (int i = 0; i < rbs.size(); i++) {
				System.out.println("Room Id:"
						+ rbs.get(i).getConferenceRoomId());
			}
		} else {
			System.out.println("Room Id Error or empty");
		}
	}

	private static void testSetTerminals() throws Exception {
		List<TerminalResource> terminals = new ArrayList<TerminalResource>();
		// H323
		TerminalResource terminal = new TerminalResource();
		terminal.setTerminalName("Terminal_H323_1");
		terminal.setTerminalNumber("562611");
		terminals.add(terminal);

		// H320
		terminal = new TerminalResource();
		terminal.setTerminalName("Terminal_H320_1");
		terminal.setTerminalNumber("6552852811");
		/**
		 * 0 - H323/IP, the default type 
		 * 1 � H320/ISDN 
		 * 2 � Dual 
		 * 3 � SIP 
		 * 4 � PSTN
		 */
		terminal.setTerminalProtocol(1);
		terminals.add(terminal);

		// SIP
		terminal = new TerminalResource();
		terminal.setTerminalName("Terminal_SIP_1");
		terminal.setTerminalNumber("sip:juping11@radvision.com");
		terminal.setTerminalProtocol(3);
		terminals.add(terminal);

		ResourceResult result = getResourceServicePort().setTerminals(
				terminals, false);
		if (result == null || result.getTerminals() == null
				|| result.getTerminals().size() == 0) {
			System.out.println("Error or Terminal Result empty");
		} else {
			List<TerminalResource> tr = result.getTerminals();
			for (int i = 0; i < tr.size(); i++) {
				System.out.println("TerminalResource: name = "
						+ tr.get(i).getTerminalName() + "; number = "
						+ tr.get(i).getTerminalNumber() + "; protocol = "
						+ tr.get(i).getTerminalProtocol() + "; id = "
						+ tr.get(i).getTerminalId());
			}
		}
	}
}
