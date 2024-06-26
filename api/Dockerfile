FROM openjdk:17-jdk-slim

WORKDIR /app

# Copia o arquivo Maven Wrapper
COPY mvnw .
COPY .mvn .mvn

# Copia o código-fonte
COPY src src

# Copia o arquivo de configuração Maven
COPY pom.xml .

# Permite executar o mvnw
RUN chmod +x mvnw

# Baixa as dependências do Maven e compila a aplicação, excluindo os testes
RUN ./mvnw package -DskipTests

EXPOSE 8080

# Define a variável de ambiente para a URL do banco de dados
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://db-postgres:5432/db_todo

ENTRYPOINT ["java","-jar","target/api-0.0.1-SNAPSHOT.jar"]