package demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("user/")
public class UserController {

    @GetMapping("userInfo")
    public Map userInfo() {
       Map<String, String> res = new HashMap<>();
       res.put("age", 18+"");
       res.put("name", "jack");
       return res;
    }
}
