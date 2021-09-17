package vn.vnpay.netty.server.server;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Project: netty-spring
 * Package: com.server.server
 * Author: zovivo
 * Date: 9/14/2021
 * Created with IntelliJ IDEA
 */

@Component
public class ServerCounter {

    private final AtomicInteger channelConnects;
    private final AtomicInteger channelDisconnects;

    public ServerCounter() {
        this.channelConnects = new AtomicInteger(0);
        this.channelDisconnects = new AtomicInteger(0);
    }

    private void reset() {
        this.channelConnects.set(0);
        this.channelDisconnects.set(0);
    }

    public void clear() {
        this.reset();
    }

    public int getChannelConnects() {
        return this.channelConnects.get();
    }

    public int incrementChannelConnectsAndGet() {
        return this.channelConnects.incrementAndGet();
    }

    public int getChannelDisconnects() {
        return this.channelDisconnects.get();
    }

    public int incrementChannelDisconnectsAndGet() {
        return this.channelDisconnects.incrementAndGet();
    }

    @Override
    public String toString() {
        StringBuilder to = new StringBuilder();
        to.append("[channelConnects=");
        to.append(getChannelConnects());
        to.append(" channelDisconnects=");
        to.append(getChannelDisconnects());
        to.append("]]");
        return to.toString();
    }

}
