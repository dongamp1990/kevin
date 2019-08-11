package demo.controller;

import demo.service.remoto.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private UserService userService;

    @GetMapping("order/{id}")
    public Map order(@PathVariable(value = "id") Integer id) {
        Map<String, Object> map = new HashMap<>();
        Map<String, String> order = new HashMap<>();
        order.put("amount", "5");
        order.put("no", "2019001");
        order.put("id", id+"");
        Map userInfo = userService.userInfo();
        map.put("order", order);
        map.put("user", userInfo);
        return map;
    }
}
