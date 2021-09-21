package vn.vnpay.netty.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import vn.vnpay.netty.client.constant.ClientConstant;
import vn.vnpay.netty.message.TransactionMessage;
import vn.vnpay.netty.model.Payment;
import vn.vnpay.netty.util.CommonUtils;
import vn.vnpay.netty.util.MessageUtils;

import java.util.UUID;

/**
 * Project: netty-spring
 * Package: com.client.handler
 * Author: zovivo
 * Date: 9/15/2021
 * Created with IntelliJ IDEA
 */
@RequiredArgsConstructor
public class ClientHandler extends SimpleChannelInboundHandler<byte[]> {

    private static final Logger logger = LogManager.getLogger(ClientHandler.class);

    private final Payment payment;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String requestId = UUID.randomUUID().toString();
        ThreadContext.put(ClientConstant.LOG_TOKEN_KEY, requestId);
        String isoMessage = "0200F638461128A0A00800000000045400A013035100***19960430001000003600000000003600000080503240771967010250408050000010000D000000000668686814035100***1996=00000112599208DIGIM000VIETCOMBANK                                                 000704Hanoi                                                                                     00120210805VBKN      VBKN000000000000001          0000000000000000704704BARDBARD      1315002810001360007040000000000000000036000006100000000000000150MBVCB.700050928.060798.QUA T L chuyen tien.CT tu 0351000771996 QUA T L toi 1500281000136 Nguyen Thi Hoa  AGRIBANK  Nong nghiep va phat trien nong thon10001***                                         019ET=0805032504060798";
        logger.info("Write to channel: {} message: {}", ctx.channel().id().asLongText(), isoMessage);
        byte[] data = MessageUtils.packMsg(isoMessage);
        ctx.writeAndFlush(data);
        ThreadContext.clearMap();
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, byte[] msg) throws Exception {
        TransactionMessage transactionMessage = MessageUtils.unpackMsg(msg);
        ThreadContext.put(ClientConstant.LOG_TOKEN_KEY, transactionMessage.getProcessingCode());
        logger.info("Response code: {}", transactionMessage.getResponseCode());
        logger.info("Read from channel: {} message: {}", ctx.channel().id().asLongText(), CommonUtils.parseObjectToString(transactionMessage));
        ctx.close();
        ThreadContext.remove(ClientConstant.LOG_TOKEN_KEY);
    }

}
