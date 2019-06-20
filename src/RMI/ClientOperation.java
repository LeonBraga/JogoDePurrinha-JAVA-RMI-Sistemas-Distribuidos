package RMI;


import RMI.RMIInterface;
import java.rmi.Naming;
import java.util.Scanner;

public class ClientOperation {
    
    public static void main(String[] args) {
        try{
        	
        	String servidor1 = "localhost";
        	String servidor2 = "localhost";
        	String servidorV = "";
        	String servidor = "localhost";
        	String opt="0";
        	
        	Scanner scanner = new Scanner(System.in);
        	
        	while(!(opt.matches("1|2|3"))) {
	        	System.out.println("Digite 1 para conectar em servidor A");
	        	System.out.println("Digite 2 para conectar em servidor B");
	        	System.out.println("Ou digite a senha secreta para habilitar um novo servidor:");
	        	opt = scanner.nextLine();
	        	if (!(opt.matches("1|2|n"))) {
	        		System.out.println("Opcao invalida...");
	        	}
        	}
        	
        	if(opt.equals("1")) {
        		servidor = servidor1;        		
        	}
        	else if(opt.equals("2")) {
        		servidor = servidor2;       		
        	}
        	else if(opt.equals("n")) {
        		System.out.println("Digite o IP para se conectar: ");
        		servidorV = scanner.nextLine();
        		servidor = servidorV;        		
        	}
        	else {
        		System.out.println("Erro! Servidor nao localizado em cliente....");
        		System.out.println("Iniciado em localhost");
        	}
        	
        	RMIInterface mensageiro = (RMIInterface)Naming.lookup("//"+servidor+"/mensageiro");
//        	RMIInterface mensageiro = (RMIInterface) Naming.lookup(RMIInterfaceImp.getURI());        	
            
            while (true) {//enquanto para sempre                
            	
                String meuNumeroJogador = "0";
                String contadorJogador = "0";
                
                String nome = "";
                String jogada = "";
                String palpite = "";
                
                while(nome.equals("")) {
                	System.out.println("Digite o nome do jogador: ");
                	nome = scanner.nextLine();
                }
                
                mensageiro.cadastraJogador(nome);                	
                
                meuNumeroJogador = mensageiro.getNumeroJogador(nome);
                System.out.println("Seu Numero de Jogador: "+ meuNumeroJogador);
                
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
                		while(jogada.equals("")) {
                			System.out.println("Digite o sua jogada 0, 1, 2 ou 3: ");
                			jogada = scanner.nextLine();
                			if (!(jogada.matches("0|1|2|3"))) {
                				System.out.println("Jogada invalida!");
                				jogada = "";
                			}
                		}
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
                  		while (palpite.equals("")) {
                  			System.out.println("Digite o seu palpite (0-6): ");
                            palpite = scanner.nextLine();
                 			if (!(palpite.matches("0|1|2|3|4|5|6"))) {
                 				System.out.println("Palpite invalido!");
                 				palpite = "";
                 			}
                  		}
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
                		while(jogada.equals("")) {
                			System.out.println("Digite o sua jogada 0, 1, 2 ou 3: ");
                			jogada = scanner.nextLine();
                			if (!(jogada.matches("0|1|2|3"))) {
                				System.out.println("Jogada invalida!");
                				jogada = "";
                			}
                		}
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
                  		String palpite1 = mensageiro.getPalpite1();
                  		while(palpite.equals("")) {
                  			System.out.println("O palpite do Jogador 1 foi: "+ palpite1);
                            System.out.println("Digite o seu palpite : ");
                            palpite = scanner.nextLine();
                            if (palpite.equals(palpite1)) {
                            	System.out.println("Palpite invalido!");
                            	System.out.println("Seu palpite não pode ser igual ao do jogador 1!");
                 				palpite = "";
                            }
                            else if (!(palpite.matches("0|1|2|3|4|5|6"))) {
                				System.out.println("Palpite invalido!");
                				palpite = "";
                			}
                  		}
                        mensageiro.fazerPalpite2(nome,palpite);
                	}
                	     	
                	//j2 fez palpite, recebendo resultado        	
                }   
                
                contadorJogador = mensageiro.getContador();
                if (contadorJogador.equals("6") || contadorJogador.equals("7")) {                		
                    String resultado = mensageiro.saberResultado(nome);
                	System.out.println("-------RESULTADO------");
                	System.out.println("----------------------");

                    if (resultado.equals("0")) {
                    	System.out.println("Jogada J1: "+ mensageiro.getJogada1() + "/ Palpite J1: "+ mensageiro.getPalpite1());
                    	System.out.println("Jogada J2: "+ mensageiro.getJogada2() + "/ Palpite J2: "+ mensageiro.getPalpite2());
                    	System.out.println("Resultado: "+ (Integer.parseInt(mensageiro.getJogada1()) + Integer.parseInt(mensageiro.getJogada2())));
                    	System.out.println("Empate, nenhum jogador acertou o palpite.");
                    }
                    else if (resultado.equals(meuNumeroJogador)) {
                    	System.out.println("Jogada J1: "+ mensageiro.getJogada1() + "/ Palpite J1: "+ mensageiro.getPalpite1());
                    	System.out.println("Jogada J2: "+ mensageiro.getJogada2() + "/ Palpite J2: "+ mensageiro.getPalpite2());
                    	System.out.println("Resultado: "+ (Integer.parseInt(mensageiro.getJogada1()) + Integer.parseInt(mensageiro.getJogada2())));
                    	System.out.println("Parabéns! Você acertou o palpite e venceu!");
                    }
                    else {
                    	System.out.println("Jogada J1: "+ mensageiro.getJogada1() + "/ Palpite J1: "+ mensageiro.getPalpite1());
                    	System.out.println("Jogada J2: "+ mensageiro.getJogada2() + "/ Palpite J2: "+ mensageiro.getPalpite2());
                    	System.out.println("Resultado: "+ (Integer.parseInt(mensageiro.getJogada1()) + Integer.parseInt(mensageiro.getJogada2())));
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
