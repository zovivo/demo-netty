package vn.vnpay.netty.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum State {

    SUCCESS("success"),
    IN_PROGRESS("in progress"),
    FAIL("fail"),
    ;

    private final String description;

}
