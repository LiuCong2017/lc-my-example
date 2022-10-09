package com.ws.springws02.websocket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller("socket_sys")
@RequestMapping("/api/socket")
public class SystemController {
    //页面请求
//    @GetMapping("/index/{userId}")
//    @GetMapping("/index")
////    public ModelAndView socket(@PathVariable String userId) {
//    public ModelAndView socket() {
//        String userId = "32joj323j23o";
//        ModelAndView mav = new ModelAndView("/socket1");
//        mav.addObject("userId", userId);
//        return mav;
//    }

    //推送数据接口
    @ResponseBody
    @RequestMapping("/socket/push/{cid}/{msg}")
    public Map pushToWeb(@PathVariable("cid") String cid, @PathVariable("msg") String message) {
        Map<String,Object> result = new HashMap<>();
        WebSocketServer.sendInfo(message, cid);
        result.put("code", cid);
        result.put("msg", message);
        return result;
    }
}
