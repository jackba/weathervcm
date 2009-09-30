package com.radvision.icm.service.test;

import com.radvision.icm.service.ControlResult;
import com.radvision.icm.service.ControlService;
import com.radvision.icm.service.ControlServicePortType;
import com.radvision.icm.service.Terminal;
import com.radvision.icm.service.vcm.ServiceType;

public class ControlTester extends ServiceTester {

	public static void main(String[] args) {
		try {
			if (args.length != 1) {
				System.out.println("Usage: ControlTester <testoption>");
				System.out.println("<testoption>: 1 - inviteNewTerminal");
				return;
			}
			System.out.println("Welcome you into the iCM test center.");
			System.out.println("");
			int testoption = -1;
			try {
				testoption = Integer.parseInt(args[0]);
			} catch (Exception ex) {
				System.out.println("The test option is not a digit.");
				System.out.println("<testoption>: 1 - inviteNewTerminal");
				return;
			}

			switch (testoption) {
			case 1:
				ControlTester.inviteNewTerminal();
				break;
			default:
				System.out.println("The test option is 1.");
				System.out.println("<testoption>: 1 - inviteNewTerminal");
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void inviteNewTerminal() throws Exception {
		Terminal t = new Terminal();
		// t.setTerminalId("???"); //internal EP
		t.setTerminalName("Invited External Terminal");
		t.setTerminalNumber("5626");
		t.setIsOutside(true);
		t.setIsDialIn(false);
		ControlResult cr = getControlServicePort().inviteNewTerminal("1005",
				t);
		if (cr.isSuccess()) {
			System.out
					.println("Invite a new terminal into the ongoing conference: success");
			System.out
					.println("***********************************************************");
			System.out.println();
			System.out.println("ConferenceId        : " + cr.getConferenceId());
			System.out.println("TerminalId          : " + cr.getTerminalId());
			// System.out.println("TerminalName      : "+t.getTerminalName());
			System.out
					.println("TerminalNumber      : " + t.getTerminalNumber());
			System.out.println("IP                  : " + t.getIP());
			System.out.println("ConnectRate         : " + t.getConnectRate());
			System.out.println("Description         : " + t.getDesc());
			System.out.println("IsDialIn            : " + t.isIsDialIn());
			System.out.println("IsHost              : " + t.isIsHost());
			System.out.println("IsOutside           : " + t.isIsOutside());
			System.out.println("IsVoiceOnly         : " + t.isIsVoiceOnly());
			System.out.println("TelePresence        : " + t.isTelePresence());
			System.out.println("AudioVideoPortCount : "
					+ t.getAudioVideoPortCount());
			System.out.println("AudioOnlyPortCount  : "
					+ t.getAudioOnlyPortCount());
			System.out.println("VideoOnlyPortCount  : "
					+ t.getVideoOnlyPortCount());
			System.out.println();
			System.out
					.println("***********************************************************");
		} else {
			System.out
					.println("Invite a new terminal into the ongoing conference: failure");
			TestUtil.printFailureControlResult(cr);
			throw new Exception(
					"Fails to invite a new terminal into an ongoing conference");
		}
	}

	private static ControlServicePortType getControlServicePort()
			throws Exception {
		return ((ControlService) getService(ServiceType.ControlService))
				.getControlServicePort();
	}
}
