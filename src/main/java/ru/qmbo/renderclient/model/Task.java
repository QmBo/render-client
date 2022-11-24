package ru.qmbo.renderclient.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class Task {
    private String id;
    private String status;
}
