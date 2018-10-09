package com.example.EmployeeDashBoardService.controllers;

import java.util.Collection;

import com.example.EmployeeDashBoardService.models.EmployeeInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.bouncycastle.math.ec.custom.sec.SecT571Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.EmployeeDashBoardService.models.EmployeeInfo;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


@RefreshScope
@RestController
public class EmployeeInfoController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EurekaClient eurekaClient;

    @RequestMapping("/dashboard/{myself}")
    @HystrixCommand(fallbackMethod="defaultMe")
    public EmployeeInfo findme(@PathVariable Long myself){
        Application application = eurekaClient.getApplication("EmployeeSearch");
        InstanceInfo instanceInfo = application.getInstances().get(0);
        String url = "http://"+instanceInfo.getAppName()+ ":"+instanceInfo.getPort()+"/"+"employee/find/"+myself;
        System.out.println("URL" + url);
        EmployeeInfo emp = restTemplate.getForObject(url, EmployeeInfo.class);
        System.out.println("RESPONSE " + emp);
        return emp;
    }

    @RequestMapping("/dashboard/peers")
    public Collection<EmployeeInfo> findPeers(){
        Application application = eurekaClient.getApplication("EmployeeSearch");
        InstanceInfo instanceInfo = application.getInstances().get(0);
        String url = "http://"+instanceInfo.getAppName()+ ":"+instanceInfo.getPort()+"/"+"employee/findall";
        System.out.println("URL" + url);
        Collection<EmployeeInfo> list= restTemplate.getForObject(url, Collection.class);
        System.out.println("RESPONSE " + list);
        return list;
    }

    private EmployeeInfo defaultMe(Long id){
        EmployeeInfo info = new EmployeeInfo();
        info.setEmployeeId(id);
        info.setName("Hystrix fallback");
        info.setCompanyInfo("Netfilx");
        info.setDesignation("Fallback");
        return info;
    }
}
