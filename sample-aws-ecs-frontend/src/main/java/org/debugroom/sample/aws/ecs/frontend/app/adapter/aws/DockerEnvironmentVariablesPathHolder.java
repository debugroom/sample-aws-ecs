package org.debugroom.sample.aws.ecs.frontend.app.adapter.aws;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.debugroom.sample.aws.ecs.frontend.app.adapter.ServicePathHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Slf4j
@Component
public class DockerEnvironmentVariablesPathHolder implements ServicePathHolder {

    private static final String PROPERTY_FILE_NAME = "class path resource [docker-service.properties]";
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
                propertySources.spliterator(), false);

        propertySourceStream
            .filter(p -> p instanceof EnumerablePropertySource)
            .filter(p -> PROPERTY_FILE_NAME.equals(p.getName()))
            .forEach(p -> {
                String[] propertyNames = ((EnumerablePropertySource)p).getPropertyNames();
                Stream.of(propertyNames)
                    .filter(s -> {return "name".equals(StringUtils.substringAfterLast(s, SEPARATOR));})
                    .forEach(s -> {
                        List<String> hostList = new ArrayList<>();
                        String hostKey = new StringBuilder()
                                    .append(StringUtils.substringBeforeLast(s, SEPARATOR))
                                    .append(".host")
                                    .toString();
                        String portKey = new StringBuilder()
                                    .append(StringUtils.substringBeforeLast(s, SEPARATOR))
                                    .append(".port")
                                    .toString();
                        String hostNameString = (String)environment.getProperty(hostKey);
                        String port = (String)environment.getProperty(portKey);
                        Stream.of(StringUtils.split(hostNameString, DELEMITER))
                            .forEach(host -> hostList.add(StringUtils.deleteWhitespace(host)));
                        hosts.put((String)p.getProperty(s), hostList);
                        ports.put((String)p.getProperty(s), port);
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
