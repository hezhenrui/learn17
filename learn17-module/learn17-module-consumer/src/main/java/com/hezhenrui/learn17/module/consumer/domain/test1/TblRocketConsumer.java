package com.hezhenrui.learn17.module.consumer.domain.test1;

import com.hezhenrui.learn17.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
    * rocketmq消费者表
    */
@ApiModel(value="rocketmq消费者表")
@Schema
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "test1.tbl_rocket_consumer")
public class TblRocketConsumer extends BaseEntity implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    @ApiModelProperty(value="")
    @Schema(description="")
    private Integer id;

    @Column(name = "`data`")
    @ApiModelProperty(value="")
    @Schema(description="")
    private String data;

    private static final long serialVersionUID = 1L;
}