package configuration;

import com.sun.net.httpserver.HttpHandler;
import config.Config;
import models.Board;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.remoting.httpinvoker.SimpleHttpInvokerServiceExporter;
import org.springframework.remoting.support.SimpleHttpServerFactoryBean;
import services.*;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ServerConfiguration {

    @Bean
    public SimpleHttpServerFactoryBean serverFactoryBean() {
        SimpleHttpServerFactoryBean httpServerFactoryBean = new SimpleHttpServerFactoryBean();
        httpServerFactoryBean.setPort(Config.SERVER_PORT);

        Map<String, HttpHandler> httpHandlers = new HashMap<>();
        httpHandlers.put(Config.GENERATOR_SERVICE, shipsGeneratorHttpInvokerServiceExporter());
        httpHandlers.put(Config.GAME_INITIALIZER_SERVICE, gameInitializerHttpInvokerServiceExporter());
        httpHandlers.put(Config.SHOOT_SERVICE, shootServiceHttpInvokerServiceExporter());
        httpHandlers.put(Config.PLAYER_ID_SERVICE, playerIdentifierHttpInvokerServiceExporter());
        httpServerFactoryBean.setContexts(httpHandlers);

        return httpServerFactoryBean;
    }

    @Bean
    public SimpleHttpInvokerServiceExporter shipsGeneratorHttpInvokerServiceExporter() {
        SimpleHttpInvokerServiceExporter simpleHttpInvokerServiceExporter = new SimpleHttpInvokerServiceExporter();
        simpleHttpInvokerServiceExporter.setServiceInterface(ShipGenerator.class);
        simpleHttpInvokerServiceExporter.setService(shipGenerator());
        return simpleHttpInvokerServiceExporter;
    }

    @Bean
    public SimpleHttpInvokerServiceExporter gameInitializerHttpInvokerServiceExporter() {
        SimpleHttpInvokerServiceExporter simpleHttpInvokerServiceExporter = new SimpleHttpInvokerServiceExporter();
        simpleHttpInvokerServiceExporter.setServiceInterface(GameInitializer.class);
        simpleHttpInvokerServiceExporter.setService(gameInitializer());
        return simpleHttpInvokerServiceExporter;
    }

    @Bean
    public SimpleHttpInvokerServiceExporter shootServiceHttpInvokerServiceExporter() {
        SimpleHttpInvokerServiceExporter simpleHttpInvokerServiceExporter = new SimpleHttpInvokerServiceExporter();
        simpleHttpInvokerServiceExporter.setServiceInterface(ShootService.class);
        simpleHttpInvokerServiceExporter.setService(shootService());
        return simpleHttpInvokerServiceExporter;
    }

    @Bean
    public SimpleHttpInvokerServiceExporter playerIdentifierHttpInvokerServiceExporter() {
        SimpleHttpInvokerServiceExporter simpleHttpInvokerServiceExporter = new SimpleHttpInvokerServiceExporter();
        simpleHttpInvokerServiceExporter.setServiceInterface(PlayerIdentifierService.class);
        simpleHttpInvokerServiceExporter.setService(playerIdentifierService());
        return simpleHttpInvokerServiceExporter;
    }

    @Bean
    public ShipGenerator shipGenerator() {
        return new ShipGeneratorImpl();
    }

    @Bean
    public GameInitializer gameInitializer() {
        return new GameInitializerImpl();
    }

    @Bean
    public ShootService shootService() {
        return new ShootServiceImpl();
    }

    @Bean
    public PlayerIdentifierService playerIdentifierService() {
        return new PlayerIdentifierServiceImpl();
    }

    @Bean
    @Scope ("prototype")
    public Board board() {
        return new Board();
    }
}