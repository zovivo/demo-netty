package vn.vnpay.netty.server.configuration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import vn.vnpay.common.util.CommonUtils;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

/**
 * Project: demo-netty
 * Package: vn.vnpay.netty.server.configuration
 * Author: zovivo
 * Date: 9/23/2021
 * Created with IntelliJ IDEA
 */

public class TransactionTypeComponent {

    private static final Logger logger = LogManager.getLogger(TransactionTypeComponent.class);
    private final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

    @Value("${spring.transaction-config.location}")
    private String path;
    private Map<String, Map<String, String>> transactionTypes;

    public TransactionTypeComponent(Resource resource) throws IOException {
        Map<String, Map<String, Map<String, String>>> transactionTypeMap = mapper.readValue(resource.getInputStream(), new TypeReference<Map<String, Map<String, Map<String, String>>>>() {
        });
        this.transactionTypes = transactionTypeMap.get(ServerConfig.TRANSACTION_TYPE);
        logger.debug("transactionTypes: {}", CommonUtils.parseObjectToString(transactionTypes));
    }

    public Map<String, Map<String, String>> getTransactionTypes() {
        return transactionTypes;
    }

    private void setTransactionTypes(Map<String, Map<String, String>> transactionTypes) {
        this.transactionTypes = transactionTypes;
    }

    /**
     * lấy transactionType theo transactionCode và messageType
     *
     * @param transactionCode {@link String}
     * @param messageType     {@link String}
     * @return transactionType {@link String}
     */
    public String getTransactionType(String transactionCode, String messageType) {
        return this.transactionTypes.get(transactionCode).get(messageType);
    }

    //    @Scheduled(fixedRate = 10000l)
    private void reloadPartnerProperties() throws IOException {
        Map<String, Map<String, Map<String, String>>> transactionTypeMap = mapper.readValue(new URL(path), new TypeReference<Map<String, Map<String, Map<String, String>>>>() {
        });
        this.transactionTypes = transactionTypeMap.get(ServerConfig.TRANSACTION_TYPE);
        logger.debug("transactionTypes: {}", CommonUtils.parseObjectToString(transactionTypes));
    }

}
