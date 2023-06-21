# Use uma imagem base com o Java instalado
FROM openjdk:11-jdk-slim

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copie o arquivo JAR da sua aplicação para o diretório de trabalho
COPY target/*.jar .
# Exponha a porta em que sua aplicação está ouvindo
EXPOSE 8080

# Comando para iniciar sua aplicação quando o contêiner for iniciado
CMD ["java", "-jar", "api-spring-docker-rabbitmq-1.0-SNAPSHOT.jar"]
