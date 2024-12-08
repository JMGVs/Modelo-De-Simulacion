/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Conexion.Conexion;
import static Controlador.Operaciones.calcularCrecimientoTotal;
import static Controlador.Operaciones.calcularCrecimientoVegetativo;
import static Controlador.Operaciones.calcularModeloAritmetico;
import static Controlador.Operaciones.calcularModeloExponencial;
import static Controlador.Operaciones.calcularModeloGeometrico;
import static Controlador.Operaciones.calcularSaldoMigratorio;
import static Controlador.Operaciones.calcularTasaAritmetica;
import static Controlador.Operaciones.calcularTasaExponencial;
import static Controlador.Operaciones.calcularTasaGeometrica;
import static Controlador.Operaciones.obtenerDatos;
import Modelos.ModeloDatos;
import static Modelos.ModeloDatos.cerrarConexion;
import Modelos.ModeloResultados;
import Vistas.Principal;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author monco
 */
public class ControladorPrincipal {

    public static Principal vistaPrincipal = new Principal();
    private static ModeloResultados modeloResultados;

    public static void Mostrar() {
        if (vistaPrincipal == null) {
            vistaPrincipal = new Principal();
        }
        vistaPrincipal.setVisible(true);
    }

    public static void llenarComboBoxProductos(JComboBox<String> comboproducto) {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            String query = "SELECT id_estado, nombre FROM estados";
            con = Conexion.getConnection();
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();

            // Limpiar el JComboBox
            comboproducto.removeAllItems();
            comboproducto.addItem("Seleccione estado");

            while (rs.next()) {
                int id = rs.getInt("id_estado");
                String nombre = rs.getString("nombre");
                comboproducto.addItem(id + " - " + nombre);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al llenar el combo de productos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            cerrarConexion(con, pst, rs);
        }
    }

