/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Conexion.Conexion;
import Modelos.ModeloDatos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author monco
 */
public class Operaciones {

    public static void main(String[] args) {

        int id = 2; // Ejemplo de ID
        ModeloDatos datos = obtenerDatos(id);

        // VARIABLES
        ArrayList<Integer> nacimientos = datos.getListaNacimiento();
        ArrayList<Integer> defunciones = datos.getListaDefuncion();
        ArrayList<Integer> migrantes = datos.getListaMigrantes();
        ArrayList<Integer> emigrantes = datos.getListaEmigrantes();
        ArrayList<Double> Ni = datos.getListaInicioP();

        // CALCULOS HISTORICOS
        ArrayList<Double> crecimientoVegetativo = calcularCrecimientoVegetativo(nacimientos, defunciones); // Natural
        ArrayList<Double> saldoMigratorio = calcularSaldoMigratorio(migrantes, emigrantes); // Social
        ArrayList<Double> Nf = calcularCrecimientoTotal(Ni, crecimientoVegetativo, saldoMigratorio); // Población final

        Scanner sc = new Scanner(System.in);
        System.out.print("\n¿Escriba la cantidad de años a proyectar? ");
        int aniosP = sc.nextInt();
        // Tasa relatia de poblacion 
        double rAritmetica = calcularTasaAritmetica(Ni, Nf);
        double rGeometrica = calcularTasaGeometrica(Ni, Nf);
        double rExponencial = calcularTasaExponencial(Ni, Nf);
        // Modelos
        ArrayList<Double> datArit = calcularModeloAritmetico(Ni.get(Ni.size() - 1), rAritmetica, aniosP);
        ArrayList<Double> datGeo = calcularModeloGeometrico(Ni.get(Ni.size() - 1), rGeometrica, aniosP);
        ArrayList<Double> datEx = calcularModeloExponencial(Ni.get(Ni.size() - 1), rExponencial, aniosP);

        //porcentajeCrecimiento(Ni, Nf,aniosP);
    }

    // Crecimiento natural
    public static ArrayList<Double> calcularCrecimientoVegetativo(ArrayList<Integer> nacimientos, ArrayList<Integer> defunciones) {
        System.out.println("\n--- Crecimiento Vegetativo ---");
        ArrayList<Double> crecimientoVegetativo = new ArrayList<>();

        if (nacimientos.size() != defunciones.size()) {
            throw new IllegalArgumentException("Las listas de nacimientos y defunciones deben tener el mismo tamaño");
        }

        for (int i = 0; i < nacimientos.size(); i++) {
            double crecimientoV = nacimientos.get(i) - defunciones.get(i);
            crecimientoVegetativo.add(crecimientoV);
            System.out.println("Año " + (2010 + i) + ": Crecimiento vegetativo = " + crecimientoV);
        }
        return crecimientoVegetativo;
    }

    // Crecimiento social
    public static ArrayList<Double> calcularSaldoMigratorio(ArrayList<Integer> migrantes, ArrayList<Integer> emigrantes) {
        System.out.println("\n--- Saldo Migratorio ---");
        ArrayList<Double> saldoMigratorio = new ArrayList<>();

        if (migrantes.size() != emigrantes.size()) {
            throw new IllegalArgumentException("Las listas de nacimientos y defunciones deben tener el mismo tamaño");
        }

        for (int i = 0; i < migrantes.size(); i++) {
            double saldoM = migrantes.get(i) - emigrantes.get(i);
            saldoMigratorio.add(saldoM);
            System.out.println("Año " + (2010 + i) + ": Saldo migratorio = " + saldoM);
        }
        return saldoMigratorio;
    }

    // Crecimiento total
    public static ArrayList<Double> calcularCrecimientoTotal(ArrayList<Double> Ni, ArrayList<Double> crecimientoVegetativo, ArrayList<Double> saldoMigratorio) {
        System.out.println("\n--- Poblacion final -- Datos hiatoricos-- ---");
        ArrayList<Double> Nf = new ArrayList<>();
        if (Ni.size() != crecimientoVegetativo.size() || Ni.size() != saldoMigratorio.size() || crecimientoVegetativo.size() != saldoMigratorio.size()) {
            throw new IllegalArgumentException("Las listas deben tener el mismo tamaño");
        }

        for (int i = 0; i < Ni.size(); i++) {
            double nf = Ni.get(i) + crecimientoVegetativo.get(i) + saldoMigratorio.get(i);
            Nf.add(nf);
            System.out.println("Año " + (2010 + i) + ": Población Final = " + nf);
        }
        return Nf;
    }

