package com.fullstackpeng.common.base.enums;

import lombok.Getter;

@Getter
public enum MenuTypeEnums implements ValueEnum<Integer> {
    Menu_1(1, "菜单"),
    Menu_2(2, "iframe"),
    Menu_3(3, "外链"),
    Menu_4(4, "按钮"),
    ;
    private final Integer value;
    private final String name;

    MenuTypeEnums(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    public static MenuTypeEnums of(Integer value) {
        for (MenuTypeEnums enums : MenuTypeEnums.values()) {
            if (enums.getValue().equals(value)) {
                return enums;
            }
        }
        return null;
    }

}
