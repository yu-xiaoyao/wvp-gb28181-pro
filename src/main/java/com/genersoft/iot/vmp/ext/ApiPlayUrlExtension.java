package com.genersoft.iot.vmp.ext;

import java.net.URI;

/**
 * @author kerryzhang on 23/3/5
 */

public interface ApiPlayUrlExtension {
    /**
     * api name
     *
     * @return
     */
    String apiName();

    /**
     * get
     *
     * @param app
     * @param stream
     * @param sourceUri
     * @param enableAudio
     * @param enableMp4
     * @param rtpType
     * @return
     */
    String getUrl(String app, String stream, URI sourceUri, boolean enableAudio, boolean enableMp4, String rtpType);
}
