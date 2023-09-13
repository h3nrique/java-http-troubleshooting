package br.com.fabricads.poc;

import static spark.Spark.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.stream.Collectors;

import spark.Request;
import spark.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    
    private final static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        port(8080);
        get("/*", Main::handle, gson::toJson);
        post("/*", Main::handle, gson::toJson);
        put("/*", Main::handle, gson::toJson);
        delete("/*", Main::handle, gson::toJson);
        options("/*", Main::handle, gson::toJson);
        head("/*", Main::handle, gson::toJson);
        trace("/*", Main::handle, gson::toJson);
        connect("/*", Main::handle, gson::toJson);
        patch("/*", Main::handle, gson::toJson);
    }

    private static Object handle(Request request, Response response) throws Exception {
        HashMap<String, Object> responseMap = new HashMap<String, Object>();
        responseMap.put("requestMethod", request.requestMethod());
        responseMap.put("scheme", request.scheme());
        responseMap.put("host", request.host());
        responseMap.put("pathInfo", request.pathInfo());
        responseMap.put("userAgent", request.userAgent());
        responseMap.put("serverPort", request.port());
        responseMap.put("remoteAddr", request.ip());
        responseMap.put("requestURL", request.url());
        responseMap.put("servletPath", request.servletPath());
        responseMap.put("contextPath", request.contextPath());
        responseMap.put("contentType", request.contentType());
        responseMap.put("attributes", request.attributes().stream()
                .collect(Collectors.toMap(a -> a, a -> request.attribute(a))));
        responseMap.put("headers", request.headers().stream()
                .collect(Collectors.toMap(h -> h, h -> request.headers(h))));
        responseMap.put("queryParams", request.queryParams().stream()
                .collect(Collectors.toMap(qp -> qp, qp -> request.queryParams(qp))));
        responseMap.put("body", request.body());
        responseMap.keySet()
            .stream()
            .forEach(key -> logger.info("{} :: {}", key, responseMap.get(key)));
        response.status(200);
        response.header("Content-Type", "application/json");
        return Collections.singletonMap("request",  responseMap);
    }
}
