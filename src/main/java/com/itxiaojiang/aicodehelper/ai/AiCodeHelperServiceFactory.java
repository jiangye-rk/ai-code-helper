package com.itxiaojiang.aicodehelper.ai;

import com.itxiaojiang.aicodehelper.tools.InterviewQuestionTool;
import dev.langchain4j.mcp.McpToolProvider;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.spring.AiService;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiCodeHelperServiceFactory {
    @Resource
    private ChatModel myQwenChatModel;
    @Resource
    private ContentRetriever contentRetriever;
    @Resource
    private StreamingChatModel qwenStreamingChatModel;
    @Bean
    public AiCodeHelperService aiCodeHelperService(McpToolProvider mcpToolProvider){
        ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);
        AiCodeHelperService aiCodeHelperService= AiServices.builder(AiCodeHelperService.class)
                .chatModel(myQwenChatModel)
                .streamingChatModel(qwenStreamingChatModel)
                .chatMemory(chatMemory)
                .chatMemoryProvider(memoryId ->
                        MessageWindowChatMemory.withMaxMessages(10))//每个会话独立存储
                .contentRetriever(contentRetriever)//RAG的检索器
                .tools(new InterviewQuestionTool())//工具调用
                .toolProvider(mcpToolProvider)//MCP工具调用
                .build();
        return aiCodeHelperService;
    }
}
