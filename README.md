# SWSTest

Для работы Запуска сервера необходимо добавить к проекту ```.env``` файл или определить в системе следующие переменные
```
POSTGRES_USER - Имя пользователя postgreSQL
POSTGRES_PASSWORD - Пароль к postgreSQL
POSTGRES_PORT - Порт postgreSQL в системе или docker контейнере
POSTGRES_HOST_ALIAS - адресс postgreSQL или псевдоним хоста в docker контейнере
POSTGRES_TEST_DB - название БД для тестирования
POSTGRES_DB - название основной БД
SERVER_PORT - порт сервера на localhost или в docker контейнере
```