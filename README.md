# Lightweight-RPC-Framework
轻量级rpc框架

## 架构图
![image](https://user-images.githubusercontent.com/56989167/124855860-287cd180-dfdc-11eb-95ac-61311a7ad742.png)


## 简要流程图
![V1 0 RPC流程](https://user-images.githubusercontent.com/56989167/124855056-f61ea480-dfda-11eb-8787-08e0663e96b6.png)

## 自定义数据格式
```
+---------------+---------------+-----------------+-------------+
|  Magic Number |  Package Type | Serializer Type | Data Length |
|    4 bytes    |    4 bytes    |     4 bytes     |   4 bytes   |
+---------------+---------------+-----------------+-------------+
|                          Data Bytes                           |
|                   Length: ${Data Length}                      |
+---------------------------------------------------------------+
```
Magic Number：标识这是我的协议包<br>
Package Type：标明这是一个调用请求还是调用相应<br>
Serializer Type：标明使用的序列化器，这个客户端和服务端应当保持一致<br>
Data Length：实际数据的长度，设置这个字段主要防止粘包<br>
Data Bytes：经过序列化后的实际数据<br>


## 一些概念的解释
  <a href="https://www.nowcoder.com/discuss/588903?channel=-1&source_id=profile_follow_post_nctrack">查看这里</a>
