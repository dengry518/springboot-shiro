package com.dengry.springbootshiro.controller;

import com.dengry.springbootshiro.entity.Resource;
import com.dengry.springbootshiro.service.MyService;
import com.dengry.springbootshiro.valueObject.Json;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/resource")
public class ResourceController {
    @Autowired
    MyService myService;



    @RequestMapping("/toListAll")
    public String toListAll() {
        return "resource/listAll";
    }

    @RequestMapping("/findReses")
    @ResponseBody
    public Map<String, Object> findReses(String url, Integer pageIndex, Integer pageSize) {
        return myService.findReses(url, pageIndex, pageSize);
    }

    @RequestMapping("/toAdd")
    public String toAdd() {
        return "resource/add";
    }

    @RequestMapping("/add")
    @ResponseBody
    public Json add(Resource resource) {
        log.debug("resource is {}", resource);
        myService.saveResource(resource);
        return Json.succ();
    }

    @RequestMapping("/delById")
    @ResponseBody
    public Json delById(Integer id) {
        log.debug("id is {}", id);
        myService.delResById(id);
        return Json.succ();
    }


}
