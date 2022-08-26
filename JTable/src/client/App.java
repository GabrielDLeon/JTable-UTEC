package client;

import views.Ventana;

public class App {

	public static void main(String[] args) {
		try {
			Ventana window = new Ventana();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
