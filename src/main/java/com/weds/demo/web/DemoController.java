package com.weds.demo.web;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/demo")
@Api(value = "测试接口", description = "测试接口")
public class DemoController {
}
