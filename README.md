# AI Code Helper 🤖

基于 **LangChain4j + Spring Boot + 阿里通义千问** 的智能编程助手，为程序员提供代码学习、面试准备和求职指导服务。

## ✨ 核心特性

- 💬 **流式对话** - 基于 SSE 的实时响应，支持多轮对话记忆
- 📚 **RAG 知识库** - 内置 Java 学习路线、面试题库、求职指南等专业知识
- 🛡️ **安全校验** - 输入内容安全过滤，防止恶意输入
- 🔧 **工具扩展** - 支持 MCP 协议，易于集成外部工具和 API
- ⚡ **响应式架构** - 基于 Reactor 实现高性能异步处理

## 🛠️ 技术栈

| 技术                    | 版本          | 说明        |
| --------------------- | ----------- | --------- |
| Spring Boot           | 3.5.3       | 应用框架      |
| Java                  | 21          | 编程语言      |
| LangChain4j           | 1.1.0       | AI 应用开发框架 |
| 阿里通义千问                | qwen-max    | 大语言模型     |
| LangChain4j DashScope | 1.1.0-beta7 | 阿里云模型接入   |
| Project Reactor       | -           | 响应式编程     |

## 📋 项目结构

```
ai-code-helper/
├── src/main/java/com/itxiaojiang/aicodehelper/
│   ├── ai/                    # AI 服务核心接口
│   │   ├── AiCodeHelper.java
│   │   ├── AiCodeHelperService.java
│   │   └── AiCodeHelperServiceFactory.java
│   ├── config/                # 配置类
│   │   └── CorsConfig.java
│   ├── controller/            # REST API 控制器
│   │   └── AiController.java
│   ├── guardrail/             # 输入安全校验
│   │   └── SafeInputGuardrail.java
│   ├── listener/              # 模型监听配置
│   │   └── ChatModelListenerConfig.java
│   ├── mcp/                   # MCP 协议配置
│   │   └── McpConfig.java
│   ├── model/                 # 模型配置
│   │   └── QwenChatModelConfig.java
│   ├── rag/                   # RAG 检索配置
│   │   └── RagConfig.java
│   ├── tools/                 # 工具类
│   │   └── InterviewQuestionTool.java
│   └── AiCodeHelperApplication.java
├── src/main/resources/
│   ├── docs/                  # 知识库文档
│   │   ├── Java编程学习路线.md
│   │   ├── 求职指南.md
│   │   ├── 程序员常见面试题.md
│   │   └── 项目学习建议.md
│   ├── application.yaml       # 应用配置
│   └── system-prompt.txt      # 系统提示词
└── pom.xml
```

## 🚀 快速开始

### 环境要求

- JDK 21+
- Maven 3.6+
- 阿里云 DashScope API Key

### 1. 获取 API Key

前往 [阿里云 DashScope](https://dashscope.aliyun.com/) 申请 API Key。

### 2. 配置环境变量

```bash
# Windows
set API_KEY=your_dashscope_api_key

# Linux/Mac
export API_KEY=your_dashscope_api_key
```

### 3. 克隆并运行

```bash
# 克隆项目
git clone <repository-url>
cd ai-code-helper

# 运行应用
./mvnw spring-boot:run

# 或使用 Maven
mvn spring-boot:run
```

### 4. 测试接口

```bash
# 流式对话接口
curl "http://localhost:8081/api/ai/chat?memoryId=1&message=你好，请介绍一下Java"
```

## 📡 API 接口

### 对话接口

```http
GET /api/ai/chat?memoryId={memoryId}&message={message}
```

**参数说明：**

| 参数       | 类型     | 必填 | 说明              |
| -------- | ------ | -- | --------------- |
| memoryId | int    | 是  | 对话记忆 ID，用于保持上下文 |
| message  | string | 是  | 用户输入消息          |

**响应：** Server-Sent Events (SSE) 流式文本

## ⚙️ 配置说明

### application.yaml

```yaml
server:
  port: 8081
  servlet:
    context-path: /api

langchain4j:
  community:
    dashscope:
      chat-model:
        model-name: qwen-max
        api-key: ${API_KEY}
      streaming-chat-model:
        model-name: qwen-max
        api-key: ${API_KEY}
      embedding-model:
        model-name: text-embedding-v4
        api-key: ${API_KEY}
```

### 本地配置

创建 `application-local.yaml` 覆盖默认配置（已添加到 .gitignore）：

```yaml
langchain4j:
  community:
    dashscope:
      chat-model:
        api-key: your_actual_api_key_here
```

## 🧪 测试

```bash
# 运行单元测试
./mvnw test
```

## 📚 知识库文档

项目内置以下 Markdown 格式知识库：

- **Java编程学习路线.md** - Java 技术栈学习路径
- **求职指南.md** - 程序员求职技巧和建议
- **程序员常见面试题.md** - 高频面试题目整理
- **项目学习建议.md** - 实战项目学习指导

## 🔒 安全特性

- 输入内容安全校验（`SafeInputGuardrail`）
- API Key 环境变量管理
- CORS 跨域配置

## 📝 日志与监控

- 集成 Lombok Slf4j 日志
- 模型调用监听器（`ChatModelListenerConfig`）

## 🤝 贡献指南

欢迎提交 Issue 和 Pull Request！

## 📄 许可证

[MIT License](LICENSE)

***

> 💡 **提示**：本项目仅供学习和参考使用，生产环境部署请根据实际情况调整安全配置。
