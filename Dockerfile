# базовый образ - OpenJDK 11 и установленный Maven
FROM maven:3.8.4-openjdk-17

# Соберем и запустим приложение в этой директории
WORKDIR /app

# Для сборки проекта Maven нужны исходные тексты программы
# и непосредственно файл сборки pom.xml
COPY pom.xml ./
COPY core/src/ ./core/src/
COPY core/pom.xml ./core/pom.xml
COPY dispatcher/src/ ./dispatcher/src/
COPY dispatcher/pom.xml ./dispatcher/pom.xml
COPY orders/src/ ./orders/src/
COPY orders/pom.xml ./orders/pom.xml
COPY statistic/src/ ./statistic/src/
COPY statistic/pom.xml ./statistic/pom.xml
COPY updater/src/ ./updater/src/
COPY updater/pom.xml ./updater/pom.xml

# Компиляция, сбока и упаковка приложения в архив JAR
RUN mvn package

# Запуск приложения виртуальной машиной Java из базового образа
CMD ["java", "-jar", "/app/orders/target/orders-7.0.1-SNAPSHOT.jar"]
