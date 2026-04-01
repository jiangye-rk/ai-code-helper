package com.itxiaojiang.aicodehelper;

import com.itxiaojiang.aicodehelper.ai.AiCodeHelper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AiCodeHelperText {
    @Resource
    private AiCodeHelper aiCodeHelper;
    @Test
    void chat(){
        aiCodeHelper.chat("你好，我是程序员小江");
    }

}
