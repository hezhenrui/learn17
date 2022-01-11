package com.hezhenrui.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SocketController {

    @Autowired
    private IWebSocketServer iWebSocketServer;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/webSocket")
    public ModelAndView socket() {
        ModelAndView mav=new ModelAndView("/webSocket");
//        mav.addObject("userId", userId);
        return mav;
    }


}
