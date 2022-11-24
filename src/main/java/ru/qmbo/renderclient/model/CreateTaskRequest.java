package ru.qmbo.renderclient.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class CreateTaskRequest {
    private String token;
}
