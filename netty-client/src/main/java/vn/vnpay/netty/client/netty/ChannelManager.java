package vn.vnpay.netty.client.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import vn.vnpay.common.model.Payment;
import vn.vnpay.common.util.CommonUtils;
import vn.vnpay.netty.client.handler.ClientHandler;
import vn.vnpay.netty.client.handler.RequestsHandler;

import java.util.Arrays;
import java.util.List;

/**
 * Project: netty-spring
 * Package: com.client.configuration
 * Author: zovivo
 * Date: 9/15/2021
 * Created with IntelliJ IDEA
 */
@Component
@PropertySource("classpath:application.properties")
public class ChannelManager {

    @Value("${netty.host}")
    private String host;
    @Value("${netty.tcp-port}")
    private int port;

    @Autowired
    private Bootstrap bootstrap;

    @Value("${app.isoMessage}")
    private String isoMessage;

    @Autowired
    private Environment environment;

    private static final Logger logger = LogManager.getLogger(ChannelManager.class);

    public void createChannel(ChannelHandler channelHandler) throws Exception {
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new ByteArrayEncoder(), new ByteArrayDecoder(), channelHandler);
            }
        });
        ChannelFuture f = bootstrap.connect(host, port).sync();
        f.channel().closeFuture().sync();
    }


    @Scheduled(fixedRate = 1000000000l)
    public void connectAndSend() throws Exception {
        String isoMessage2 = "A4M050000210F23846112AA080080000000006C400A01068686868680310101000000000000080503235771966710245408050000000000D0000000006686868116868686868=000001125991000010899999999VIETCOMBANK                                                 000704Hanoi                                                                                     00120210805VBKN      VBKN000000000000001          00000000000000007041000VBA&RD    001315002810001360600474500000600474500000704704000000000000000000000000000000006100000010001***                                         018TAD=Nguyen Thi Hoa";
        createChannel(new ClientHandler(this.isoMessage));
//        String isoMessage3 = "A4M050000200F638461128A0A00800000000045400A013035100***19960430001000003600000000003600000080503240771967010250408050000010000D000000000668686814035100***1996=00000112599208DIGIM000VIETCOMBANK                                                 000704Hanoi                                                                                     00120210805VBKN      VBKN000000000000001          0000000000000000704704BARDBARD      1315002810001360007040000000000000000036000006100000000000000150MBVCB.700050928.060798.QUA T L chuyen tien.CT tu 0351000771996 QUA T L toi 1500281000136 Nguyen Thi Hoa  AGRIBANK  Nong nghiep va phat trien nong thon10001***                                         019ET=0805032504060798";
//        createChannel(new ClientHandler(isoMessage3));
    }

    @Scheduled(fixedRate = 10000l)
    public void test() throws Exception {
        logger.info("===Running Application=== port: {} ", environment.getProperty("server.port"));
    }

}