# Базовый образ
FROM python:3.9-slim
# Метаданные
LABEL version="1.0"
# Переменные окружения
ENV APP_HOME=/app \
    PORT=8000
# Рабочая директория
WORKDIR $APP_HOME
# Создание пользователя
RUN useradd -m appuser && \
    chown -R appuser:appuser $APP_HOME
# Копирование файлов
COPY requirements.txt .
COPY app.py .
# Добавление файлов (с автоматическим распаковыванием)
ADD https://example.com/data/config.tar.gz /tmp/
# Установка зависимостей
RUN pip install --no-cache-dir -r requirements.txt
# Открытие порта
EXPOSE $PORT
# Том для данных
VOLUME /data
# Переключение пользователя
USER appuser
# Инструкция для сборки производных образов
ONBUILD COPY . /app/src/
# Точка входа
ENTRYPOINT ["python"]
# Команда по умолчанию
CMD ["app.py", "--port", "8000"]