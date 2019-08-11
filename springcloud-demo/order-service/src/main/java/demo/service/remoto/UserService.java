package demo.service.remoto;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Service
@FeignClient("user-service")
public interface UserService {
    @GetMapping("user/userInfo")
    public Map userInfo();
}