    // Modelo aritmético
    public static ArrayList<Double> calcularModeloAritmetico(double Ni, double r, int k) {
        ArrayList<Double> datoArit = new ArrayList<>();
        System.out.println("\n--- Modelo Aritmético ---");
        for (int i = 0; i < k; i++) {
            double Nf = Ni * (1 + r * (i + 1));
            datoArit.add(Nf);
            System.out.println("Año " + (2024 + i) + ": Población Futura = " + Nf);
        }
        return datoArit;
    }

    // Modelo geométrico
    public static ArrayList<Double> calcularModeloGeometrico(double Ni, double r, int k) {
        System.out.println("\n--- Modelo Geométrico ---");
        ArrayList<Double> datoGeo = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            double Nf = Ni * Math.pow((1 + r), (i + 1));
            datoGeo.add(Nf);
            System.out.println("Año " + (2024 + i) + ", Población Futura = " + Nf);
        }
        return datoGeo;
    }

    // Modelo exponencial
    public static ArrayList<Double> calcularModeloExponencial(double Ni, double r, int k) {
        System.out.println("\n--- Modelo Exponencial ---");
        ArrayList<Double> datoExp = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            double Nf = Ni * Math.exp(r * (i + 1));
            datoExp.add(Nf);
            System.out.println("Año " + (2024 + i) + ", Población Futura = " + Nf);
        }
        return datoExp;
    }
    // k == tiempo

    //Crecimiento relativo promedio aritmetico  r = (nf-ni)/(ni*k)
    public static double calcularTasaAritmetica(ArrayList<Double> Ni, ArrayList<Double> Nf) {
        double r = (Nf.get(Nf.size() - 1) - Ni.get(0)) / (Ni.get(0) * (Nf.size() - 1));
        return r;
    }

    //Crecimiento relativo promedio geometrico  r =   ((nf/ni)^(1/k) ) -1
    public static double calcularTasaGeometrica(ArrayList<Double> Ni, ArrayList<Double> Nf) {
        double r = Math.pow(Nf.get(Nf.size() - 1) / Ni.get(0), 1.0 / (Nf.size() - 1)) - 1;
        return r;
    }

    //Crecimiento relativo promedio aritmetico  r = (nf-ni)/(ni*k)
    public static double calcularTasaExponencial(ArrayList<Double> Ni, ArrayList<Double> Nf) {
        double r = (Math.log(Nf.get(Nf.size() - 1)) - Math.log(Ni.get(0))) / (Nf.size() - 1);
        return r;
    }

    // Datos grafica 
    public static ArrayList<Double> graficaAritD(ArrayList<Double> Nf, ArrayList<Double> datArit) {
        ArrayList<Double> datosT = new ArrayList<>(Nf);
        datosT.addAll(datArit);
        return datosT;
    }

    public static ArrayList<Double> graficaGeoD(ArrayList<Double> Nf, ArrayList<Double> datGeo) {
        ArrayList<Double> datosT = new ArrayList<>(Nf);
        datosT.addAll(datGeo);
        return datosT;
    }

    public static ArrayList<Double> graficaExpD(ArrayList<Double> Nf, ArrayList<Double> datExp) {
        ArrayList<Double> datosT = new ArrayList<>(Nf);
        datosT.addAll(datExp);
        return datosT;
    }

    public static ModeloDatos obtenerDatos(int id) {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        // Crear una instancia de la clase personalizada
        ModeloDatos datos = new ModeloDatos();

        try {
            // Consulta única para todas las columnas necesarias
            String query = "SELECT nacimiento, defuncion, migrantes, emigrantes, inicio_poblacion FROM datos WHERE id_estado = ?";
            con = Conexion.getConnection();
            pst = con.prepareStatement(query);
            pst.setInt(1, id); // Establecer el parámetro dinámico
            rs = pst.executeQuery();

            // Iterar sobre el resultado y agregar los valores a las listas correspondientes
            while (rs.next()) {
                datos.getListaNacimiento().add(rs.getInt("nacimiento"));
                datos.getListaDefuncion().add(rs.getInt("defuncion"));
                datos.getListaMigrantes().add(rs.getInt("migrantes"));
                datos.getListaEmigrantes().add(rs.getInt("emigrantes"));
                datos.getListaInicioP().add(rs.getDouble("inicio_poblacion"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener los datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            //cerrarConexion(con, pst, rs);
        }

        return datos;
    }
}
