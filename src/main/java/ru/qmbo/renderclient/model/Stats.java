package ru.qmbo.renderclient.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Accessors(chain = true)
@Data
public class Stats implements Comparable<Stats> {
    private int id;
    private String status;
    private Date date;

    @Override
    public int compareTo(Stats o) {
        return this.date.compareTo(o.getDate());
    }
}
