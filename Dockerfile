# Etapa 1: Construção
FROM maven:3.8.3-openjdk-22 AS build

# Define o diretório de trabalho
WORKDIR /app

# Copia o arquivo pom.xml e as dependências necessárias para o diretório de trabalho
COPY . .

# Executa o comando Maven para construir o projeto
RUN mvn clean package -DskipTests

FROM openjdk:22

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]