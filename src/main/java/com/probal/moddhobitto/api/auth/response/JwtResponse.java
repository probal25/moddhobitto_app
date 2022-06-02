package com.probal.moddhobitto.api.auth.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class JwtResponse {
    String token;
    Long id;
    String username;
    List<String> roles;

}
