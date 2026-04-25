# Usa una imagen estable de Eclipse Temurin para Java 17
FROM eclipse-temurin:17-jdk-jammy

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo JAR generado por Maven
COPY target/*.jar app.jar

# Copia los archivos necesarios para la migración
COPY bd.sql /app/bd.sql
COPY migration.load /app/migration.load

# Expone el puerto 8080
EXPOSE 8080

# Comando de arranque
ENTRYPOINT ["java", "-jar", "app.jar"]
