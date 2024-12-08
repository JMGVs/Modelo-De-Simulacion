/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RefineryUtilities;

/**
 *
 * @author monco
 */
public class Graficas {
 
    public static void main(String[] args) {
 
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(15, "Hospitales", "1970");
        dataset.addValue(30, "Hospitales", "1980");
        dataset.addValue(60, "Hospitales", "1990");
        dataset.addValue(120, "Hospitales", "2000");
        dataset.addValue(240, "Hospitales", "2010");
        dataset.addValue(150, "Hospitales", "2014");
 
        
        JFreeChart lineChart = ChartFactory.createLineChart(
                "Grafica Lineal", 
                "Año", 
                "Numero de Hospitales", 
                dataset,
                PlotOrientation.VERTICAL, true, true, false);
 
 
        
        ChartPanel panel = new ChartPanel(lineChart);
 
        
        
        // Creamos la ventana
        JFrame ventana = new JFrame("Grafica");
        ventana.setVisible(true);
        ventana.setSize(800, 600);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        ventana.add(panel);
 
    }
 
    
}
