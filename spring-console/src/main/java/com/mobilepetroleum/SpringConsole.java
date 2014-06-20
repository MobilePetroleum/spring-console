package com.mobilepetroleum;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

@SuppressWarnings({"SpringJavaAutowiringInspection", "UnusedDeclaration"})
public class SpringConsole implements ApplicationListener, DisposableBean {

    private final SpringServiceBean serviceBean = new SpringServiceBean();

    private String serviceNameInRmiRegistry = "spring-console";
    private int rmiRegistryPort = 25001;
    private String[] whitelist = new String[]{".*"};
    private Registry rmiRegistry;

    public SpringConsole() {
    }

    private void start(ApplicationContext applicationContext) {
        boolean success = false;
        try {
            rmiRegistry = createRmiRegistry(rmiRegistryPort);
            serviceBean.setApplicationContext(applicationContext);
            serviceBean.setAllowedPatterns(whitelist);
            rmiRegistry.rebind(serviceNameInRmiRegistry, UnicastRemoteObject.exportObject(serviceBean, rmiRegistryPort));
            success = true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (!success) {
                close();
            }

        }
    }

    private Registry createRmiRegistry(int rmiRegistryPort) throws RemoteException {
        try {
            return LocateRegistry.createRegistry(rmiRegistryPort);
        } catch (RemoteException ignored) {
            return LocateRegistry.getRegistry(rmiRegistryPort);
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

    public String[] getWhitelist() {
        return whitelist;
    }

    public void setWhitelist(String[] whitelist) {
        this.whitelist = whitelist;
    }
}
