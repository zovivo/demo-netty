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
//        String requestId = UUID.randomUUID().toString();
//        ThreadContext.put(ClientConstant.LOG_TOKEN_KEY, requestId);
//        Giao dich sign on
//        String isoMessage = "081082200000020000000400000000000000081703414081111400001301";
//        Giao dich IBFT thu huong- van tin
//        String isoMessage = "0210F23846112AA080080000000006C400A01068686868680310101000000000000080503235771966710245408050000000000D0000000006686868116868686868=000001125991000010899999999VIETCOMBANK                                                 000704Hanoi                                                                                     00120210805VBKN      VBKN000000000000001          00000000000000007041000VBA&RD    001315002810001360600474500000600474500000704704000000000000000000000000000000006100000010001***                                         018TAD=Nguyen Thi Hoa";
//        Giao dich IBFT thu huong- ghi co
//        String isoMessage = "0200F638461128A0A00800000000045400A013035100***19960430001000003600000000003600000080503240771967010250408050000010000D000000000668686814035100***1996=00000112599208DIGIM000VIETCOMBANK                                                 000704Hanoi                                                                                     00120210805VBKN      VBKN000000000000001          0000000000000000704704BARDBARD      1315002810001360007040000000000000000036000006100000000000000150MBVCB.700050928.060798.QUA T L chuyen tien.CT tu 0351000771996 QUA T L toi 1500281000136 Nguyen Thi Hoa  AGRIBANK  Nong nghiep va phat trien nong thon10001***                                         019ET=0805032504060798";
//        Giao dich IBFT phat lenh- tru tien
//        String isoMessage = "0210F63866112AA0A0080000000004D600A019999999*********01363690000000001350000000001350000080504000472008211014208056012704000000D00000000110000000000220999999*********0136=00000112599900001081000MB90TWMB                          Hanoi                         00070418 Tran Huu Duc, My Dinh 1, Na100001                        Hanoi, Vietnam                00420210805BARD      BARD                         00000010000000007047041000VBA&RD    13150028100013606004870000006004870000000000000000000000000000000000006100000000000000054DongABank;9704060129837294;Nguyen Thi Hoa chuyen khoan1206970406001***                                         036FN2=BARD\\x10P2=999999*********0136\\x10TC=6";
//        Giao dich IBFT phat lenh- giao dich dao -tru tien
        String isoMessage = "0430F63866112EA0A00800000000045400A019999999*********10713690000000002500000000002500000072104253153594011234307216012704000000D00000000110000000000220999999*********1071=00000112392294033200060081000MB90TWMB                          Hanoi                         00070418 Tran Huu Duc, My Dinh 1, Na100001                        Hanoi, Vietnam                00420210721BARD      BARD                         0000001000000000704704BARDBARD      1316002052210710000000000000000000000000000006100000000000000054DongABank;9704060129837294;HO DANG PHUONG chuyen khoan12001***                                         005ERR=0";
        ThreadContext.put(ClientConstant.LOG_TOKEN_KEY, MessageUtils.unpackMsg(CommonUtils.convertStringToBytes(isoMessage)).getSystemTraceNumber());
        logger.info("Write to channel: {} message: {}", ctx.channel().id().asLongText(), isoMessage);
        byte[] data = MessageUtils.packMsg(isoMessage);
        ctx.writeAndFlush(data);
        ThreadContext.clearMap();
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, byte[] msg) throws Exception {
        TransactionMessage transactionMessage = MessageUtils.unpackMsg(msg);
        ThreadContext.put(ClientConstant.LOG_TOKEN_KEY, transactionMessage.getSystemTraceNumber());
        logger.info("Response code: {}", transactionMessage.getResponseCode());
        logger.info("Response from channel: {} TransactionMessage: {}", ctx.channel().id().asLongText(), CommonUtils.parseObjectToString(transactionMessage));
        ctx.close();
        ThreadContext.remove(ClientConstant.LOG_TOKEN_KEY);
    }

}