    public static Integer obtenerIdProductoSeleccionado(JComboBox<String> comboproducto) {
        String seleccion = (String) comboproducto.getSelectedItem();
        if (seleccion != null && !seleccion.equals("Seleccione un estado")) {
            String[] partes = seleccion.split(" - ");
            if (partes.length > 0) {
                try {
                    return Integer.parseInt(partes[0].trim());
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Error al parsear el ID del producto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        return null;
    }

    private static void limpiarCampos() {
        vistaPrincipal.txtDatos.setText("");
        vistaPrincipal.txtEstado.setSelectedIndex(0);
    }

    public static int obtenerTxt() {
        String texto = vistaPrincipal.txtDatos.getText();

        if (texto.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo de texto está vacío.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return -1;
        }

        try {
            int num = Integer.parseInt(texto);
            return num;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }

    public static ModeloResultados operar() {
        int id = ControladorPrincipal.obtenerIdProductoSeleccionado(vistaPrincipal.txtEstado);
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

        //Scanner sc = new Scanner(System.in);
        //System.out.print("\n¿Escriba la cantidad de años a proyectar? ");
        int aniosP = obtenerTxt();
        // Tasa relatia de poblacion 
        double rAritmetica = calcularTasaAritmetica(Ni, Nf);
        double rGeometrica = calcularTasaGeometrica(Ni, Nf);
        double rExponencial = calcularTasaExponencial(Ni, Nf);
        // Modelos
        ArrayList<Double> datArit = calcularModeloAritmetico(Ni.get(Ni.size() - 1), rAritmetica, aniosP);
        ArrayList<Double> datGeo = calcularModeloGeometrico(Ni.get(Ni.size() - 1), rGeometrica, aniosP);
        ArrayList<Double> datEx = calcularModeloExponencial(Ni.get(Ni.size() - 1), rExponencial, aniosP);
        //porcentajeCrecimiento(Ni, Nf,aniosP);

        //Datos para graficar 
        ArrayList<Double> dArti = graficaAritD(Nf, datArit);
        ArrayList<Double> dGeo = graficaAritD(Nf, datArit);
        ArrayList<Double> dExp = graficaAritD(Nf, datArit);

        graficaLinealHistorico(Nf);
        graficaLinealAritmetico(dArti);
        graficaLinealGeometrico(dGeo);
        graficaLinealExponencial(dExp);

        modeloResultados = new ModeloResultados(crecimientoVegetativo, saldoMigratorio, Nf, datArit, datGeo, datEx);
        return modeloResultados;

    }

    public static void mostrarHistoricos() {
        if (modeloResultados == null) {
            JOptionPane.showMessageDialog(null, "No hay datos calculados. Ejecute el método 'operar' primero.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Obtener los datos de modeloResultados
        ArrayList<Double> crecimientoVegetativo = modeloResultados.getCrecimientoVegetativo();
        ArrayList<Double> saldoMigratorio = modeloResultados.getSaldoMigratorio();
        ArrayList<Double> nf = modeloResultados.getNf();

        // Construir cadenas para mostrar
        StringBuilder mensaje = new StringBuilder("Datos Generales:\n\n");

        mensaje.append("Crecimiento Vegetativo:\n");
        int i=1;
        for (Double valor : crecimientoVegetativo) {
            mensaje.append("Año: "+ (2010 + i)+" ").append(valor).append("\n");
            i++;
        }
        i=1;
        mensaje.append("\nSaldo Migratorio:\n");
        for (Double valor : saldoMigratorio) {
            mensaje.append("Año: "+ (2010 + i)+" ").append(valor).append("\n");
            i++;
        }
        i=1;
        mensaje.append("\nPoblación Final (Nf):\n");
        for (Double valor : nf) {
            mensaje.append("Año: "+ (2010 + i)+" ").append(valor).append("\n");
            i++;
        }

        // Crear un JTextArea y configurar para que sea de solo lectura y tenga un scroll
        JTextArea textArea = new JTextArea(mensaje.toString());
        textArea.setEditable(false);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);

        // Agregar un JScrollPane para que el JTextArea tenga desplazamiento
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 300)); // Ajusta el tamaño como desees

        // Mostrar el JScrollPane en un JOptionPane
        JOptionPane.showMessageDialog(null, scrollPane, "Datos de Resultados", JOptionPane.INFORMATION_MESSAGE);
    }
    public static void mostrarAritmeticos() {
        if (modeloResultados == null) {
            JOptionPane.showMessageDialog(null, "No hay datos calculados. Ejecute el método 'operar' primero.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Obtener los datos de modeloResultados
        ArrayList<Double> aritmeticos = modeloResultados.getDatAritmetico();
        

        // Construir cadenas para mostrar
        StringBuilder mensaje = new StringBuilder("Modelo Aritmetico:\n\n");

        mensaje.append("Crecimiento Aritmetico:\n");
        int i=1;
        for (Double valor : aritmeticos) {
            mensaje.append("Año: "+ (2024 + i+" ")).append(valor).append("\n");
            i++;
        }


        // Crear un JTextArea y configurar para que sea de solo lectura y tenga un scroll
        JTextArea textArea = new JTextArea(mensaje.toString());
        textArea.setEditable(false);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);

        // Agregar un JScrollPane para que el JTextArea tenga desplazamiento
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 300)); // Ajusta el tamaño como desees

        // Mostrar el JScrollPane en un JOptionPane
        JOptionPane.showMessageDialog(null, scrollPane, "Datos de Resultados", JOptionPane.INFORMATION_MESSAGE);
    }
    
     public static void mostrarGeometricos() {
        if (modeloResultados == null) {
            JOptionPane.showMessageDialog(null, "No hay datos calculados. Ejecute el método 'operar' primero.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Obtener los datos de modeloResultados
        ArrayList<Double> geometricos = modeloResultados.getDatGeometrico();
        

        // Construir cadenas para mostrar
        StringBuilder mensaje = new StringBuilder("Modelo Geometrico:\n\n");

        mensaje.append("Crecimieto futuro:\n");
        int i=1;
        for (Double valor : geometricos) {
            mensaje.append("Año: "+ (2024 + i+" ")).append(valor).append("\n");
            i++;
        }


        // Crear un JTextArea y configurar para que sea de solo lectura y tenga un scroll
        JTextArea textArea = new JTextArea(mensaje.toString());
        textArea.setEditable(false);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);

        // Agregar un JScrollPane para que el JTextArea tenga desplazamiento
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 300)); // Ajusta el tamaño como desees

        // Mostrar el JScrollPane en un JOptionPane
        JOptionPane.showMessageDialog(null, scrollPane, "Datos de Resultados", JOptionPane.INFORMATION_MESSAGE);
    }
    
     public static void mostrarExponencial() {
        if (modeloResultados == null) {
            JOptionPane.showMessageDialog(null, "No hay datos calculados. Ejecute el método 'operar' primero.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Obtener los datos de modeloResultados
        ArrayList<Double> exponencial = modeloResultados.getDatExponencial();
        

        // Construir cadenas para mostrar
        StringBuilder mensaje = new StringBuilder("Modelo Exponencial:\n\n");

        mensaje.append("Crecimieto futuro:\n");
        int i=1;
        for (Double valor : exponencial) {
            mensaje.append("Año: "+ (2024 + i+" ")).append(valor).append("\n");
            i++;
        }


        // Crear un JTextArea y configurar para que sea de solo lectura y tenga un scroll
        JTextArea textArea = new JTextArea(mensaje.toString());
        textArea.setEditable(false);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);

        // Agregar un JScrollPane para que el JTextArea tenga desplazamiento
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 300)); // Ajusta el tamaño como desees

        // Mostrar el JScrollPane en un JOptionPane
        JOptionPane.showMessageDialog(null, scrollPane, "Datos de Resultados", JOptionPane.INFORMATION_MESSAGE);
    }

