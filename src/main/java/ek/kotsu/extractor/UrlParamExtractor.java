package ek.kotsu.extractor;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * Created by Eric Kim on 16/2/24.
 */
@Component
public class UrlParamExtractor implements Extractor {

    @Override
    public boolean support(String contentType) {
        return true;
    }

    @Override
    public Optional<String> extract(NativeWebRequest webRequest, String paramName) {
        String param = webRequest.getNativeRequest(HttpServletRequest.class).getParameter(paramName);
        return null == param ? null : Optional.of(param);
    }
}
