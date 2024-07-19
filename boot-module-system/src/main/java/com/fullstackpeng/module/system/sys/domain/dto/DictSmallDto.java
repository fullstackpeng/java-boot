package com.fullstackpeng.module.system.sys.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;


@Data
@EqualsAndHashCode(callSuper = false)
@ToString
public class DictSmallDto implements Serializable {
    /**
     * id
     */
    @NotBlank(message = "id不能为空")
    private String id;
    /**
     * 名称
     */
    @NotBlank(message = "名称不能为空")
    private String name;
    /**
     * 类型
     */
    @NotBlank(message = "类型不能为空")
    private String type;
}
