package com.cloud.mdblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cloud.mdblog.utils.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_channel")
public class Channel extends Entity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer parentId;

    private String channelImg;

    private String summary;

    private String single;

    private String url;

    private String seoTitle;

    private String seoKeyword;

    private String seoDescription;

    private String content;

    private Integer orderby;

    private Date createDate;

    private Integer createUser;

    private String deletedFlag;

    private String userName;

    private String pos;

}
