//package com.example.netology_cloudservice;
////import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.testcontainers.containers.GenericContainer;
//import org.testcontainers.containers.Network;
//import org.testcontainers.containers.PostgreSQLContainer;
//import org.testcontainers.junit.jupiter.Container;
//import org.testcontainers.junit.jupiter.Testcontainers;
//
//import java.util.Map;
//
////@Slf4j
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Testcontainers
//class NetologyCloudServiceApplicationTests {
//
//    private static final int PORT_DB = 1625;
//    private static final int PORT_SERVER = 5050;
//
//    private static final String DATABASE_NAME = "cloud_service";
//    private static final String DATABASE_USERNAME = "QUEEN";
//    private static final String DATABASE_PASSWORD = "2402";
//
//    private static final Network CLOUD_NETWORK = Network.newNetwork();
//
//    @Container
//    public static PostgreSQLContainer<?> databaseContainer = new PostgreSQLContainer<>("postgres")
//            .withNetwork(CLOUD_NETWORK)
//            .withExposedPorts(PORT_DB)
//            .withDatabaseName(DATABASE_NAME)
//            .withUsername(DATABASE_USERNAME)
//            .withPassword(DATABASE_PASSWORD);
//
//    @Container
//    public static GenericContainer<?> serverContainer = new GenericContainer<>("backend_cloud_service")
//            .withNetwork(CLOUD_NETWORK)
//            .withExposedPorts(PORT_SERVER)
//            .withEnv(Map.of("SPRING_DATASOURCE_URL", "jdbc:postgres://localhost:1625/cloud_service" ))
//            .dependsOn(databaseContainer);
//
//    @Test
//    void contextDatabase() {
//        Assertions.assertTrue(databaseContainer.isRunning());
//    }
//
//    @Test
//    void contextServer() {
//        Assertions.assertFalse(serverContainer.isRunning());
//    }
//}
