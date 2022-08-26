package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.DAOEmpleados;
import model.Empleado;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class Ventana {

	private JFrame frame;
	private JTextField textCedula;
	private JTextField textApellido;
	private JTextField textNombre;
	private JButton btnEliminar;
	private JButton btnBuscar;
	private JButton btnModificar;
	private JButton btnMostrarTodo;
	private Tablero tablero = new Tablero();

	public Ventana() {
		initialize();
		frame.setVisible(true);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 420, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		textCedula = new JTextField();
		textCedula.setBounds(50, 70, 173, 20);
		frame.getContentPane().add(textCedula);
		textCedula.setColumns(10);

		textNombre = new JTextField();
		textNombre.setBounds(50, 115, 173, 20);
		frame.getContentPane().add(textNombre);
		textNombre.setColumns(10);

		textApellido = new JTextField();
		textApellido.setBounds(50, 163, 173, 20);
		frame.getContentPane().add(textApellido);
		textApellido.setColumns(10);

		JButton btnAlta = new JButton("Alta");
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				altaEmpleado();
			}
		});
		btnAlta.setBounds(246, 69, 89, 23);
		frame.getContentPane().add(btnAlta);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bajaEmpleado();
			}
		});
		btnEliminar.setBounds(246, 162, 89, 23);
		frame.getContentPane().add(btnEliminar);

		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarEmpleado();
			}
		});
		btnBuscar.setBounds(246, 208, 89, 23);
		frame.getContentPane().add(btnBuscar);

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarEmpleado();
			}
		});
		btnModificar.setBounds(246, 114, 89, 23);
		frame.getContentPane().add(btnModificar);

		btnMostrarTodo = new JButton("Mostrar Todo");
		btnMostrarTodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablero.mostrar();
			}
		});
		btnMostrarTodo.setBounds(50, 208, 173, 23);
		frame.getContentPane().add(btnMostrarTodo);

		JLabel lblCedula = new JLabel("Cedula");
		lblCedula.setBounds(50, 55, 46, 14);
		frame.getContentPane().add(lblCedula);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(50, 100, 46, 14);
		frame.getContentPane().add(lblNombre);

		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(50, 145, 46, 14);
		frame.getContentPane().add(lblApellido);
		
		JLabel lblTitulo = new JLabel("Gestor de Personas");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTitulo.setBounds(120, 15, 157, 14);
		frame.getContentPane().add(lblTitulo);
	}

	private void altaEmpleado() {
		String ci = textCedula.getText();
		String nombre = textNombre.getText();
		String apellido = textApellido.getText();

		if (ci.isEmpty() || nombre.isEmpty() || apellido.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Debe ingresar todos los campos", "Error",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			Empleado e = new Empleado(ci, nombre, apellido);

			boolean resultado = DAOEmpleados.insert(e);

			if (resultado == true) {
				tablero.actualizarTablero();
				JOptionPane.showMessageDialog(null, "El empleado fue ingresado correctamente", "Exito",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "No fue posible ingresar el empleado", "Error",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	private void bajaEmpleado() {
		String ci = textCedula.getText();
		DAOEmpleados.delete(ci);

		if (ci.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Debe ingresar el campo cedula", "Error",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			boolean resultado = DAOEmpleados.delete(ci);
			if (resultado == true) {
				tablero.actualizarTablero();
				JOptionPane.showMessageDialog(null, "El empleado fue eliminado correctamente", "Exito",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "No fue posible eliminar el empleado", "Error",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	private void buscarEmpleado() {
		String ci = textCedula.getText();
		if (ci.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Debe ingresar el campo cedula para buscar el empleado", "Error",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			Empleado e = DAOEmpleados.find(ci);
			if (e == null) {
				JOptionPane.showMessageDialog(null, "No se encontro un empleado vinculado a esa cedula", "Error",
						JOptionPane.INFORMATION_MESSAGE);
				textCedula.setText("");
			} else {
				JOptionPane.showMessageDialog(null, "Se encontro un empleado vinculado", "Exito",
						JOptionPane.INFORMATION_MESSAGE);
				textNombre.setText(e.getNombre());
				textApellido.setText(e.getApellido());
			}
		}
	}

	private void modificarEmpleado() {
		String ci = textCedula.getText();
		String nombre = textNombre.getText();
		String apellido = textApellido.getText();

		if (ci.isEmpty() || nombre.isEmpty() || apellido.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Debe ingresar todos los campos", "Error",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			Empleado e = new Empleado(ci, nombre, apellido);
			boolean resultado = DAOEmpleados.update(e);
			if (resultado == true) {
				tablero.actualizarTablero();
				JOptionPane.showMessageDialog(null, "Se modifico el empleado correctamente", "Exito",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "No se pudo modificar el empleado", "Error",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
}