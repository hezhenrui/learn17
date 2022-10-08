package com.hezhenrui.learn17.module.demo.domain.test;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "test.tbl_new_demo_relation")
@Schema
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "test.tbl_new_demo_relation")
public class TblNewDemoRelation implements Serializable {
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

    @Column(name = "relation_id")
    @ApiModelProperty(value = "")
    @Schema(description = "")
    private Integer relationId;

    private static final long serialVersionUID = 1L;
}