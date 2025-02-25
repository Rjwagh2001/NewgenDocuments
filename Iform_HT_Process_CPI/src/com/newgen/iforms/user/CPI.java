
package com.newgen.iforms.user;

import java.io.File;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import com.newgen.iforms.EControl;
import com.newgen.iforms.FormDef;
import com.newgen.iforms.custom.IFormListenerFactory;
import com.newgen.iforms.custom.IFormReference;
import com.newgen.iforms.custom.IFormServerEventHandler;
import com.newgen.mvcbeans.model.WorkdeskModel;


public class CPI  implements IFormListenerFactory  {

	@Override
	public IFormServerEventHandler getClassInstance(IFormReference ifr) {
		
		 log_CnD objLog = new log_CnD();
		// TODO Auto-generated method stub

	            objLog.servicelog("Calling CPI_Common..");
	            String sActivityName=ifr.getActivityName();
	            String sProcessName = ifr.getProcessName();
	 
	            return new CommonModule(); 
	}

}
