package com.hezhenrui.learn17.module.consumer.domain.test;

import com.hezhenrui.learn17.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
    * rocketmq事务日志表
    */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="rocketmq事务日志表")
@Schema
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "test.tbl_rocket_transaction_ex")
public class TblRocketTransactionEx extends BaseEntity implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    @ApiModelProperty(value="")
    @Schema(description="")
    private Integer id;

    @Column(name = "commit_id")
    @ApiModelProperty(value="")
    @Schema(description="")
    private String commitId;

    @Column(name = "log")
    @ApiModelProperty(value="")
    @Schema(description="")
    private String log;

    private static final long serialVersionUID = 1L;
}