package com.probal.moddhobitto.api.auth.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
public class AppUserDto implements Serializable {

    @NotNull
    private String phone;

    @NotNull
    private String password;

}
