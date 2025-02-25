package com.newgen.iforms.user;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import com.newgen.iforms.EControl;
import com.newgen.iforms.FormDef;
import com.newgen.iforms.custom.IFormReference;
import com.newgen.iforms.custom.IFormServerEventHandler;
import com.newgen.mvcbeans.model.WorkdeskModel;

public class log_CnD implements Serializable {
	
	 private static final long serialVersionUID = 1L;

	    /**
	     * This method is used to write logs into log file.
	     * 
	     * @param strLog This parameter is used to write on log file.
	     */
	    public void servicelog(String strLog) {

	        try {
	            File objDirs = null;
	            String user_dir = System.getProperty("user.dir");
	            String sLogFilePath = user_dir + System.getProperty("file.separator") + "Lombard" + System.getProperty("file.separator") + "Lombard_LOGS_CPI";
	            objDirs = new File(sLogFilePath);
	            objDirs.mkdirs();
	            GregorianCalendar cal = new GregorianCalendar();
	            int logcount = 0;
	            sLogFilePath = sLogFilePath + System.getProperty("file.separator") + "Lombard_LOGS" + cal.get(5) + "-" + (cal.get(2) + 1) + "-" + cal.get(1) + "_" + logcount + ".log";

	            File logfile = new File(sLogFilePath);
	            if (logfile.length() > 5242880L) {
	                logcount++;
	            }
	            FileOutputStream fos = new FileOutputStream(sLogFilePath, true);
	            PrintWriter pw = new PrintWriter(fos);
	            pw.println("................................................................");
	            pw.print(new Date() + "  ");
	            pw.println(strLog);
	            pw.close();
	        } catch (Exception ex) {
	            System.out.println("Error in creating log " + ex.getMessage());
	        }
	    }
	}