package ek.kotsu.extractor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

/**
 * Created by Eric Kim on 16/3/24.
 */
@Component
public class JsonParamExtractor implements Extractor {

    private static final String REQUEST_BODY = "REQUEST_BODY";

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean support(String contentType) {
        return contentType.toLowerCase().contains("json");
    }

    @Override
    public Optional<String> extract(NativeWebRequest webRequest, String paramName) {
        HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        try {
            String body = this.getRequestBodyAsString(servletRequest);
            JsonNode jsonNode = objectMapper.readTree(body);
            if (!jsonNode.has(paramName)) {
                return null;
            } else if (jsonNode.get(paramName).isNull()) {
                return Optional.ofNullable(null);
            } else {
                return Optional.of(jsonNode.get(paramName).asText());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getRequestBodyAsString(HttpServletRequest servletRequest) throws IOException {
        String jsonBody = (String) servletRequest.getAttribute(REQUEST_BODY);
        if (jsonBody == null) {
            String body = servletRequest.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
            servletRequest.getReader().close();
            servletRequest.setAttribute(REQUEST_BODY, body);
            return body;
        }
        return jsonBody;
    }

}
