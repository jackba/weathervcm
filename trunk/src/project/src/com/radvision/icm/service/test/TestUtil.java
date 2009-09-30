package com.radvision.icm.service.test;
import java.util.List;

import com.radvision.icm.service.ConferenceAdvancedInfo;
import com.radvision.icm.service.ConferenceInfo;
import com.radvision.icm.service.ControlResult;
import com.radvision.icm.service.EventInfo;
import com.radvision.icm.service.GatewayInfo;
import com.radvision.icm.service.GatewayPort;
import com.radvision.icm.service.LayoutInfo;
import com.radvision.icm.service.RoomInfo;
import com.radvision.icm.service.ScheduleInfo;
import com.radvision.icm.service.ScheduleResult;
import com.radvision.icm.service.SdgAccessInfo;
import com.radvision.icm.service.SdgInfo;
import com.radvision.icm.service.TerminalInfo;
import com.radvision.icm.service.TerminalLayoutInfo;
import com.radvision.icm.service.UserInfo;
import com.radvision.icm.service.ViewInfo;
import com.radvision.icm.service.VirtualRoomInfo;


public class TestUtil
{
	public static ConferenceInfo init(ConferenceInfo info)
	{
		info.setAdmitUnresolvedCalls(true);
		info.setForcedDialableConferenceId(false);
		info.setBlockDialin(false);
		info.setWaitingRoom(false);
		info.setTestSchedule(false);
		info.setConferenceStatus(0);
		info.setReservedIPPorts(0);
		info.setReservedSPAudioOnlyPorts(0);
		info.setReservedTPAudioOnlyPorts(0);
		info.setReservedISDNPorts(0);
		info.setRequiredLevel(4);
		info.setPriority(2);
		return info;
	}
	public static TerminalInfo init(TerminalInfo info)
	{
		info.setDual(false);
		info.setHost(false);
		info.setISDN(false);
		info.setHost(false);
		info.setAudioOnly(false);
		info.setTelePresence(false);
		info.setAudioVideoPortCount(1);
        info.setAudioOnlyPortCount(0);
        info.setVideoOnlyPortCount(0);
		return info;
	}
	public static void printFailureControlResult(ControlResult cr)
	{
		System.out.println("***********************************************************");
		System.out.println();
		System.out.println("ConferenceId       : "+cr.getConferenceId());
		System.out.println("ErrorCode          : "+cr.getErrorCode());
		System.out.println("ErrorMessage       : "+cr.getErrorInfo());
		System.out.println("ErrorDeviceId      : "+cr.getDeviceId());
		System.out.println("ErrorDeviceIP      : "+cr.getDeviceIp());
		System.out.println("ErrorDeviceName    : "+cr.getDeviceName());
		System.out.println("OperationFailedCode: "+cr.getOperationFailedCode());
		System.out.println();
		System.out.println("***********************************************************");
	}
	public static void printSuccessScheduleResult(ScheduleResult cr)
	{
		System.out.println("****************************************************************************");
		System.out.println();
		System.out.println("ConferenceId: "+cr.getConferenceId());
		System.out.println("PublicURL   : "+cr.getPublicAccessUrl());
		System.out.println("LocalURL    : "+cr.getLocalAccessUrl());
		System.out.println();
		System.out.println("----------------------------------------------------------------------------");
		ConferenceInfo cb  = cr.getConferenceInfo();
		if(cb!=null)
		{
		    System.out.println();
			System.out.println("StartTime                   : "+cb.getStartTime());
			System.out.println("EndTime                     : "+cb.getEndTime());
			System.out.println("ClassificationName          : "+cb.getClassificationName());
			System.out.println("Status                      : "+cb.getConferenceStatus());
			System.out.println("Subject                     : "+cb.getSubject());
			System.out.println("Description                 : "+cb.getDescription());
			System.out.println("DialableConferenceId        : "+cb.getDialableConferenceId());
			System.out.println("MeetingTypeId               : "+cb.getMeetingTypeId());
			System.out.println("ServicePrefix               : "+cb.getServicePrefix());
			System.out.println("Password                    : "+cb.getPassword());
			System.out.println("Priority                    : "+cb.getPriority());
			System.out.println("RecurrenceId                : "+cb.getRecurrenceId());
			System.out.println("RequiredLevel               : "+cb.getRequiredLevel());
			System.out.println("ReservedIPPorts             : "+cb.getReservedIPPorts());
			System.out.println("ReservedISDNPorts           : "+cb.getReservedISDNPorts());
			System.out.println("ReservedTPAudioOnlyPorts    : "+cb.getReservedTPAudioOnlyPorts());
			System.out.println("ReservedSPAudioOnlyPorts    : "+cb.getReservedSPAudioOnlyPorts());
			System.out.println("IsBlockDialin               : "+cb.isBlockDialin());
			System.out.println("IsAdmitUnresolvedCalls      : "+cb.isAdmitUnresolvedCalls());
			System.out.println("IsForcedDialableConferenceId: "+cb.isForcedDialableConferenceId());
			System.out.println("IsTestSchedule              : "+cb.isTestSchedule());
			System.out.println("IsWaitingRoom               : "+cb.isWaitingRoom());
			System.out.println("IsDialin                    : "+cb.isDialIn());
			System.out.println("IsAutoExtend                : "+cb.isAutoExtend());
			System.out.println("Time zone Id	            : "+cb.getTimeZoneId());
			System.out.println("Organization ID             : "+cb.getOrgID());
			System.out.println("User ID                     : "+cb.getUserID());
			System.out.println("AllowStreaming              : "+cb.isAllowStreaming());
			System.out.println("Streaming status            : "+cb.getStreamingStatus());
			System.out.println("AllowRecording              : "+cb.isAllowRecording());
			System.out.println("RecordMeetingWhenStart      : "+cb.isRecordMeetingWhenStart());
			System.out.println("RecordingQuality            : "+cb.getRecordingQuality());
			System.out.println("RecordingDuration           : "+cb.getRecordingDuration());
			
			ConferenceAdvancedInfo cab = cb.getAdvancedInfo();
			if(cab!=null)
			{
				System.out.println("IsAllowDynamicGrow          : "+cab.isAllowDynamicGrow());
				System.out.println("IsEnableMCUCascading        : "+cab.isEnableMCUCascading());
				System.out.println("DurationAfterLeft           : "+cab.getDurationAfterLeft());
				System.out.println("TerminationCondition        : "+cab.getTerminationCondition());
			}
			System.out.println();
			System.out.println("----------------------------------------------------------------------------");
			System.out.println();
			List<TerminalInfo> tinfos = cb.getTerminals();
			if(tinfos!=null&&tinfos.size()>0)
			{
				for(int i=0;i<tinfos.size();i++)
				{
					TerminalInfo tinfo  = tinfos.get(i);
					System.out.println("Terminal["+i+"]");
					System.out.println("  TerminalId          : "+tinfo.getTerminalId());
					System.out.println("  TerminalNumber      : "+tinfo.getTerminalNumber());
					System.out.println("  AudioOnly           : "+tinfo.isAudioOnly());
					System.out.println("  TelePresence        : "+tinfo.isTelePresence());
					System.out.println("  AudioVideoPortCount : "+tinfo.getAudioVideoPortCount());
					System.out.println("  AudioOnlyPortCount  : "+tinfo.getAudioOnlyPortCount());
					System.out.println("  VideoOnlyPortCount  : "+tinfo.getVideoOnlyPortCount());
					ScheduleInfo si = tinfo.getScheduleInfo();
					if(si!=null)
					{
						System.out.println("  ScheduleInfo  : ");
						System.out.println("    ZonePrefix          : "+si.getZonePrefix());
						System.out.println("    DIDNumber           : "+si.getDIDNumber());
						System.out.println("    DialableConferenceId: "+si.getDialableConferenceId());
						System.out.println("    DialingExpression   : "+si.getDialingExpression());
						GatewayInfo gi = si.getGateway();
						if(gi!=null)
						{
							List<GatewayPort> gps = gi.getGatewayPorts();
							if(gps!=null)
							{
								for(int j=0;j<gps.size();j++)
								{
								    System.out.println("    GatewayInfo["+j+"]: ");
									GatewayPort gp = gps.get(j);
									System.out.println("      GatewayPort    : "+j);
									System.out.println("      CountryCode    : "+gp.getCountryCode());
									System.out.println("      AreaCode       : "+gp.getAreaCode());
								    System.out.println("      TelephoneNumber: "+gp.getTelephoneNumber());
									System.out.println("      NetworkAlias   : "+gp.getNetworkAlias());
								}
							}
						}
					}
					System.out.println();
				}
			}
			else
			{
			    System.out.println("No terminal info");
			    System.out.println();
			}
			System.out.println("----------------------------------------------------------------------------");
			System.out.println();
			List<RoomInfo> rs = cb.getRooms();
			if(rs!=null&&rs.size()>0)
			{
				for(int i=0;i<rs.size();i++)
				{
					RoomInfo ri = rs.get(i);
					System.out.println("ConferenceRoom["+i+"]");
					System.out.println("  RoomId    : "+ri.getRoomId());
					System.out.println("  TerminalId: "+ri.getTerminalId());
					System.out.println();
				}
			}
			else
			{
			    System.out.println("No room info");
			    System.out.println();
			}
			System.out.println("----------------------------------------------------------------------------");
			System.out.println();
			List ls = cb.getLayouts();
			if(ls!=null&&ls.size()>0)
			{
				for(int i=0;i<ls.size();i++)
				{
					LayoutInfo li = (LayoutInfo)ls.get(i);
					System.out.println("Layout["+i+"]");
					System.out.println("  LayoutType: "+li.getLayoutType());
					System.out.println("  ViewId    : "+li.getViewId());
					System.out.println();
				}
			}
			else
			{
			    System.out.println("No layout info");
			    System.out.println();
			}
			System.out.println("----------------------------------------------------------------------------");
			System.out.println();
			List<EventInfo> es = cb.getEvents();
			if(es!=null&&es.size()>0)
			{
				for(int i=0;i<es.size();i++)
				{
					EventInfo ei = es.get(i);
					System.out.println("Event["+i+"]");
					System.out.println("  EventType   : "+ei.getEventType());
					System.out.println("  EventMemo   : "+ei.getEventMemo());
					System.out.println("  RelativeTime: "+ei.getRelativeTime());
					System.out.println();
				}
			}
			else
			{
			    System.out.println("No event info");
			    System.out.println();
			}
		}
		System.out.println("****************************************************************************");
	}
	public static void printFailureScheduleResult(ScheduleResult cr)
	{
		System.out.println("***********************************************************");
		System.out.println();
		System.out.println("ConferenceId           : "+cr.getConferenceId());
		System.out.println("ErrorCode              : "+cr.getErrorStatus());
		System.out.println("ErrorMessage           : "+cr.getErrorInfo());
		System.out.println("ErrorResourceId        : "+cr.getResourceId());
		System.out.println("ErrorResourceType      : "+cr.getResourceType());
		System.out.println("ErrorResourceDeviceType: "+cr.getResourceDeviceType());
		System.out.println();
		System.out.println("***********************************************************");
	}

