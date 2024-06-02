# devicer

пример микросервисной архитектуры.
взят проект pribormaster (монолит) и распилен на несколько сервисов
https://github.com/alex4doorow/pribormaster

core - библиотека моделей, утилит, сервисов

dispatcher - оркестратор взаимодействия между модулями

orders - создание, вычитка ордеров

stokers - создание, вичитка товаров, их учет

web - интерфейс для администратора системы

updater - обновление бд

база у всех сервисов общая

start 15/05/2024

spring boot 3.2.5 java 17

postgres jpa liquibase

kafka

rest swagger

TODO
web thymeleaf orders -> list -> add -> show -> list
spring security
grpc
websockets
ehcache
secrets
promethium graham 

