package com.ficha.demo.config;

import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

/**
 * CONFIGURACIÓN DEL SERVIDOR TCP DE H2:
 * Permite que clientes de consola o herramientas externas (como DBeaver, H2 Shell o CLI)
 * se conecten a la base de datos que está corriendo en la memoria de Spring Boot.
 */
@Configuration
public class H2TcpConfig {

    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2TcpServer() throws SQLException {
        // Escucha en el puerto 9092 para conexiones TCP externas
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
    }
}
