package com.fullstackpeng.module.system.common.domain.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;


@Data
@EqualsAndHashCode
@ToString
public class SelectOptionVo implements Serializable {
    private String label;
    private String value;
}
