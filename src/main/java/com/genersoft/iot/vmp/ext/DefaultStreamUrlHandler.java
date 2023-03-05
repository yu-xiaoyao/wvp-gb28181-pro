package com.genersoft.iot.vmp.ext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author kerryzhang on 2023/03/05
 */

@Component
public class DefaultStreamUrlHandler implements StreamUrlHandler {

    private static final Logger logger = LoggerFactory.getLogger(DefaultStreamUrlHandler.class);

    /**
     * default 这个必须排在最后
     *
     * @return
     */
    @Override
    public int getOrder() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean supportSchema(String url) {
        // 如果到了这个,应该是全部都支持的...
        return true;
    }

    @Override
    public String handleUrl(String app, String stream, String url, boolean enableAudio, boolean enableMp4, String rtpType) {
        // 原样返回
        logger.info("handleUrl. app = {}, stream = {}, url = {}, enableAudio = {}, enableMp4 = {}, rtpType = {}", app, stream, url, enableAudio, enableMp4, rtpType);
        return url;
    }
}
