package com.radvision.icm.service.test;

import java.util.ArrayList;
import java.util.List;

import com.radvision.icm.service.UserInfo;
import com.radvision.icm.service.UserResult;
import com.radvision.icm.service.UserService;
import com.radvision.icm.service.UserServicePortType;
import com.radvision.icm.service.vcm.ServiceType;

public class UserTester extends ServiceTester {

	public static void main(String[] args) {
		try {
			setUsers();
//			if (args.length != 1) {
//				System.out.println("Usage: UserTester <testoption>");
//				System.out.println("<testoption>: 1 - setUsers");
//				return;
//			}
//			System.out.println("Welcome you into the iCM test center.");
//			System.out.println("");
//			int testoption = -1;
//			try {
//				testoption = Integer.parseInt(args[0]);
//			} catch (Exception ex) {
//				System.out.println("The test option is not a digit.");
//				System.out.println("<testoption>: 1 - setUsers");
//				return;
//			}
//
//			switch (testoption) {
//			case 1:
//				UserTester.setUsers();
//				break;
//			default:
//				System.out.println("The test option is 1.");
//				System.out.println("<testoption>: 1 - setUsers");
//				break;
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void setUsers() throws Exception {
		List<UserInfo> uis = new ArrayList<UserInfo>();
		UserInfo info = new UserInfo();
		info.setRoleId(2);
		info.setUserLoginId("super");
		info.setUserFirstName("超级管理员");
		info.setUserLastName("超级管理员");
		info.setPassword("111111");
		uis.add(info);
		
//		info = new UserInfo();
//		info.setEmamil("lipeng@radvision");
//		info.setRoleId(2);
//		info.setUserLoginId("lipeng");
//		info.setUserFirstName("Lipeng");
//		info.setUserLastName("He");
//		// info.setPassword( "test4me" );
//		uis.add(info);

		UserResult ur = getUserServicePortType().setUsers(uis);
		
		System.out.println("result: " + ur.isSuccess());
	}

	private static UserServicePortType getUserServicePortType()
			throws Exception {
		return ((UserService) getService(ServiceType.UserService))
				.getUserServicePort();
	}
}
