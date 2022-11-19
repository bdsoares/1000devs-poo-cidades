package br.com.mesttra.cidades.dao;

import br.com.mesttra.cidades.connectionfactory.ConnectionFactory;
import br.com.mesttra.cidades.pojo.CidadePOJO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CidadeDAO {
    private final Connection conn;

    public CidadeDAO() {
        this.conn = new ConnectionFactory().getConnection();
    }

    public boolean insertCidade(CidadePOJO cidade) throws SQLException {
        String sql = "INSERT INTO cidades (ddd, nome, nro_habitantes, renda_per_capita, capital, estado, nome_prefeito) " +
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
        } catch (Exception ex) {
            System.out.println("Erro ao realizar inserção!");
            System.out.println(ex.getMessage());
        }

        return false;
    }

    public boolean deleteCidade(int ddd) throws SQLException {
        String sql = "DELETE FROM cidades WHERE ddd = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, ddd);
            stmt.execute();

            return true;
        } catch (Exception ex) {
            System.out.println("Erro ao realizar remoção!");
            System.out.println(ex.getMessage());
        }

        return false;
    }

    public ArrayList<CidadePOJO> selectCidades() {
        String sql = "SELECT * FROM cidades";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            return returnCidades(rs);
        } catch (Exception ex) {
            System.out.println("Erro ao realizar consulta!");
            System.out.println(ex.getMessage());
        }

        return null;
    }

    public ArrayList<CidadePOJO> buscaCidades(int tipoBusca, String params) {
        String sql = "SELECT * FROM cidades WHERE ";

        switch (tipoBusca) {
            case 1 -> sql += "ddd = ?";
            case 2 -> sql += "nome like ?";
            case 3 -> sql += "estado like ?";
            case 4 -> sql += "capital = ?";
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            switch (tipoBusca) {
                case 1 -> stmt.setInt(1, Integer.parseInt(params));
                case 2, 3 -> stmt.setString(1, "%" + params + "%");
                case 4 -> stmt.setBoolean(1, params.equalsIgnoreCase("S"));
                default -> { return null; }
            }

            ResultSet rs = stmt.executeQuery();
            return returnCidades(rs);
        } catch(Exception ex) {
            System.out.println("Erro ao realizar busca!");
            System.out.println(ex.getMessage());
        }

        return null;
    }

    private ArrayList<CidadePOJO> returnCidades(ResultSet rs) throws SQLException {
        ArrayList<CidadePOJO> cidades = new ArrayList<>();

        while (rs.next()) {
            CidadePOJO cidade = new CidadePOJO(
                    rs.getInt("ddd"),
                    rs.getString("nome"),
                    rs.getInt("nro_habitantes"),
                    rs.getDouble("renda_per_capita"),
                    rs.getBoolean("capital"),
                    rs.getString("estado"),
                    rs.getString("nome_prefeito")
            );

            cidades.add(cidade);
        }

        return cidades;
    }
}
