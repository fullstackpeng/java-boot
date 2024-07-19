package com.fullstackpeng;

import com.fullstackpeng.common.base.BootAdminApp;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class BootTestApplication extends BootAdminApp {

    public static void main(String[] args) {
        bootstrap(BootTestApplication.class, args);
    }

}
