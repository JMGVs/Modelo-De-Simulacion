/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import java.util.ArrayList;

/**
 *
 * @author monco
 */
public class ModeloResultados {

        private ArrayList<Double> crecimientoVegetativo;
        private ArrayList<Double> saldoMigratorio;
        private ArrayList<Double> datAritmetico;
        private ArrayList<Double> Nf;
        private ArrayList<Double> datGeometrico;
        private ArrayList<Double> datExponencial;

    public ModeloResultados(ArrayList<Double> crecimientoVegetativo, ArrayList<Double> saldoMigratorio,ArrayList<Double> Nf, ArrayList<Double> datAritmetico,
            ArrayList<Double> datGeometrico, ArrayList<Double> datExponencial) {
        this.crecimientoVegetativo = crecimientoVegetativo;
        this.saldoMigratorio = saldoMigratorio;
        this.datAritmetico = datAritmetico;
        this.Nf = Nf;
        this.datGeometrico = datGeometrico;
        this.datExponencial = datExponencial;
    }

    public ModeloResultados() {
        this.crecimientoVegetativo = crecimientoVegetativo;
        this.saldoMigratorio = saldoMigratorio;
        this.Nf = Nf;
        this.datAritmetico = datAritmetico;
        this.datGeometrico = datGeometrico;
        this.datExponencial = datExponencial;
    }

    public ArrayList<Double> getCrecimientoVegetativo() {
        return crecimientoVegetativo;
    }

    public void setCrecimientoVegetativo(ArrayList<Double> crecimientoVegetativo) {
        this.crecimientoVegetativo = crecimientoVegetativo;
    }

    
    

    public ArrayList<Double> getSaldoMigratorio() {
        return saldoMigratorio;
    }

    public void setSaldoMigratorio(ArrayList<Double> saldoMigratorio) {
        this.saldoMigratorio = saldoMigratorio;
    }

    public ArrayList<Double> getDatAritmetico() {
        return datAritmetico;
    }

    public void setDatAritmetico(ArrayList<Double> datAritmetico) {
        this.datAritmetico = datAritmetico;
    }

    public ArrayList<Double> getNf() {
        return Nf;
    }

    public void setNf(ArrayList<Double> Nf) {
        this.Nf = Nf;
    }

    public ArrayList<Double> getDatGeometrico() {
        return datGeometrico;
    }

    public void setDatGeometrico(ArrayList<Double> datGeometrico) {
        this.datGeometrico = datGeometrico;
    }

    public ArrayList<Double> getDatExponencial() {
        return datExponencial;
    }

    public void setDatExponencial(ArrayList<Double> datExponencial) {
        this.datExponencial = datExponencial;
    }

        
}
