# Sistema de Gestión de Inventario y Ventas

## Descripción
Este sistema permite gestionar productos, ventas y generar reportes en formato PDF. Está desarrollado con **Spring Boot** para el backend y **Vue.js** para el frontend, usando **PostgreSQL** como base de datos.

## Requisitos Previos
Antes de instalar y ejecutar el proyecto, asegúrate de tener instalado:
- **Java 17 o superior**
- **Maven**
- **PostgreSQL**
- **Node.js y npm**
- **Git**

## Instalación

### Clonar el repositorio
```sh
git clone https://github.com/tu-usuario/tu-repositorio.git
cd tu-repositorio
```

### Configuración del Backend
1. Ir a la carpeta del backend:
   ```sh
   cd backend
   ```
2. Configurar la base de datos en `application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/tu_base_de_datos
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña
   spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
   spring.jpa.hibernate.ddl-auto=update
   ```
3. Construir y ejecutar el backend:
   ```sh
   mvn clean install
   mvn spring-boot:run
   ```

### Configuración del Frontend
1. Ir a la carpeta del frontend:
   ```sh
   cd ../frontend
   ```
2. Instalar dependencias:
   ```sh
   npm install
   ```
3. Ejecutar el frontend:
   ```sh
   npm run dev
   ```

## Uso del Sistema
1. Accede al frontend en `http://localhost:5173`
2. Gestiona productos y ventas desde la interfaz.
3. Genera reportes de ventas en formato PDF.

## Ejemplo de Reporte
Adjunta un ejemplo de reporte generado por el sistema.

## Contribuciones
Si deseas contribuir, realiza un fork del repositorio y abre un pull request con tus mejoras.

## Licencia
Este proyecto está bajo la licencia [MIT](LICENSE).

