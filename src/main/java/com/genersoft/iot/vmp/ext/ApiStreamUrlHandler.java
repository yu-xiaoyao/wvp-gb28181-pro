package com.genersoft.iot.vmp.ext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author kerryzhang on 2023/03/05
 */

@Component
public class ApiStreamUrlHandler implements StreamUrlHandler, BeanPostProcessor {

    private static final Logger logger = LoggerFactory.getLogger(ApiStreamUrlHandler.class);
    private final Map<String, ApiPlayUrlExtension> map = new ConcurrentHashMap<>(4);

    @Override
    public boolean supportSchema(String url) {
        return url.startsWith("api://");
    }

    @Override
    public String handleUrl(String app, String stream, String url, boolean enableAudio, boolean enableMp4, String rtpType) {
        logger.info("handleUrl. app = {}, stream = {}, url = {}, enableAudio = {}, enableMp4 = {}, rtpType = {}", app, stream, url, enableAudio, enableMp4, rtpType);
        URI uri = URI.create(url);
        if (!supportSchema(uri.getScheme())) {
            return null;
        }
        String serviceName = uri.getHost();
        ApiPlayUrlExtension extension = map.get(serviceName);
        if (extension == null) {
            return null;
        }
        return extension.getUrl(app, stream, uri, enableAudio, enableMp4, rtpType);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ApiPlayUrlExtension) {
            register((ApiPlayUrlExtension) bean);
        }
        return bean;
    }

    public void register(ApiPlayUrlExtension extension) {
        ApiPlayUrlExtension put = map.put(extension.apiName(), extension);
        if (put != null) {
            throw new RuntimeException("ApiPlayUrlExtension duplication for key = " + extension.apiName());
        }
    }
}
