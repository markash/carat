package za.co.yellowfire.carat.jetty;

//import org.eclipse.jetty.deploy.DeploymentManager;
//import org.eclipse.jetty.deploy.PropertiesConfigurationManager;
//import org.eclipse.jetty.deploy.providers.WebAppProvider;
//import org.eclipse.jetty.jmx.MBeanContainer;
//import org.eclipse.jetty.server.*;
//import org.eclipse.jetty.server.handler.*;
//import org.eclipse.jetty.util.resource.Resource;
//import org.eclipse.jetty.util.resource.ResourceCollection;
//import org.eclipse.jetty.util.ssl.SslContextFactory;
//import org.eclipse.jetty.util.thread.QueuedThreadPool;
//import org.eclipse.jetty.util.thread.ScheduledExecutorScheduler;
//import org.eclipse.jetty.webapp.WebAppContext;
//import org.eclipse.jetty.webapp.WebXmlConfiguration;
//import org.eclipse.jetty.xml.XmlConfiguration;
//
//import java.io.File;
//import java.lang.management.ManagementFactory;

public class Main {
    private static final String BASE_PATH = "./src/main/webapp/WEB-INF";
    private static final String TARGET_PATH = "./target";

    public static void main(String[] args) throws Exception {

//
//        QueuedThreadPool threadPool = new QueuedThreadPool();
//        threadPool.setMaxThreads(500);
//
//        Server server = new Server(threadPool);
//        server.addBean(new ScheduledExecutorScheduler());
//
//        HttpConfiguration http_config = new HttpConfiguration();
//        http_config.setSecureScheme("https");
//        http_config.setSecurePort(8443);
//        http_config.setOutputBufferSize(32768);
//        http_config.setRequestHeaderSize(8192);
//        http_config.setResponseHeaderSize(8192);
//        http_config.setSendServerVersion(true);
//        http_config.setSendDateHeader(false);
//
//        HandlerCollection handlers = new HandlerCollection();
//        ContextHandlerCollection contexts = new ContextHandlerCollection();
//        handlers.setHandlers(new Handler[] { contexts, new DefaultHandler() });
//        server.setHandler(handlers);
//
//        server.setDumpAfterStart(false);
//        server.setDumpBeforeStop(false);
//        server.setStopAtShutdown(true);
//
//        //MBeanContainer mbContainer=new MBeanContainer(ManagementFactory.getPlatformMBeanServer());
//        //server.addBean(mbContainer);
//
//        ServerConnector http = new ServerConnector(server,new HttpConnectionFactory(http_config));
//        http.setPort(8080);
//        http.setIdleTimeout(30000);
//        server.addConnector(http);
//
////        SslContextFactory sslContextFactory = new SslContextFactory();
////            sslContextFactory.setKeyStorePath(jetty_home + "/etc/keystore");
////            sslContextFactory.setKeyStorePassword("OBF:1vny1zlo1x8e1vnw1vn61x8g1zlu1vn4");
////            sslContextFactory.setKeyManagerPassword("OBF:1u2u1wml1z7s1z7a1wnl1u2g");
////            sslContextFactory.setTrustStorePath(jetty_home + "/etc/keystore");
////            sslContextFactory.setTrustStorePassword("OBF:1vny1zlo1x8e1vnw1vn61x8g1zlu1vn4");
////            sslContextFactory.setExcludeCipherSuites(
////                "SSL_RSA_WITH_DES_CBC_SHA",
////                "SSL_DHE_RSA_WITH_DES_CBC_SHA",
////                "SSL_DHE_DSS_WITH_DES_CBC_SHA",
////                "SSL_RSA_EXPORT_WITH_RC4_40_MD5",
////                "SSL_RSA_EXPORT_WITH_DES40_CBC_SHA",
////                "SSL_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA",
////                "SSL_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA");
//
//        // SSL HTTP Configuration
//        HttpConfiguration https_config = new HttpConfiguration(http_config);
//        https_config.addCustomizer(new SecureRequestCustomizer());
//
//        // SSL Connector
////        ServerConnector sslConnector = new ServerConnector(server,
////            new SslConnectionFactory(sslContextFactory,"http/1.1"),
////            new HttpConnectionFactory(https_config));
////        sslConnector.setPort(8443);
////        server.addConnector(sslConnector);
//
//
//
//        WebAppContext webapp = new WebAppContext();
//        webapp.setContextPath("/");
//        webapp.setServerClasses(new String[]{"org.eclipse.jetty.servlet.ServletContextHandler.Decorator"});
//        webapp.setWelcomeFiles(new String[] {"index.jsf"});
//        webapp.setResourceAlias("/WEB-INF/classes/", "/classes/");
//        webapp.setOverrideDescriptor(BASE_PATH + "/web-add.xml");
//        webapp.setBaseResource(
//                new ResourceCollection(
//                        new String[] {"./src/main/webapp", "./target"}));
//
//        XmlConfiguration config = new XmlConfiguration(new File(BASE_PATH + "/jetty-env.xml").toURI().toURL());
//        config.configure(webapp);
//        config = new XmlConfiguration(new File(BASE_PATH + "/jetty-context.xml").toURI().toURL());
//        config.configure(webapp);
//        config = new XmlConfiguration(new File(BASE_PATH + "/jetty-jmx.xml").toURI().toURL());
//        config.configure(server);
//
//        server.setHandler(webapp);
//
//
////        // === jetty-deploy.xml ===
////        DeploymentManager deployer = new DeploymentManager();
////        deployer.setContexts(contexts);
////        deployer.setContextAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern",".*/servlet-api-[^/]*\\.jar$");
////
////        WebAppProvider webapp_provider = new WebAppProvider();
////        webapp_provider.setMonitoredDirName(TARGET_PATH + "/webapps");
////        //webapp_provider.setDefaultsDescriptor(TARGET_PATH + "/etc/webdefault.xml");
////        webapp_provider.setScanInterval(1);
////        webapp_provider.setExtractWars(true);
////        webapp_provider.setConfigurationManager(new PropertiesConfigurationManager());
////
////        deployer.addAppProvider(webapp_provider);
////        server.addBean(deployer);
//
//        // === jetty-stats.xml ===
//        StatisticsHandler stats = new StatisticsHandler();
//        stats.setHandler(server.getHandler());
//        server.setHandler(stats);
//
//        // === jetty-requestlog.xml ===
//        NCSARequestLog requestLog = new NCSARequestLog();
//        requestLog.setFilename("/logs/yyyy_mm_dd.request.log");
//        requestLog.setFilenameDateFormat("yyyy_MM_dd");
//        requestLog.setRetainDays(90);
//        requestLog.setAppend(true);
//        requestLog.setExtended(true);
//        requestLog.setLogCookies(false);
//        requestLog.setLogTimeZone("GMT");
//        RequestLogHandler requestLogHandler = new RequestLogHandler();
//        requestLogHandler.setRequestLog(requestLog);
//        handlers.addHandler(requestLogHandler);
//
//        // === jetty-lowresources.xml ===
//        LowResourceMonitor lowResourcesMonitor=new LowResourceMonitor(server);
//        lowResourcesMonitor.setPeriod(1000);
//        lowResourcesMonitor.setLowResourcesIdleTimeout(200);
//        lowResourcesMonitor.setMonitorThreads(true);
//        lowResourcesMonitor.setMaxConnections(0);
//        lowResourcesMonitor.setMaxMemory(0);
//        lowResourcesMonitor.setMaxLowResourcesTime(5000);
//        server.addBean(lowResourcesMonitor);
//
//        // === test-realm.xml ===
////        HashLoginService login = new HashLoginService();
////        login.setName("Test Realm");
////        login.setConfig(jetty_home + "/etc/realm.properties");
////        login.setRefreshInterval(0);
////        server.addBean(login);
//
//        server.start();
//        server.join();
    }
}
