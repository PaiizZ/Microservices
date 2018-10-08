package com.example.EmployeeDashBoardService;

import java.util.Collection;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RefreshScope
@RestController
public class FeignEmployeeInfoController {
    @Autowired
    EmployeeServiceProxy proxyService;

    public String fallback(Throwable hystrixCommand) {
        return "Fall Back Hello world";
    }
    @RequestMapping("/dashboard/feign/{myself}")
    public EmployeeInfo findme(@PathVariable Long myself) {
        return proxyService.findById(myself);
    }
    @RequestMapping("/dashboard/feign/peers")
    public Collection <EmployeeInfo> findPeers() {
        return proxyService.findAll();
    }
}
