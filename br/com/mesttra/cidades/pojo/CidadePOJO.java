package br.com.mesttra.cidades.pojo;

public class CidadePOJO {
    private final int ddd;
    private final String nome;
    private final int nroHabitantes;
    private final double rendaPerCapita;
    private final boolean capital;
    private final String estado;
    private final String nomePrefeito;

    public CidadePOJO(int ddd, String nome, int nroHabitantes, double rendaPerCapita, boolean capital, String estado, String nomePrefeito) {
        this.ddd = ddd;
        this.nome = nome;
        this.nroHabitantes = nroHabitantes;
        this.rendaPerCapita = rendaPerCapita;
        this.capital = capital;
        this.estado = estado;
        this.nomePrefeito = nomePrefeito;
    }

    public int getDdd() {
        return ddd;
    }

    public String getNome() {
        return nome;
    }

    public int getNroHabitantes() {
        return nroHabitantes;
    }

    public double getRendaPerCapita() {
        return rendaPerCapita;
    }

    public boolean isCapital() {
        return capital;
    }

    public String getEstado() {
        return estado;
    }

    public String getNomePrefeito() {
        return nomePrefeito;
    }
}
