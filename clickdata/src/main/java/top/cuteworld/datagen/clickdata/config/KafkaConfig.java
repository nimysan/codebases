package top.cuteworld.datagen.clickdata.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import top.cuteworld.datagen.clickdata.model.UserActionEmitter;
import top.cuteworld.datagen.clickdata.model.UserBehaviorItem;

import java.util.function.Function;

@Slf4j
@Configuration
public class KafkaConfig {

    private final String TOPIC_NAME = "flink-user-behavior-data";

    @Bean
    public NewTopic topic() {
        return TopicBuilder.name(TOPIC_NAME)
                .partitions(10)
                .replicas(1)
                .build();
    }


    @Bean
    public UserActionEmitter emitter(KafkaTemplate<String, UserBehaviorItem> kafkaTemplate) {
        UserActionEmitter userActionEmitter = new UserActionEmitter(new Function<UserBehaviorItem, Void>() {
            @Override
            public Void apply(UserBehaviorItem userBehaviorItem) {
                kafkaTemplate.send(TOPIC_NAME, userBehaviorItem);
                return null;
            }

        });
        userActionEmitter.batchEmit();
        return userActionEmitter;
    }

    @Bean
    public ApplicationRunner runner(KafkaTemplate<String, String> template) {
        return args -> {
            template.send(TOPIC_NAME, "test");
        };
    }
}
