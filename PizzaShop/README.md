# 🍕 PizzaShop

**PizzaShop** — это Android-приложение для просмотра и добавления пиццы в корзину, созданное с использованием **Jetpack Compose** и **Material3**. Позволяет просмотреть сипсок пицц, посмотреть данные о пицце, добавить пиццу в корзину.

---

## 🚀 Возможности

-  🍕Просмотр списка пицц  
-  🍕Просмотр информации о пицце  
- 🛒 Добавление пиццы в корзину  
- 🗑 Удаление пиццы из корзины  
- 📋 Меню приложения  
- 👤 Профиль пользователя  
- ⚙️ Настройки приложения  
- ℹ️ О приложении

---

## 🛠️ Стек технологий

- Kotlin
- Jetpack Compose (UI)
- Material 3 (Design System)
- Android SDK 24+
- Navigation Compose 2.9.0

---

## 🧪 Минимальные требования

| Требование        | Значение                    |
|-------------------|-----------------------------|
| Android Studio    | Giraffe или выше (Hedgehog рекомендуется) |
| Gradle Plugin     | Kotlin Gradle Plugin 1.9.x+ |
| Compile SDK       | 35                          |
| Target SDK        | 35                          |
| Min SDK           | 24                          |
| JVM Target        | 11                          |

---
## 🌐 Навигационная структура приложения PizzaShop
```
🍕 PizzaScreen
├─▶ ℹ️ DetailsScreen (при выборе пиццы)
│   └─▶ 🛒 BasketScreen (добавление в корзину)
├─▶ 🛒 BasketScreen (прямой переход)
└─▶ 📋 Меню
    ├─▶ 👤 ProfileScreen
    ├─▶ ⚙️ SettingsScreen
    └─▶ 📱 AboutScreen
```

---

## ⚙️ Установка

### Сборка проекта
**Через Gradle** 

```./gradlew build```

**Cтандартная сборка JAR**

```./gradlew jar```

### Установка через командную строку

**Через Gradle (без сборки JAR)**

```./gradlew run```

**Через собранный JAR**

```java -jar build/libs/PizzaShop.jar```

### Установка через GitHub

1. Клонируйте репозиторий:
   
   ```git clone https://github.com/SapunArtem/PizzaShop.git```
   
2. Откройте проект в Android Studio
3. Соберите APK или запустите на эмуляторе/устройстве
