package com.github.learn17.common.po.test;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "test.tbl_new_demo")
@Schema
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "test.tbl_new_demo")
public class TblNewDemo implements Serializable {
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