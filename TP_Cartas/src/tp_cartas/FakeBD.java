package tp_cartas;

import java.util.Scanner;
import java.util.Vector;

public class FakeBD {

    //Criação das variáveis usadas:
    private Vector<Cartas> baralho;
    private Vector<Jogador> tabelaNickname = new Vector<>();

    //Construtor que aceita um vetor de cartas como parâmetro e inicializa o atributo baralho com esse valor:
    public FakeBD(Vector<Cartas> baralho) {
        this.baralho = baralho;
    }

    //Construtor usado para criar uma instância da classe FakeBD sem nenhum parâmetro:
    public FakeBD() {
        this.baralho = new Vector<>();
        this.tabelaNickname = new Vector<>();
        ListaNickEscolha();
    }

    //Método que preenche a tabela de nicknames com objetos da classe Jogador
    public void ListaNickEscolha() {
        this.tabelaNickname.add(new Jogador("BadBoy"));
        this.tabelaNickname.add(new Jogador("Cowboy"));
        this.tabelaNickname.add(new Jogador("Boss"));
        this.tabelaNickname.add(new Jogador("Gasoline"));
        this.tabelaNickname.add(new Jogador("Gangster"));
        this.tabelaNickname.add(new Jogador("Godzilla"));
        this.tabelaNickname.add(new Jogador("Harvard"));
        this.tabelaNickname.add(new Jogador("Hero"));
        this.tabelaNickname.add(new Jogador("Hercules"));
        this.tabelaNickname.add(new Jogador("Hollywood"));
        this.tabelaNickname.add(new Jogador("Hoss"));
        this.tabelaNickname.add(new Jogador("Hunk"));
        this.tabelaNickname.add(new Jogador("Jedi"));
        this.tabelaNickname.add(new Jogador("Killer"));
    }

    //Métodos Get que permitem acessar os valores dos atributos privados da classe:
    public Vector<Jogador> getTabelaNickname() {
        return tabelaNickname;
    }

    public Vector<Cartas> getBaralho() {
        return baralho;
    }

    //Método que permite adicionar novas cartas ao baralho. Aceitaum objeto da classe Cartas
    //como parâmetro e adiciona ao vetor baralho:
    public void insereNovaCarta(Cartas novaCarta) {
        this.baralho.add(novaCarta);
    }

 
}
