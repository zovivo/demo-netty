package vn.vnpay.netty.server.configuration;

/**
 * Project: netty-spring
 * Package: com.server.configuration
 * Author: zovivo
 * Date: 9/14/2021
 * Created with IntelliJ IDEA
 */
public class ServerConfig {

    public static int MAX_FRAME_LENGTH = 256 * 1024;
    public static String PIPELINE_SERVER_CONNECTOR_NAME = "serverConnector";
    public static String LOG_TOKEN_KEY = "tokenKey";

}
