package com.itxiaojiang.aicodehelper.ai;

import dev.langchain4j.rag.content.Content;
import dev.langchain4j.service.Result;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AiCodeHelperServiceTest {
    @Resource
    private AiCodeHelperService aiCodeHelperService;
    @Test
    void chat() {
        String result = aiCodeHelperService.chat("你好，我是程序员小江");
        System.out.println(result);
    }
    @Test
    void chatWithMemory() {
        String result = aiCodeHelperService.chat("你好，我是程序员鱼皮");
        System.out.println(result);
        result = aiCodeHelperService.chat("你好，我是谁来着？");
        System.out.println(result);
    }
    @Test
    void chatWithRag() {
        Result<String> result = aiCodeHelperService.chatWithRag("怎么学习 Java？有哪些常见面试题？");
        String content = result.content();
        List<Content> sources = result.sources();
        System.out.println(content);
        System.out.println(sources);
    }
}