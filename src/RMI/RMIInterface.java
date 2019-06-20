package RMI;


import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIInterface extends Remote {
//    public void enviarMensagem(String mensagem) throws RemoteException; //método para enviar mensagens
//    public String lerMensagem() throws RemoteException; //método para ler mensagens
    
    public String cadastraJogador(String nome) throws RemoteException;  //Cadastra nome jogador 1 e 2
	public String getNumeroJogador(String nome) throws RemoteException;
	public String getContador() throws RemoteException;
	public String fazerJogada1(String nome, String jogada) throws RemoteException;
	public String fazerJogada2(String nome, String jogada) throws RemoteException;
	public String fazerPalpite1(String nome, String palpite) throws RemoteException;
	public String fazerPalpite2(String nome, String palpite) throws RemoteException;
	public String saberResultado(String nome) throws RemoteException;
	public String getJogada1() throws RemoteException;
	public String getJogada2() throws RemoteException;
	public String getPalpite1() throws RemoteException;
	public String getPalpite2() throws RemoteException;
} 
