# 🎮 2D Игра на JavaFX — _charlsih_

Простая 2D-десктопная игра, созданная с использованием **JavaFX** и **Maven**. Поддерживается автоматическая сборка через `Makefile`, запуск через `java -jar`, и компиляция на JDK 17.

---

## 🛠 Технологии

- ☕ Java 17
    
- 🎨 JavaFX 17.0.2
    
- 🧰 Maven (упаковка, зависимости)
    
- 🧱 Make (автоматическая установка SDK/FX/зависимостей)
    
- 🎮 JavaRush Game Engine (необходимое подключение — если используется)
    

---

## 📦 Установка и запуск

### 🧪 Требования:

- Linux/macOS (поддержка Windows — через PowerShell вручную)
    
- Установлен `make`, `curl`, `unzip`, `openjdk-17-jdk`
    

---

### 🚀 Быстрый запуск

bash

Копировать код

`make run`

Это:

1. Установит недостающие зависимости
    
2. Скачает JavaFX SDK
    
3. Скомпилирует исходники
    
4. Запустит игру
    

---

### 🧰 Ручная сборка через Maven

1. Соберите исполняемый JAR:

`mvn clean package`

2. Запустите:

`java --module-path lib/javafx-sdk-17.0.2/lib \      --add-modules javafx.controls,javafx.fxml,javafx.graphics \      -jar target/charlsih-0.0.1-SNAPSHOT.jar`

💡 Если используете **JavaFX Maven Plugin**, то можно просто:

`mvn javafx:run`

---

## 🕹 Управление

| Действие | Клавиша |
| -------- | ------- |
| Движение | стрелки |

---

## 📁 Структура проекта

```text
src/
├── main/
│   ├── java/com/mygame/ 
│   │   ├── Main.java         # Точка входа
│   │   ├── GameEngine.java   # Логика и правила 
│   │   └── GameScene.java    # Рендер и сцена 
│   └── resources/            # Ресурсы: изображения, звуки 
Makefile                     # Автоматизация сборки и запуска 
pom.xml                      # Maven-конфигурация
```

---

## 🛠 Makefile Команды

|Цель|Описание|
|---|---|
|`make run`|Запуск игры|
|`make compile`|Компиляция проекта|
|`make clean`|Очистка (`bin/`, `lib/`)|
|`make setup`|Установка JDK и JavaFX SDK|

---

## ✨ Возможности

- Простое управление персонажем
    
- Отрисовка 2D-сцены
    
- Быстрая сборка и запуск
    
- Интеграция с JavaFX SDK
    
- Расширяемая архитектура