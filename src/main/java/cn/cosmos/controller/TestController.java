package cn.cosmos.controller;

import cn.cosmos.websocket.WebSocketServer;
import cn.hutool.core.date.DateUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created with CosmosRay
 *
 * @author CosmosRay
 * @date 2020/01/09
 * Funciton:
 */
@RestController
public class TestController {

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public void test(){
        System.out.println("触发websocket ... ...");
        WebSocketServer.sendMessage(DateUtil.today());
    }
}
