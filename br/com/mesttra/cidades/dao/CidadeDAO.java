package br.com.mesttra.cidades.dao;

import br.com.mesttra.cidades.connectionfactory.ConnectionFactory;
import br.com.mesttra.cidades.pojo.CidadePOJO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CidadeDAO {
    private final Connection conn;

    public CidadeDAO() {
        this.conn = new ConnectionFactory().getConnection();
    }

    public boolean insertCidade(CidadePOJO cidade) throws SQLException {
        String sql = "INSERT INTO cidade (ddd, nome, nro_habitantes, renda_per_capita, capital, estado, nome_prefeito) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cidade.getDdd());
            stmt.setString(2, cidade.getNome());
            stmt.setInt(3, cidade.getNroHabitantes());
            stmt.setDouble(4, cidade.getRendaPerCapita());
            stmt.setBoolean(5, cidade.isCapital());
            stmt.setString(6, cidade.getEstado());
            stmt.setString(7, cidade.getNomePrefeito());

            stmt.execute();
            return true;
        } catch(Exception ex) {
            System.out.println("Erro ao realizar inserção!");
            System.out.println(ex.getMessage());
        }

        return false;
    }

    public boolean deleteCidade(int ddd) throws SQLException {
        String sql = "DELETE FROM cidade WHERE ddd = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, ddd);
            stmt.execute();

            return true;
        } catch(Exception ex) {
            System.out.println("Erro ao realizar remoção!");
            System.out.println(ex.getMessage());
        }

        return false;
    }
}
