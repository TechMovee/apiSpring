WORKDIR /app

# Copia o arquivo pom.xml e as dependências necessárias para o diretório de trabalho
COPY . .

# Executa o comando Maven para construir o projeto
RUN mvn clean package -DskipTests

FROM openjdk:17

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
