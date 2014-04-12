package com.mobilepetroleum;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

@SuppressWarnings({"SpringJavaAutowiringInspection", "UnusedDeclaration"})
public class SpringConsole implements ApplicationListener, DisposableBean {

    private final SpringServiceBean serviceBean = new SpringServiceBean();
    private String serviceNameInRegistry = "spring-console";
    private int port = 25001;
    private Registry registry;

    public SpringConsole() { }

    public SpringConsole(int port) {
        this.port = port;
    }

    public SpringConsole(int port, String serviceNameInRegistry) {
        this.port = port;
        this.serviceNameInRegistry = serviceNameInRegistry;
    }

    public SpringConsole(Registry registry) {
        this.registry = registry;
    }

    public SpringConsole(Registry registry, String serviceNameInRegistry) {
        this.registry = registry;
        this.serviceNameInRegistry = serviceNameInRegistry;
    }

    private void start(ApplicationContext applicationContext) {
        try {
            if (registry == null) registry = LocateRegistry.createRegistry(port);

            serviceBean.setApplicationContext(applicationContext);
            registry.rebind(serviceNameInRegistry, UnicastRemoteObject.exportObject(serviceBean, port));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void close() {
        try {
            registry.unbind(serviceNameInRegistry);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            UnicastRemoteObject.unexportObject(serviceBean, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if (applicationEvent instanceof ContextRefreshedEvent) {
            start(((ContextRefreshedEvent) applicationEvent).getApplicationContext());
        }
    }

    // @Override
    public void destroy() throws Exception {
        close();
    }
}
