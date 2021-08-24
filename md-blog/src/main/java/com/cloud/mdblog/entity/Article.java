package com.cloud.mdblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cloud.mdblog.utils.Entity;
import com.cloud.mdblog.vo.ArticleVo;
import com.cloud.mdblog.vo.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_article")
public class Article extends Entity implements Serializable  {

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

    private Date createDate;


    private Integer createUser;

    private Date updateDate;

    private List<Map<String,Object>> articleAttachments;

    private List<Integer> selectTagList;

    private User user;

    private List<Page> pageList;

    private Channel channel;

    private List<ArticleVo> articleVo;

    private Integer articleView;

    private Boolean front;
}
