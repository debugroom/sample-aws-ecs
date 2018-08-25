package org.debugroom.sample.aws.ecs.frontend.app.adapter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ServiceNotFoundException extends RuntimeException{

    private String code;
    private Object[] args;

    public ServiceNotFoundException(String message){
        super(message);
    }

    public ServiceNotFoundException(String code, String message){
        super(message);
        this.code = code;
    }

    public ServiceNotFoundException(String code, Throwable throwable){
        super(throwable);
        this.code = code;
    }

    public ServiceNotFoundException(String code, Throwable throwable, Object... args) {
        super(throwable);
        this.code = code;
        this.args = args;
    }

}
