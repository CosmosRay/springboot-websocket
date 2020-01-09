package cn.cosmos.websocket;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.websocket.server.ServerEndpointConfig;

/**
 * 此为注解配置方式
 *
 * @author CosmosRay
 * @date 2020年1月9日15:39:25
 */
@Configuration
public class WebSocketConfig extends ServerEndpointConfig.Configurator implements ApplicationContextAware {
    private static volatile ApplicationContext context;
    @Override
    public <T> T getEndpointInstance(Class<T> clazz) {
        return context.getBean(clazz);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        WebSocketConfig.context = applicationContext;
    }

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    @Bean
    public WebSocketConfig newConfigure() {
        return new WebSocketConfig();
    }
}
