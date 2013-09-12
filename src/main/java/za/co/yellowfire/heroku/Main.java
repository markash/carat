package za.co.yellowfire.heroku;

import lombok.extern.slf4j.Slf4j;
import org.glassfish.embeddable.*;
import org.glassfish.embeddable.archive.ScatteredArchive;
import org.jooq.tools.StringUtils;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class Main {
    public static void main(String...args) throws Exception {
        String directory = "target/carat/";
        log.info("Deployment directory is {}", directory);

        String webPort = System.getenv("PORT");
        if (webPort == null || webPort.isEmpty()) {
            log.info("Environment application port was not defined, using default");
            webPort = "8080";
        }
        log.info("Environment application port is {}", webPort);

        GlassFishProperties gfProps = new GlassFishProperties();
        gfProps.setPort("http-listener", Integer.valueOf(webPort));
        gfProps.setProperty("domain-dir", "glassfishDomain");

        GlassFish glassfish = GlassFishRuntime.bootstrap().newGlassFish(gfProps);
        glassfish.start();

        CommandRunner runner = glassfish.getCommandRunner();

        String dbUrl = System.getenv("DATABASE_URL");
        if (StringUtils.isBlank(dbUrl)) {
            dbUrl = "postgres://carat:password1*@localhost:5432/carat-db";
        }

        System.out.println("-------db url: " + dbUrl);
        Matcher matcher = Pattern.compile("postgres://(.*):(.*)@(.*)/(.*)").matcher(dbUrl);
        matcher.find();

        String host = matcher.group(3);
        String port = "5432";
        String database = matcher.group(4);
        String user = matcher.group(1);
        String password = matcher.group(2);
        if (host.contains(":")) {
            final String[] parts = host.split(":");
            host = parts[0];
            port = parts[1];
        }
        String properties = "user=" + user + ":password=" + password + ":databaseName=" + database + ":loglevel=4:serverName=" + host + ":portNumber=" + port;


        System.out.println("-------properties: " + properties);

        CommandResult result = runner.run("create-jdbc-connection-pool", "--datasourceclassname", "org.postgresql.ds.PGSimpleDataSource", "--restype", "javax.sql.DataSource",
                //"--property", "url='" + dbUrl + "'",
                //"--property", "user=user:password=postgres:databasename=petclinic:server=localhost:port=5432",
                //"--steadypoolsize", "1",
                //"--maxpoolsize", "1",
                "--property", properties,
                "PostgresPool");

        System.out.println("------output of create conn pool: " + result.getOutput());

        result = runner.run("create-jdbc-resource", "--connectionpoolid", "PostgresPool", "jdbc/carat");
        result = runner.run("create-jdbc-resource", "--connectionpoolid", "PostgresPool", "jdbc/batch");

        System.out.println("------output of create jdbc: " + result.getOutput());

//        result = runner.run("set-log-level", "javax.enterprise.system.container.web=INFO:javax.enterprise.system.container.ejb=FINEST");
//
//        System.out.println("------output of set log level: " + result.getOutput());

        Deployer deployer = glassfish.getDeployer();

        // Create a scattered web application.
        ScatteredArchive archive = new ScatteredArchive("carat", ScatteredArchive.Type.WAR, new File(directory));
        // target/classes directory contains my complied servlets
        //archive.addClassPath(new File("target", "classes"));
        deployer.deploy(archive.toURI());
    }
}
