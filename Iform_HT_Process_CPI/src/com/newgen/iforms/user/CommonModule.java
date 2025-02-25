/**

* @author Priti Maheshwari 26\06\2024
* Common function used for every worksteps
*/

//added by Priti Maheshwari for CPI Process @start

package com.newgen.iforms.user;

//import java.awt.List;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.List;
import java.text.ParseException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import com.newgen.iforms.EControl;
import com.newgen.iforms.FormDef;
import com.newgen.iforms.custom.IFormReference;
import com.newgen.iforms.custom.IFormServerEventHandler;
import com.newgen.json.JSONObject;
import com.newgen.mvcbeans.model.WorkdeskModel;
import com.newgen.mvcbeans.model.wfobjects.WDGeneralData;
import javax.swing.JOptionPane;

public class CommonModule implements IFormServerEventHandler {
	

		log_CnD objLog = new log_CnD();

	@Override
	public void beforeFormLoad(FormDef arg0, IFormReference arg1) {
		// TODO Auto-generated method stub
	
	}
	
	@Override
	public String executeCustomService(FormDef arg0, IFormReference arg1, String arg2, String arg3, String arg4) {
		// TODO Auto-generated method stub
		return null;
		}

	@Override
	public JSONArray executeEvent(FormDef arg0, IFormReference arg1, String arg2, String arg3) {
		// TODO Auto-generated method stub
		return null;
		}

	@Override
	public String executeServerEvent(IFormReference ifr, String arg1, String arg2, String arg3) {
		// TODO Auto-generated method stub
		log_CnD objLog = new log_CnD();
		
		objLog.servicelog("Inside executeServerEvent ");

		objLog.servicelog("Parameter Passing " + arg1 + "Parameter1" + arg2 + " " + "Parameter" + arg3);
		// String userName=ifr.getUserName();
		String pId = ifr.getObjGeneralData().getM_strProcessInstanceId();

		// ifr.setValue("winame", pId);
		objLog.servicelog("PID value is " + pId + "   winame is>>" + ifr.getValue("wi_name"));
		
		if (arg3.equalsIgnoreCase("duplicateCheck")) {

			return duplicateCheck(ifr, arg2);
		}
		if (arg1.equalsIgnoreCase("GetexceptioninQC")) {
			objLog.servicelog("Inside Exception executeServerEvent ::::");
			String statuString = GetCustomException(ifr);
			return statuString;
		}
		if (arg3.equalsIgnoreCase("generateURNNo")) {
			objLog.servicelog("Inside generateURNNo executeServerEvent ::::");
			return generateURNNo(ifr);

		}
		if (arg3.equalsIgnoreCase("IntroSet")) {
			objLog.servicelog("Inside Introduction WS executeServerEvent ::::");
			return IntroSet(ifr);

		}
		if (arg3.equalsIgnoreCase("GetMailData")) {
			objLog.servicelog("Inside Setting formload values for Mail at CPU assignment executeServerEvent ::::");
			GetMailData(ifr);

		}

		if (arg3.equalsIgnoreCase("CPI_CPU_HEALTH_STATUS")) {
			objLog.servicelog(
					"Inside Setting formload values for Mail at CPI_CPU_HEALTH_STATUS executeServerEvent ::::");
			String status = CPI_CPU_HEALTH_STATUS(ifr);
			objLog.servicelog(
					"status CPI_CPU_HEALTH_STATUS(ifr)-------------------------------->" + CPI_CPU_HEALTH_STATUS(ifr));
			objLog.servicelog("status-------------------------------->" + status);

			return status;

		}
		if (arg3.equalsIgnoreCase("TATCalculateHistory")) {
			objLog.servicelog("Inside RemarkHistory call proc executeServerEvent ::::");
			  String qryRes = "";
			    String ENTRY_DATETIME = "";
			    String EXIT_DATE = "";
			    try {
			    	 objLog.servicelog("Entered in Remakr History:::: " );
			     
			        String activityName = (String) ifr.getActivityName();
			        String getQry = "select INTRO_DATE,EXIT_DATE from ng_cpi_ext_table where wi_name='" + pId + "'";
			        ArrayList value = (ArrayList) ifr.getDataFromDB(getQry);
			        objLog.servicelog("Query:::: " + getQry);
			        if (value != null && value.size() > 0) {
			            qryRes = (value.get(0)).toString();
			            objLog.servicelog("qryRes:::: " + qryRes);

			            qryRes = qryRes.substring((qryRes.indexOf("[") + 1), (qryRes.indexOf("]")));
			            String res[] = qryRes.split(",");
			            ENTRY_DATETIME = res[0].trim();
			            EXIT_DATE = res[1].trim();
			            objLog.servicelog("ENTRY_DATETIME:::: " + ENTRY_DATETIME);
			            objLog.servicelog("EXIT_DATE:::: " + EXIT_DATE);
			        }
			      int TAT =  TATCalculate(ifr,ENTRY_DATETIME,EXIT_DATE);
			      objLog.servicelog("TAT:::: " + TAT);
			      String TATinString = Integer.toString(TAT);
			      objLog.servicelog("TATinString:::: " + TATinString);
			      return TATinString;
			         
			    }  catch (Exception e) {
			        e.printStackTrace();
			      
			    }
			

		}if (arg3.equalsIgnoreCase("GetRMID")) {
			objLog.servicelog("Inside RM ID  executeServerEvent ::::");
			int flag= GetRMID(ifr);
			String flaginString = Integer.toString(flag);
		      objLog.servicelog("flaginString:::: " + flaginString);
		      return flaginString;
			

		}if (arg3.equalsIgnoreCase("OnhypothecatedRefresh")) {
			objLog.servicelog("Inside OnhypothecatedRefresh executeServerEvent ::::");
		      
			 OnhypothecatedRefresh(ifr);
			

		}if (arg3.equalsIgnoreCase("GetSPCode")) {
			objLog.servicelog("Inside GetSPCode executeServerEvent ::::");
		      
			GetSPCode(ifr);
			

		}if (arg3.equalsIgnoreCase("getClassOfBusiness")) {
			objLog.servicelog("Inside getClassOfBusiness executeServerEvent ::::");
		      
			return getClassOfBusiness(ifr);
			

		}if (arg3.equalsIgnoreCase("GetSPCodeV")) {
			objLog.servicelog("Inside GetSPCodeV executeServerEvent when verticles are selected with deal location ::::");  
			GetSPCodeV(ifr);
		}if (arg3.equalsIgnoreCase("GetSPCodeS")) {
			objLog.servicelog("Inside GetSPCodeS executeServerEvent when verticles are selected with deal location and sol id  ::::");  
			GetSPCodeS(ifr);
		}if (arg3.equalsIgnoreCase("GetSourceId")) {
			objLog.servicelog("Inside GetSourceId executeServerEvent when verticles are selected with deal location and sol id  ::::");  
			return GetSourceId(ifr);
		}if (arg3.equalsIgnoreCase("GetSubvId")) {
			objLog.servicelog("Inside GetSubvId executeServerEvent when sub verticle is selected and according to this sorce value populated  ::::");  
			return GetSubvId(ifr);
		}if (arg3.equalsIgnoreCase("SetZoneHub")) {
			objLog.servicelog("Inside SetZoneHub executeServerEvent when sync cases are introduced ::::");  
			SetZoneHub(ifr);
		}
		
		
		
		
		

		
		return "";

		}
		
