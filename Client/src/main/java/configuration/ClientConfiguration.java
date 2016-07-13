package configuration;

import config.Config;
import gui.BattleshipMainFrame;
import gui.BoardPanel;
import gui.PlayerPanel;
import main.GuiRunner;
import models.BoardType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;
import services.GameInitializer;
import services.ShipGenerator;
import services.ShootService;

@Configuration
public class ClientConfiguration {

    @Bean
    public GuiRunner guiRunner() {
        return new GuiRunner();
    }

    @Bean
    public BattleshipMainFrame battleshipMainFrame() {
        return new BattleshipMainFrame();
    }

    @Bean
    public PlayerPanel userPanel() {
        PlayerPanel playerPanel = new PlayerPanel();
        return playerPanel;
    }

    @Bean
    public PlayerPanel rivalPanel() {
        PlayerPanel playerPanel = new PlayerPanel();
        return playerPanel;
    }

    @Bean
    public BoardPanel userBoardPanel() {
        return new BoardPanel(BoardType.Yours);
    }

    @Bean
    public BoardPanel rivalBoardPanel() {
        return new BoardPanel(BoardType.Rivals);
    }

    // To try may be dependency on this class throw exeption


    @Bean
    public HttpInvokerProxyFactoryBean generatorHttpInvokerProxyFactoryBean() {
        HttpInvokerProxyFactoryBean httpInvoker = new HttpInvokerProxyFactoryBean();
        String serviceURL = "http://" + Config.HOST_NAME + ":" + Config.SERVER_PORT + Config.GENERATOR_SERVICE;
        httpInvoker.setServiceInterface(ShipGenerator.class);
        httpInvoker.setServiceUrl(serviceURL);
        return httpInvoker;
    }

    @Bean
    public HttpInvokerProxyFactoryBean gameInitializerHttpInvokerProxyFactoryBean() {
        HttpInvokerProxyFactoryBean httpInvoker = new HttpInvokerProxyFactoryBean();
        String serviceURL = "http://" + Config.HOST_NAME + ":" + Config.SERVER_PORT + Config.GAME_INITIALIZER_SERVICE;
        httpInvoker.setServiceInterface(GameInitializer.class);
        httpInvoker.setServiceUrl(serviceURL);
        return httpInvoker;
    }

    @Bean
    public HttpInvokerProxyFactoryBean shootServerHttpInvokerProxyFactoryBean() {
        HttpInvokerProxyFactoryBean httpInvoker = new HttpInvokerProxyFactoryBean();
        String serviceURL = "http://" + Config.HOST_NAME + ":" + Config.SERVER_PORT + Config.SHOOT_SERVICE;
        httpInvoker.setServiceInterface(ShootService.class);
        httpInvoker.setServiceUrl(serviceURL);
        return httpInvoker;
    }
}