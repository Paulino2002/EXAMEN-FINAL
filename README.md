# Proyecto Final – Sistema CRUD basado en la Base de Datos Sakila

**Autora:** Erika Graciela Paulino  
**Materia:** Lenguaje de Programación II (Java)  
**Sección:** Z06

---

## 📌 Descripción General
Este proyecto implementa un sistema CRUD en Java inspirado en la base de datos **Sakila**, permitiendo gestionar **películas** y **rentas** mediante una interfaz de consola. Su estructura está diseñada aplicando principios sólidos de Programación Orientada a Objetos y una arquitectura por capas para garantizar claridad, mantenimiento y escalabilidad.

---

## 🎯 Objetivos del Proyecto
El sistema permite:
- Registrar, consultar, actualizar y eliminar **películas**.
- Registrar y gestionar **rentas**.
- Representar las entidades mediante clases modelo basadas en Sakila.
- Aplicar una arquitectura organizada con separación de responsabilidades.
- Generar reportes JSON con los datos almacenados.

---

## 🏗 Arquitectura del Sistema
El proyecto está dividido en varias capas claramente definidas:

### 1. **Models (Modelos)**
Clases que representan entidades del sistema:
- Pelicula
- Cliente
- Inventario
- Personal
- Renta
- Idioma
- EntidadAbstracta *(clase padre base)*

---

### 2. **Data (Acceso a Datos)**
Implementa la lógica CRUD y comunicación con la base de datos:
- **iDatapost** (interfaz CRUD estándar)
- **ContextoDatos** (clase abstracta con métodos finales: GET, POST, PUT, DELETE)
- **GestorPeliculas**
- **GestorRentas**

---

### 3. **Controllers (Controladores)**
Coordinan la comunicación entre la capa de datos y la interfaz:
- ControladorPeliculas
- ControladorRentas

---

### 4. **App (Interfaz de Consola)**
Ubicada en:
```
com.sakila.app.MenuPrincipal
```
Presenta el menú principal para interactuar con el sistema.

---

### 5. **Reports (Reportes)**
Generación de archivos:
- GeneradorJSONPeliculas
- GeneradorReportes

---

## ▶️ Cómo Ejecutar el Programa
1. Abrir el proyecto en **Eclipse**.
2. Ejecutar la clase:
```
com.sakila.app.MenuPrincipal
```
3. Navegar mediante el menú para acceder a:
   - Gestión de Películas
   - Gestión de Rentas

---

## 🔧 Funcionalidades Principales

### ✔ CRUD de Películas
- Agregar nueva película
- Listar películas existentes
- Actualizar registros
- Eliminar registros
- Exportar películas a JSON

### ✔ CRUD de Rentas
- Registrar una renta
- Listar rentas
- Eliminar una renta

---

## 📄 Reportes Generados
El sistema produce:
```
peliculas_sakila.json
```
Este archivo contiene todas las películas almacenadas.

---

## 🛠 Tecnologías Utilizadas
- Java 8+
- Programación Orientada a Objetos (POO)
- Interfaces y clases abstractas
- Estructuras de datos
- Librerías JSON
- Eclipse IDE

---

## 📚 Nota Final
Este proyecto refleja la aplicación práctica de los conceptos estudiados en la asignatura **Lenguaje de Programación II (Java)**. Combina arquitectura, POO y manejo de datos para construir un sistema funcional basado en Sakila.

