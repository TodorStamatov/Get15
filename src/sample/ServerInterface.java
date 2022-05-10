package sample;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {
    char connect(Callback client) throws RemoteException;

    void move(int number,char playerID) throws RemoteException;
}
