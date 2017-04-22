package ru.zapovednik;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.webapp.WebAppContext;

import java.util.Arrays;


public class Launcher {

    public static final String clientRoot = "./../wb-client/dist";

    public static void main(String[] args) throws Exception {
        String port = System.getProperty("jetty.port");
        Server server = new Server(port != null ? Integer.valueOf(port) : 18080);

        WebAppContext webappContext = new WebAppContext();
        webappContext.setDescriptor("./src/main/webapp/WEB-INF/web.xml");
        webappContext.setOverrideDescriptors(Arrays.asList("./src/main/webapp/WEB-INF/web.xml"));
        webappContext.setResourceBase("./src/main/webapp/");
        webappContext.setContextPath("/");
        webappContext.setParentLoaderPriority(true);

/*
        SessionHandler sessionHandler = new SessionHandler();
        HashSessionManager hashSessionManager = new HashSessionManager();
        hashSessionManager.setSavePeriod(5);
        hashSessionManager.setIdleSavePeriod(5);
        hashSessionManager.setMaxInactiveInterval(120);
        hashSessionManager.setStoreDirectory(new File((System.getProperty("user.home")), "jetty"));
        sessionHandler.setSessionManager(hashSessionManager);
        webappContext.setSessionHandler(sessionHandler);
*/

        ContextHandlerCollection handlers = new ContextHandlerCollection();
        handlers.setHandlers(new Handler[]{
                resourceHandler("/wb/static/rainbow/client", clientRoot),
                webappContext,
        });

        server.setHandler(handlers);

        server.start();
    }

    private static ContextHandler resourceHandler(String contextPath, String resourceBase) {
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase(resourceBase);
        ContextHandler contextHandler = new ContextHandler(contextPath);
        contextHandler.setHandler(resourceHandler);
        return contextHandler;
    }
}
