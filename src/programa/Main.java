package programa;

import dao.GastosDaoJdbc;
import dao.LucrosDaoJdbc;
import dao.imp.GastosDao;
import dao.imp.LucroDao;
import db.DB;
import entidades.Gastos;
import entidades.Lucros;

import java.sql.Connection;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        Connection conn = DB.conectar();

        GastosDao gastosDao = new GastosDaoJdbc(conn);
        LucroDao lucrosDao = new LucrosDaoJdbc(conn);

        int opcao = -1;

        while (opcao != 0) {

            System.out.println("\n==============================");
            System.out.println("      SISTEMA FINANCEIRO      ");
            System.out.println("==============================");
            System.out.println("1 - Inserir Lucro");
            System.out.println("2 - Inserir Gasto");
            System.out.println("3 - Listar Lucros");
            System.out.println("4 - Listar Gastos");
            System.out.println("5 - Atualizar Lucro");
            System.out.println("6 - Atualizar Gasto");
            System.out.println("7 - Deletar Lucro");
            System.out.println("8 - Deletar Gasto");
            System.out.println("9 - Ver Saldo Total");
            System.out.println("10 - Ver Lucros por Mes");
            System.out.println("11 - Ver Gastos por Mes");
            System.out.println("12 - Ver Saldo por Mes");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {

                case 1:

                    System.out.println("\n--- Inserir Lucro ---");

                    System.out.print("Descricao: ");
                    String descLucro = sc.nextLine();

                    System.out.print("Categoria: ");
                    String catLucro = sc.nextLine();

                    System.out.print("Valor: ");
                    double valorLucro = sc.nextDouble();
                    sc.nextLine();

                    Lucros lucro = new Lucros();
                    lucro.setDescricao(descLucro);
                    lucro.setCategoria(catLucro);
                    lucro.setValor(valorLucro);
                    lucro.setData(new Date());

                    lucrosDao.insert(lucro);

                    System.out.println("Lucro inserido!");
                    break;

                case 2:

                    System.out.println("\n--- Inserir Gasto ---");

                    System.out.print("Descricao: ");
                    String descGasto = sc.nextLine();

                    System.out.print("Categoria: ");
                    String catGasto = sc.nextLine();

                    System.out.print("Valor: ");
                    double valorGasto = sc.nextDouble();
                    sc.nextLine();

                    Gastos gasto = new Gastos();
                    gasto.setDescricao(descGasto);
                    gasto.setCategoria(catGasto);
                    gasto.setValor(valorGasto);
                    gasto.setData(new Date());

                    gastosDao.insert(gasto);

                    System.out.println("Gasto inserido!");
                    break;

                case 3:

                    System.out.println("\n--- Lista de Lucros ---");

                    List<Lucros> lucros = lucrosDao.findAll();

                    for (Lucros l : lucros) {
                        System.out.println(l);
                    }

                    break;

                case 4:

                    System.out.println("\n--- Lista de Gastos ---");

                    List<Gastos> gastos = gastosDao.findAll();

                    for (Gastos g : gastos) {
                        System.out.println(g);
                    }

                    break;

                case 5:

                    System.out.println("\n--- Atualizar Lucro ---");

                    System.out.print("ID do lucro: ");
                    int idLucro = sc.nextInt();

                    System.out.print("Valor para adicionar: ");
                    double novoValorLucro = sc.nextDouble();

                    Lucros upLucro = new Lucros();
                    upLucro.setId(idLucro);
                    upLucro.setValor(novoValorLucro);

                    lucrosDao.update(upLucro);

                    System.out.println("Lucro atualizado!");
                    break;

                case 6:

                    System.out.println("\n--- Atualizar Gasto ---");

                    System.out.print("ID do gasto: ");
                    int idGasto = sc.nextInt();

                    System.out.print("Valor para adicionar: ");
                    double novoValorGasto = sc.nextDouble();

                    Gastos upGasto = new Gastos();
                    upGasto.setId(idGasto);
                    upGasto.setValor(novoValorGasto);

                    gastosDao.update(upGasto);

                    System.out.println("Gasto atualizado!");
                    break;

                case 7:

                    System.out.println("\n--- Deletar Lucro ---");

                    System.out.print("ID do lucro: ");
                    int delLucro = sc.nextInt();

                    lucrosDao.deleteById(delLucro);

                    System.out.println("Lucro deletado!");
                    break;

                case 8:

                    System.out.println("\n--- Deletar Gasto ---");

                    System.out.print("ID do gasto: ");
                    int delGasto = sc.nextInt();

                    gastosDao.deleteById(delGasto);

                    System.out.println("Gasto deletado!");
                    break;

                case 9:

                    System.out.println("\n--- Saldo Total ---");

                    double totalLucro = 0;
                    double totalGasto = 0;

                    for (Lucros l : lucrosDao.findAll()) {
                        totalLucro += l.getValor();
                    }

                    for (Gastos g : gastosDao.findAll()) {
                        totalGasto += g.getValor();
                    }

                    System.out.println("Total Lucros: " + totalLucro);
                    System.out.println("Total Gastos: " + totalGasto);
                    System.out.println("Saldo: " + (totalLucro - totalGasto));

                    break;

                case 10:

                    System.out.print("Digite o mes (1-12): ");
                    int mesLucro = sc.nextInt();

                    for (Lucros l : lucrosDao.findAll()) {

                        Calendar cal = Calendar.getInstance();
                        cal.setTime(l.getData());

                        int mes = cal.get(Calendar.MONTH) + 1;

                        if (mes == mesLucro) {
                            System.out.println(l);
                        }
                    }

                    break;

                case 11:

                    System.out.print("Digite o mes (1-12): ");
                    int mesGasto = sc.nextInt();

                    for (Gastos g : gastosDao.findAll()) {

                        Calendar cal = Calendar.getInstance();
                        cal.setTime(g.getData());

                        int mes = cal.get(Calendar.MONTH) + 1;

                        if (mes == mesGasto) {
                            System.out.println(g);
                        }
                    }

                    break;

                case 12:

                    System.out.print("Digite o mes (1-12): ");
                    int mesSaldo = sc.nextInt();

                    double lucrosMes = 0;
                    double gastosMes = 0;

                    for (Lucros l : lucrosDao.findAll()) {

                        Calendar cal = Calendar.getInstance();
                        cal.setTime(l.getData());

                        int mes = cal.get(Calendar.MONTH) + 1;

                        if (mes == mesSaldo) {
                            lucrosMes += l.getValor();
                        }
                    }

                    for (Gastos g : gastosDao.findAll()) {

                        Calendar cal = Calendar.getInstance();
                        cal.setTime(g.getData());

                        int mes = cal.get(Calendar.MONTH) + 1;

                        if (mes == mesSaldo) {
                            gastosMes += g.getValor();
                        }
                    }

                    System.out.println("Lucros no mes: " + lucrosMes);
                    System.out.println("Gastos no mes: " + gastosMes);
                    System.out.println("Saldo do mes: " + (lucrosMes - gastosMes));

                    break;

                case 0:
                    System.out.println("\nSaindo do sistema...");
                    break;

                default:
                    System.out.println("Opcao invalida!");
            }
        }

        DB.Desconectar();
        sc.close();
    }
}