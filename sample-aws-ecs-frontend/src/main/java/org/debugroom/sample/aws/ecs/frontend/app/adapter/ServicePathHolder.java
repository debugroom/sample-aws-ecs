package org.debugroom.sample.aws.ecs.frontend.app.adapter;

import java.util.List;
import java.util.Map;

public interface ServicePathHolder {

    public Map<String, List<String>> getHosts();
    public Map<String, String> getHostPorts();

}
