package tp_cartas;

public class Cartas {

    //Criação das variáveis usadas:
    protected String nome;        
    protected String descricao;    
    protected double ataque;       
    protected double defesa;        
    protected String tipo;          
    protected String status;     

    //Construtor da classe:
    public Cartas(String nome, String descricao, double ataque, double defesa, String tipo, String status) {
        this.nome = nome;
        this.descricao = descricao;
        this.ataque = ataque;
        this.defesa = defesa;       
        this.tipo = tipo;
        this.status = status;
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

    public String getTipo() {
        return tipo;
    }

    public String getStatus() {
        return status;
    }

    //Métodos Set que permitem alterar os valores desses atributos:
    public void setStatus(String status) {
        this.status = status;
    }

    public void setAtaque(double ataque) {
        this.ataque = ataque;
    }

    public void setDefesa(double defesa) {
        this.defesa = defesa;
    }

    //Método toString que retorna apenas o nome da carta de forma legível em string:
    @Override
    public String toString() {
        return nome;
    }
}
