package ek.kotsu.extractor;

import ek.kotsu.basic.exception.ContentTypeNotSupportedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;
import java.util.Optional;

/**
 * Created by Eric Kim on 16/2/24.
 */
@Component
public class ExtractorManager {

    @Autowired
    private List<Extractor> extractors;

    public Optional<String> extract(NativeWebRequest webRequest, String paramName) throws ContentTypeNotSupportedException {
        // first extract from url parameter
        Optional<String> urlParamResult = new UrlParamExtractor().extract(webRequest, paramName);
        if (null != urlParamResult) {
            return urlParamResult;
        }
        // find corresponding extractor
        String contentType = webRequest.getHeader("Content-Type");
        if (null != contentType) {
            for (Extractor extractor : extractors) {
                if (extractor.support(contentType)) {
                    return extractor.extract(webRequest, paramName);
                }
            }
            throw new ContentTypeNotSupportedException("Content-Type " + contentType + " not supported.");
        }
        return null;
    }

}
