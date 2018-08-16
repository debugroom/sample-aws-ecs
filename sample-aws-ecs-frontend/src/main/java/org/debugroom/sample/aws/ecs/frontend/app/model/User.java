package org.debugroom.sample.aws.ecs.frontend.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.ZonedDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User implements Serializable {

    private String userId;
    private String userName;
    private String loginId;
    private ZonedDateTime lastUpdatedDate;

}
