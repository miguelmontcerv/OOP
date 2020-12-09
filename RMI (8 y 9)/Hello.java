import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Hello extends Remote{
	String printMsg(String pregunta) throws RemoteException;
}
