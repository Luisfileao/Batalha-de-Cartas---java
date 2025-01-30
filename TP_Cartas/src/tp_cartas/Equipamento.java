package tp_cartas;

//Subclasse da classe "Cartas",  herda todos os atributos e métodos públicos da classe 
public class Equipamento extends Cartas {

    //Construtor da classe:
    public Equipamento(String nome, String descricao, double ataque, double defesa, String tipo, String status) {
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
}
