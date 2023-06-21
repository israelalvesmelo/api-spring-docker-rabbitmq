# Define a imagem base do Maven com OpenJDK 11
FROM maven:3.8.4-openjdk-11 AS build

# Define o diretório de trabalho
WORKDIR /app

# Copia o arquivo pom.xml para o diretório de trabalho
COPY pom.xml .

# Executa o comando 'mvn dependency:go-offline' para baixar as dependências do Maven em cache
RUN mvn dependency:go-offline -B

# Copia o código fonte do projeto para o diretório de trabalho
COPY src ./src

# Executa o comando 'mvn package' para fazer o build do projeto e gerar o arquivo .jar
RUN mvn package -DskipTests

# Define uma nova imagem base apenas com o JRE
FROM openjdk:11-jre-slim

# Copia o arquivo .jar gerado do estágio de build anterior
COPY --from=build /app/target/*.jar /app/api.jar

# Define o comando de inicialização para executar o arquivo .jar
CMD ["java", "-jar", "/app/api.jar"]