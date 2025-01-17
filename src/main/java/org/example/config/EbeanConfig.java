package org.example.config;

import io.ebean.EbeanServer;
import io.ebean.EbeanServerFactory;
import io.ebean.config.ServerConfig;
import io.ebean.datasource.DataSourceConfig;
import org.example.entity.Post;

public class EbeanConfig {

    public static EbeanServer initializeEbeanServer() {
        ServerConfig config = new ServerConfig();
        config.setName("db");
        config.setDefaultServer(true);

        // Set these options to create and/or update tables if they don't exist
        config.setDdlCreateOnly(false); // Only create tables, don't modify existing ones
        config.setDdlGenerate(true);    // Generate necessary DDL
        config.setDdlRun(true);         // Execute DDL commands

        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDriver("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("root");
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/test");
        config.setDataSourceConfig(dataSourceConfig);

        // Add entity classes that you want to manage with Ebean
        config.addClass(org.example.entity.User.class);
        config.addClass(Post.class);

        return EbeanServerFactory.create(config);
    }
}
