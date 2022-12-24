# IDEA

## IDEA Spring依赖包找不到

```bash
#更新idea配置文件
mvn -U idea:idea
```

## 本地连接VPC内MSK

```bash
#挑选其中一个broker做隧道连接
b-3.rdskafkareplication.qh77pm.c1.kafka.us-east-1.amazonaws.com:9092
ssh -i us-east-1.pem -L 9092:b-3.rdskafkareplication.qh77pm.c1.kafka.us-east-1.amazonaws.com:9092 ec2-user@54.152.225.130
#然后在本地localhost:9092
```
### 安装和访问kafka管理界面

```bash
ssh -i us-east-1.pem -L 9000:54.152.225.130:9000 ec2-user@54.152.225.130
```



## 连接MSK错误排除

[Producer clientId=producer-1] Error while fetching metadata with correlation id 143 :
{flink-user-behavior-data=UNKNOWN_TOPIC_OR_PARTITION}

