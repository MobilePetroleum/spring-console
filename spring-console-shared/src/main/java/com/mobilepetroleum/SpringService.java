package com.mobilepetroleum;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SpringService extends Remote {
    String invoke(String beanName, String methodName, MethodParameter[] parameters) throws RemoteException;
}