	public static void printConferenceInfo(ConferenceInfo cb)
	{
		if(cb==null)
		{
			System.out.println("Null ConferenceInfo object when printing it.");
			return;
		}
		System.out.println("****************************************************************************");
		System.out.println();
		System.out.println("ConferenceId                : "+cb.getConferenceId());
		System.out.println("StartTime                   : "+cb.getStartTime());
		System.out.println("EndTime                     : "+cb.getEndTime());
		System.out.println("ClassificationName          : "+cb.getClassificationName());
		System.out.println("Status                      : "+cb.getConferenceStatus());
		System.out.println("Subject                     : "+cb.getSubject());
		System.out.println("Description                 : "+cb.getDescription());
		System.out.println("DialableConferenceId        : "+cb.getDialableConferenceId());
		System.out.println("MeetingTypeId               : "+cb.getMeetingTypeId());
		System.out.println("ServicePrefix               : "+cb.getServicePrefix());
		System.out.println("Password                    : "+cb.getPassword());
		System.out.println("Control Password            : "+cb.getFullControlPassword());
        //System.out.println("Chair Control Password      : "+cb.getChairControlPassword());
		System.out.println("Priority                    : "+cb.getPriority());
		System.out.println("RecurrenceId                : "+cb.getRecurrenceId());
		System.out.println("RequiredLevel               : "+cb.getRequiredLevel());
		System.out.println("ReservedIPPorts             : "+cb.getReservedIPPorts());
		System.out.println("ReservedISDNPorts           : "+cb.getReservedISDNPorts());
		System.out.println("IsBlockDialin               : "+cb.isBlockDialin());
		System.out.println("IsAdmitUnresolvedCalls      : "+cb.isAdmitUnresolvedCalls());
		System.out.println("IsForcedDialableConferenceId: "+cb.isForcedDialableConferenceId());
		System.out.println("IsTestSchedule              : "+cb.isTestSchedule());
		System.out.println("IsWaitingRoom               : "+cb.isWaitingRoom());
		System.out.println("IsAutoExtend                : "+cb.isAutoExtend());
        System.out.println("Streaming status            : "+cb.getStreamingStatus());
        //System.out.println("Local start time            : "+cb.getLocalStartTimeStr());
        System.out.println("Time zone Id	            : "+cb.getTimeZoneId());
        System.out.println("Organization ID             : "+cb.getOrgID());
        System.out.println("User ID                     : "+cb.getUserID());
        System.out.println("AllowStreaming              : "+cb.isAllowStreaming());
        System.out.println("AllowRecording              : "+cb.isAllowRecording());
        System.out.println("RecordMeetingWhenStart      : "+cb.isRecordMeetingWhenStart());
        System.out.println("RecordingQuality            : "+cb.getRecordingQuality());
        System.out.println("RecordingDuration           : "+cb.getRecordingDuration());
        
		ConferenceAdvancedInfo cab = cb.getAdvancedInfo();
		if(cab!=null)
		{
			System.out.println("IsAllowDynamicGrow          : "+cab.isAllowDynamicGrow());
			System.out.println("IsEnableMCUCascading        : "+cab.isEnableMCUCascading());
			System.out.println("DurationAfterLeft           : "+cab.getDurationAfterLeft());
			System.out.println("TerminationCondition        : "+cab.getTerminationCondition());
		}
		System.out.println();
		System.out.println("----------------------------------------------------------------------------");
		System.out.println();
		List<TerminalInfo> ts = cb.getTerminals();
		if(ts!=null&&ts.size()>0)
		{
			for(int i=0;i<ts.size();i++)
			{
				TerminalInfo t = ts.get(i);
				System.out.println("Terminal["+i+"]");
				System.out.println("  TerminalId          : "+t.getTerminalId());
				System.out.println("  CountryCode         : "+t.getCountryCode());
				System.out.println("  AreaCode            : "+t.getAreaCode());
				System.out.println("  TerminalNumber      : "+t.getTerminalNumber());
				System.out.println("  IsDual              : "+t.isDual());
				System.out.println("  IsHost              : "+t.isHost());
				System.out.println("  IsISDN              : "+t.isISDN());
				System.out.println("  IsOutSider          : "+t.isOutSider());
				System.out.println("  IsAudioOnly         : "+t.isAudioOnly());
				System.out.println("  TelePresence        : "+t.isTelePresence());
				System.out.println("  AudioVideoPortCount : "+t.getAudioVideoPortCount());
				System.out.println("  AudioOnlyPortCount  : "+t.getAudioOnlyPortCount());
				System.out.println("  VideoOnlyPortCount  : "+t.getVideoOnlyPortCount());
				List<TerminalLayoutInfo> tls = t.getLayouts();
				if(tls!=null)
				{
					for(int j=0;j<tls.size();j++)
					{
						TerminalLayoutInfo tl = tls.get(j);
						System.out.println("  Layout["+j+"]");
						System.out.println("    LayoutType: "+tl.getLayoutType());
						System.out.println("    PositionId: "+tl.getPositionId());
						System.out.println("    ViewId    : "+tl.getViewId());
					}
				}
			}
		}
		else
		{
		    System.out.println("No terminal info");
		    System.out.println();
		}
		System.out.println("----------------------------------------------------------------------------");
		System.out.println();
		List<RoomInfo> rs = cb.getRooms();
		if(rs!=null&&rs.size()>0)
		{
			for(int i=0;i<rs.size();i++)
			{
				RoomInfo ri = rs.get(i);
				System.out.println("ConferenceRoom["+i+"]");
				System.out.println("  RoomId    : "+ri.getRoomId());
				System.out.println("  TerminalId: "+ri.getTerminalId());
				System.out.println();
			}
		}
		else
		{
		    System.out.println("No room info");
		    System.out.println();
		}
		System.out.println("----------------------------------------------------------------------------");
		System.out.println();
		List ls = cb.getLayouts();
		if(ls!=null&&ls.size()>0)
		{
			for(int i=0;i<ls.size();i++)
			{
				LayoutInfo li = (LayoutInfo)ls.get(i);
				System.out.println("Layout["+i+"]");
				System.out.println("  LayoutType: "+li.getLayoutType());
				System.out.println("  ViewId    : "+li.getViewId());
				System.out.println("  LayoutId    : "+li.getLayoutId());
				System.out.println("  VideoRole    : "+li.getVideoRole());
				System.out.println();
			}
		}
		else
		{
		    System.out.println("No layout info");
		    System.out.println();
		}
		System.out.println("----------------------------------------------------------------------------");
		System.out.println();
		List<EventInfo> es = cb.getEvents();
		if(es!=null&&es.size()>0)
		{
			for(int i=0;i<es.size();i++)
			{
				EventInfo ei = es.get(i);
				System.out.println("Event["+i+"]");
				System.out.println("  EventType   : "+ei.getEventType());
				System.out.println("  EventMemo   : "+ei.getEventMemo());
				System.out.println("  RelativeTime: "+ei.getRelativeTime());
				System.out.println();
			}
		}
		else
		{
		    System.out.println("No event info");
		    System.out.println();
		}
        System.out.println("----------------------------------------------------------------------------");
        System.out.println();
        printSDGInfo(cb.getSDGInfo());
		System.out.println("****************************************************************************");
	}

