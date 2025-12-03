// Archivo: module-info.java

module SakilaCRUD {
    requires java.sql; 
    requires java.logging; 
    requires mysql.connector.j; 
    
    // 1. Exportar el paquete de la aplicación para que el lanzador lo encuentre
    exports com.sakila.app; 
    
    // 2. Exportar otros paquetes si es necesario
    exports com.sakila.models;
    
    // Si la versión de JDK lo requiere para reflexión:
    opens com.sakila.app to java.desktop; 
    
    // Puedes también configurar la clase principal aquí si tu IDE lo soporta:
    // provides com.sakila.app.MenuPrincipal with com.sakila.app.MenuPrincipal; 
}