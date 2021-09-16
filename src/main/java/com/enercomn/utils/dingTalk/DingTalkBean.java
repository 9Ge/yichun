package com.enercomn.utils.dingTalk;

import lombok.Data;

import java.util.List;

/**
 * 钉钉推送实体类
 * lyh
 */
@Data
public class DingTalkBean {
    /**
     * 推送信息类型 text /  link  /  markdown
     */
    private Integer MsgType;

    /**
     * 文本信息 适用于 text信息类型
     */
    private String textContent;

    /**
     * 艾特人员集合适用于 text /  link  /  markdown
     */
    private List<String> allAtList;

    /**
     * 信息链接地址 适用于 link信息类型
     */
    private String linkMessageUrl;
    /**
     * 图片地址 适用于 link信息类型
     */
    private String linkPicUrl;

    /**
     * 信息标题 适用于 link MarkDown 信息类型
     */
    private String linkOrMarkDownTitle;

    /**
     * 信息文本 适用于 MarkDown 信息类型
     */
    private String MarkDownText;

    /**
     * 钉钉报警推送接口地址
     */
    private String dingTalkUrl;

    /**
     * 钉钉推送密钥
     */
    private String dingTalkToken;
}
