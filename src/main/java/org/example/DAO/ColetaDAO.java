package org.example.DAO;

import org.example.Connection.ConexaoFactory;
import org.example.model.Coleta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ColetaDAO {

    public void inserir(Coleta coleta) throws SQLException {
        String sql = "INSERT INTO T_SST_COLETA (id_coleta, tipo_residuo, peso_kg, data_coleta, status_coleta, pontos_gerados) " +
                "VALUES (seq_coleta.NEXTVAL, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, coleta.getTipoResiduo());
            stmt.setDouble(2, coleta.getPesoKg());
            stmt.setDate(3, coleta.getDataColeta());
            stmt.setString(4, coleta.getStatusColeta());
            stmt.setInt(5, coleta.getPontosGerados());

            stmt.executeUpdate();
        }
    }

    public List<Coleta> listar() throws SQLException {
        List<Coleta> lista = new ArrayList<>();
        String sql = "SELECT * FROM T_SST_COLETA ORDER BY id_coleta";

        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Coleta c = new Coleta(
                        rs.getLong("id_coleta"),
                        rs.getString("tipo_residuo"),
                        rs.getDouble("peso_kg"),
                        rs.getDate("data_coleta"),
                        rs.getString("status_coleta"),
                        rs.getInt("pontos_gerados")
                );
                lista.add(c);
            }
        }
        return lista;
    }

    public void atualizar(Coleta coleta) throws SQLException {
        String sql = "UPDATE T_SST_COLETA SET tipo_residuo = ?, peso_kg = ?, status_coleta = ?, pontos_gerados = ? WHERE id_coleta = ?";

        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, coleta.getTipoResiduo());
            stmt.setDouble(2, coleta.getPesoKg());
            stmt.setString(3, coleta.getStatusColeta());
            stmt.setInt(4, coleta.getPontosGerados());
            stmt.setLong(5, coleta.getId());

            stmt.executeUpdate();
        }
    }

    public void deletar(Long id) throws SQLException {
        String sql = "DELETE FROM T_SST_COLETA WHERE id_coleta = ?";

        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
}