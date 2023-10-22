## Memory API接口开放平台

## 项目概述

### 项目名称

### 使用场景

- 📊 开放平台的接口提供：适用于需要提供 API 接口给其他开发者或应用程序使用的项目。
- 👨‍💻 接口管理和调试：管理员和开发者可以使用该平台进行接口管理、调试和监控。
- 💻 应用程序开发：开发者可以在代码中使用提供的 SDK 快速调用接口，加速应用程序开发。
- 💰 计费与限制管理：适用于需要对接口调用进行计费和限制的项目，提供了灵活的计费和充值系统。

### 主要功能

- 🚀 全栈微服务项目，提供 API 接口供开发者调用的平台，以满足各种应用程序的接口需求。
- 👮‍♂️ 管理员功能：接入和发布接口、统计接口调用情况，方便管理和监控接口的使用情况。
- 👥 用户功能：注册登录并开通接口调用权限、浏览接口、在线调试。使用客户端 SDK，轻松在代码中调用接口。
- 💰 计费系统：用户对每个接口的调用有限制，可以通过登录免费领取次数或充值获取次数。
- 📝 用户上传接口：用户可下载平台提供的 SDK 请求库，封装并发布自己设计的接口，供其他开发者使用。

### 项目启动

- 拉取代码后，应该如何运行该项目？

#### 后端

- 配置Nacos、MySQL、Redis 为本机地址：

```yaml
  # 数据库配置
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/xxx
    username: xxx
    password: xxx
```

```yaml
  # Redis 配置
  redis:
    database: 0
    host: localhost
    port: 6379
    timeout: 5000
    password: Dw990831
```

```yaml
dubbo:
  application:
    name: dubbo-springboot-demo-provider
  protocol:
    name: dubbo
    port: -1
  registry:
    id: nacos-registry
    address: nacos://localhost:8848
```

- 首先在**本地启动 nacos**（bin 目录下执行）：

```
startup.cmd -m standalone
```

- ##### 依次启动 memory-api-backend、gateway、memory-client

#### 前端

- 确保本地 **Node.js 环境配置**完成，版本为 v18.xx
- 修改请求的**后端地址**：

```react
 baseURL: process.env.NODE_ENV === 'production' ? 'http://120.55.62.195:8102' : 'http://localhost:8102',
 withCredentials: true,
```

- 执行以下命令，一键启动前端项目：

```
yarn start:dev
```

## 技术选型

### 后端

- Java Spring Boot 框架
- MySQL 数据库
- Mybatis-Plus 及 MyBatis X 自动生成
- API 签名认证（HTTP 调用）
- Spring Boot Starter（SDK 开发）
- Dubbo 分布式（RPC、Nacos）
- Spring Cloud Gateway 微服务网关
- Swagger + Knife4j 接口文档生成
- Hutool、Apache Common Utils、Gson 等工具库

### 前端

- React 18
- Ant Design Pro 5.x 脚手架
- Ant Design % Procomponents 组件库
- Umi 4 前端框架
- OpenAPI 前端代码生成

## 项目结构

### 架构设计

### 功能模块

#### 接口管理

#### 用户管理

#### 接口调用（在线调试）

## 效果展示

## 项目收获

