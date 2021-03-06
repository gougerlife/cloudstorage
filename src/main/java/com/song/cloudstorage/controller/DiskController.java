package com.song.cloudstorage.controller;


import com.alibaba.fastjson.JSON;
import com.song.cloudstorage.dao.MyFileDao;
import com.song.cloudstorage.model.MyFile;
import com.song.cloudstorage.util.FileStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/home")
public class DiskController extends Support{


    private static final String FILEBASEPATH = FileStorage.getFilePath();

    @Autowired
    private MyFileDao myFileDao;

    /**
     * list all files in the according directory
     */
    @RequestMapping("/list_myfile")
    @ResponseBody
    public String listFiles(int id, String pwd) {
        MyFile folder = myFileDao.getMyFile(id);
        List<MyFile> myFiles = null;

        if(folder.getIsLock() == 1) {
            if(folder.getPassword().equals(pwd)) {
                myFiles = myFileDao.getFilesByFolderId(id);
            }else {
                return "fail";
            }
        }else {
            myFiles = myFileDao.getFilesByFolderId(id);
        }

        return JSON.toJSONString(myFiles);
    }
}