    //Datos y operaciones -- graficas
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
            cerrarConexion(con, pst, rs);
        }

        return datos;
    }

    public static void graficaLinealHistorico(ArrayList<Double> nf) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int anio = 2010;

        // Crear dataset
        for (double historico : nf) {
            dataset.addValue(historico, "Histórico", String.valueOf(anio));
            anio++;
        }

        JFreeChart lineChart = ChartFactory.createLineChart(
                "Gráfica Histórica",
                "Año",
                "Número de Habitantes",
                dataset,
                PlotOrientation.VERTICAL, false, true, true);

        CategoryPlot lineCategoryPlot = lineChart.getCategoryPlot();
        lineCategoryPlot.setBackgroundPaint(Color.white);

        // Configurar el rango del eje Y dinámicamente
        NumberAxis rangeAxis = (NumberAxis) lineCategoryPlot.getRangeAxis();
        double minY = nf.stream().min(Double::compareTo).orElse(0.0);
        double maxY = nf.stream().max(Double::compareTo).orElse(0.0);

        double padding = (maxY - minY) * 0.1; // Agregar 10% de margen
        rangeAxis.setRange(minY - padding, maxY + padding);

        double rango = maxY - minY;
        double intervalo;
        if (rango <= 500000) {
            intervalo = 50000;
        } else if (rango <= 2000000) {
            intervalo = 200000;
        } else {
            intervalo = 500000;
        }
        rangeAxis.setTickUnit(new NumberTickUnit(intervalo));

        // Personalizar el color de la línea
        LineAndShapeRenderer lineRenderer = (LineAndShapeRenderer) lineCategoryPlot.getRenderer();
        Color lineChartColor = new Color(204, 0, 51);
        lineRenderer.setSeriesPaint(0, lineChartColor);

        // Configurar las etiquetas del eje X en diagonal
        CategoryAxis domainAxis = lineCategoryPlot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45); // Rotar las etiquetas 45 grados

        // Agregar la gráfica al panel
        ChartPanel lineChartPanel = new ChartPanel(lineChart);
        vistaPrincipal.panelHistorico.removeAll();
        vistaPrincipal.panelHistorico.add(lineChartPanel, BorderLayout.CENTER);
        vistaPrincipal.panelHistorico.validate();
    }

    public static void graficaLinealAritmetico(ArrayList<Double> dArti) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int anio = 2010;

        // Crear dataset
        for (double aritmetico : dArti) {
            dataset.addValue(aritmetico, "Aritmético", String.valueOf(anio));
            anio++;
        }

        JFreeChart lineChart = ChartFactory.createLineChart(
                "Gráfica Aritmética",
                "Año",
                "Número de Habitantes",
                dataset,
                PlotOrientation.VERTICAL, false, true, true);

        CategoryPlot lineCategoryPlot = lineChart.getCategoryPlot();
        lineCategoryPlot.setBackgroundPaint(Color.white);

        // Configurar el rango del eje Y dinámicamente
        NumberAxis rangeAxis = (NumberAxis) lineCategoryPlot.getRangeAxis();
        double minY = dArti.stream().min(Double::compareTo).orElse(0.0);
        double maxY = dArti.stream().max(Double::compareTo).orElse(0.0);

        double padding = (maxY - minY) * 0.1; // Agregar 10% de margen
        rangeAxis.setRange(minY - padding, maxY + padding);

        // Configurar intervalos del eje Y
        double rango = maxY - minY;
        double intervalo;
        if (rango <= 500000) {
            intervalo = 50000;
        } else if (rango <= 2000000) {
            intervalo = 200000;
        } else {
            intervalo = 500000;
        }
        rangeAxis.setTickUnit(new NumberTickUnit(intervalo));

        // Personalizar el color de la línea
        LineAndShapeRenderer lineRenderer = (LineAndShapeRenderer) lineCategoryPlot.getRenderer();
        Color lineChartColor = new Color(204, 0, 51);
        lineRenderer.setSeriesPaint(0, lineChartColor);

        // Configurar las etiquetas del eje X en diagonal
        CategoryAxis domainAxis = lineCategoryPlot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45); // Rotar las etiquetas 45 grados

        // Agregar la gráfica al panel
        ChartPanel lineChartPanel = new ChartPanel(lineChart);
        vistaPrincipal.panelAritmetico.removeAll();
        vistaPrincipal.panelAritmetico.add(lineChartPanel, BorderLayout.CENTER);
        vistaPrincipal.panelAritmetico.validate();
    }

    public static void graficaLinealGeometrico(ArrayList<Double> dGeo) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int anio = 2010;

        // Crear dataset
        for (double geometrico : dGeo) {
            dataset.addValue(geometrico, "Geométrico", String.valueOf(anio));
            anio++;
        }

        JFreeChart lineChart = ChartFactory.createLineChart(
                "Gráfica Geométrica",
                "Año",
                "Número de Habitantes",
                dataset,
                PlotOrientation.VERTICAL, false, true, true);

        CategoryPlot lineCategoryPlot = lineChart.getCategoryPlot();
        lineCategoryPlot.setBackgroundPaint(Color.white);

        // Configurar el rango del eje Y dinámicamente
        NumberAxis rangeAxis = (NumberAxis) lineCategoryPlot.getRangeAxis();
        double minY = dGeo.stream().min(Double::compareTo).orElse(0.0);
        double maxY = dGeo.stream().max(Double::compareTo).orElse(0.0);

        double padding = (maxY - minY) * 0.1; // Agregar 10% de margen
        rangeAxis.setRange(minY - padding, maxY + padding);

        // Configurar intervalos del eje Y
        double rango = maxY - minY;
        double intervalo;
        if (rango <= 500000) {
            intervalo = 50000;
        } else if (rango <= 2000000) {
            intervalo = 200000;
        } else {
            intervalo = 500000;
        }
        rangeAxis.setTickUnit(new NumberTickUnit(intervalo));

        // Personalizar el color de la línea
        LineAndShapeRenderer lineRenderer = (LineAndShapeRenderer) lineCategoryPlot.getRenderer();
        Color lineChartColor = new Color(204, 0, 51);
        lineRenderer.setSeriesPaint(0, lineChartColor);

        // Configurar las etiquetas del eje X en diagonal
        CategoryAxis domainAxis = lineCategoryPlot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45); // Rotar las etiquetas 45 grados

        // Agregar la gráfica al panel
        ChartPanel lineChartPanel = new ChartPanel(lineChart);
        vistaPrincipal.panelGeometrico.removeAll();
        vistaPrincipal.panelGeometrico.add(lineChartPanel, BorderLayout.CENTER);
        vistaPrincipal.panelGeometrico.validate();
    }

    public static void graficaLinealExponencial(ArrayList<Double> dExp) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int anio = 2010;

        // Crear dataset
        for (double exponencial : dExp) {
            dataset.addValue(exponencial, "Exponencial", String.valueOf(anio));
            anio++;
        }

        JFreeChart lineChart = ChartFactory.createLineChart(
                "Gráfica Exponencial",
                "Año",
                "Número de Habitantes",
                dataset,
                PlotOrientation.VERTICAL, false, true, true);

        CategoryPlot lineCategoryPlot = lineChart.getCategoryPlot();
        lineCategoryPlot.setBackgroundPaint(Color.white);

        // Configurar el rango del eje Y dinámicamente
        NumberAxis rangeAxis = (NumberAxis) lineCategoryPlot.getRangeAxis();
        double minY = dExp.stream().min(Double::compareTo).orElse(0.0);
        double maxY = dExp.stream().max(Double::compareTo).orElse(0.0);

        double padding = (maxY - minY) * 0.1; // Agregar 10% de margen
        rangeAxis.setRange(minY - padding, maxY + padding);

        // Configurar intervalos del eje Y
        double rango = maxY - minY;
        double intervalo;
        if (rango <= 500000) {
            intervalo = 50000;
        } else if (rango <= 2000000) {
            intervalo = 200000;
        } else {
            intervalo = 500000;
        }
        rangeAxis.setTickUnit(new NumberTickUnit(intervalo));

        // Personalizar el color de la línea
        LineAndShapeRenderer lineRenderer = (LineAndShapeRenderer) lineCategoryPlot.getRenderer();
        Color lineChartColor = new Color(204, 0, 51);
        lineRenderer.setSeriesPaint(0, lineChartColor);

        // Configurar las etiquetas del eje X en diagonal
        CategoryAxis domainAxis = lineCategoryPlot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45); // Rotar las etiquetas 45 grados

        // Agregar la gráfica al panel
        ChartPanel lineChartPanel = new ChartPanel(lineChart);
        vistaPrincipal.panelExponencial.removeAll();
        vistaPrincipal.panelExponencial.add(lineChartPanel, BorderLayout.CENTER);
        vistaPrincipal.panelExponencial.validate();
    }

    public static void mostrarDescripcionHistorica() {
        String descripcion = "La gráfica histórica muestra el crecimiento real de la población en función del tiempo. \n\n"
                + "Datos utilizados:\n"
                + "- Nacimientos\n"
                + "- Muertes\n"
                + "- Migración (inmigración y emigración)\n\n"
                + "Estos datos se recopilan de registros oficiales como censos,\n actas de nacimiento, defunción y estadísticas migratorias. "
                + "El comportamiento no siempre es lineal, ya que puede haber \n fluctuaciones debido a eventos económicos, sociales o naturales.";
        JOptionPane.showMessageDialog(null, descripcion, "Gráfica Histórica", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void mostrarDescripcionAritmetica() {
        // Descripción específica de la gráfica
        String descripcion = "Esta gráfica representa el crecimiento poblacional utilizando el modelo aritmético. \n\n"
                + "En el eje vertical (Y) se muestra el número de habitantes, y en el eje horizontal (X) se presentan los años.\n\n"
                + "El modelo aritmético asume un incremento constante en la población, "
                + "lo que significa que la cantidad de personas que se añaden cada año permanece fija.\n "
                + "Este tipo de modelo simplifica el análisis al ignorar factores externos como cambios en tasas de natalidad, mortalidad o migración.";
        JOptionPane.showMessageDialog(null, descripcion, "Explicación de la Gráfica Aritmética", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void mostrarDescripcionGeometrica() {
        String descripcion = "Esta gráfica representa el crecimiento poblacional utilizando el modelo geométrico. \n\n"
                + "En el eje vertical (Y) se muestra el número de habitantes, y en el eje horizontal (X) se presentan los años.\n\n"
                + "El modelo geométrico considera que la población crece proporcionalmente a su tamaño actual. "
                + "Esto implica que, a medida que la población aumenta, \nla cantidad de nuevos habitantes se incrementa de manera proporcional. "
                + "Este modelo es útil para estudiar escenarios donde el crecimiento se acelera con el tiempo.";
        JOptionPane.showMessageDialog(null, descripcion, "Gráfica Geométrica", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void mostrarDescripcionExponencial() {
        String descripcion = "Esta gráfica representa el crecimiento poblacional utilizando el modelo exponencial. \n\n"
                + "En el eje vertical (Y) se muestra el número de habitantes, y en el eje horizontal (X) se presentan los años.\n\n"
                + "El modelo exponencial muestra un crecimiento continuo, donde la población aumenta de forma acelerada con el tiempo. "
                + "\nEste comportamiento es característico de sistemas donde no existen límites inmediatos al crecimiento, "
                + "\naunque en la realidad suele estar sujeto a restricciones como recursos limitados o cambios demográficos.";
        JOptionPane.showMessageDialog(null, descripcion, "Gráfica Exponencial", JOptionPane.INFORMATION_MESSAGE);
    }

}
