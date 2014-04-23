package com.mobilepetroleum;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

@SuppressWarnings({"SpringJavaAutowiringInspection", "UnusedDeclaration"})
public class SpringConsole implements ApplicationListener, DisposableBean {

    private final SpringServiceBean serviceBean = new SpringServiceBean();

    private String serviceNameInRmiRegistry = "spring-console";
    private int rmiRegistryPort = 25001;
    private Registry rmiRegistry;

    public SpringConsole() { }

    public String getServiceNameInRmiRegistry() {
        return serviceNameInRmiRegistry;
    }

    public void setServiceNameInRmiRegistry(String serviceNameInRmiRegistry) {
        this.serviceNameInRmiRegistry = serviceNameInRmiRegistry;
    }

    public int getRmiRegistryPort() {
        return rmiRegistryPort;
    }

    public void setRmiRegistryPort(int rmiRegistryPort) {
        this.rmiRegistryPort = rmiRegistryPort;
    }

    public Registry getRmiRegistry() {
        return rmiRegistry;
    }

    public void setRmiRegistry(Registry rmiRegistry) {
        this.rmiRegistry = rmiRegistry;
    }

    private void start(ApplicationContext applicationContext) {
        try {
            if (rmiRegistry == null) rmiRegistry = LocateRegistry.createRegistry(rmiRegistryPort);

            serviceBean.setApplicationContext(applicationContext);
            rmiRegistry.rebind(serviceNameInRmiRegistry, UnicastRemoteObject.exportObject(serviceBean, rmiRegistryPort));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void close() {
        try {
            rmiRegistry.unbind(serviceNameInRmiRegistry);
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
