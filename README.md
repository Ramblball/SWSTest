# SWSTest

Сервер предназначен для запуска в docker контейнере через docker-compose

Для работы Запуска сервера необходимо добавить к проекту ```.env``` файл со следующими переменными
```
POSTGRES_USER - Имя пользователя postgreSQL
POSTGRES_PASSWORD - Пароль к postgreSQL
POSTGRES_PORT - Порт postgreSQL в docker контейнере
POSTGRES_HOST_ALIAS - псевдоним хоста БД в docker контейнере
POSTGRES_TEST_DB - название БД для тестирования
POSTGRES_DB - название основной БД
SERVER_PORT - порт сервера в docker контейнере
```

