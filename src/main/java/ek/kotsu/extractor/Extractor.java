package ek.kotsu.extractor;

import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;

/**
 * Created by Eric Kim on 16/2/24.
 */
public interface Extractor {

    boolean support(String contentType);

    Optional<String> extract(NativeWebRequest webRequest, String paramName);

}
