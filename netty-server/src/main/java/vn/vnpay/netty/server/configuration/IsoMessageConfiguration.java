package vn.vnpay.netty.server.configuration;

import org.jpos.iso.ISOException;
import org.jpos.iso.packager.GenericPackager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import vn.vnpay.netty.server.util.MessagePackager;

import java.io.IOException;

/**
 * Project: demo-netty
 * Package: vn.vnpay.netty.server.configuration
 * Author: zovivo
 * Date: 9/24/2021
 * Created with IntelliJ IDEA
 */

@Configuration
public class IsoMessageConfiguration {

    @Value("${spring.iso-config.location}")
    private String path;

    @Autowired
    private ResourceLoader resourceLoader;

    @Bean("messagePackager")
    public MessagePackager messagePackager() throws IOException, ISOException {
        GenericPackager packager = new GenericPackager(resourceLoader.getResource(path).getInputStream());
        return new MessagePackager(packager);
    }

}
