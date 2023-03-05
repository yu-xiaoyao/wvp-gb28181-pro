package com.genersoft.iot.vmp.ext;

import org.jetbrains.annotations.NotNull;
import org.springframework.core.Ordered;

/**
 * @author kerryzhang on 23/3/5
 */

public interface StreamUrlHandler extends Ordered, Comparable<StreamUrlHandler> {

    @Override
    default int getOrder() {
        return 0;
    }

    @Override
    default int compareTo(@NotNull StreamUrlHandler o) {
        return Integer.compare(getOrder(), o.getOrder());
    }

    /**
     * 支持些协议
     *
     * @param url
     * @return
     */
    boolean supportSchema(String url);

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
    String handleUrl(String app, String stream, String url, boolean enableAudio, boolean enableMp4, String rtpType);

}
