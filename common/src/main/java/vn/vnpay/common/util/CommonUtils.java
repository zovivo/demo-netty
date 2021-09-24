package vn.vnpay.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class CommonUtils {

    public static final String REQUEST_ID = "request_id";
    private static final Logger logger = LogManager.getLogger(CommonUtils.class);
    private static final Gson gson = new Gson();
    private static final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    private static final Date date = new Date();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Timestamp getCurrentTime() {
        timestamp.setTime(System.currentTimeMillis());
        return timestamp;
    }

    public static String getTimeByFormat(String formatDate, long time) {
        date.setTime(time);
        String dateStr = new SimpleDateFormat(formatDate).format(date);
        return dateStr;
    }

    public static byte[] convertStringToBytes(String input) {
        byte[] bytes = new byte[input.length()];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (int) input.charAt(i);
        }
        return bytes;
    }

    public static String parseObjectToString(Object object) {
        return gson.toJson(object);
    }

    public static <T> T parseStringToObject(String json, Class<T> classObject) {
        try {
            return gson.fromJson(json, classObject);
        } catch (Exception e) {
            logger.warn("parse Exception: ", e);
            return null;
        }
    }

    public static String convertBytesToString(byte[] bytes) {
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public static byte[] removeHeaderMessage(byte[] messageByte) {
        String message = convertBytesToString(messageByte);
        return convertStringToBytes(message.substring(8));
    }

    public static String randomID(){
        return UUID.randomUUID().toString();
    }

    public static <T> T convertData(Object obj, Class<T> clazz) {
        return objectMapper.convertValue(obj, clazz);
    }

}
