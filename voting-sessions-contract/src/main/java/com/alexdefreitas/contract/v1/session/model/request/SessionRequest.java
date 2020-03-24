package com.alexdefreitas.contract.v1.session.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SessionRequest {

    @ApiModelProperty(value = "Session duration in minutes.", example = "Default value = 1")
    private Integer minuteDuration = 1;
}
