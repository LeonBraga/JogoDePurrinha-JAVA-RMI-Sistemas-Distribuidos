package RMI;


import RMI.RMIInterface;
import java.rmi.Naming;
import java.util.Scanner;

public class ClientOperation {
    
    public static void main(String[] args) {
        try{
        	RMIInterface mensageiro = (RMIInterface)Naming.lookup("//192.168.1.6/mensageiro");
//        	RMIInterface mensageiro = (RMIInterface) Naming.lookup(RMIInterfaceImp.getURI());
            Scanner scanner = new Scanner(System.in);
            
            while (true) {//enquanto para sempre                
//                mensagem = scanner.nextLine();//recebe o digitado
//                mensageiro.enviarMensagem(mensagem);//manda pro servidor
                
                String meuNumeroJogador = "0";
                String contadorJogador = "0";
                
                String nome;
                String jogada;
                String palpite;
                
                System.out.println("Digite o nome do jogador: ");
                nome = scanner.nextLine();
                
                mensageiro.cadastraJogador(nome);                	
                
                meuNumeroJogador = mensageiro.getNumeroJogador(nome);
                System.out.println("Meu Numero de Jogador: "+meuNumeroJogador);
                
                if (meuNumeroJogador.equals("1")) {
                	System.out.println("Iniciando J1");
                	//j1 cadastrou nome, aguardando j2
                	while (mensageiro.getContador().equals("1")) {
                		System.out.println("...");
                		System.out.println("Aguardando jogador 2 cadastrar nome...");
                		Thread.sleep(5000);
                	}
                	
                	contadorJogador = mensageiro.getContador();
                	//j2 cadastrou nome, fazendo jogada de j1
                	if (contadorJogador.equals("2")) {       
                		System.out.println("...");
                        System.out.println("Digite o sua jogada 1, 2 ou 3: ");
                        jogada = scanner.nextLine();
                        mensageiro.fazerJogada1(nome,jogada);
                	}
                	
                	//j1 fez jogada, aguardando jogada j2
                	while(mensageiro.getContador().equals("3")) {
                		System.out.println("...");
                		System.out.println("Aguardando jogador 2 realizar jogada...");
                		Thread.sleep(5000);
                	}
                	
                  	contadorJogador = mensageiro.getContador();
                	//j2 fez jogada, j1 fazendo palpite
                  	if (contadorJogador.equals("4")) {       
                  		System.out.println("...");
                        System.out.println("Digite o seu palpite (2-6): ");
                        palpite = scanner.nextLine();
                        mensageiro.fazerPalpite1(nome,palpite);
                	}
                	
                  	//j1 fez palpite, aguardando palpite j2
                	while(mensageiro.getContador().equals("5")) {
                		System.out.println("...");
                		System.out.println("Aguardando jogador 2 realizar palpite...");
                		Thread.sleep(5000);
                	}
                  	
                	//j2 fez palpite, recebendo resultado
                }
                else if (meuNumeroJogador.equals("2")) {
                            	
                	System.out.println("Iniciando J2");
                	//j2 cadastrou nome, aguardando j1 fazer jogada
                	while (mensageiro.getContador().equals("2")) {
                		System.out.println("...");
                		System.out.println("Aguardando jogador 1 realizar jogada...");
                		Thread.sleep(5000);
                	}
                	
                	contadorJogador = mensageiro.getContador();
                	//j1 fez jogada, fazendo jogada de j2
                	if (contadorJogador.equals("3")) { 
                		System.out.println("...");
                        System.out.println("Digite o sua jogada 1, 2 ou 3: ");
                        jogada = scanner.nextLine();
                        mensageiro.fazerJogada2(nome,jogada);
                	}
                	
                	contadorJogador = mensageiro.getContador();
                	//j2 fez jogada, aguardando palpite j1
                	while(mensageiro.getContador().equals("4")) {
                		System.out.println("...");
                		System.out.println("Aguardando jogador 1 realizar palpite...");
                		Thread.sleep(5000);
                	}
                	
                  	contadorJogador = mensageiro.getContador();
                	//j1 fez palpite, j2 fazendo palpite
                  	if (contadorJogador.equals("5")) {   
                  		System.out.println("----------------------");
                  		System.out.println("O palpite do Jogador 1 foi: "+ mensageiro.getPalpite1());
                        System.out.println("Digite o seu palpite (2-6): ");
                        palpite = scanner.nextLine();
                        mensageiro.fazerPalpite2(nome,palpite);
                	}
                	     	
                	//j2 fez palpite, recebendo resultado        	
                }   
                
                contadorJogador = mensageiro.getContador();
                if (contadorJogador.equals("6") || contadorJogador.equals("0")) {                		
                    String resultado = mensageiro.saberResultado(nome);
                	System.out.println("-------RESULTADO------");
                	System.out.println("----------------------");

                    if (resultado=="0") {
                    	System.out.println("Jogada J1: "+ mensageiro.getJogada1() + "/ Palpite J1: "+ mensageiro.getPalpite1());
                    	System.out.println("Jogada J2: "+ mensageiro.getJogada2() + "/ Palpite J2: "+ mensageiro.getPalpite2());
                    	System.out.println("Resultado: "+ Integer.parseInt(mensageiro.getJogada1()) + Integer.parseInt(mensageiro.getJogada2()));
                    	System.out.println("Empate, nenhum jogador acertou o palpite.");
                    }
                    else if (resultado==meuNumeroJogador) {
                    	System.out.println("Jogada J1: "+ mensageiro.getJogada1() + "/ Palpite J1: "+ mensageiro.getPalpite1());
                    	System.out.println("Jogada J2: "+ mensageiro.getJogada2() + "/ Palpite J2: "+ mensageiro.getPalpite2());
                    	System.out.println("Resultado: "+ Integer.parseInt(mensageiro.getJogada1()) + Integer.parseInt(mensageiro.getJogada2()));
                    	System.out.println("Parabéns! Você acertou o palpite e venceu!");
                    }
                    else {
                    	System.out.println("Jogada J1: "+ mensageiro.getJogada1() + "/ Palpite J1: "+ mensageiro.getPalpite1());
                    	System.out.println("Jogada J2: "+ mensageiro.getJogada2() + "/ Palpite J2: "+ mensageiro.getPalpite2());
                    	System.out.println("Resultado: "+ Integer.parseInt(mensageiro.getJogada1()) + Integer.parseInt(mensageiro.getJogada2()));
                    	System.out.println("Derrota! Seu oponente acertou o palpite.");
                    }
                    System.out.println("----------------------");
                    System.out.println("----------------------");
            	}
            }
            
            
        } catch (Exception e) {
            System.out.println("Erro: "+e);
        }
        
    }
    
}