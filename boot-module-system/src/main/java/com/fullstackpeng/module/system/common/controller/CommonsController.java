package com.fullstackpeng.module.system.common.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/commons")
@Tag(name = "公共接口")
@RequiredArgsConstructor
public class CommonsController {
}
