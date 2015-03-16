package com.magikhelper.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.magikhelper.services.UsersService;
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
}
