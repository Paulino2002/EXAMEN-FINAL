package com.sakila.app;

import com.sakila.controllers.ControladorPeliculas;
import com.sakila.controllers.ControladorRentas;
import com.sakila.models.Pelicula;
import com.sakila.models.Cliente;
import com.sakila.models.Inventario;
import com.sakila.models.Personal;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * UI de consola simple que permite CRUD basico para películas y rentas.
 */
public class MenuPrincipal {

    private final Scanner scanner = new Scanner(System.in);
    private final ControladorPeliculas controladorPeliculas = new ControladorPeliculas();
    private final ControladorRentas controladorRentas = new ControladorRentas();

    public static void main(String[] args) {
        new MenuPrincipal().mostrarMenu();
    }

    public void mostrarMenu() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n===== MENU SAKILA CRUD =====");
            System.out.println("1. Agregar Pelicula");
            System.out.println("2. Modificar Pelicula");
            System.out.println("3. Eliminar Pelicula (inactivo el record)");
            System.out.println("4. Buscar Pelicula por Titulo");
            System.out.println("5. Listar Peliculas");
            System.out.println("6. Rentar Pelicula");
            System.out.println("7. Devolver Pelicula");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion: ");

            opcion = obtenerEntero();

            switch (opcion) {
                case 1: agregarPelicula(); break;
                case 2: modificarPelicula(); break;
                case 3: eliminarPelicula(); break;
                case 4: buscarPelicula(); break;
                case 5: listarPeliculas(); break;
                case 6: rentarPelicula(); break;
                case 7: devolverPelicula(); break;
                case 0: System.out.println("Saliendo..."); break;
                default: System.out.println("Opcion invalida");
            }
        }
    }

    private void agregarPelicula() {
        System.out.println("--- AGREGAR PELICULA ---");
        System.out.print("Titulo: ");
        String titulo = scanner.nextLine();
        System.out.print("Descripcion: ");
        String desc = scanner.nextLine();
        System.out.print("Ano lanzamiento (YYYY) o Enter: ");
        String ano = scanner.nextLine();
        Integer anoN = ano.isBlank() ? null : Integer.parseInt(ano);
        System.out.print("ID Idioma (Entero): ");
        int idIdioma = obtenerEntero();
        System.out.print("Duracion (En horas, debe ser un numero entero): ");
        int dur = obtenerEntero();
        System.out.print("Costo renta: ");
        double costo = Double.parseDouble(scanner.nextLine());

        Pelicula p = new Pelicula(titulo, anoN, new com.sakila.models.Idioma(idIdioma), dur, costo, desc);
        if (controladorPeliculas.agregarPelicula(p)) System.out.println("Película agregada"); else System.out.println("Error al agregar");
    }

    private void modificarPelicula() {
        System.out.println("--- MODIFICAR PELICULA ---");
        System.out.print("ID de pelicula: ");
        int id = obtenerEntero();
        System.out.print("Nuevo Titulo: ");
        String titulo = scanner.nextLine();
        System.out.print("Nueva descripcion: ");
        String desc = scanner.nextLine();
        System.out.print("Ano (YYYY) o Enter: ");
        String ano = scanner.nextLine();
        Integer anoN = ano.isBlank() ? null : Integer.parseInt(ano);
        System.out.print("ID Idioma: ");
        int idIdioma = obtenerEntero();
        System.out.print("Duracion: ");
        int dur = obtenerEntero();
        System.out.print("Costo renta: ");
        double costo = Double.parseDouble(scanner.nextLine());

        Pelicula p = new Pelicula(id, titulo, anoN, new com.sakila.models.Idioma(idIdioma), dur, costo, desc, null);
        if (controladorPeliculas.modificarPelicula(p)) System.out.println("Modificada"); else System.out.println("Error al modificar");
    }

    private void eliminarPelicula() {
        System.out.print("ID pelicula a eliminar: ");
        int id = obtenerEntero();
        if (controladorPeliculas.eliminarPelicula(id)) System.out.println("Eliminada"); else System.out.println("Error al eliminar");
    }

    private void buscarPelicula() {
        System.out.print("Titulo a buscar: ");
        String t = scanner.nextLine();
        ArrayList<Pelicula> res = controladorPeliculas.buscarPorTitulo(t);
        for (Pelicula p : res) {
            System.out.printf("ID:%d Titulo:%s Costo:%.2f\n", p.getIdRegistro(), p.getTitulo(), p.getCostoRenta());
        }
    }

    
    private void listarPeliculas() {
        ArrayList<Pelicula> res = controladorPeliculas.listarTodas();
        for (Pelicula p : res) {
            System.out.printf("ID:%d Titulo:%s\n", p.getIdRegistro(), p.getTitulo());
        }

        System.out.println("¿Deseas generar archivo JSON con los detalles completos? (1=Sí / 0=No)");
        int op = obtenerEntero();

        if (op == 1) {
            com.sakila.reports.GeneradorJSONPeliculas gen = new com.sakila.reports.GeneradorJSONPeliculas();
            boolean ok = gen.generarJSON("peliculas_sakila.json", res);

            if (ok)
                System.out.println("Archivo JSON generado: peliculas_sakila.json");
            else
                System.out.println("Error generando JSON");
        }
    }


    private void rentarPelicula() {
        System.out.print("ID inventario: ");
        int idInv = obtenerEntero();
        System.out.print("ID cliente: ");
        int idCli = obtenerEntero();
        System.out.print("ID staff(1-2): ");
        int idStaff = obtenerEntero();

        Cliente cliente = new Cliente(idCli);
        Inventario inventario = new Inventario(idInv);
        Personal personal = new Personal(idStaff);

        Integer id = controladorRentas.rentarPeliculaYRetornarID(cliente, inventario, personal);

        if (id != null) {
            System.out.println("Renta creada con exito. ID generado (Se necesitara para devolver la pelicula): " + id);
        } else {
            System.out.println("No se pudo crear la renta.");
        }
    }


    private void devolverPelicula() {
        System.out.print("ID renta a devolver: ");
        int id = obtenerEntero();
        if (controladorRentas.devolverPelicula(id)) System.out.println("Devuelta"); else System.out.println("Error");
    }

    private int obtenerEntero() {
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada invalida. Debe ser numero entero.");
            scanner.next();
        }
        int v = scanner.nextInt();
        scanner.nextLine();
        return v;
    }
}

