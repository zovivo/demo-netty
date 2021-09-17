package vn.vnpay.netty.util;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.packager.GenericPackager;
import vn.vnpay.netty.message.PaymentMessage;
import vn.vnpay.netty.model.Payment;

/**
 * Project: demo-netty
 * Package: vn.vnpay.netty.util
 * Author: zovivo
 * Date: 9/17/2021
 * Created with IntelliJ IDEA
 */
public class MessageUtils {

    private static GenericPackager packager = null;
    private static final ISOMsg isoMsg = new ISOMsg();

    static {
        try {
            packager = new GenericPackager("configs/iso/ISOPackage.xml");
            isoMsg.setPackager(packager);
        } catch (ISOException e) {
            e.printStackTrace();
        }
    }



    public static byte[] pack(PaymentMessage paymentMessage) throws ISOException {
        byte[] packedMessage;
        isoMsg.setMTI("0200");
        isoMsg.set(3, "000010");
        isoMsg.set(4, "1500");
        isoMsg.set(7, "1206041200");
        isoMsg.set(11, "000001");
        isoMsg.set(41, "12340001");
        isoMsg.set(49, "840");
        packedMessage = isoMsg.pack();
        return packedMessage;
    }

    public static PaymentMessage unpack(byte[] packedMessage) throws ISOException {
        Payment payment = new Payment();
        isoMsg.unpack(packedMessage);
        payment.setRealAmount(isoMsg.getString(4));
        PaymentMessage paymentMessage = new PaymentMessage(payment);
        return paymentMessage;
    }

}
