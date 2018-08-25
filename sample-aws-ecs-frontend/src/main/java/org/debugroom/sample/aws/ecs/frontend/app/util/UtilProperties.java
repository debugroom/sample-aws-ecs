package org.debugroom.sample.aws.ecs.frontend.app.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix="util")
public class UtilProperties {

    private String protocol;

}
