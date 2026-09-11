package org.example.Main;

import org.example.DAO.ColetaDAO;
import org.example.model.Coleta;

import java.sql.Date;
import java.util.List;

public class TesteSistema {

    public static void main(String[] args) {
        ColetaDAO dao = new ColetaDAO();

        try {
            Coleta novaColeta = new Coleta();
            novaColeta.setTipoResiduo("ELETRONICO");
            novaColeta.setPesoKg(12.5);
            novaColeta.setDataColeta(new Date(System.currentTimeMillis()));
            novaColeta.setStatusColeta("PENDENTE");

            novaColeta.processarDescarte();

            System.out.println("Estimativa de CO2 evitado: " + novaColeta.estimarReducaoCO2() + " kg");


            dao.inserir(novaColeta);
            System.out.println("Coleta cadastrada com sucesso!");

            System.out.println("\n--- Registros no Banco ---");
            List<Coleta> lista = dao.listar();
            for (Coleta c : lista) {
                System.out.println(c);
            }

        } catch (Exception e) {
            System.err.println("Erro ao executar teste: " + e.getMessage());
            e.printStackTrace();
        }
    }
}