package com.magikhelper.controllers.security;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.magikhelper.entities.SecurityToken;
import com.magikhelper.services.SecurityTokenService;
import com.magikhelper.services.UsersService;
import com.magikhelper.utils.MagikHelperConstants;
import com.magikhelper.utils.Utils;
import com.magikhelper.vo.UserVO;
import com.magikhelper.vo.ErrorMessageVO;
import com.magikhelper.vo.LoginVO;

/**
 *
 * @author aahmed
 */
@RestController
@RequestMapping(value="/security")
public class LoginController {

    private static final Log LOGGER = LogFactory.getLog(LoginController.class);

    @Autowired
    private UsersService usersService;
    
    @Autowired
    private SecurityTokenService securityTokenService;

    @RequestMapping(value="/login",method = RequestMethod.POST,headers="Accept=application/json")
    public Object login(@RequestBody LoginVO vo, 
    		HttpServletRequest request,
    		HttpServletResponse response,
    		@RequestHeader(value="DEVICE_ID") String deviceId) {
//    	String email = request.getParameter("email");
//    	String password = request.getParameter("password");
    	LOGGER.debug("email: "+vo.getEmail());
    	LOGGER.debug("deviceId: "+deviceId);
        UserVO client = usersService.loginUser(vo.getEmail(), vo.getPassword());
        if (client != null){
        	HttpSession session = request.getSession(true);
        	String token = Utils.getSecurityToken(client.getEmail()+new Date().getTime()+MagikHelperConstants.SECURITY_KEY);
        	LOGGER.debug("Is session new: "+session.isNew());
        	
        	SecurityToken securityToken = new SecurityToken();
        	securityToken.setToken(token);
        	securityToken.setDeviceId(deviceId);
        	securityToken.populatedAuditFields(client.getEmail());
        	securityTokenService.saveToken(securityToken);
        	
        	LOGGER.debug("token: "+token);

        	session.setAttribute(MagikHelperConstants.SESSION_ATT_LOGIN,client);
        	response.setHeader("MH_SECURITY_TOKEN", token);
        	return client;
        }
        else {
        	return new ErrorMessageVO(MagikHelperConstants.ERROR_CODE_1001, MagikHelperConstants.ERROR_MESSAGE_1001);
        }
    }
    
}
