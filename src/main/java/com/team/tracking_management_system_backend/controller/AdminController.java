package com.team.tracking_management_system_backend.controller;


import com.team.tracking_management_system_backend.component.RequestComponent;
import com.team.tracking_management_system_backend.entity.Admin;
import com.team.tracking_management_system_backend.entity.Manager;
import com.team.tracking_management_system_backend.entity.User;
import com.team.tracking_management_system_backend.repository.ManagerRepository;
import com.team.tracking_management_system_backend.service.AdminService;
import com.team.tracking_management_system_backend.service.UserService;
import com.team.tracking_management_system_backend.vo.MessageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private RequestComponent requestComponent;
    @Autowired
    private AdminService adminService;
    @Autowired
    private ManagerRepository managerRepository;

    @PostMapping("addManager")
    public Map addManager(@RequestBody Manager m){
        User user = userService.getUserByNumber(requestComponent.getUid());
        if(user != null){
            return Map.of("message",new MessageVO("该用户已存在"),"managers",adminService.getManagers());
        }
        adminService.addManager(m);
        return Map.of("message",new MessageVO("添加成功"),"managers",adminService.getManagers());
    }
    @GetMapping("getManagers")
    public Map getManagers(){
        return Map.of("managers",adminService.getManagers());
    }
    @PostMapping("deleteManager")
    public Map deleteManager(@RequestBody Manager m){
        adminService.deleteManager(m.getId());
        return Map.of("managers",adminService.getManagers());
    }
    @GetMapping("adminindex")
    public Map getAdmin(){
        log.debug("{}", requestComponent.getUid());
        Admin a = adminService.getAdmin(requestComponent.getUid());
        return Map.of(
                "admin",a
        );
    }
}
