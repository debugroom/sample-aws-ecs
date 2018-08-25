package org.debugroom.sample.aws.ecs.frontend.app.adapter;

import java.util.List;
import java.util.Objects;
import java.util.Random;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RandomAccessServiceProvider implements ConnectServiceProvider{

    @Autowired
    ServicePathHolder servicePathHolder;

    @Override
    public String getAuthority(String service) {
        StringBuilder stringBuilder = new StringBuilder();
        List<String> hosts = servicePathHolder.getHosts().get(service);
        if(hosts.size() == 0){
            new ServiceNotFoundException("No service found! : " + service);
        }else if(hosts.size() == 1){
            stringBuilder.append(hosts.get(0));
        }else{
            stringBuilder.append(hosts.get(new Random().nextInt(hosts.size())));
        }
        String port = servicePathHolder.getHostPorts().get(service);
        if(Objects.nonNull(port)){
            stringBuilder.append(":").append(port);
        }
        return stringBuilder.toString();
    }

}
