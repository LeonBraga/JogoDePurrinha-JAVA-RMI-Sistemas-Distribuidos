package RMI;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIInterfaceImp extends UnicastRemoteObject implements RMIInterface {
    private static String SERVIDOR = "192.168.1.6";
    private final static Integer PORTA = 1099;
    private static String SERVICO = "ServicoMensagens";
    
    private String msgRecebida = "Sem mensagem";
    
    public String contador = "0";
    
    public String nomeJogador1 = "";
    public String nomeJogador2 = "";
    public String jogada1 = "0";
    public String jogada2 = "0";
    public String palpite1 = "0";
    public String palpite2 = "0";
    
    public static String getURI(){
        String uri = String.format("rmi://%s:%d/$s", SERVIDOR, PORTA, SERVICO);
        return uri;
    }

    public RMIInterfaceImp() throws RemoteException {
        super();
    }
    
    public void enviarMensagem(String mensagem) throws RemoteException {
        System.out.println(mensagem);
        msgRecebida = mensagem;
    }
    
    public String lerMensagem() throws RemoteException {
        return msgRecebida;
    }
    
    public String cadastraJogador(String nome) throws RemoteException {
    	
        if (contador.equals("0")) {
        	nomeJogador1 = nome;
        	contador = "1";
        	System.out.println("nj1 SET");
        	return "1";
        }
        else if (contador.equals("1")) {
        	nomeJogador2 = nome;
        	contador = "2";
        	System.out.println("nj2 SET");
        	return "1";
        }
        else {
        	return "0";
        }
    }
    
    public String fazerJogada1(String nome, String jogada) throws RemoteException {
    	if (contador.equals("2")) {
    		jogada1 = jogada;
    		contador = "3";
    		System.out.println("jj1 SET");
    		return "1";
    	}
    	else {
    		return "0";
    	}
    }
    
    public String fazerJogada2(String nome, String jogada) throws RemoteException {
    	System.out.println("entrou fazerJogada2 SET");
    	if (contador.equals("3")) {
    		jogada2 = jogada;
    		contador = "4";
    		System.out.println("jj2 SET");
    		return "1";
    	}
    	else {
    		return "0";
    	}
    }
    
    public String fazerPalpite1(String nome, String palpite) throws RemoteException {
    	if (contador.equals("4")) {
    		palpite1 = palpite;
    		contador = "5";
    		System.out.println("pj1 SET");
    		return "1";
    	}
    	else {
    		return "0";
    	}
    }
    
    public String fazerPalpite2(String nome, String palpite) throws RemoteException {
    	if (contador.equals("5")) {
    		palpite2 = palpite;
    		contador = "6";
    		System.out.println("pj2 SET");
    		return "1";
    	}
    	else {
    		return "0";
    	}
    }

    public String getPalpite1() {
    		return palpite1;
    }

	public String getPalpite2() {
		return palpite2;
}
    
    public String getJogada1() {
		return jogada1;
}
    
    public String getJogada2() {
		return jogada2;
}

    public String saberResultado(String nome) { 	
    	if (contador.equals("6")) {
    		int resultado = Integer.parseInt(jogada1) + Integer.parseInt(jogada2);
    		if (resultado==Integer.parseInt(palpite1)) {
    			contador = "0";
    			return "1";
    		}
    		else if (resultado==Integer.parseInt(palpite2)) {
    			contador = "0";
    			return "2";
    		}
    		else {
    			contador = "0";
    			return "3";
    		}
    	}
    	else {
    		return "0";
    	}
    }

    public String getContador() throws RemoteException {
       return contador;
    }
    
    public String getNumeroJogador(String nome) throws RemoteException {
    	System.out.println("Nome J1: "+nomeJogador1);
    	System.out.println("Nome J2: "+nomeJogador2);
        if (nome.equals(nomeJogador1)) {
        	System.out.println("Retornou como J1: "+nomeJogador1);
        	return "1";
        }
        else if (nome.equals(nomeJogador2)) {
        	System.out.println("Retornou como J2: "+nomeJogador2);
        	return "2";
        }
        else {
        	System.out.println("Retornou 0");
        	return "0";
        }
     }
    
}