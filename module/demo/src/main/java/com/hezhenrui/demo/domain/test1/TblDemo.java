package com.hezhenrui.demo.domain.test1;

import java.io.Serializable;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_demo")
public class TblDemo implements Serializable {
    @Column(name = "id")
    private Integer id;

    @Column(name = "`name`")
    private String name;

    @Column(name = "age")
    private String age;

    @Column(name = "relation_id")
    private Integer relationId;

    private static final long serialVersionUID = 1L;
}