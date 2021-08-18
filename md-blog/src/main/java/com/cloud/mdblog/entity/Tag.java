package com.cloud.mdblog.entity;

import com.cloud.mdblog.utils.Entity;
import lombok.Data;

@Data
public class Tag extends Entity {
    private Integer id;
    private String tagName;
}
