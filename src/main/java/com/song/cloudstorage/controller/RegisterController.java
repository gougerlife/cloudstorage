package com.song.cloudstorage.controller;

import com.song.cloudstorage.dao.MyDiskInfoDao;
import com.song.cloudstorage.dao.MyFileDao;
import com.song.cloudstorage.model.User;
import com.song.cloudstorage.service.MyDiskInfoService;
import com.song.cloudstorage.service.MyFileService;
import com.song.cloudstorage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/register")
public class RegisterController extends Support{

    @Autowired
    private UserService userService;

    @Autowired
    private MyDiskInfoService myDiskInfoService;

    @Autowired
    private MyFileService myFileService;
    /**
     * user registration
     * @param user
     * @return
     */
    @RequestMapping("/welcome")
    public String register(User user) {
        userService.save(user);
        session.setAttribute("diskinfo", myDiskInfoService.load(user.getId()));
        session.setAttribute("homeId", myFileService.getHomeId(user.getId()));
        session.setAttribute("user", user);
        return "redirect:/home/disk";
    }
}
