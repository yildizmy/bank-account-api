package com.github.yildizmy;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;

@SpringBootApplication
public class BankAccountApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankAccountApiApplication.class, args);
    }

    // start internal h2 server so that we can query on the dB from IDE
    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2Server() throws SQLException {
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
    }
}
