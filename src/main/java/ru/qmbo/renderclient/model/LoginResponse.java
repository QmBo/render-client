package ru.qmbo.renderclient.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class LoginResponse {
    private String status;
    private String token;
}
