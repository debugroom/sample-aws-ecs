package org.debugroom.sample.aws.ecs.frontend.app.adapter.local;

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
public class PropertyPathHolder implements ServicePathHolder {

    private static final String PROPERTY_FILE_NAME = "class path resource [local-service.properties]";
    private static final String SEPARATOR = ".";
    private static final String DELEMITER = ",";

    @Autowired
    Environment environment;

    private Map<String, List<String>> hosts;
    private Map<String, String> ports;

    @PostConstruct
    public void postConstruct(){
        hosts = new ConcurrentHashMap<>();
        ports = new ConcurrentHashMap<>();

        PropertySources propertySources = ((AbstractEnvironment)environment).getPropertySources();
        Stream<PropertySource<?>> propertySourceStream = StreamSupport.stream(
                propertySources.spliterator(),false);
        propertySourceStream
            .filter((PropertySource propertySource) -> propertySource instanceof EnumerablePropertySource)
            .filter(p -> PROPERTY_FILE_NAME.equals(p.getName()))
            .forEach((PropertySource propertySource) -> {
                String[] propertyNames = ((EnumerablePropertySource<?>)propertySource).getPropertyNames();
                Stream.of(propertyNames)
                    .filter(s -> { return "name".equals(StringUtils.substringAfterLast(s, SEPARATOR)); })
                    .forEach((String propertyName) -> {
                        List<String> hostList = new ArrayList<>();
                        String hostKey = new StringBuilder()
                                .append(StringUtils.substringBeforeLast(propertyName, SEPARATOR))
                                .append(".host")
                                .toString();
                        String portKey = new StringBuilder()
                                .append(StringUtils.substringBeforeLast(propertyName, SEPARATOR))
                                .append(".port")
                                .toString();
                        String hostNameString = environment.getProperty(hostKey);
                        String port = environment.getProperty(portKey);
                        Stream.of(StringUtils.split(hostNameString, DELEMITER))
                                .forEach(s -> hostList.add(StringUtils.deleteWhitespace(s)));
                        hosts.put((String)propertySource.getProperty(propertyName), hostList);
                        ports.put((String)propertySource.getProperty(propertyName), port);
                    });
            });
    }


    @Override
    public Map<String, List<String>> getHosts() {
        return hosts;
    }

    @Override
    public Map<String, String> getHostPorts() {
        return ports;
    }
}
