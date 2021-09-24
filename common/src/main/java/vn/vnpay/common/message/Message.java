package vn.vnpay.common.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.vnpay.common.constant.State;

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
    protected String channelId;
    protected State state;

}
