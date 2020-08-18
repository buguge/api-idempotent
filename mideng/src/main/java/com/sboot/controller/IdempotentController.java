package com.sboot.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
@Slf4j
public class IdempotentController {

    @RequestMapping(value = "/user")
    public String init(HttpServletRequest request) {
        log.info("进入页面");
        request.setAttribute("ticket", UUID.randomUUID().toString());
        return "user";
    }

    @RequestMapping(value = "/user/getTicket")
    @ResponseBody
    public String getTicket(HttpServletRequest request) {
        return TockenUtil.getToken();
    }

    @RequestMapping(value = "/user/addUser")
    @ResponseBody
    public Result addUser(HttpServletRequest request, @RequestBody User h5PayRequestVo) throws Exception {
        String uuid = java.util.UUID.randomUUID().toString();
        Thread.currentThread().setName(System.currentTimeMillis() + uuid.replace("-", ""));
        log.info("参数：{}", JSON.toJSONString(h5PayRequestVo));

        if (!TockenUtil.existToken(h5PayRequestVo.getTicket())) {
            log.info("重复提交");
            return Result.error("重复提交");
        }

        Thread.sleep(RandomUtils.nextInt(10, 2000));

        log.info("ok:{}", uuid);
        return Result.ok(uuid);
    }
}
