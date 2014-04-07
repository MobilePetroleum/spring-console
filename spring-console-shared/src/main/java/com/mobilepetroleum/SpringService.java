package com.mobilepetroleum;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SpringService extends Remote {
    String invoke(Invocable invocable) throws RemoteException;
}
