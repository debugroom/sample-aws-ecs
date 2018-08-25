package org.debugroom.sample.aws.ecs.frontend.app.util;

import org.debugroom.sample.aws.ecs.frontend.app.adapter.ConnectServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Component
public class RequestBuilder {

    private static final String SEPARATOR = "/";

    @Autowired
    UtilProperties properties;

    @Autowired
    ConnectServiceProvider connectServiceProvider;

    public UriComponents buildUriComponents(String serviceName, String path){
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance();
        return uriComponentsBuilder.scheme(properties.getProtocol())
                .host(connectServiceProvider.getAuthority(serviceName))
                .path(serviceName)
                .pathSegment(path)
                .build();
    }

    public UriComponents buildUriComponents(
            String serviceName, String path, MultiValueMap<String, String> params){
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance();
        return uriComponentsBuilder.scheme(properties.getProtocol())
                .host(connectServiceProvider.getAuthority(serviceName))
                .path(serviceName)
                .pathSegment(path)
                .queryParams(params)
                .build();
    }

    public UriComponents buildUriComponents(
            String serviceName, String path, Map<String, String> uriVariables){
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance();
        return uriComponentsBuilder.scheme(properties.getProtocol())
                .host(connectServiceProvider.getAuthority(serviceName))
                .path(serviceName)
                .pathSegment(path)
                .buildAndExpand(uriVariables);
    }

    public UriComponents buildUriComponents(
            String serviceName, String path, Map<String, String> uriVariables,
            MultiValueMap<String, String> params){
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance();
        return  uriComponentsBuilder.scheme(properties.getProtocol())
                .host(connectServiceProvider.getAuthority(serviceName))
                .path(serviceName)
                .pathSegment(path)
                .queryParams(params)
                .buildAndExpand(uriVariables);
    }


}
