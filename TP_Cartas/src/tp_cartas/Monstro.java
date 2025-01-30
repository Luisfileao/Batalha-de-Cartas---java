package tp_cartas;

import java.util.Scanner;

//Subclasse da classe "Cartas",  herda todos os atributos e métodos públicos da classe:
public class Monstro extends Cartas {

    //Construtor usado para criar objetos de carta do tipo "Monstro":
    public Monstro(String nome, String descricao, double ataque, double defesa, String tipo, String status) {
        super(nome, descricao, ataque, defesa, tipo, status);
    }

    //Métodos Get que permitem acessar os valores dos atributos privados da classe:
    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getAtaque() {
        return ataque;
    }

    public double getDefesa() {
        return defesa;
    }

    //Métodos Set que permitem alterar os valores desses atributos
    public void setAtaque(double ataque) {
        this.ataque = ataque;
    }

    public void setDefesa(double defesa) {
        this.defesa = defesa;
    }

}
