/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author monco
 */
public class ModeloDatos {

    private ArrayList<Integer> listaNacimiento;
    private ArrayList<Integer> listaDefuncion;
    private ArrayList<Integer> listaMigrantes;
    private ArrayList<Integer> listaEmigrantes;
    private ArrayList<Double> listaInicioP;

    public ModeloDatos() {
        listaNacimiento = new ArrayList<>();
        listaDefuncion = new ArrayList<>();
        listaMigrantes = new ArrayList<>();
        listaEmigrantes = new ArrayList<>();
        listaInicioP = new ArrayList<>();
    }

    public ArrayList<Integer> getListaNacimiento() {
        return listaNacimiento;
    }

    public void setListaNacimiento(ArrayList<Integer> listaNacimiento) {
        this.listaNacimiento = listaNacimiento;
    }

    public ArrayList<Integer> getListaDefuncion() {
        return listaDefuncion;
    }

    public void setListaDefuncion(ArrayList<Integer> listaDefuncion) {
        this.listaDefuncion = listaDefuncion;
    }

    public ArrayList<Integer> getListaMigrantes() {
        return listaMigrantes;
    }

    public void setListaMigrantes(ArrayList<Integer> listaMigrantes) {
        this.listaMigrantes = listaMigrantes;
    }

    public ArrayList<Integer> getListaEmigrantes() {
        return listaEmigrantes;
    }

    public void setListaEmigrantes(ArrayList<Integer> listaEmigrantes) {
        this.listaEmigrantes = listaEmigrantes;
    }

    public ArrayList<Double> getListaInicioP() {
        return listaInicioP;
    }

    public void setListaInicioP(ArrayList<Double> listaInicioP) {
        this.listaInicioP = listaInicioP;
    }

    public static void cerrarConexion(Connection con, PreparedStatement pst, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void cerrarConexion(Connection con, PreparedStatement pst, Statement stmt) {
        try {
            if (pst != null) {
                pst.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "ModeloDatos{" + "listaNacimiento=" + listaNacimiento + ", listaDefuncion=" + listaDefuncion + ", listaMigrantes="
                + listaMigrantes + ", listaEmigrantes=" + listaEmigrantes + ", listaInicioP=" + listaInicioP + '}';
    }

}