	public String generateHTML(EControl arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCustomFilterXML(FormDef arg0, IFormReference arg1, String arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getWidgetNameToBeShown(IFormReference arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean introduceWorkItemInSpecificProcess(IFormReference arg0, String arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String introduceWorkItemInWorkFlow(IFormReference arg0, HttpServletRequest arg1, HttpServletResponse arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String introduceWorkItemInWorkFlow(IFormReference arg0, HttpServletRequest arg1, HttpServletResponse arg2,
			WorkdeskModel arg3) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String onChangeEventServerSide(IFormReference arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String postHookExportToPDF(IFormReference arg0, File arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void postHookOnDocumentOperations(IFormReference arg0, String arg1, String arg2, int arg3, String arg4) {
		// TODO Auto-generated method stub

		}
	
	@Override
	public void postHookOnDocumentUpload(IFormReference arg0, String arg1, String arg2, File arg3, int arg4) {
		// TODO Auto-generated method stub

	}
	
	public String setMaskedValue(String arg0, String currentVal) {
		// TODO Auto-generated method stub
		return currentVal;
	}
	
	@Override
	public void updateDataInWidget(IFormReference arg0, String arg1) {
		// TODO Auto-generated method stub
	
	}
	
	public String validateDocumentConfiguration(String arg0, String arg1, File arg2, Locale arg3) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public JSONArray validateSubmittedForm(FormDef arg0, IFormReference arg1, String arg2) {
		// TODO Auto-generated method stub
		return null;
	}
		
	// Duplicatecheck function written by Priti 26/06/2024
		
	public String duplicateCheck(IFormReference ifr, String condition) {
		String countValue = "";
		String sQuery = "";

		try {
			objLog.servicelog("condition ::::>" + condition);

			switch (condition) {

			case "POLICY_NO": {

				sQuery = "select COUNT(WI_NAME) from ng_cpi_ext_table where POLICY_NO='"
						+ ((String) ifr.getValue("CPI_POLICY_NO")) + "' and  current_wrk_step != 'Discard'";
				objLog.servicelog("AFTER policyNo QUERY >>>>>>>>>>>>>" + sQuery);
				List<List> dataFromDataSource = new ArrayList();

				dataFromDataSource = ifr.getDataFromDB(sQuery);

				countValue = dataFromDataSource.get(0).get(0).toString();

				objLog.servicelog("DuplicateCountValue POLICY_NO ::::>" + countValue);

				return countValue;

			}

			case "PROPOSAL_NO":

			{

				sQuery = "select COUNT(WI_NAME) from ng_cpi_ext_table where PROPOSAL_NO='"
						+ ((String) ifr.getValue("CPI_PROPOSAL_NO")) + "' and  current_wrk_step != 'Discard'"; // CR-8001-54702
																												// E-Mail
																												// Filter
				objLog.servicelog("AFTER PROPOSAL_NO QUERY >>>>>>>>>>>>>" + sQuery);
					List<List> dataFromDataSource = new ArrayList();

					dataFromDataSource = ifr.getDataFromDB(sQuery);

					countValue = dataFromDataSource.get(0).get(0).toString();

					objLog.servicelog("DuplicateCountValue PROPOSAL_NO::::>" + countValue);

					return countValue;
				}
				case "QUOTE_NO": {

					sQuery = "select COUNT(WI_NAME) from ng_cpi_ext_table where QUOTE_NO='"
							+ ((String) ifr.getValue("CPI_QUOTE_NO")) + "' and  current_wrk_step != 'Discard'"; // CR-8001-54702
																												// E-Mail
																												// Filter
					objLog.servicelog("AFTER QUOTE_NO QUERY >>>>>>>>>>>>>" + sQuery);
					List<List> dataFromDataSource = new ArrayList();

					dataFromDataSource = ifr.getDataFromDB(sQuery);

					countValue = dataFromDataSource.get(0).get(0).toString();

					objLog.servicelog("DuplicateCountValue QUOTE_NO :::::>" + countValue);
				
					return countValue;
				}
				case "ENDORSEMENT_NO": {

					sQuery = "select COUNT(WI_NAME) from ng_cpi_ext_table where ENDORSEMENT_NO='"
							+ ((String) ifr.getValue("CPI_ENDORSEMENT_NO")) + "' and  current_wrk_step != 'Discard'"; // CR-8001-54702
																														// E-Mail
																														// Filter
					objLog.servicelog("AFTER QUOTE_NO QUERY >>>>>>>>>>>>>" + sQuery);
					List<List> dataFromDataSource = new ArrayList();
			
					dataFromDataSource = ifr.getDataFromDB(sQuery);

					countValue = dataFromDataSource.get(0).get(0).toString();

					objLog.servicelog("DuplicateCountValue ENDORSEMENT_NO ::::::: >" + countValue);
		
					return countValue;
				}
				
				
				}

			} catch (Exception ex) {
				ex.printStackTrace();

				objLog.servicelog("Exception in Switch of Duplicate check" + ex);
			}

			return "";
		}

		public String GetCustomException(IFormReference ifr) {
			String activityName = (String) ifr.getActivityName();
			WDGeneralData wdret = ifr.getObjGeneralData();
			String processInstanceId = wdret.getM_strProcessInstanceId();
			objLog.servicelog("processInstanceId--->" + processInstanceId);
			List<List> ListInstru = new ArrayList();
			String query = "select INSTRUMENTSTATUS from WFINSTRUMENTTABLE where PROCESSINSTANCEID='"
					+ processInstanceId + "'";
		
			objLog.servicelog("query--->" + query);

			ListInstru = (List) ifr.getDataFromDB(query);
			objLog.servicelog("ListInstru:: " + ListInstru.toString());

			String instrumentData = ListInstru.get(0).get(0).toString();

			objLog.servicelog("instrumentData:: " + instrumentData);

			return instrumentData;
		}

		public String generateURNNo(IFormReference ifr) {
			String rmId = null;

			String processType = null;
			String riskInspectionDate = null;
			String classOfBusiness = null;
			String finYear = null;
			String endorEffDate = null;
			int procCode = 0;
			String nameOfComp = "";
			String offCode = "";
			String serialNo = "";
			String qryRes = "";
			String workStepName = (String) ifr.getActivityName();

			rmId = (String) ifr.getValue("CPI_RM_ID");
			String productName = (String) ifr.getValue("CPI_PRODUCT_NAME");
			processType = (String) ifr.getValue("CPI_Process_Type");
			classOfBusiness = getClassOfBusiness(ifr);
			try {
				if (workStepName.equalsIgnoreCase("RM_Green_File")
						|| workStepName.equalsIgnoreCase("BSG_QC")/* Quote BSG_QC URN generate Suraj */) {
					riskInspectionDate = (String) ifr.getValue("CPI_RISK_INSPECTION_DATE");
				} else if (workStepName.equalsIgnoreCase("Endorsement")) {
					endorEffDate = (String) ifr.getValue("CPI_ENDORSEMENT_EFFECTIVE_DATE");
				}

				if (classOfBusiness == null || classOfBusiness.equals("")) {
					JOptionPane.showMessageDialog(null, "ClassOfBusiness of Product doesn't exist in the System");
					return "0";
				}

				if ((riskInspectionDate != null && !riskInspectionDate.equalsIgnoreCase(""))
						|| (endorEffDate != null && !endorEffDate.equalsIgnoreCase(""))) {
					String dateFieldName = "";
					if (workStepName.equalsIgnoreCase("RM_Green_File") || workStepName.equalsIgnoreCase("BSG_QC")) {
						finYear = getFinancialYear(riskInspectionDate);
						dateFieldName = "risk inception date";
					} else if (workStepName.equalsIgnoreCase("Endorsement")) {
						finYear = getFinancialYear(endorEffDate);
						dateFieldName = "Endorsement Effective date";
					}

				}
				if (processType != null && processType.equalsIgnoreCase("Corporate Policy Insurance")) {
					// procCode = 1;
					procCode = 0; // NEW FORMAT URN CR-8001-56225
				} else if (processType != null && processType.equalsIgnoreCase("Endorsement")) {
					String extEndrType = (String) ifr.getValue("CPI_EXTERNAL_ENDORSEMENT_TYPE");
					if (extEndrType != null && extEndrType.equalsIgnoreCase("Extension endorsement")) {
						// procCode = 1; // for all Extension endorsement
						procCode = 0; // for all Extension endorsement NEW FORMAT CR-8001-56225
					} else {
						// procCode = 2;// for all Endorsement cases
						procCode = 1; // for all Endorsement cases NEW FORMAT CR-8001-56225
					}
				}

				String getQry = "select Name_of_Comp,Office_Code,SerialNo from NG_CPI_URNNo where Policy_TypeCode="
						+ procCode;
				ArrayList urnList = (ArrayList) ifr.getDataFromDB(getQry);
				objLog.servicelog("Query:::: " + getQry);
				if (urnList != null && urnList.size() > 0) {
					qryRes = (urnList.get(0)).toString();
					objLog.servicelog("qryRes:::: " + qryRes);
					//////// //System.out.println("qryRes== " + qryRes);
					qryRes = qryRes.substring((qryRes.indexOf("[") + 1), (qryRes.indexOf("]")));
					String res[] = qryRes.split(",");
					nameOfComp = res[0].trim();
					offCode = res[1].trim();
					serialNo = res[2].trim();
					objLog.servicelog("offCode:::: " + offCode);
					objLog.servicelog("serialNo:::: " + serialNo);
				}
				if (classOfBusiness != null && !classOfBusiness.equals("") && !finYear.equals("")
						&& !nameOfComp.equals("") && !offCode.equals("") && !serialNo.equals("")) {
					int serial = Integer.parseInt(serialNo);
					serial = serial + 1;
					int ser = serial;
					int count = 0;
					while (ser > 0) {
						ser = ser / 10;
						count++;
					}
					String num = "";
					for (int m = count; m < 8; m++) {
						num = num + "0";
					}
					String finalUrnNo = num + "" + serial;
					// String urnNumber = nameOfComp + "/" + offCode + "/" + classOfBusiness + "/" +
					// procCode + "/" + finalUrnNo + "/" + finYear;
					String urnNumber = nameOfComp + "" + offCode + "" + classOfBusiness + "" + procCode + ""
							+ finalUrnNo + "" + finYear; // NEW FORMAT URN CR-8001-56225
				 
					return urnNumber + "~" + finalUrnNo;
				}
			} catch (Exception e) {
				objLog.servicelog("ERROR for generating URN NO" + e);

			}
	
	
	
			return "";
	
		}

		// Setting coinsurance and cpucustomer on Introduction
		public String IntroSet(IFormReference ifr) {
			String sActivityName = (String) ifr.getActivityName();

			if (sActivityName.equalsIgnoreCase("Work Introduction1")) {

				String CoInsIdFlag = "F";
				String cpuDataWashingFlag = "F";/* CR 46 CPU DataWashing */
				String userCoinsuranceGroup = "coinsurance";
				String userCpucustomerGroup = "cpucustomer";
				String sQuery = "select Emp_ID,EMP_GROUP from ng_cpi_coins_emp_master";
				ArrayList getUser = (ArrayList) ifr.getDataFromDB(sQuery);
				String sUserName = ifr.getUserName();

				for (int i = 0; i < getUser.size(); i++) {
					String userName = "", userGroupFlag = "";
					String s_CoUser = (getUser.get(i)).toString();
					objLog.servicelog("s_CoUser:::: " + s_CoUser);
					// s_CoUser=s_CoUser.substring((s_CoUser.indexOf("[")+1),(s_CoUser.indexOf("]")));
					/******************* CR 46 CPU DataWashing ********************/
					userName = s_CoUser.substring((s_CoUser.indexOf("[") + 1), (s_CoUser.indexOf(",")));
					objLog.servicelog("userName:::: " + userName);
					userGroupFlag = s_CoUser.substring((s_CoUser.indexOf(",") + 1), (s_CoUser.indexOf("]")));
					objLog.servicelog("userGroupFlag:::: " + userGroupFlag);

					if (userName.equalsIgnoreCase(sUserName)) {
						//////// //System.out.println("Inside CR 46 work introducion ::");
						if (userGroupFlag.trim().equalsIgnoreCase(userCoinsuranceGroup)) {
							CoInsIdFlag = "C";
							objLog.servicelog("coinsurance Flag : " + CoInsIdFlag);
							return CoInsIdFlag;
						}
						if (userGroupFlag.trim().equalsIgnoreCase(userCpucustomerGroup)) {
							cpuDataWashingFlag = "D";
							objLog.servicelog("cpucustomer Flag : " + cpuDataWashingFlag);
							return cpuDataWashingFlag;
						}
					}

					/***************** end CR 46 CPU DataWashing *******************/
				}
			}
			return "";
		}

		// Setting mail data
		public void GetMailData(IFormReference ifr) {
			String sActivityName = (String) ifr.getActivityName();
 
			String clientDet = "";
			objLog.servicelog("Enter in GetMailDate::::: ");
			String interactionId = (String) ifr.getValue("CPI_INTERACTIONID");
			if (sActivityName.equalsIgnoreCase("COPS_Cpu_Assignment")
					|| sActivityName.equalsIgnoreCase("CPU_ASSIGNMENT")) {
 
				String aliasID = (String) ifr.getValue("CPI_MAIL_ALIASID");
				objLog.servicelog("aliasID:::" + aliasID);
				if (aliasID.equalsIgnoreCase("3") || aliasID.equalsIgnoreCase("2") || aliasID.equalsIgnoreCase("1")) {
					// formObject.setNGVisible("Label187", true);
 
					ifr.setStyle("CPI_CLIENT_MAIL_RECEIVED_DATE", "visible", "true");
 
					objLog.servicelog("interactionId: " + interactionId);
					String interationQuery = "select a.createddate from TRN_Mailinbox a, TRN_INTERACTIONMAILINBOX t where a.mailid = t.intmailid and t.interactionid = '"
							+ interactionId + "'";
					objLog.servicelog("interationQuery: " + interationQuery);
 
					ArrayList<ArrayList<String>> value = (ArrayList) ifr.getDataFromDB(interationQuery);
					objLog.servicelog("value: " + value);
					
					if(!value.equals(null) && !value.isEmpty() && !value.get(0).isEmpty())
					// if (value.size() != 0)
					{
 
						try {
 
							String date = value.get(0).get(0);
							objLog.servicelog("previous date Date:::" + date);
							SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
							objLog.servicelog(" after SimpleDateFormat");
							Date created = sdf.parse(date);
							objLog.servicelog(" after created");
							sdf = new SimpleDateFormat("dd/MM/yyyy");
							objLog.servicelog(" after sdf");
							date = sdf.format(created);
							objLog.servicelog(" after date");
							objLog.servicelog("Date:::" + date);
 
 
 
							objLog.servicelog("date: " + date);
							ifr.setStyle("CPI_CLIENT_MAIL_RECEIVED_DATE", "disable", "false");
 
							// setSpecificDateFormat("CPI_CLIENT_MAIL_RECEIVED_DATE", "dd/MM/yyyy");
							ifr.setValue("CPI_CLIENT_MAIL_RECEIVED_DATE", date);
				
							ifr.setStyle("CPI_ENDORSEMENT_EFFECTIVE_DATE", "visible", "true");
							// setSpecificDateFormat("CPI_ENDORSEMENT_EFFECTIVE_DATE", "dd/MM/yyyy");
							ifr.setValue("CPI_ENDORSEMENT_EFFECTIVE_DATE", date);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					objLog.servicelog("End SetDate executeCommand");
 
				}
				
				String clientMail = (String) ifr.getValue("CPI_CUSTOMER_EMAILID");
				objLog.servicelog("clientMail:::" + clientMail);
				objLog.servicelog("start of RMID :===");
				String queryClient = "SELECT RM_Emp_Id,rm_name,PRIMARY_VERTICAL,PRIMARY_SUB_VERTICAL,IL_LOCATION,BROKER_AGENT_NAME,agent_code FROM (select RM_Emp_Id,rm_name,PRIMARY_VERTICAL,PRIMARY_SUB_VERTICAL,IL_LOCATION,BROKER_AGENT_NAME,agent_code from ng_cpi_client_reg_mst where CLIENT_EMAIL_ID1='"
						+ clientMail + "' or CLIENT_EMAIL_ID2 = '" + clientMail + "' or CLIENT_EMAIL_ID3 = '"
						+ clientMail + "' or CLIENT_EMAIL_ID4 = '" + clientMail + "' or CLIENT_EMAIL_ID5 = '"
						+ clientMail + "' order by client_reg_no desc) where rownum=1 ";
				objLog.servicelog("queryClient===" + queryClient);
				ArrayList clientList = (ArrayList) ifr.getDataFromDB(queryClient);
				try {
					if (clientList != null && clientList.size() > 0) {
						String clientDetails = (clientList.get(0)).toString();
						clientDet = clientDetails.substring((clientDetails.indexOf("[") + 1),
								(clientDetails.indexOf("]")));
						String regClientDet[] = clientDet.split(",");
						objLog.servicelog("regClientDet===" + regClientDet.length);
						if (regClientDet.length > 0) {
							String Id = (String) ifr.getValue("CPI_RM_ID");
						
							ifr.setValue("CPI_RM_ID", regClientDet[0].trim());
							objLog.servicelog("CPI_RM_ID:::" + regClientDet[0].trim());
							ifr.setValue("CPI_RM_NAME", regClientDet[1].trim());
							objLog.servicelog("CPI_RM_NAME:::" + regClientDet[1].trim());
							ifr.setValue("CPI_PRIMARY_SUB_VERTICAL", regClientDet[3].trim());
							objLog.servicelog("CPI_PRIMARY_SUB_VERTICAL:::" + regClientDet[3].trim());
							ifr.setValue("CPI_PRIMARY_VERTICAL", regClientDet[2].trim());
							objLog.servicelog("CPI_PRIMARY_VERTICAL:::" + regClientDet[2].trim());
							ifr.setValue("CPI_IL_LOCATION", regClientDet[4].trim());
							objLog.servicelog("CPI_IL_LOCATION:::" + regClientDet[4].trim());
							ifr.setValue("CPI_NAME_OF_BROKER_AGENT", regClientDet[5].trim());
							objLog.servicelog("CPI_NAME_OF_BROKER_AGENT:::" + regClientDet[5].trim());
							ifr.setValue("CPI_AGENT_CODE", regClientDet[6].trim());
							objLog.servicelog("CPI_AGENT_CODE:::" + regClientDet[6].trim());
			
						}
 
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				objLog.servicelog("End ID set executeCommand");
			}
		}

		public static String setSpecificDateFormat(String dateString, String pattern) throws ParseException {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			Date date = sdf.parse(dateString);
			return sdf.format(date);
		}

		public String getFinancialYear(String riskDate) {

			//////// //System.out.println("riskDate==" + riskDate);
			int rYear1 = Integer.parseInt(riskDate.substring(6, 10));
			int rMonth1 = Integer.parseInt(riskDate.substring(3, 5));
			int rDay1 = Integer.parseInt(riskDate.substring(0, 2));
			int preYear = 0;
			int postYear = 0;
			//////// //System.out.println("rYear1=" + rYear1 + "rMonth1==" + rMonth1 +
			//////// "rDay1==" + rDay1);
			if (rYear1 < 2014) {
				return "0";
			} else if (rMonth1 > 3 && rMonth1 <= 12) {
				preYear = Integer.parseInt(riskDate.substring(8, 10));
				postYear = Integer.parseInt(riskDate.substring(8, 10)) + 1;
			} else if (rMonth1 >= 1 && rMonth1 <= 3) {
				postYear = Integer.parseInt(riskDate.substring(8, 10));
				preYear = Integer.parseInt(riskDate.substring(8, 10)) - 1;
			}

			if (preYear > 0 && postYear > 0) {
				return preYear + "" + postYear;
			}
			return "1";

		}

		/*
		 * public String setURNNo(IFormReference ifr) { String
		 * spid=ifr.getObjGeneralData().getM_strProcessInstanceId();
		 * 
		 * String query = "select URNNO from NG_CPI_URNNo where LASTUPDATED_WORKITEM='"
		 * + spid + "'"; objLog.servicelog("query for urnno:: " + query);
		 * 
		 * ArrayList Dbvalue =(ArrayList)ifr.getDataFromDB(query);
		 * objLog.servicelog("DbValue:: " + Dbvalue); String urn =
		 * (Dbvalue.get(0)).toString(); urn = urn.substring((urn.indexOf("[") + 1),
		 * (urn.indexOf("]"))); String sQuery1 =
		 * "update ng_cpi_ext_table set LEADER_URN_NO='" + urn + "' where WI_NAME=" +
		 * spid+ "'"; objLog.servicelog("sQuery1 for urnno:: " + sQuery1);
		 * ifr.saveDataInDB(sQuery1); ifr.setValue("CPI_Leader_URN_No",urn);
		 * 
		 * objLog.servicelog(" urnno:: " + urn);
		 * 
		 * return urn;
		 * 
		 * }
		 */
		public String getClassOfBusiness(IFormReference ifr) {
			String clsBusName = "";
			String prodName = (String) ifr.getValue("CPI_PRODUCT_NAME");
			String clsBusQry = "select distinct class_of_business from NG_PRODUCT_MASTER where PRODUCT_NAME ='"
					+ prodName + "'";
			objLog.servicelog("clsBusQry=" + clsBusQry);
			ArrayList busNameList = (ArrayList) ifr.getDataFromDB(clsBusQry);
			if (busNameList != null && busNameList.size() > 0) {
				clsBusName = (busNameList.get(0)).toString();
				clsBusName = clsBusName.substring((clsBusName.indexOf("[") + 1), (clsBusName.indexOf("]")));
				objLog.servicelog("clsBusName=" + clsBusName);
			}
			//////// //System.out.println("clsBusName=" + clsBusName);
			if (!clsBusName.equalsIgnoreCase("") && clsBusName != null) {
				return clsBusName;
			}
			
			return clsBusName;
		}

		public String GetDealStatus(IFormReference ifr) {

			String Dealstatus = (String) ifr.getValue("ICICILOMBARD_HT_DEAL_NO");

			System.out.println("Dealstatus-->" + Dealstatus);

			List<List> ListInstru = new ArrayList();
			
			String query = "select il.txt_office from MV_GENMST_OFFICE il,MV_Gen_Deal_Detail gd where il.num_office_cd = gd.num_office_cd and gd.TXT_DEAL_ID='"
					+ Dealstatus + "'";

			objLog.servicelog("query--->" + query);

			ListInstru = (List) ifr.getDataFromDB(query);

			objLog.servicelog("ListInstru:: " + ListInstru.toString());

			String Location = ListInstru.get(0).get(0).toString();
					
					
			objLog.servicelog("Location:: " + Location);

			return Location;

		}

		public String CPI_CPU_HEALTH_STATUS(IFormReference ifr) {
			objLog.servicelog("Inside CPI_CPU_HEALTH_STATUS");
 
			String s_CoUser = null;
			String prodoctName = (String) ifr.getValue("CPI_PRODUCT_NAME");
			objLog.servicelog("Inside CPI_CPU_HEALTH_STATUS prodoctName -->" + prodoctName);
			String prodoctType = (String) ifr.getValue("CPI_PRODUCT_TYPE");
			String wi_name = (String) ifr.getValue("WI_NAME");
			objLog.servicelog("Inside CPI_CPU_HEALTH_STATUS prodoctType -->" + prodoctType);
			String CPI_CPU_HEALTH_STATUS1 = (String) ifr.getValue("CPI_CPU_HEALTH_STATUS");
			objLog.servicelog("Inside CPI_CPU_HEALTH_STATUS CPI_CPU_HEALTH_STATUS -->" + CPI_CPU_HEALTH_STATUS1);
			String lstrActivityName = (String) ifr.getActivityName();
			objLog.servicelog("Inside CPI_CPU_HEALTH_STATUS getUser -->" + lstrActivityName);
			String CPI_DATAWASHING_TYPE1 = (String) ifr.getValue("CPI_DATAWASHING_TYPE");
			objLog.servicelog("Inside CPI_CPU_HEALTH_STATUS CPI_DATAWASHING_TYPE -->" + CPI_DATAWASHING_TYPE1);
			if (lstrActivityName.equalsIgnoreCase("CPU_Assignment")
					|| lstrActivityName.equalsIgnoreCase("CPU_Data_Washing")
					|| lstrActivityName.equalsIgnoreCase("CPUCustClarification")/* CR 46 CPU DataWashing */
					|| lstrActivityName.equalsIgnoreCase("CPU_DW_Clarification")/* CR-OMCPI-1314-03 CPU DataWashing */
					|| lstrActivityName.equalsIgnoreCase("RM_Clarification")
					|| lstrActivityName.equalsIgnoreCase("CO_INSURANCE")
					|| lstrActivityName.equalsIgnoreCase("CO_INSURANCE_RM")
					|| lstrActivityName.equalsIgnoreCase("COPS_Policy_Endr")
					|| lstrActivityName.equalsIgnoreCase("BSG_QC")) {
 
				List<List> getUser = new ArrayList();
				String sQuery = "select product_routing from ng_product_master where product_name='" + prodoctName
						+ "' and product_type_id=(select record_id from ng_product_type_master where product_type='"
						+ prodoctType + "') and rownum<=1";
				objLog.servicelog("Inside CPI_CPU_HEALTH_STATUS sQuery -->" + sQuery);
 
				getUser = (List) ifr.getDataFromDB(sQuery);
				objLog.servicelog("getUser-->" + getUser);
 
				// if (getUser != null)
 
				if (!getUser.equals(null) && !getUser.isEmpty() && !getUser.get(0).isEmpty()) {
 
					s_CoUser = (getUser.get(0).get(0).toString());
					objLog.servicelog("Not null value -->" + s_CoUser);
 
					return s_CoUser;
				} else {
					objLog.servicelog("Null null value -->" + s_CoUser);
					return null;
				}
 
				// objLog.servicelog("after CPI_CPU_HEALTH_STATUS sQuery -->" + s_CoUser);f
 
			}
			return "";
	
	}
		
	//	public int TATCalculate(IFormReference ifr) 
		public int TATCalculate(IFormReference ifr,String ENTRY_DATETIME,String EXIT_DATE){
		   
		    try {
		    	 objLog.servicelog("Entered in TATCalculate:::: " );
		        
		        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		        Date entryDate = sdf.parse(ENTRY_DATETIME);
	            Date exitDate = sdf.parse(EXIT_DATE);
	            objLog.servicelog(" entryDate:::" + entryDate);
		        objLog.servicelog("exitDate :::" + exitDate);
	         // Calculate TAT in seconds
	            long tatInMilliseconds = exitDate.getTime() - entryDate.getTime();
	            int tatInSeconds = (int) (tatInMilliseconds / 1000);
		        objLog.servicelog("tatInSeconds :::" + tatInSeconds);
		        return tatInSeconds;  
		    }  catch (Exception e) {
		        e.printStackTrace();
		        return -1;
		    }
		     
		}
		
		public int GetRMID(IFormReference ifr) {
	         String sCPI_RM_ID = (String) ifr.getValue("CPI_RM_ID");

             String sQuery = "SELECT count(1) FROM PDBUSER where useralive = 'Y' and USERNAME='" + sCPI_RM_ID + "'";
             ArrayList getUser =  (ArrayList) ifr.getDataFromDB(sQuery);
             String s_RMUser = (getUser.get(0)).toString();
             objLog.servicelog("s_RMUser :::" + s_RMUser);
             s_RMUser = s_RMUser.substring(s_RMUser.indexOf("[") + 1, s_RMUser.indexOf("]"));
             objLog.servicelog("s_RMUser :::" + s_RMUser);
             int iRMValue = Integer.parseInt(s_RMUser);
             objLog.servicelog("iRMValue :::" + iRMValue);
             return iRMValue ;
		}
	public void OnhypothecatedRefresh(IFormReference ifr) 
	
	{
		String pId = ifr.getObjGeneralData().getM_strProcessInstanceId();
		
		  String sQuery = "SELECT HYPOTHECATED_TO FROM NG_CPI_EXT_TABLE where wi_name='" + pId + "'";
		  objLog.servicelog("sQuery :::" + sQuery);

			ArrayList hypo =(ArrayList) ifr.getDataFromDB(sQuery);

			String qryRes =  (hypo.get(0)).toString();
			String hypothecated = qryRes.replace("[", "").replace("]", "");
			 objLog.servicelog("hypothecated :::" + hypothecated);
			 
			ifr.setValue("CPI_HYPOTHECATED_TO", hypothecated);
			 objLog.servicelog("END hypothecated :::");
	}
public void GetSPCode(IFormReference ifr) 
	
	{
	try {
		 objLog.servicelog("GetSPCode for Only Branch name : ");
	String BranchName = (String) ifr.getValue("CPI_BRANCH_NAME");
	objLog.servicelog("BranchName: " + BranchName);
	if (!BranchName.equalsIgnoreCase("") ) {
		objLog.servicelog("Branch Name not null : ");
		BranchName = BranchName.trim();
		
	 String sQuery ="Select sp_name1,sp_code1,sp_name2,sp_code2,sp_name3,sp_code3,sp_name4,sp_code4 from ng_cpi_branch_master where TRIM(BRANCH)='"+BranchName+"'";
	 ArrayList Code =(ArrayList)ifr.getDataFromDB(sQuery);
	 objLog.servicelog("sQuery: " + sQuery);
	 if (Code != null && Code.size() > 0) {
		 String qryRes = Code.get(0).toString();
		    objLog.servicelog("qryRes:::: " + qryRes);

		    // Remove square brackets if present and split by comma to get individual values
		    String cleanedRes = qryRes.replace("[", "").replace("]", "");
		    String[] res = cleanedRes.split(",");

		    
		    if (res.length > 0) {
		    	String SpName1 = res[0].trim();
		        String SPCode1 = res[1].trim();
		        String SpName2 = res[2].trim();
		        String SPCode2 = res[3].trim();
		        String SpName3 = res[4].trim();
		        String SPCode3 = res[5].trim();
		        String SpName4 = res[6].trim();
		        String SPCode4 = res[7].trim();
		        
		        objLog.servicelog("SpName1: " + SpName1);
		        objLog.servicelog("SPCode1: " + SPCode1);
		        objLog.servicelog("SpName2: " + SpName2);
		        objLog.servicelog("SPCode2: " + SPCode2);
		        objLog.servicelog("SpName3: " + SpName3);
		        objLog.servicelog("SPCode3: " + SPCode3);
		        objLog.servicelog("SpName4: " + SpName4);
		        objLog.servicelog("SPCode4: " + SPCode4);
		        
		    	ifr.setValue("CPI_SP_NAME1", SpName1);
		    	ifr.setValue("CPI_SP_CODE1", SPCode1);
		    	ifr.setValue("CPI_SP_NAME2", SpName2);
		    	ifr.setValue("CPI_SP_CODE2", SPCode2);
		    	ifr.setValue("CPI_SP_NAME3", SpName3);
		    	ifr.setValue("CPI_SP_CODE3", SPCode3);
		    	ifr.setValue("CPI_SP_NAME4", SpName4);
		    	ifr.setValue("CPI_SP_CODE4", SPCode4);
		    	
		    	String Name1 = (String) ifr.getValue("CPI_SP_NAME1");
		    	String Code1 = (String) ifr.getValue("CPI_SP_CODE1");
		         	 objLog.servicelog("CPI_SP_NAME1: " +Name1 );
			        objLog.servicelog("CPI_SP_CODE1: " + Code1);
		       
		        
		        
		    }
		    
	 }
	}
	}catch (Exception e) {
		 objLog.servicelog("Error in setting value::: ");
	        e.printStackTrace();
	       
	    }
     
	}
public void GetSPCodeV(IFormReference ifr) 

{
try {
	 objLog.servicelog("GetSPCode for Branch name when verticles are selected with deal location : ");
String BranchName = (String) ifr.getValue("CPI_BRANCH_NAME");
String SourceName = (String) ifr.getValue("CPI_SOURCE_NAME");
String DealLocation = (String) ifr.getValue("CPI_DEAL_IL_LOCATION");
objLog.servicelog("BranchName: " + BranchName);
if (!BranchName.equalsIgnoreCase("") ) {
	objLog.servicelog("Branch Name not null : ");

	String sQuery1 = "SELECT DISTINCT SOL_ID FROM NG_CPI_SP_CODE_MASTER " +
            "WHERE TRIM(SOURCE_NAME) = '" + SourceName.trim() + "' " +
            "AND TRIM(DEAL_IL_LOCATION) = '" + DealLocation.trim() + "' " +
            "AND TRIM(BANK_BRANCH_NAME) = '" + BranchName.trim() + "'";
	 objLog.servicelog("sQuery: " + sQuery1);
	 ArrayList Id =(ArrayList)ifr.getDataFromDB(sQuery1);

		String Solid =  (Id.get(0)).toString();
	  Solid = Solid.replace("[", "").replace("]", "");
		 objLog.servicelog("CPI_SOL_ID :::" + Solid);
		ifr.setValue("CPI_SOL_ID", Solid);
		 objLog.servicelog("END CPI_SOL_ID :::");
		 String SolID = (String) ifr.getValue("CPI_SOL_ID");
		 //added for SP Name   
		 String sQuery = "SELECT SP_NAME, SP_CODE ,SP_EMPLOYEE_ID " +
		                 "FROM NG_CPI_SP_CODE_MASTER " +
		                 "WHERE TRIM(BANK_BRANCH_NAME) = '" + BranchName.trim() + "' " +
		                 "AND TRIM(SOL_ID) = '" + SolID.trim() + "' " +
		                 "AND TRIM(DEAL_IL_LOCATION) = '" + DealLocation.trim() + "'";  

 ArrayList Code =(ArrayList)ifr.getDataFromDB(sQuery);
 objLog.servicelog("sQuery: " + sQuery);
 if (Code != null && Code.size() > 0) {
	 String qryRes = Code.get(0).toString();
	    objLog.servicelog("qryRes:::: " + qryRes);

	    // Remove square brackets if present and split by comma to get individual values
	    String cleanedRes = qryRes.replace("[", "").replace("]", "");
	    String[] res = cleanedRes.split(",");

	    
	    if (res.length > 0) {
	    	String SpName1 = res[0].trim();
	        String SPCode1 = res[1].trim();
	        String EmpId = res[2].trim();
	       
	        objLog.servicelog("SpName1: " + SpName1);
	        objLog.servicelog("SPCode1: " + SPCode1);
	        objLog.servicelog("CPI_SP_EMPLOYEE_ID: " + EmpId);
	       
	    	ifr.setValue("CPI_SP_NAME1", SpName1);
	    	ifr.setValue("CPI_SP_CODE1", SPCode1);
	    	ifr.setValue("CPI_SP_EMPLOYEE_ID", EmpId);
	    	
	    	
	    	String Name1 = (String) ifr.getValue("CPI_SP_NAME1");
	    	String Code1 = (String) ifr.getValue("CPI_SP_CODE1");
	         	 objLog.servicelog("CPI_SP_NAME1: " +Name1 );
		        objLog.servicelog("CPI_SP_CODE1: " + Code1);
	       
	        
	        
	    }
	    
	    
	    
 }
}
}catch (Exception e) {
	 objLog.servicelog("Error in setting value::: ");
        e.printStackTrace();
       
    }
 
}
public void GetSPCodeS(IFormReference ifr) {
	
	try {
		 objLog.servicelog("GetSPCode for Branch name when verticles are selected with deal location and sol id first selected::: ");
	
	String SourceName = (String) ifr.getValue("CPI_SOURCE_NAME");
	String DealLocation = (String) ifr.getValue("CPI_DEAL_IL_LOCATION");
	 String SolID = (String) ifr.getValue("CPI_SOL_ID");
	objLog.servicelog("SolID: " + SolID);
	if (!SolID.equalsIgnoreCase("") ) {
		objLog.servicelog("SolID not null : ");
		String sQuery1 = "SELECT DISTINCT BANK_BRANCH_NAME FROM NG_CPI_SP_CODE_MASTER " +
	            "WHERE TRIM(SOL_ID) = '" + SolID.trim() + "' " +
	            "AND TRIM(DEAL_IL_LOCATION) = '" + DealLocation.trim() + "' " ;

 objLog.servicelog("sQuery: " + sQuery1);
 ArrayList Id =(ArrayList)ifr.getDataFromDB(sQuery1);

	String BranchName =  (Id.get(0)).toString();
	BranchName = BranchName.replace("[", "").replace("]", "");
	 objLog.servicelog("CPI_BRANCH_NAME :::" + BranchName);
	ifr.setValue("CPI_BRANCH_NAME", BranchName);
	 objLog.servicelog("END CPI_BRANCH_NAME :::");
	 
	 String sQuery = "SELECT SP_NAME, SP_CODE, SP_EMPLOYEE_ID " +
             "FROM NG_CPI_SP_CODE_MASTER " +
             "WHERE TRIM(BANK_BRANCH_NAME) = '" + BranchName.trim() + "' " +
             "AND TRIM(SOL_ID) = '" + SolID.trim() + "' " +
             "AND TRIM(DEAL_IL_LOCATION) = '" + DealLocation.trim() + "'";  


ArrayList Code =(ArrayList)ifr.getDataFromDB(sQuery);
objLog.servicelog("sQuery: " + sQuery);
if (Code != null && Code.size() > 0) {
String qryRes = Code.get(0).toString();
objLog.servicelog("qryRes:::: " + qryRes);

// Remove square brackets if present and split by comma to get individual values
String cleanedRes = qryRes.replace("[", "").replace("]", "");
String[] res = cleanedRes.split(",");


if (res.length > 0) {
String SpName1 = res[0].trim();
String SPCode1 = res[1].trim();
String EmpId = res[2].trim();

objLog.servicelog("SpName1: " + SpName1);
objLog.servicelog("SPCode1: " + SPCode1);
objLog.servicelog("CPI_SP_EMPLOYEE_ID: " + EmpId);

ifr.setValue("CPI_SP_NAME1", SpName1);
ifr.setValue("CPI_SP_CODE1", SPCode1);
ifr.setValue("CPI_SP_EMPLOYEE_ID", EmpId);


String Name1 = (String) ifr.getValue("CPI_SP_NAME1");
String Code1 = (String) ifr.getValue("CPI_SP_CODE1");
 	 objLog.servicelog("CPI_SP_NAME1: " +Name1 );
    objLog.servicelog("CPI_SP_CODE1: " + Code1);



}



}
}
}catch (Exception e) {
objLog.servicelog("Error in setting value::: ");
e.printStackTrace();

}
}
public String GetSourceId(IFormReference ifr) {
	
	String sourceName = (String) ifr.getValue("CPI_SOURCE_NAME");
	try {
	String Query = "select distinct RECORDID from NG_CPI_SOURCE_MASTER where SOURCE ='"
			+ sourceName + "'";
	ArrayList Code =(ArrayList)ifr.getDataFromDB(Query);
	objLog.servicelog("sQuery: " + Query);
	  // Check if 'code' is not null and contains at least one element
    if (Code != null && !Code.isEmpty()) {
        String Id = Code.get(0).toString();
        Id = Id.replace("[", "").replace("]", "");
        objLog.servicelog("Id: " + Id);
        return Id;
    } else {
        objLog.servicelog("No RECORDID found for source: " + sourceName);
        return "0";  
    } 
    } catch (Exception e) {
        e.printStackTrace();
        return "0";  // or a default value if you prefer
    }
	
	
	
}
public String GetSubvId(IFormReference ifr) {
    String sourceName = (String) ifr.getValue("CPI_SOURCE_NAME");
    String subVerticle = (String) ifr.getValue("CPI_PRIMARY_SUB_VERTICAL");

    try {
       
        String query = "SELECT RECORDID FROM PRIMARY_VERT_DETAILS_MAS WHERE PRIMARY_SUB_VERTICAL_VALUE = '" + subVerticle + "'";
        objLog.servicelog("Query to get RECORDID: " + query);

        ArrayList recordIdList = (ArrayList) ifr.getDataFromDB(query);
        
        if (recordIdList != null && !recordIdList.isEmpty()) {
            String recordId = recordIdList.get(0).toString();
            recordId = recordId.replace("[", "").replace("]", "");
            objLog.servicelog("recordId -1125line ::: " + recordId);

            String query1 = "SELECT DISTINCT PRIMARY_SUB_VERTICAL_ID FROM NG_CPI_SOURCE_MASTER WHERE RECORDID = '" + recordId + "'";
            objLog.servicelog("Query to get PRIMARY_SUB_VERTICAL_ID: " + query1);

            ArrayList subVerticleIdList = (ArrayList) ifr.getDataFromDB(query1);

            // Check if 'subVerticleIdList' is not null and contains at least one element
            if (subVerticleIdList != null && !subVerticleIdList.isEmpty()) {
                String subVerticleId = subVerticleIdList.get(0).toString();
                subVerticleId = subVerticleId.replace("[", "").replace("]", "");
                objLog.servicelog("Found PRIMARY_SUB_VERTICAL_ID: " + subVerticleId);
                return subVerticleId;
            } else {
                objLog.servicelog("No PRIMARY_SUB_VERTICAL_ID found for RECORDID: " + recordId);
                return "0";
            }
        } else {
            objLog.servicelog("No RECORDID found for subVerticle: " + subVerticle);
            return "0";
        }
    } catch (Exception e) {
        e.printStackTrace();
        return "0";  // Returning default value in case of an exception
    }
}

public void SetZoneHub(IFormReference ifr) {
	
	String activityName = (String) ifr.getActivityName();
	String introducedBy = (String) ifr.getValue("CPI_ITRODUCEDBY");
	String zone = (String) ifr.getValue("CPI_ZONE");
	String IL_LOCATION = (String) ifr.getValue("CPI_IL_LOCATION");
	try {
      
        if ((activityName.equalsIgnoreCase("RM_GREEN_FILE") || 
             activityName.equalsIgnoreCase("ENDORSEMENT") || 
             activityName.equalsIgnoreCase("BSG_QC") || 
             activityName.equalsIgnoreCase("RM_CLARIFICATION") || 
             activityName.equalsIgnoreCase("BSG_CLARIFICATION") || 
             activityName.equalsIgnoreCase("CPU_HEALTH") || 
             activityName.equalsIgnoreCase("RE-INSURANCE") || 
             activityName.equalsIgnoreCase("UW_DEO")
             || activityName.equalsIgnoreCase("COPS_Cpu_Assignment") ||
             activityName.equalsIgnoreCase("CPU_ASSIGNMENT")
            || activityName.equalsIgnoreCase("Finance_Team")
            || activityName.equalsIgnoreCase("IPartner_Sync")) &&
            (introducedBy.equalsIgnoreCase("LMS_MODIFY") || 
             introducedBy.equalsIgnoreCase("LMS_STRAIGHT") || 
             introducedBy.equalsIgnoreCase("SPHINX ONLINE") || 
             introducedBy.equalsIgnoreCase("SPHINX OFFLINE") || 
             introducedBy.equalsIgnoreCase("MOBIAPP") || 
             introducedBy.equalsIgnoreCase("IPARTNER") || 
             introducedBy.equalsIgnoreCase("NEO1") || 
             introducedBy.equalsIgnoreCase("MYRA1") || 
             introducedBy.equalsIgnoreCase("ROBO") || 
             introducedBy.equalsIgnoreCase("QUOTEUPLOAD")) &&
            (zone == null || zone.isEmpty())) {
        	 String sQuery = "SELECT zone,hub FROM NG_IL_LOCATION_MASTER WHERE IL_LOCATION_VALUE = '" + IL_LOCATION + "'";
        			
        ArrayList Code =(ArrayList)ifr.getDataFromDB(sQuery);
        	objLog.servicelog("sQuery: " + sQuery);
        	if (Code != null && Code.size() > 0) {
        	String qryRes = Code.get(0).toString();
        	objLog.servicelog("qryRes:::: " + qryRes);

        	// Remove square brackets if present and split by comma to get individual values
        	String cleanedRes = qryRes.replace("[", "").replace("]", "");
        	String[] res = cleanedRes.split(",");


        	if (res.length > 0) {
        	String ILzone = res[0].trim();
        	String hub = res[1].trim();
        	
        	objLog.servicelog("CPI_ZONE: " + ILzone);
        	objLog.servicelog("CPI_HUB: " + hub);
        	

        	ifr.setValue("CPI_ZONE", ILzone);
        	ifr.setValue("CPI_HUB", hub);
        

        	
        
        
        	}
        	}
        	}
        	
        
	}catch (Exception e) {
        e.printStackTrace();
        
    }
}


	
		@Override
		public String generateHTML(IFormReference arg0, EControl arg1) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String validateDocumentConfiguration(IFormReference arg0, String arg1, String arg2, File arg3,
				Locale arg4) {
			// TODO Auto-generated method stub
			return null;
		}
	
		@Override
		public String setMaskedValue(IFormReference arg0, String arg1, String arg2) {
			// TODO Auto-generated method stub
			return arg2;
		}

	}