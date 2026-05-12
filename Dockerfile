FROM eclipse-temurin:26-jdk AS builder

WORKDIR /app

# Instalar Maven en la imagen de build
RUN apt-get update && apt-get install -y maven && rm -rf /var/lib/apt/lists/*

# Copiar pom y resolver dependencias
COPY pom.xml .
RUN mvn -B -q -DskipTests dependency:go-offline || true

# Copiar el resto del código y compilar
COPY src ./src
RUN mvn -B -q -DskipTests package

# Etapa 2: imagen ligera para ejecutar
FROM eclipse-temurin:26-jre

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080
ENV JAVA_OPTS=""

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
