package com.magikhelper.controllers.vendor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.magikhelper.services.UsersService;
import com.magikhelper.vo.UserVO;
import com.magikhelper.vo.VendorVO;

/**
 *
 * @author aahmed
 */
@RestController
@RequestMapping("/vendors")
public class VendorController {

    @Autowired
    private UsersService usersService;

    @RequestMapping(value="/skills",method = RequestMethod.GET,headers="Accept=application/json")
    public List<VendorVO> getSkills() {
        List<VendorVO> vendors = usersService.getVendorWithSkills(null);
        return vendors;
    }
    
    @RequestMapping(value="/skills/{vendorId}",method = RequestMethod.GET,headers="Accept=application/json")
    public List<VendorVO> getSkill(@PathVariable("vendorId") Integer vendorId) {
        List<VendorVO> vendors = usersService.getVendorWithSkills(vendorId);
        return vendors;
    }
    
    @RequestMapping(method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateClient(@RequestBody UserVO vendor, HttpServletRequest request, HttpServletResponse response) {
			usersService.updateUser(vendor);
    }

}