	public static void printVirtualRoomInfo(VirtualRoomInfo cb)
		{
			if(cb==null)
			{
				System.out.println("Null VirtualRoomInfo object when printing it.");
				return;
			}
			System.out.println("****************************************************************************");
			System.out.println();
			System.out.println("Id                           : "+cb.getConferenceId());
			System.out.println("Name   	                     : "+cb.getName());
			System.out.println("Subject                      : "+cb.getSubject());
			System.out.println("Description                  : "+cb.getDescription());
			System.out.println("Virtual Room Number	         : "+cb.getVirtualRoomNumber());
			System.out.println("MeetingTypeId                : "+cb.getMeetingTypeId());
			System.out.println("ServicePrefix                : "+cb.getServicePrefix());
			System.out.println("Password                     : "+cb.getPassword());
            System.out.println("Control Password             : "+cb.getFullControlPassword());
			System.out.println("Priority                     : "+cb.getPriority());
			System.out.println("ReservedIPPorts              : "+cb.getReservedIPPorts());
			System.out.println("IsBlockDialin                : "+cb.isBlockDialin());
            System.out.println("AdmitUnresolvedCalls         : "+cb.isAdmitUnresolvedCalls());
			System.out.println("IsWaitingRoom                : "+cb.isWaitingRoom());
            System.out.println("IsAutoExtend                 : "+cb.isAutoExtend());
            System.out.println("IsPublic                     : "+cb.isPublic());
            System.out.println("Required level               : "+cb.getRequiredLevel());
            System.out.println("Streaming status             : "+cb.getStreamingStatus());
			System.out.println("Time zone Id	             : "+cb.getTimeZoneId());
			System.out.println("Organization ID              : "+cb.getOrgID());
            System.out.println("User ID                      : "+cb.getUserID());
            System.out.println("AllowStreaming               : "+cb.isAllowStreaming());
            System.out.println("AllowRecording               : "+cb.isAllowRecording());
            System.out.println("RecordMeetingWhenStart       : "+cb.isRecordMeetingWhenStart());
            System.out.println("RecordingQuality             : "+cb.getRecordingQuality());
            System.out.println("RecordingDuration            : "+cb.getRecordingDuration());
            System.out.println("----------------------------------------------------------------------------");
            System.out.println();
			List<TerminalInfo> ts = cb.getTerminals();
			if(ts!=null&&ts.size()>0)
			{
				for(int i=0;i<ts.size();i++)
				{
					TerminalInfo t = ts.get(i);
					System.out.println("Terminal["+i+"]");
					System.out.println("  TerminalId          : "+t.getTerminalId());
					System.out.println("  CountryCode         : "+t.getCountryCode());
					System.out.println("  AreaCode            : "+t.getAreaCode());
					System.out.println("  TerminalNumber      : "+t.getTerminalNumber());
					System.out.println("  IsDual              : "+t.isDual());
					System.out.println("  IsHost              : "+t.isHost());
					System.out.println("  IsISDN              : "+t.isISDN());
					System.out.println("  IsOutSider          : "+t.isOutSider());

					List<TerminalLayoutInfo> tls = t.getLayouts();
					if(tls!=null)
					{
						for(int j=0;j<tls.size();j++)
						{
							TerminalLayoutInfo tl = tls.get(j);
							System.out.println("  Layout["+j+"]");
							System.out.println("    LayoutType: "+tl.getLayoutType());
							System.out.println("    PositionId: "+tl.getPositionId());
							System.out.println("    ViewId    : "+tl.getViewId());
						}
					}
				}
			}
			else
			{
			    System.out.println("No terminal info");
			    System.out.println();
			}
			System.out.println("----------------------------------------------------------------------------");
			System.out.println();
			List<RoomInfo> rs = cb.getRooms();
			if(rs!=null&&rs.size()>0)
			{
				for(int i=0;i<rs.size();i++)
				{
					RoomInfo r = rs.get(i);
					System.out.println("Room ["+i+"],  RoomId    : "+r.getRoomId());
					System.out.println();
				}
			}
			else
			{
			    System.out.println("No room info");
			    System.out.println();
			}
			System.out.println("----------------------------------------------------------------------------");
			System.out.println();
			List ls = cb.getLayouts();
			if(ls!=null&&ls.size()>0)
			{
				for(int i=0;i<ls.size();i++)
				{
					/*ViewInfo li = (ViewInfo)ls.get(i);
											System.out.println("View["+i+"]");
					                	    System.out.println("  ViewId    : "+li.getViewId());
					                	    System.out.println("  Initial layout : "+li.getInitialLayoutID());
					                	    System.out.println("  Max layout : "+li.getMaxLayoutID());
					                	    System.out.println("  Dynamic : "+li.isDynamic());
						System.out.println();*/
					Object l = ls.get(i);
					if (l instanceof ViewInfo)
					{
						ViewInfo li = (ViewInfo)l;
						System.out.println("View["+i+"]");
                	    System.out.println("  ViewId    : "+li.getViewId());
                	    System.out.println("  LayoutType    : "+li.getLayoutType());
                	    System.out.println("  Initial layout : "+li.getInitialLayoutID());
                	    System.out.println("  Max layout : "+li.getMaxLayoutID());
                	    System.out.println("  Dynamic : "+li.isDynamic());
						System.out.println();
					}
					else if (l instanceof LayoutInfo)
					{
						LayoutInfo li = (LayoutInfo)l;
						System.out.println("View["+i+"]");
                	    System.out.println("  ViewId    : "+li.getViewId());
                	    System.out.println("  LayoutType    : "+li.getLayoutType());
                	    System.out.println("  LayoutId    : "+li.getLayoutId());
						System.out.println("  VideoRole    : "+li.getVideoRole());
					}
					else
					{
						System.out.println("********************** "+l.getClass().getName());
					}
				}
			}
			else
			{
			    System.out.println("No view info");
			    System.out.println();
			}
			System.out.println("----------------------------------------------------------------------------");
			System.out.println();
			/*List<EventInfo> es = cb.getEvents();
			if(es!=null&&es.size()>0)
			{
				for(int i=0;i<es.size();i++)
				{
					EventInfo ei = es.get(i);
					System.out.println("Event["+i+"]");
					System.out.println("  EventType   : "+ei.getEventType());
					System.out.println("  EventMemo   : "+ei.getEventMemo());
					System.out.println("  RelativeTime: "+ei.getRelativeTime());
					System.out.println();
				}
			}
			else
			{
			    System.out.println("No event info");
			    System.out.println();
			}
            System.out.println("----------------------------------------------------------------------------");
            System.out.println();
            */
			if (cb.getGatewayInfo()!=null)
			{
				System.out.println("GatewayInfo: ");
				GatewayInfo gi = cb.getGatewayInfo();
				if(gi!=null)
				{
					List<GatewayPort> gps = gi.getGatewayPorts();
					if(gps!=null)
					{
						for(int j=0;j<gps.size();j++)
						{
							System.out.println("    GatewayInfo["+j+"]: ");
							GatewayPort gp = gps.get(j);
							System.out.println("      GatewayPort    : "+j);
							System.out.println("      CountryCode    : "+gp.getCountryCode());
							System.out.println("      AreaCode       : "+gp.getAreaCode());
							System.out.println("      TelephoneNumber: "+gp.getTelephoneNumber());
							System.out.println("      NetworkAlias   : "+gp.getNetworkAlias());
						}
					}
				}

			}
            System.out.println("----------------------------------------------------------------------------");
            System.out.println();
            printSDGInfo(cb.getSDGInfo());


            System.out.println("****************************************************************************");
		}

