package com.mobilepetroleum;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

@SuppressWarnings("SpringJavaAutowiringInspection")
public class SpringConsole implements ApplicationContextAware {

    private int port = 25001;
    private ApplicationContext applicationContext;
    private SpringServiceBean serviceBean = new SpringServiceBean();
    private Registry registry;

    public SpringConsole(int port) {
        this.port = port;
    }

    public SpringConsole() {
    }

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
        System.out.println("close");
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
}
