package top.cuteworld.datagen.clickdata.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class UserBehaviorItem {
    private Date eventTime;
    private String productId;
    private String userId;
    private String action;
}


