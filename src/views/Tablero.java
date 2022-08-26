package views;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.DAOEmpleados;
import model.Empleado;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JFrame;

import java.awt.*;
import java.util.LinkedList;

// Clase que muestra el contenido de una tabla de la BD utilizando una tabla
public class Tablero extends JFrame {

	private DefaultTableModel modelo = new DefaultTableModel();
	private JTable tabla = new JTable(modelo);
	private String[] columnas = { "Nombre", "Apellido", "Cedula" };

	{
		for (String nombre : columnas) {
			modelo.addColumn(nombre);
		}
		tabla.setPreferredScrollableViewportSize(new Dimension(600, 600));
	}
	
	public void mostrar() {
		JFrame frame = new JFrame("Mostrar");

		JScrollPane scrollPane = new JScrollPane(tabla);

		JPanel panel = new JPanel();
		panel.add(scrollPane);
		frame.add(panel);
		frame.pack();
		
		actualizarTablero();
		
		frame.setVisible(true);
	}

	public void actualizarTablero() {
		Object[] fila = new Object[columnas.length];

		// Se rellena cada posición del array con una de las columnas de la tabla en base de datos.
		LinkedList<Empleado> todosEmpleados = DAOEmpleados.findAll();

		modelo.setRowCount(0);
		
		for (Empleado empleado : todosEmpleados) {
			fila[0] = empleado.getNombre();
			fila[1] = empleado.getApellido();
			fila[2] = empleado.getCedula();
			modelo.addRow(fila);
		}
	}

}
