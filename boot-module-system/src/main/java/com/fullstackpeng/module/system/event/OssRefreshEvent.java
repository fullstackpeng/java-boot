package com.fullstackpeng.module.system.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class OssRefreshEvent extends ApplicationEvent {
    private final String sysCode;

    public OssRefreshEvent(String sysCode, Object source) {
        super(source);
        this.sysCode = sysCode;
    }
}
