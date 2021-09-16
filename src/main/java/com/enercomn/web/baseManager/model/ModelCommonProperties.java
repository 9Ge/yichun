package com.enercomn.web.baseManager.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author yangcheng
 */
@Data
public class ModelCommonProperties {

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人",hidden = true)
    private String createUser;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty(value ="创建时间",hidden = true)
    private Date createDate;

    /**
     * 修改人
     */
    @ApiModelProperty(value ="修改人",hidden = true)
    private String updateUser;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty(value ="修改时间",hidden = true)
    private Date updateDate;

    /**
     * 有效标志符
     */
    @ApiModelProperty(value ="有效标志符 0有效 1无效")
    private Integer isDel=0;

}
