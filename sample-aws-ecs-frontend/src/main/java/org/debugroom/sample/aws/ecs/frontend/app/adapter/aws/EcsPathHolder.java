package org.debugroom.sample.aws.ecs.frontend.app.adapter.aws;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.*;
import org.springframework.stereotype.Component;

import org.debugroom.sample.aws.ecs.frontend.app.adapter.ServicePathHolder;

@Component
public class EcsPathHolder implements ServicePathHolder {

    private static final String PROPERTY_FILE_NAME = "class path resource [ecs-service.properties]";
    private static final String SEPARATOR = ".";

    private Map<String, List<String>> hosts;
    private Map<String, String> ports;

    @Autowired
    Environment environment;

    @PostConstruct
    public void postConstruct(){
        hosts = new ConcurrentHashMap<>();
        ports = new ConcurrentHashMap<>();

        PropertySources propertySources = ((AbstractEnvironment)environment).getPropertySources();
        Stream<PropertySource<?>> propertySourceStream = StreamSupport.stream(
                propertySources.spliterator(), false);
        propertySourceStream
            .filter((p) -> p instanceof EnumerablePropertySource)
            .filter(p -> PROPERTY_FILE_NAME.equals(p.getName()))
            .forEach(p -> {
                String[] propertyNames = ((EnumerablePropertySource<?>)p).getPropertyNames();
                Stream.of(propertyNames)
                    .filter(s -> { return "name".equals(StringUtils.substringAfterLast(s, SEPARATOR)); })
                    .forEach(s ->{
                        List<String> hostList = new ArrayList<>();
                        String hostKey = new StringBuilder()
                                .append(StringUtils.substringBeforeLast(s, SEPARATOR))
                                .append(".host")
                                .toString();
                        hostList.add(StringUtils.deleteWhitespace((String)environment.getProperty(hostKey)));
                        hosts.put((String)p.getProperty(s), hostList);
                    });
                });
    }

    @Override
    public Map<String, List<String>> getHosts() { return hosts; }

    @Override
    public Map<String, String> getHostPorts() { return ports; }

}
