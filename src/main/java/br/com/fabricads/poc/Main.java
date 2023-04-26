package br.com.fabricads.poc;

import static spark.Spark.*;

import spark.Request;
import spark.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    
    private final static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        port(8080);
        get("/*", Main::handle);
        post("/*", Main::handle);
        put("/*", Main::handle);
        delete("/*", Main::handle);
        options("/*", Main::handle);
        head("/*", Main::handle);
        trace("/*", Main::handle);
        connect("/*", Main::handle);
        patch("/*", Main::handle);
    }

    private static Object handle(Request request, Response response) throws Exception {
        logger.info("requestMethod :: {}", request.requestMethod());
        logger.info("scheme :: {}", request.scheme());
        logger.info("host :: {}", request.host());
        logger.info("pathInfo :: {}", request.pathInfo());
        logger.info("userAgent :: {}", request.userAgent());
        logger.info("serverPort :: {}", request.port());
        logger.info("remoteAddr :: {}", request.ip());
        logger.info("requestURL :: {}", request.url());
        logger.info("servletPath :: {}", request.servletPath());
        logger.info("contextPath :: {}", request.contextPath());
        logger.info("contentType :: {}", request.contentType());
        request.attributes().stream()
            .forEach(a -> logger.info("attribute '{}' -> {}", a, request.attribute(a)));
        request.headers().stream()
            .forEach(h -> logger.info("header '{}' -> {}", h, request.headers(h)));
            request.queryParams().stream()
                .forEach(p -> logger.info("queryParam '{}' -> {}", p, request.queryParams(p)));
        logger.info("body :: {}", request.body());
        response.status(204);
        return "";
    }
}
