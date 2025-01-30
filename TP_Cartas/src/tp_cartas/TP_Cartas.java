package tp_cartas;

//Todos os import necessários para o funcionamento do código:
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class TP_Cartas {

    //Criação das variáveis usadas
    public static Scanner teclado = new Scanner(System.in);
    public static FakeBD bancoDados = new FakeBD();
    public static Jogador Player1;
    public static Jogador Player2;
    public static Vector<Cartas> deckP1 = new Vector<>();
    public static Vector<Cartas> deckP2 = new Vector<>();
    public static Vector<Cartas> TabuleiroJ1 = new Vector<>();
    public static Vector<Cartas> TabuleiroJ2 = new Vector<>();
    public static Monstro cartaMonstro;
    public static Cartas novaCarta;
    public static int modo;

    //Ponto de entrada do programa. 
    public static void main(String[] args) {
        //Logo chama os seguintes métodos:
        LeituraArquivo();
        recebeCartas();
        gerenciamentoRodadas();
    }

    //Método responsável por distribuir cartas dos baralhos do banco de dados para os baralhos dos jogadores:
    public static void recebeCartas() {
        /*
        Distribui 5 cartas para cada jogador, retirando essas cartas do baralho do banco de dados e
        adicionando-as aos baralhos dos jogadores utilizando um "for" para fazer um loop: */
        for (int i = 0; i < 5; i++) {
            deckP1.add(bancoDados.getBaralho().get(0));
            bancoDados.getBaralho().remove(0);
        }
        for (int i = 0; i < 5; i++) {
            deckP2.add(bancoDados.getBaralho().get(0));
            bancoDados.getBaralho().remove(0);
        }
    }

    /*
    Método que lida com a interação do jogador para posicionar cartas do tipo "Monstro" no tabuleiro do
    Jogador 1, permitindo interações contínuas até que o tabuleiro esteja cheio ou o jogador decida parar de adicionar cartas:
     */
    public static void posicionamentoCartasTabP1() {
        /*
        Verifica se o tamanho do tabuleiro do Jogador 1 (representado por TabuleiroJ1) é menor ou igual a 5.
        Se for maior, a função informa que o tabuleiro está lotado e não permite mais cartas a serem adicionadas: */
        if (TabuleiroJ1.size() <= 5) {
            int indice;
            //Solicita ao jogador que selecione uma carta para posicionar a partir do seu deck:
            System.out.println(Player1.getNome() + ", qual carta deseja posicionar?\n" + deckP1 + "\nSelecione o indice da carta (lembrando que a primeira carta e o indice 0)");
            //Jogador escolhe o índice da carta:
            indice = teclado.nextInt();
            //Se for do tipo "Monstro", ela é adicionada ao tabuleiro:
            if (deckP1.get(indice).getTipo().equalsIgnoreCase("monstro")) {
                TabuleiroJ1.add(deckP1.get(indice));
                System.out.println("Selecione o modo da carta selecionada (atk = 0 e def = 1)");
                //Jogador escolhe o modo da carta:
                modo = teclado.nextInt();
                if (modo == 0) {
                    deckP1.get(indice).setStatus("ataque");
                }
                if (modo == 1) {
                    deckP1.get(indice).setStatus("defesa");
                }
                //A carta é removida do deck do jogador:
                deckP1.remove(indice);
                System.out.println("Carta adicionada ao tabuleiro com sucesso!");
                System.out.println();
                //O tabuleiro do Jogador 1 é exibido:
                System.out.println("Tabuleiro do Jogador 1: " + TabuleiroJ1);
                String op2;
                //Loop do-while usado para permitir que o jogador adicione mais cartas ao tabuleiro:
                do {
                    System.out.println("Deseja adicionar alguma outra carta?");
                    //O jogador escolhe continuar adicionando cartas ou parar.
                    op2 = teclado.next();
                    if (op2.equalsIgnoreCase("sim")) {
                        //Caso escolha continuar, a mesma lógica acima é repetida:
                        if (deckP1.isEmpty() == true || TabuleiroJ1.size() == 5) {
                            System.err.println("\nNão há mais monstros,em seu deck, a serem adicionados ou o limite de posicionamento de 5 monstros foi atingido no tabuleiro.");
                            break;
                        }
                        System.out.println("Qual carta deseja posicionar?\n" + deckP1 + "\nSelecione o indice da carta (lembrando que a primeira carta e o indice 0)"); // ainda tenho q colocar o status da carta
                        indice = teclado.nextInt();
                        if (deckP1.get(indice).getTipo().equalsIgnoreCase("monstro")) {
                            TabuleiroJ1.add(deckP1.get(indice));
                            System.out.println("Selecione o modo da carta selecionada (atk = 0 e def = 1)");
                            modo = teclado.nextInt();
                            if (modo == 0) {
                                deckP1.get(indice).setStatus("ataque");
                            }
                            if (modo == 1) {
                                deckP1.get(indice).setStatus("defesa");
                            }
                            deckP1.remove(indice);
                            System.out.println("Carta adicionada ao tabuleiro com sucesso!");
                            System.out.println();
                            System.out.println("Tabuleiro do Jogador 1: " + TabuleiroJ1);
                        } else {
                            System.err.println("A carta selecionada não e um monstro! Tente adicionar uma outra carta!\n");
                        }
                    }
                    //Loop continua enquanto o jogador não digitar "não" ou "nao" ou se o deck do jogador acabar:
                } while (!(op2.equalsIgnoreCase("não") || op2.equalsIgnoreCase("nao")) || deckP1.isEmpty() == true);
                //Caso carta selecionada não for do tipo "Monstro", uma mensagem de erro é exibida e o método é repitido ao jogador:
            } else {
                System.err.println("A carta selecionada não e um monstro! Tente adicionar uma outra carta!\n");
                posicionamentoCartasTabP1();
            }
            //Caso o tamanho do tabuleiro for maior que 5, uma mensagem de erro é exibida, indicando que o jogador não pode adicionar mais cartas:
        } else {
            System.err.println("Tabuleiro lotado! Voce nao pode adicionar mais monstros. Espere algum ser destruido...");
        }
    }

    //Método com a mesma função e lógica do anterior, mas se aplica ao Jogador 2:
    public static void posicionamentoCartasTabP2() {
        if (TabuleiroJ2.size() <= 5) {
            int indice;
            System.out.println(Player2.getNome() + ", qual carta deseja posicionar?\n" + deckP2 + "\nSelecione o indice da carta (lembrando que a primeira carta e o indice 0)");
            indice = teclado.nextInt();
            if (deckP2.get(indice).getTipo().equalsIgnoreCase("monstro")) {
                TabuleiroJ2.add(deckP2.get(indice));
                System.out.println("Selecione o modo da carta selecionada (atk = 0 e def = 1)");
                modo = teclado.nextInt();
                if (modo == 0) {
                    deckP2.get(indice).setStatus("ataque");
                }
                if (modo == 1) {
                    deckP2.get(indice).setStatus("defesa");
                }
                deckP2.remove(indice);
                System.out.println("Carta adicionada ao tabuleiro com sucesso!");
                System.out.println();
                System.out.println("Tabuleiro do Jogador 2: " + TabuleiroJ2);
                String op2;
                do {
                    System.out.println("Deseja adicionar alguma outra carta?");
                    op2 = teclado.next();
                    if (op2.equalsIgnoreCase("sim")) {
                        if (deckP2.isEmpty() == true || TabuleiroJ2.size() == 5) {
                            System.err.println("\nNão há mais monstros,em seu deck, a serem adicionados ou o limite de posicionamento de 5 monstros foi atingido no tabuleiro.");
                            break;
                        }
                        System.out.println("Qual carta deseja posicionar?\n" + deckP2 + "\nSelecione o indice da carta (lembrando que a primeira carta e o indice 0)"); // ainda tenho q colocar o status da carta
                        indice = teclado.nextInt();
                        if (deckP2.get(indice).getTipo().equalsIgnoreCase("monstro")) {
                            TabuleiroJ2.add(deckP2.get(indice));
                            System.out.println("Selecione o modo da carta selecionada (atk = 0 e def = 1)");
                            modo = teclado.nextInt();
                            if (modo == 0) {
                                deckP2.get(indice).setStatus("ataque");
                            }
                            if (modo == 1) {
                                deckP2.get(indice).setStatus("defesa");
                            }
                            deckP2.remove(indice);
                            System.out.println("Carta adicionada ao tabuleiro com sucesso!");
                            System.out.println();
                            System.out.println("Tabuleiro do Jogador 2: " + TabuleiroJ2);
                        } else {
                            System.err.println("A carta selecionada não e um monstro! Tente adicionar uma outra carta!\n");
                        }
                    }
                } while (!(op2.equalsIgnoreCase("não") || op2.equalsIgnoreCase("nao")) || deckP2.isEmpty() == true);
            } else {
                System.err.println("A carta selecionada não e um monstro! Tente adicionar uma outra carta!\n");
                posicionamentoCartasTabP2();
            }
        } else {
            System.err.println("Tabuleiro lotado! Voce nao pode adicionar mais monstros. Espere algum ser destruido...");
        }
    }

    /*
    Método que lida com a leitura de um arquivo CSV que contém informações sobre as cartas, processa essas informações e
    cria objetos de cartas que são adicionados ao banco de dados para uso consecutivo no jogo:
     */
    public static void LeituraArquivo() {
        File arquivoProduto = new File("cartas.csv");
        if (arquivoProduto.exists() && arquivoProduto.canRead()) {
            try {
                // O ARQUIVO EXISTE E PODE SER LIDO
                FileReader marcaLeitura = new FileReader(arquivoProduto);
                BufferedReader buffLeitura = new BufferedReader(marcaLeitura);
                String linha;
                buffLeitura.readLine(); //Cabeçalho sendo descartado pelo buffer, para que a leitura do arquivo comece da segunda linha
                //Procedimento para a leitura de todas as linhas do arquivo
                do {
                    linha = buffLeitura.readLine(); //Leitura realizada
                    if (linha != null) { //Verificação para saber se a linha é nula ou não
                        String dadosLinha[] = linha.split(";"); //Os dados serão separados pela vírgula através do método .split()
                        if (dadosLinha.length == 5) {
                            //Índices: 0 - Nome, 1 - Descrição, 2 - ataque, 3 - defesa, 4 - tipo
                            double ataque = Double.parseDouble(dadosLinha[2]);
                            double defesa = Double.parseDouble(dadosLinha[3]);
                            novaCarta = new Cartas(dadosLinha[0], dadosLinha[1], ataque, defesa, dadosLinha[4], null);
                            bancoDados.insereNovaCarta(novaCarta);
                        } else {
                            System.err.println("Problema na linha " + linha);
                        }
                    }
                } while (linha != null);
                Collections.shuffle(bancoDados.getBaralho());
            } catch (FileNotFoundException ex) {
                System.err.println("Arquivo inexistente no caminho especificado. Erro:  " + ex.getMessage()); //Exceção mostrada
            } catch (InputMismatchException ex) {
                System.err.println("Erro na entrada de dados: " + ex.getMessage()); //Exceção mostrada
            } catch (IOException ex) {
                System.err.println("Erro na leitura de dados do arquivo: " + ex.getMessage()); //Exceção mostrada
            }
        }
    }

    //Método que controla a lógica do jogo em torno das rodadas e interações entre os jogadores:
    public static void gerenciamentoRodadas() {
        //Variáveis de controle:
        int acoesJogadores = 0;
        int quantRodadas = 2;
        int opcao;
        //Se "acoesJogadores" for par, é vez do jogador 1
        if (acoesJogadores % 2 == 0) {
            System.out.println("\nVEZ DO JOGADOR 1:");
            System.out.println("Primeiramente, escolha seu nick selecionando o indice de 0 a 13: " + bancoDados.getTabelaNickname());
            opcao = teclado.nextInt();
            Player1 = bancoDados.getTabelaNickname().get(opcao);
            //O nome escolhido é removido da tabela de nicknames.
            bancoDados.getTabelaNickname().remove(opcao);
            System.out.println();
            //O jogador é obrigado a posicionar seus monstros na primeira rodada utilizando o método posicionamentoCartasTabP1:
            System.out.println("Agora, nesta primeira rodada, voce é obrigado a posicionar seus monstros!\n");
            posicionamentoCartasTabP1();
            acoesJogadores++;
        }
        //Se "acoesJogadores" for ímpar, é vez do jogador 2
        if (acoesJogadores % 2 != 0) {
            System.out.println("\nVEZ DO JOGADOR 2:");
            System.out.println("Primeiramente, escolha seu nick selecionando o indice de 0 a 12: " + bancoDados.getTabelaNickname());
            opcao = teclado.nextInt();
            Player2 = bancoDados.getTabelaNickname().get(opcao);
            //O nome escolhido é removido da tabela de nicknames.
            bancoDados.getTabelaNickname().remove(opcao);
            System.out.println();
            //O jogador é obrigado a posicionar seus monstros na primeira rodada utilizando o método posicionamentoCartasTabP2:
            System.out.println("Agora, nesta primeira rodada, voce é obrigado a posicionar seus monstros!\n");
            posicionamentoCartasTabP2();
            acoesJogadores++;
        }
        //Enquanto houverem rodadas e o baralho ainda ter alguma carta, O fluxo do jogo ocorre alternadamente entre os jogadores:
        while (quantRodadas > 0 && bancoDados.getBaralho().isEmpty() == false) {
            System.out.println();
            System.out.println("                                                    RODADA: " + quantRodadas);
            System.out.println("--------------------------------------------------------------------------------------------------------------------");
            while (acoesJogadores % 2 == 0) {
                System.out.println("\nVEZ DO JOGADOR 1:");
                menu(acoesJogadores);
                acoesJogadores++;
                break;
            }
            while (acoesJogadores % 2 != 0) {
                System.out.println("\nVEZ DO JOGADOR 2:");
                menu(acoesJogadores);
                acoesJogadores++;
                break;
            }
            //Após a vez de cada jogador, "acoesJogadores" é incrementado para controlar a alternância:
            quantRodadas++;
            //A cada rodada, se o número de cartas no deck dos jogadores for menor que 10, uma carta é adicionada do baralho do FakeBD e removida deste.
            if (deckP1.size() < 10) {
                deckP1.add(bancoDados.getBaralho().get(0));
                bancoDados.getBaralho().remove(0);
            } else {
                //Uma mensagem é exibida caso o limite de cartas seja atingido.
                System.out.println();
                System.err.println(Player1.getNome() + ", limite de cartas no deck atingido!");
            }
            if (deckP2.size() < 10) {
                deckP2.add(bancoDados.getBaralho().get(0));
                bancoDados.getBaralho().remove(0);
            } else {
                System.out.println();
                System.err.println(Player2.getNome() + ", limite de cartas no deck atingido!");
            }
            System.out.println("\nEssa rodada terminou! Se o limite de cada jogador nao foi atingido, cada um recebe uma carta...\n");
            //Caso um jogador acabe com a vida do outro, este ganha e o jogo acaba, mostrando o vencedor e o perdedor:
            if (Player1.getVida() <= 0 || Player2.getVida() <= 0) {
                System.out.println("-----------------------------------------------------------------------------------------------------------------------");
                System.out.println("                                              ATUALIZACOES DA RODADA");
                System.out.println();
                System.out.println("                                              O JOGO ACABOU!!!\n");
                if (Player1.getVida() >= 0) {
                    System.out.println(Player1.getNome() + ", voce ganhou! " + Player2.getNome() + ", voce perdeu");
                } else {
                    System.out.println(Player2.getNome() + ", voce ganhou! " + Player1.getNome() + ", voce perdeu");
                }
                System.out.println("-----------------------------------------------------------------------------------------------------------------------\n");
            }
            //Caso o baralho fique vazio, o jogo acaba e é mostrado o vencedor e o perdedor:
            if (bancoDados.getBaralho().isEmpty() == true) {
                System.out.println("-----------------------------------------------------------------------------------------------------------------------");
                System.out.println("                                              ATUALIZACOES DA RODADA");
                if (Player1.getVida() > Player2.getVida()) {
                    System.out.println("\nO baralho acabou! Portanto, " + Player1.getNome() + " foi considerado vencedor por possuir maior pontuacao.\n");
                }
                if (Player2.getVida() > Player1.getVida()) {
                    System.out.println("\nO baralho acabou! Portanto, " + Player1.getNome() + " foi considerado vencedor por possuir maior pontuacao.\n");
                }
                System.out.println("-----------------------------------------------------------------------------------------------------------------------");
                //Caso nenhum dos dois casos acima aconteça, é mostrado uma tela atualizando algumas informações e o jogo continua:
            } else {
                System.out.println("-----------------------------------------------------------------------------------------------------------------------");
                System.out.println("                                              ATUALIZACOES DA RODADA");
                System.out.println("Pontos de vida do Jogador 1: " + Player1.getVida() + " pontos.");
                System.out.println("Pontos de vida do Jogador 2: " + Player2.getVida() + " pontos.");
                System.out.println("-----------------------------------------------------------------------------------------------------------------------\n");
            }
        }
    }

    /*
    Método responsável por exibir um menu de opções para os jogadores durante a sua vez no jogo.
    Cada opção do menu corresponde a uma ação que o jogador pode escolher realizar.
     */
    public static void menu(int acoes) {
        //Inicialização de variáveis
        int escolha;
        boolean passou = false;
        do {
            System.out.println("                                                  MENU DE ACOES");
            System.out.println("Selecione uma acao: ");
            System.out.println("1 - Posicionar monstros no tabuleiro.\n"
                    + "2 - Equipar os monstros.\n"
                    + "3 - Alterar os status de defesa e ataque.\n"
                    + "4 - Realizar um ataque.\n"
                    + "5 - Passar a vez.");
            escolha = teclado.nextInt();
            switch (escolha) {
                case 1:
                    if (acoes % 2 == 0) {
                        posicionamentoCartasTabP1();
                    } else {
                        posicionamentoCartasTabP2();
                    }
                    System.out.println("\nDeseja realizar mais uma acao ou passar a vez?");
                    break;
                case 2:
                    if (acoes % 2 == 0) {
                        if (passou == false) {
                            equipamentoP1();
                            passou = true;
                        } else {
                            System.out.println("Voce ja passou por aqui nessa rodada. Espere a proxima rodada");
                        }
                    } else {
                        if (passou == false) {
                            equipamentoP2();
                            passou = true;
                        } else {
                            System.out.println("Voce ja passou por aqui nessa rodada. Espere a proxima rodada");
                        }
                    }
                    System.out.println("\nDeseja realizar mais uma acao ou passar a vez?");
                    break;
                case 3:
                    if (acoes % 2 == 0) {
                        mudaStatusMonstroP1();
                    } else {
                        mudaStatusMonstroP2();
                    }
                    System.out.println("\nDeseja realizar mais uma acao ou passar a vez?");
                    break;
                case 4:
                    if (acoes % 2 == 0) {
                        combateP1();
                    } else {
                        combateP2();
                    }
                    System.out.println("\nDeseja realizar mais uma acao ou passar a vez?");
                    break;
                case 5:
                    break;
            }
        } while (escolha != 5);
    }

    //Método que permite ao Jogador 1 alterar o status de ataque/defesa de uma carta do seu tabuleiro:
    public static void mudaStatusMonstroP1() {
        //Inicializa a variável
        int indice;
        //O jogador é solicitado a selecionar uma carta do seu tabuleiro para alterar o status:
        System.out.println("Qual carta deseja alterar o status?\n" + TabuleiroJ1 + "\nSelecione o indice da carta (lembrando que a primeira carta e o indice 0)");
        //O jogador seleciona um índice da carta a ser alterada.
        indice = teclado.nextInt();
        //É verificado status atual da carta selecionada. Caso for defesa, é alterado para ataque e vice versa:
        if (TabuleiroJ1.get(indice).getStatus() == "defesa") {
            System.out.println("Essa carta possuia status de defesa, agora ela esta em modo de ataque");
            TabuleiroJ1.get(indice).setStatus("ataque");
        } else {
            System.out.println("Essa carta possuia status de ataque, agora ela esta em modo de defesa");
            TabuleiroJ1.get(indice).setStatus("defesa");
        }
    }

    //Método com a mesma função e lógica do anterior, mas se aplica ao Jogador 2:
    public static void mudaStatusMonstroP2() {
        int indice;
        System.out.println("Qual carta deseja alterar o status?\n" + TabuleiroJ2 + "\nSelecione o indice da carta (lembrando que a primeira carta e o indice 0)");
        indice = teclado.nextInt();
        if (TabuleiroJ2.get(indice).getStatus() == "defesa") {
            System.out.println("Essa carta possuia status de defesa, agora ela esta em modo de ataque");
            TabuleiroJ2.get(indice).setStatus("ataque");
        } else {
            System.out.println("Essa carta possuia status de ataque, agora ela esta em modo de defesa");
            TabuleiroJ2.get(indice).setStatus("defesa");
        }
    }

    //Método que lida com o processo de combate:
    public static void combateP1() {
        //Criação de variáveis:
        int indice1;
        int indice2;
        Vector<Cartas> MonstroDisponiveisP1 = new Vector<>();
        MonstroDisponiveisP1 = TabuleiroJ1;
        System.out.println("\nOBS: Lembrando que cada monstro tem direito a um ataque por rodada!");
        do {
            MonstroDisponiveisP1 = TabuleiroJ1;
            //Se o tabuleiro estiver vazio, é informado que não há monstros para atacar:
            if (TabuleiroJ1.isEmpty() == true) {
                System.out.println("Nao há monstros no tabuleiro. Volte ao menu e posicione-os");
                break;
            }
            System.out.println(Player1.getNome() + ", qual carta disponivel do seu tabuleiro você deseja usar para atacar o oponente? Selecione o indice da mesma.");
            System.out.println("Seu campo: " + MonstroDisponiveisP1);
            //O jogador escolhe uma carta disponível para atacar do seu tabuleiro :
            indice1 = teclado.nextInt();
            //É feita uma validação para garantir que a carta está em modo de ataque.
            //Se estiver em modo de defesa, é pedido para escolher outra carta ou mudar o modo da escolhida:
            if (TabuleiroJ1.get(indice1).getStatus().equalsIgnoreCase("defesa")) {
                System.out.println("Sua carta está em modo de defesa. Para poder atacar, sua carta precisa estar em modo de ataque, por isso, tente mudar o status da carta...");
                System.out.println("Essa carta possuia status de defesa, agora ela esta em modo de ataque");
                TabuleiroJ1.get(indice1).setStatus("ataque");
            }
            //Se extiver em modo de ataque, o jogo continua:
            if (TabuleiroJ1.get(indice1).getStatus().equalsIgnoreCase("ataque")) {
                System.out.println("Você atacará com a seguinte carta: " + MonstroDisponiveisP1.get(indice1));
                //Se o tabuleiro do Jogador 2 não estiver vazio, o jogador é solicitado a escolher uma carta alvo:
                if (!TabuleiroJ2.isEmpty() == true) {
                    System.out.println("Voce deseja atacar qual carta do oponente?");
                    System.out.println("Campo adversário: " + TabuleiroJ2);
                    indice2 = teclado.nextInt();
                    System.out.println("Você atacará a seguinte carta: " + TabuleiroJ2.get(indice2) + " e, ela está no modo de " + TabuleiroJ2.get(indice2).getStatus());
                    //É feita uma validação para verificar se a carta alvo está no modo de ataque ou defesa:
                    if (TabuleiroJ2.get(indice2).getStatus().equalsIgnoreCase("ataque")) {
                        //Caso seu ataque seja maior que o ataque do oponente, ele perde o monstro e x pontos de vida:
                        if (TabuleiroJ1.get(indice1).getAtaque() > TabuleiroJ2.get(indice2).getAtaque()) {
                            double diferenca = (TabuleiroJ1.get(indice1).getAtaque()) - (TabuleiroJ2.get(indice2).getAtaque());
                            Player2.setVida(Player2.getVida() - diferenca);
                            System.out.println("Parabéns, belo ataque! O oponente perdeu " + diferenca + " pontos de vida e perdeu um monstro.");
                            TabuleiroJ2.remove(indice2);
                        }
                        //Caso o ataque seja menor que o ataque do oponente, você perde a carta usada e x pontos de vida:
                        if (TabuleiroJ1.get(indice1).getAtaque() < TabuleiroJ2.get(indice2).getAtaque()) {
                            double diferenca2 = (TabuleiroJ2.get(indice2).getAtaque()) - (TabuleiroJ1.get(indice1).getAtaque());
                            Player1.setVida(Player1.getVida() - diferenca2);
                            System.out.println("Vish, o ataque da sua carta é menor do que o ataque do oponente.");
                            System.out.println("Você perderá " + diferenca2 + " pontos de vida e o respectivo monstro");
                            TabuleiroJ1.remove(indice1);
                        }
                    }
                    if ((TabuleiroJ2.get(indice2).getStatus().equalsIgnoreCase("defesa"))) {
                        //Caso seu ataque seja maior que a defesa do oponente, ele perde apenas o monstro:
                        if (TabuleiroJ1.get(indice1).getAtaque() > TabuleiroJ2.get(indice2).getDefesa()) {
                            System.out.println("Parabéns, belo ataque! O oponente perdeu um monstro.");
                            TabuleiroJ2.remove(indice2);
                        }
                        //Caso seu ataque seja menor que a defesa do oponente, você perde o x pontos de vida
                        if (TabuleiroJ1.get(indice1).getAtaque() < TabuleiroJ2.get(indice2).getDefesa()) {
                            double diferenca2 = (TabuleiroJ2.get(indice2).getDefesa()) - (TabuleiroJ1.get(indice1).getAtaque());
                            Player1.setVida(Player1.getVida() - diferenca2);
                            System.out.println("Vish, o ataque da sua carta é menor do que a defesa do oponente.");
                            System.out.println("Você perderá " + diferenca2 + " pontos de vida.");
                        }
                    }
                    //Caso o tabuleiro do oponente esteja vazio, você causará dano direto na vida do oponente
                } else {
                    System.out.println("O adversário não possui nenhum monstro posicionado. Você causará dano direto no oponente!");
                    Player2.setVida(Player2.getVida() - TabuleiroJ1.get(indice1).getAtaque());
                }
            }
            //O combate continua enquanto houver cartas disponíveis no tabuleiro do Jogador 1 
        } while (MonstroDisponiveisP1.isEmpty() == true);
    }

    //Método que lida com o processo de combate, com funcionamento e lógica igual ao "combateP1", porém para o Jogador 2:
    public static void combateP2() {
        int indice1;
        int indice2;
        Vector<Cartas> MonstroDisponiveisP2 = new Vector<>();
        MonstroDisponiveisP2 = TabuleiroJ2;
        System.out.println("\nOBS: Lembrando que cada monstro tem direito a um ataque por rodada!");
        do {
            if (TabuleiroJ2.isEmpty() == true) {
                System.out.println("Nao ha monstros no seu tabuleiro. Volte ao menu e posicione-os");
                break;
            }
            System.out.println(Player2.getNome() + ", qual carta disponivel do seu tabuleiro você deseja usar para atacar o oponente? Selecione o indice da mesma.");
            System.out.println("Seu campo: " + MonstroDisponiveisP2);
            indice1 = teclado.nextInt();
            if (MonstroDisponiveisP2.get(indice1).getStatus().equalsIgnoreCase("defesa")) {
                System.out.println("Sua carta está em modo de defesa. Para poder atacar, sua carta precisa estar em modo de ataque, por isso, tente mudar o status da carta...");
                System.out.println("Essa carta possuia status de defesa, agora ela esta em modo de ataque");
                TabuleiroJ2.get(indice1).setStatus("ataque");
            }
            if (MonstroDisponiveisP2.get(indice1).getStatus().equalsIgnoreCase("ataque")) {
                System.out.println("Você atacará com a seguinte carta: " + MonstroDisponiveisP2.get(indice1));
                if (!TabuleiroJ1.isEmpty() == true) {
                    System.out.println("Voce deseja atacar qual carta do oponente?");
                    System.out.println("Campo adversário: " + TabuleiroJ1);
                    indice2 = teclado.nextInt();
                    System.out.println("Você atacará a seguinte carta: " + TabuleiroJ1.get(indice2) + " e, ela está no modo de " + TabuleiroJ1.get(indice2).getStatus());
                    if (TabuleiroJ1.get(indice2).getStatus().equalsIgnoreCase("ataque")) {
                        if (MonstroDisponiveisP2.get(indice1).getAtaque() > TabuleiroJ1.get(indice2).getAtaque()) {
                            double diferenca = (MonstroDisponiveisP2.get(indice1).getAtaque()) - (TabuleiroJ1.get(indice2).getAtaque());
                            Player1.setVida(Player1.getVida() - diferenca);
                            System.out.println("Parabéns, belo ataque! O oponente perdeu " + diferenca + " pontos de vida e perdeu um monstro.");
                            TabuleiroJ1.remove(indice2);
                        }
                        if (MonstroDisponiveisP2.get(indice1).getAtaque() < TabuleiroJ1.get(indice2).getAtaque()) {
                            double diferenca2 = (TabuleiroJ1.get(indice2).getAtaque()) - (MonstroDisponiveisP2.get(indice1).getAtaque());
                            Player2.setVida(Player2.getVida() - diferenca2);
                            System.out.println("Vish, o ataque da sua carta é menor do que o ataque do oponente.");
                            System.out.println("Você perderá " + diferenca2 + " pontos de vida e o respectivo monstro");
                            TabuleiroJ2.remove(indice1);
                        }
                    }
                    if ((TabuleiroJ1.get(indice2).getStatus().equalsIgnoreCase("defesa"))) {
                        if (MonstroDisponiveisP2.get(indice1).getAtaque() > TabuleiroJ1.get(indice2).getDefesa()) {
                            System.out.println("Parabéns, belo ataque! O oponente perdeu um monstro.");
                            TabuleiroJ1.remove(indice2);
                        }
                        if (MonstroDisponiveisP2.get(indice1).getAtaque() < TabuleiroJ1.get(indice2).getDefesa()) {
                            double diferenca2 = (TabuleiroJ1.get(indice2).getDefesa()) - (MonstroDisponiveisP2.get(indice1).getAtaque());
                            Player2.setVida(Player2.getVida() - diferenca2);
                            System.out.println("Vish, o ataque da sua carta é menor do que a defesa do oponente.");
                            System.out.println("Você perderá " + diferenca2 + " pontos de vida.");
                        }
                    }
                } else {
                    System.out.println("O adversário não possui nenhum monstro posicionado. Você causará dano direto no oponente!");
                    Player1.setVida(Player1.getVida() - MonstroDisponiveisP2.get(indice1).getAtaque());
                }
            }
        } while (MonstroDisponiveisP2.isEmpty() == true);
    }

    //Método que permite que melhore as estatísticas de suas cartas no tabuleiro através do uso de equipamentos disponíveis em seu deck:
    public static void equipamentoP1() {
        //Criação de variáveis:
        int indice1;
        int indice2;
        Vector<Cartas> equipDisponiveisP1 = new Vector<>();
        //Método percorre o deck do Jogador 1 e adiciona os equipamentos encontrados no vetor "equipDisponiveisP1":
        for (int i = 0; i < deckP1.size(); i++) {
            if (deckP1.get(i).getTipo().equalsIgnoreCase("equipamento")) {
                equipDisponiveisP1.add(deckP1.get(i));
            }
        }
        //Verifica se há equipamentos disponíveis no vetor "equipDisponiveisP1" e, caso não tenha, uma mensagem informando o jogador é mostrada:
        if (equipDisponiveisP1.isEmpty() == true) {
            System.out.println("Você não tem equipamentos no seu deck! Infelizmente não poderá equipar nessa rodada");
        }
        //Verifica se o tabuleiro do Jogador 1 está vazio e, caso não esteja, uma mensagem informando o jogador é mostrada:
        if (TabuleiroJ1.isEmpty() == true) {
            System.out.println("Voce nao tem cartas posicionadas! Nao há o que melhorar...");
        } else {
            System.out.println("\nSeu deck: " + equipDisponiveisP1);
            System.out.println("Selecione o indice do equipamento que deseja utilizar: ");
            //É pedido que o Jogador 1 selecione o equipamento:
            indice1 = teclado.nextInt();
            System.out.println("E qual carta do seu tabuleiro deseja melhorar?");
            System.out.println(TabuleiroJ1);
            //É pedido que o Jogador 1 selecione o a carta que será melhorada:
            indice2 = teclado.nextInt();
            System.out.println("Equipamento: " + equipDisponiveisP1.get(indice1) + "\n"
                    + "Carta a melhorar: " + TabuleiroJ1.get(indice2));
            //É adicionado o ataque e defesa do equipamento na carta selecionada para melhoria:
            TabuleiroJ1.get(indice2).setAtaque((TabuleiroJ1.get(indice2).getAtaque()) + (equipDisponiveisP1.get(indice1).getAtaque()));
            TabuleiroJ1.get(indice2).setDefesa((TabuleiroJ1.get(indice2).getAtaque()) + (equipDisponiveisP1.get(indice1).getAtaque()));
            System.out.println("Carta melhorada com sucesso!");
            //É removido o equipamento usado do deck do Jogador 1:
            deckP1.remove(equipDisponiveisP1.get(indice1));
        }
    }

    //Método de melhorias com mesma lógica e funcionamento do "equipamentoP1", porém para o Jogador 2:
    public static void equipamentoP2() {
        int indice1;
        int indice2;
        Vector<Cartas> equipDisponiveisP2 = new Vector<>();
        for (int i = 0; i < deckP2.size(); i++) {
            if (deckP2.get(i).getTipo().equalsIgnoreCase("equipamento")) {
                equipDisponiveisP2.add(deckP2.get(i));
            }
        }
        if (equipDisponiveisP2.isEmpty() == true) {
            System.out.println("Você não tem equipamentos no seu deck! Infelizmente não poderá equipar nessa rodada");
        }
        if (TabuleiroJ2.isEmpty() == true) {
            System.out.println("Voce nao tem cartas posicionadas! Nao há o que melhorar...");
        } else {
            System.out.println("\nSeu deck: " + equipDisponiveisP2);
            System.out.println("Selecione o indice do equipamento que deseja utilizar: ");
            indice1 = teclado.nextInt();
            System.out.println("E qual carta do seu tabuleiro deseja melhorar?");
            System.out.println(TabuleiroJ2);
            indice2 = teclado.nextInt();
            System.out.println("Equipamento: " + equipDisponiveisP2.get(indice1) + "\n"
                    + "Carta a melhorar: " + TabuleiroJ2.get(indice2));
            TabuleiroJ2.get(indice2).setAtaque((TabuleiroJ2.get(indice2).getAtaque()) + (equipDisponiveisP2.get(indice1).getAtaque()));
            TabuleiroJ2.get(indice2).setDefesa((TabuleiroJ2.get(indice2).getAtaque()) + (equipDisponiveisP2.get(indice1).getAtaque()));
            System.out.println("Carta melhorada com sucesso!");
            deckP1.remove(equipDisponiveisP2.get(indice1));
        }
    }
}
