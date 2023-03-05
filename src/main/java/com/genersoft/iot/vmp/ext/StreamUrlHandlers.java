package com.genersoft.iot.vmp.ext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author kerryzhang on 2023/03/05
 */

@Component
public class StreamUrlHandlers implements BeanPostProcessor {
    private static final Logger logger = LoggerFactory.getLogger(StreamUrlHandlers.class);
    private final Set<StreamUrlHandler> handlerSet = new TreeSet<>();


    /**
     * handle
     *
     * @param app
     * @param stream
     * @param url
     * @param enableAudio
     * @param enableMp4
     * @param rtpType
     * @return
     */
    public String handleUrl(String app, String stream, String url, boolean enableAudio, boolean enableMp4, String rtpType) {
        for (StreamUrlHandler streamUrlHandler : handlerSet) {
            boolean supportSchema = streamUrlHandler.supportSchema(url);
            if (supportSchema) {
                return streamUrlHandler.handleUrl(app, stream, url, enableAudio, enableMp4, rtpType);
            }
        }
        // 默认返回原始值
        return url;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof StreamUrlHandler) {
            logger.info("postProcessAfterInitialization. bean = {}, beanName = {}", bean, beanName);
            boolean add = handlerSet.add((StreamUrlHandler) bean);
            if (!add) {
                throw new RuntimeException("StreamUrlHandler duplication for bean name = " + bean.getClass());
            }

        }
        return bean;
    }
}
