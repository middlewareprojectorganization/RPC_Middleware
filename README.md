# 中间件项目管控平台
## 1. 简介
该项目采用自研中间件，借鉴dubbo代码，重构并升级dubbo的功能，实现自己的需求，该项目实现了自定义注册中心config-center,以及动态配置持久化到数据库，采用Netty实现动态配置拉取，监听，以及变更刷新。实现了注册到config-center。config-center实现了灵活的配置管理，粒度细化到应用，可根据自己实现，细化到机器。config-center实现了注册中心，配置推送，配置动态绑定，服务监控中心，以及限流规则动态配置推送等功能。


## 2.如何使用