		public static void printSDGInfo(SdgInfo info)
		{
			if (info!=null)
			{
		    	System.out.println("SDG               : ");
				List<SdgAccessInfo> ai = info.getSDGAccessInfos();
				if (ai!=null && ai.size()>0)
				{
			     	for (int i=0; i < ai.size(); i++)
			     	{
			        	SdgAccessInfo a = ai.get(i);
			        	System.out.println("SDG  URL             : "+a.getSDGAccessURL());
			        	//System.out.println("SDG  streaming URL   : "+a.getSDGStreamingURL());
		            }
		        }
			}
			else
			{
			    System.out.println("No SDG info");
			    System.out.println();
            }
		}
		
		public static void printUserInfo(UserInfo info)
		{
			if (info!=null)
			{
		    	System.out.println("[User Id            ]:"+info.getUserId());
		    	System.out.println("[User Login Id      ]:"+info.getUserLoginId());
		    	System.out.println("[User First Name    ]:"+info.getUserFirstName());
		    	System.out.println("[User Last Name     ]:"+info.getUserLastName());
		    	System.out.println("[Password           ]:"+info.getPassword());
		    	System.out.println("[Role             Id]:"+info.getRoleId());
		    	System.out.println("[Timezone Id        ]:"+info.getTimezoneId());
		    	System.out.println("[Branch             ]:"+info.getBranch());
		    	System.out.println("[Default Termial Id ]:"+info.getDefaultTermialId());
		    	System.out.println("[Department         ]:"+info.getDepartment());
		    	System.out.println("[Email              ]:"+info.getEmamil());
		    	System.out.println("[Mobile Phone Number]:"+info.getMobilePhoneNumber());
		    	System.out.println("[Office Phone Number]:"+info.getOfficePhoneNumber());
			}
			else
			{
			    System.out.println("No user info!");
            }
		}
}
