package com.indven.util.interceptor;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.indven.framework.util.ApplicationSessions;
import com.indven.framework.util.IndvenApplicationConstants;
import com.indven.portal.administration.vo.UserInfoVO;
import com.indven.portal.menu.vo.MenuMasterVO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.opensymphony.xwork2.interceptor.PreResultListener;

/**
 * Interceptor to check if user is authenticated to access the invoked action
 * 
 * @author Neel Borooah 
 * 
 * 
 */

public class UserSessionMgmtInterceptor implements Interceptor {

	
	private static final long serialVersionUID = 8478614982104563186L;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Main interceptor method
	 * Returns status
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		String status = "invalid";

		String sessionID = ServletActionContext.getRequest().getSession().getId();
		Map<String, Object> session = ActionContext.getContext().getSession();
		Map<Long, String> mapOfActiveSessions = ApplicationSessions.getMapOfActiveSessions();
		String requestId = ServletActionContext.getRequest().getParameter("requestId");
		UserInfoVO userInfoVO = (UserInfoVO) session.get("loginData");
		String actionName = invocation.getInvocationContext().getName();
		String errorMsg = "";
		Long selectedMenuId = null;
		
		if(actionName.equalsIgnoreCase("loginaction")) {
			selectedMenuId = 101L;
		}
		if(selectedMenuId == null) {
			String selectedMenuIdStr = null;
			selectedMenuIdStr = ServletActionContext.getRequest().getParameter("nodeclicked");
			
			if(selectedMenuIdStr == null){
				if(ServletActionContext.getRequest().getSession().getAttribute("nodeclicked")  == null) {
					selectedMenuIdStr = "101";
				} else {
					selectedMenuIdStr = ServletActionContext.getRequest().getSession().getAttribute("nodeclicked").toString();
				}
			}
			selectedMenuId = Long.parseLong(selectedMenuIdStr);
		}
		
		
		ServletActionContext.getRequest().getSession().setAttribute("nodeclicked", selectedMenuId);
		
		if(actionName.equalsIgnoreCase("faqpageaction") || actionName.equalsIgnoreCase("aboutpageaction") || actionName.equalsIgnoreCase("gofrlhthome")|| actionName.equalsIgnoreCase("getloginform")|| actionName.equalsIgnoreCase("viewManuscriptSearch")|| actionName.equalsIgnoreCase("searchManuscriptPublicUse")|| actionName.equalsIgnoreCase("exportReportForPublic")){
			status = invocation.invoke();
		} else if(actionName.equalsIgnoreCase("loadforguest")) {
			
			if(ServletActionContext.getRequest().getSession().getAttribute(IndvenApplicationConstants.LOGGEDIN_USER_SESSION_DATA) != null) {
				ServletActionContext.getRequest().getSession().setAttribute("nodeclicked", 201L);
				invocation.getInvocationContext().setName("gofrlhthome");
			}

			status = invocation.invoke();
		} else if(actionName.equalsIgnoreCase("loginaction") || actionName.equalsIgnoreCase("resetPasswordAction") 
				||  actionName.equalsIgnoreCase("showResetPasswordPage")) {
			//If user is logging in
			status = invocation.invoke();
		} else if(requestId == null && ServletActionContext.getRequest().getHeader("referer") == null) {
			
			if(userInfoVO == null) {
				errorMsg = "Please log in";
				status = "invalid";
			} else {
				errorMsg = "Invalid Access";
				status = "invalidLoggedInAccess";
			}
			
		} else if(userInfoVO != null && mapOfActiveSessions.get(userInfoVO.getId()) != null && mapOfActiveSessions.get(userInfoVO.getId()) == sessionID) {
			//If it is active user who has called the action
			
			MenuMasterVO menuList = (MenuMasterVO) session.get("headerMenu");
  			if(checkIfValidAccess(menuList.getChild(), requestId)) {
				//Checks if user is permitted to invoke the given action
  				ServletActionContext.getRequest().setAttribute("requestId", requestId);
  				status = invocation.invoke();
			} else {
				errorMsg = "Invalid access";
				status = "invalidLoggedInAccess";
			}
			
		} else if(actionName.equalsIgnoreCase("logoutaction")){
			//If user has logged in from another machine, then this method
			//	is called to log out the previous session of the same user
			
			errorMsg = "You have logged in from another machine";
			invocation.invoke();
			
		} else if(userInfoVO != null) {
			//User has logged in from another machine, hence call logout action on this session
			status = "logout";
		} else {
			//User has not logged in and does not have permission to call action
			errorMsg = "Please log in";
		}
		//Add the action error message to result
		final String error = errorMsg;
		PreResultListener preResultListener = new PreResultListener() {
            public void beforeResult(ActionInvocation invocation, String resultCode) {
            	ActionSupport action = (ActionSupport) invocation.getAction();
            	action.addActionError(error);
			}
		};
		invocation.addPreResultListener(preResultListener);
		return status;
	}
	
	/**
	 * Checks if request id has valid access
	 * @param menuList
	 * @param requestId
	 * @return
	 */
	public boolean checkIfValidAccess(List<MenuMasterVO> menuList, String requestId) {
		boolean result = false;
		for(Iterator<MenuMasterVO> i = menuList.iterator(); i.hasNext();) {
			MenuMasterVO node = i.next();
			if(node.getRequestId().equalsIgnoreCase(requestId)) {
				if(node.getChecked() == true) {
					return true;
				}
			} else {
				if(node.getChild().size() > 0) {
					result = checkIfValidAccess(node.getChild(), requestId);
					if(result == true) {
						return result;
					}
				}
			}
		}
		return result;
	}
}