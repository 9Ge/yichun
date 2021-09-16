package com.enercomn.web.A05OPLClass.bean;

import com.enercomn.web.baseManager.model.ModelCommonProperties;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * tb_opl_train_records
 * @author  yangcheng
 */
@Data
@ApiModel
public class A05TbOplTrainRecords extends ModelCommonProperties implements Serializable {
    /**
     * 培训记录表id
     */
    @ApiModelProperty("培训记录表id")
    private String totrId;

    /**
     * opl 单点课id
     */
    @ApiModelProperty("opl单点课id")
    private String tocId;

    /**
     * 培训人员id
     */
    @ApiModelProperty("培训人员id")
    private String userId;

    /**
     * 学习时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty("学习时间")
    private Date trainDate;

    private static final long serialVersionUID = 1L;
}
