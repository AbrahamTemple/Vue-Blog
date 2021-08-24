package com.cloud.mdblog.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.cloud.mdblog.entity.Channel;
import com.cloud.mdblog.entity.User;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class ArticleVo {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer channelId;

    private String title;

    private String titleImg;

    private String summary;

    private String author;

    private String url;

    private String content;

    private Integer status;

    private Integer commentStatus;

    private Integer rotation;

    private Integer top;

    private Integer orderby;

    private Date createDate;

    private Integer createUser;

    private Date updateDate;

    private List<Map<String,Object>> articleAttachments;

    private List<Integer> selectTagList;

    private User user;

    private List<Page> pageList;

    private Channel channel;
}
