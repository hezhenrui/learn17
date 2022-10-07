package com.hezhenrui.demo.domain.test1;

import com.hezhenrui.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import lombok.*;
import lombok.experimental.SuperBuilder;

/**
    * rocketmq消费者表
    */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
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