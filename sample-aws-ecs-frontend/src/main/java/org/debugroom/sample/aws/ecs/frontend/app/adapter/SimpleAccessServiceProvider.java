package org.debugroom.sample.aws.ecs.frontend.app.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class SimpleAccessServiceProvider implements  ConnectServiceProvider{

    @Autowired
    ServicePathHolder servicePathHolder;

    @Override
    public String getAuthority(String service) {
        StringBuilder stringBuilder = new StringBuilder();
        List<String> hosts = servicePathHolder.getHosts().get(service);
        if(hosts.size() == 0){
            new ServiceNotFoundException("No serivce found! :" + service);
        }else {
            stringBuilder.append(hosts.get(0));
        }
        String port = servicePathHolder.getHostPorts().get(service);
        if(Objects.nonNull(port)){
            stringBuilder.append(":").append(port);
        }
        return stringBuilder.toString();
    }

}
