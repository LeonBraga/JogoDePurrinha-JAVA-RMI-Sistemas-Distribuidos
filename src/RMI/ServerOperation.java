package RMI;

import RMI.RMIInterface;
import RMI.RMIInterfaceImp;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

public class ServerOperation {

    public ServerOperation() throws RemoteException, MalformedURLException {
        try{
        	RMIInterface mensageiro = (RMIInterface) new RMIInterfaceImp();
            Naming.rebind(RMIInterfaceImp.getURI(), (Remote) mensageiro);
            
        } catch (Exception e){
            System.out.println("Erro: "+e);
        }
    }
 
    public static void main(String[] args){
        try {
        	Registry r = java.rmi.registry.LocateRegistry.createRegistry(1099);
        	r.rebind("mensageiro", new RMIInterfaceImp());
//            new ServerOperation();
            System.out.println("Servidor rodando...");
        } catch (Exception e) {
            System.out.println("Erro: "+e);        
        }
    }
    
    
}