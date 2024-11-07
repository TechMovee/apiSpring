# Etapa 1: Construção
FROM maven:3.8.3-openjdk-17 AS build

# Define o diretório de trabalho
WORKDIR /app

# Copia o arquivo pom.xml e as dependências necessárias para o diretório de trabalho
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copia todo o conteúdo do projeto
COPY . .

# Executa o comando Maven para construir o projeto em formato .war
RUN mvn clean package -DskipTests

# Etapa 2: Configuração do servidor de aplicação
FROM tomcat:10.1-jdk17

# Define o diretório de trabalho
WORKDIR /usr/local/tomcat/webapps/

# Copia o arquivo .war gerado para o diretório webapps do Tomcat
COPY --from=build /app/target/*.war ./

# Expõe a porta 8080
EXPOSE 8080

# Inicia o Tomcat
CMD ["catalina.sh", "run"]
