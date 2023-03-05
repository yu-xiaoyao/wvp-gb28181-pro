package com.genersoft.iot.vmp.ext;

import org.springframework.stereotype.Component;

import java.net.URI;

/**
 * @author kerryzhang on 2023/03/05
 */

@Component
public class ApiPlayUrlWvpExtension implements ApiPlayUrlExtension {
    @Override
    public String apiName() {
        return "wvp";
    }

    @Override
    public String getUrl(String app, String stream, URI sourceUri, boolean enableAudio, boolean enableMp4, String rtpType) {
        return null;
    }
}
