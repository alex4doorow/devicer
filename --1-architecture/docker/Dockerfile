# базовый образ - OpenJDK 11 и установленный Maven
FROM maven:3.8.4-openjdk-17

# Соберем и запустим приложение в этой директории
WORKDIR /app

# Для сборки проекта Maven нужны исходные тексты программы
# и непосредственно файл сборки pom.xml
COPY pom.xml ./
COPY src/ ./src/

# Компиляция, сбока и упаковка приложения в архив JAR
RUN mvn package

# Запуск приложения виртуальной машиной Java из базового образа
CMD ["java", "-jar", "/app/target/orders-7.0.1-SNAPSHOT.jar"]
