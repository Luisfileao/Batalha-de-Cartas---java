package tp_cartas;

public class Jogador {

    //Criação das variáveis usadas:
    private String nome;
    private double vida;

    //Construtor usado para criar um objeto de jogador, que possui nome e vida de 10000
    public Jogador(String nome) {
        this.nome = nome;
        this.vida = 10000.0;
    }

    //Métodos Get que permitem acessar os valores dos atributos privados da classe:
    public String getNome() {
        return nome;
    }

    public double getVida() {
        return vida;
    }

    //Método Set que permite alterar os valores desse atributo:
    public void setVida(double vida) {
        this.vida = vida;
    }

    //Método toString que retorna apenas o nome do jogador de forma legível em string:
    @Override
    public String toString() {
        return nome;
    }
}
