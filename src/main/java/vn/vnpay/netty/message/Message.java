package vn.vnpay.netty.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.vnpay.netty.constant.State;

import java.sql.Timestamp;

/**
 * Project: netty-spring
 * Package: com.server.model
 * Author: zovivo
 * Date: 9/15/2021
 * Created with IntelliJ IDEA
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Message {

    protected String requestId;
    protected Timestamp beginProcessTime;
    protected String channelId;
    protected State state;
}
