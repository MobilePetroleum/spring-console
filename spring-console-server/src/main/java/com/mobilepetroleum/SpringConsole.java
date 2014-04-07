package com.mobilepetroleum;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

@SuppressWarnings("SpringJavaAutowiringInspection")
public class SpringConsole implements ApplicationContextAware, ApplicationListener {

    private int port = 25001;
    private ApplicationContext applicationContext;
    private SpringServiceBean serviceBean = new SpringServiceBean();
    private Registry registry;

    @SuppressWarnings("UnusedDeclaration")
    public SpringConsole(int port) { this.port = port; }

    @SuppressWarnings("UnusedDeclaration")
    public SpringConsole() { }

    @SuppressWarnings("UnusedDeclaration")
    public void start() {
        try {
            registry = LocateRegistry.createRegistry(port);
            serviceBean.setApplicationContext(applicationContext);
            registry.bind("spring-console", UnicastRemoteObject.exportObject(serviceBean, port));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void close() {
        try {
            registry.unbind("spring-console");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            UnicastRemoteObject.unexportObject(serviceBean, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if (applicationEvent instanceof ContextClosedEvent) close();
    }
}
