package demo.gateway.controller;

import demo.gateway.service.GatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatewayController {

    @Autowired
    private GatewayService gatewayService;

    @GetMapping("addRoute")
    public String addRoute() {
        gatewayService.save();
        return "success";
    }
}
