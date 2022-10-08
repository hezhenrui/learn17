package com.hezhenrui.learn17.module.consumer.domain.test;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@ApiModel(value = "test.tbl_new_demo_copy")
@Schema
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "test.tbl_new_demo_copy")
public class TblNewDemoCopy implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    @ApiModelProperty(value = "")
    @Schema(description = "")
    private Integer id;

    @Column(name = "`name`")
    @ApiModelProperty(value = "")
    @Schema(description = "")
    private String name;

    @Column(name = "age")
    @ApiModelProperty(value = "")
    @Schema(description = "")
    private String age;

    private static final long serialVersionUID = 1L;
}