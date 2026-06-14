package com.caloriescanner.data

data class FoodItem(
    val name: String,
    val nameRu: String,
    val calories: Int,
    val protein: Float,
    val fat: Float,
    val carbs: Float,
    val category: String
)

object FoodDatabase {
    private val foods = listOf(
        // Фрукты
        FoodItem("Apple", "Яблоко", 52, 0.3f, 0.2f, 14f, "Фрукты"),
        FoodItem("Banana", "Банан", 89, 1.1f, 0.3f, 23f, "Фрукты"),
        FoodItem("Orange", "Апельсин", 47, 0.9f, 0.1f, 12f, "Фрукты"),
        FoodItem("Grape", "Виноград", 69, 0.7f, 0.2f, 18f, "Фрукты"),
        FoodItem("Strawberry", "Клубника", 32, 0.7f, 0.3f, 7.7f, "Фрукты"),
        FoodItem("Watermelon", "Арбуз", 30, 0.6f, 0.2f, 7.6f, "Фрукты"),
        FoodItem("Pineapple", "Ананас", 50, 0.5f, 0.1f, 13f, "Фрукты"),
        FoodItem("Mango", "Манго", 60, 0.8f, 0.4f, 15f, "Фрукты"),

        // Овощи
        FoodItem("Carrot", "Морковь", 41, 0.9f, 0.2f, 10f, "Овощи"),
        FoodItem("Tomato", "Помидор", 18, 0.9f, 0.2f, 3.9f, "Овощи"),
        FoodItem("Cucumber", "Огурец", 16, 0.7f, 0.1f, 3.6f, "Овощи"),
        FoodItem("Potato", "Картофель", 77, 2f, 0.1f, 17f, "Овощи"),
        FoodItem("Broccoli", "Брокколи", 34, 2.8f, 0.4f, 7f, "Овощи"),
        FoodItem("Lettuce", "Салат", 15, 1.4f, 0.2f, 2.9f, "Овощи"),
        FoodItem("Onion", "Лук", 40, 1.1f, 0.1f, 9.3f, "Овощи"),
        FoodItem("Bell Pepper", "Перец", 31, 1f, 0.3f, 6f, "Овощи"),

        // Мясо
        FoodItem("Chicken Breast", "Куриная грудка", 165, 31f, 3.6f, 0f, "Мясо"),
        FoodItem("Beef", "Говядина", 250, 26f, 15f, 0f, "Мясо"),
        FoodItem("Pork", "Свинина", 242, 27f, 14f, 0f, "Мясо"),
        FoodItem("Turkey", "Индейка", 135, 30f, 1f, 0f, "Мясо"),
        FoodItem("Lamb", "Баранина", 294, 25f, 21f, 0f, "Мясо"),
        FoodItem("Duck", "Утка", 337, 19f, 28f, 0f, "Мясо"),

        // Рыба
        FoodItem("Salmon", "Лосось", 208, 20f, 13f, 0f, "Рыба"),
        FoodItem("Tuna", "Тунец", 132, 28f, 1.3f, 0f, "Рыба"),
        FoodItem("Shrimp", "Креветки", 99, 24f, 0.3f, 0.2f, "Рыба"),
        FoodItem("Cod", "Треска", 82, 18f, 0.7f, 0f, "Рыба"),
        FoodItem("Sardine", "Сардины", 208, 25f, 11f, 0f, "Рыба"),

        // Молочные продукты
        FoodItem("Milk", "Молоко", 42, 3.4f, 1f, 5f, "Молочные"),
        FoodItem("Cheese", "Сыр", 402, 25f, 33f, 1.3f, "Молочные"),
        FoodItem("Yogurt", "Йогурт", 59, 10f, 0.4f, 3.6f, "Молочные"),
        FoodItem("Butter", "Масло", 717, 0.9f, 81f, 0.1f, "Молочные"),
        FoodItem("Egg", "Яйцо", 155, 13f, 11f, 1.1f, "Молочные"),

        // Зерновые
        FoodItem("Rice", "Рис", 130, 2.7f, 0.3f, 28f, "Зерновые"),
        FoodItem("Bread", "Хлеб", 265, 9f, 3.2f, 49f, "Зерновые"),
        FoodItem("Pasta", "Паста", 131, 5f, 1.1f, 25f, "Зерновые"),
        FoodItem("Oatmeal", "Овсянка", 68, 2.4f, 1.4f, 12f, "Зерновые"),
        FoodItem("Cereal", "Хлопья", 379, 7f, 1.4f, 84f, "Зерновые"),

        // Орехи
        FoodItem("Almonds", "Миндаль", 579, 21f, 50f, 22f, "Орехи"),
        FoodItem("Walnuts", "Грецкий орех", 654, 15f, 65f, 14f, "Орехи"),
        FoodItem("Peanuts", "Арахис", 567, 26f, 49f, 16f, "Орехи"),

        // Напитки
        FoodItem("Coffee", "Кофе", 2, 0.3f, 0f, 0f, "Напитки"),
        FoodItem("Tea", "Чай", 1, 0f, 0f, 0.3f, "Напитки"),
        FoodItem("Orange Juice", "Апельсиновый сок", 45, 0.7f, 0.2f, 10f, "Напитки"),
        FoodItem("Cola", "Кола", 42, 0f, 0f, 11f, "Напитки"),
        FoodItem("Beer", "Пиво", 43, 0.5f, 0f, 3.6f, "Напитки"),
        FoodItem("Wine", "Вино", 83, 0.1f, 0f, 2.6f, "Напитки"),

        // Сладости
        FoodItem("Chocolate", "Шоколад", 546, 5f, 31f, 60f, "Сладости"),
        FoodItem("Ice Cream", "Мороженое", 207, 3.5f, 11f, 24f, "Сладости"),
        FoodItem("Cake", "Торт", 347, 4.5f, 16f, 46f, "Сладости"),
        FoodItem("Cookie", "Печенье", 502, 5.8f, 25f, 65f, "Сладости"),
        FoodItem("Candy", "Конфета", 394, 4f, 4.3f, 86f, "Сладости"),

        // Фастфуд
        FoodItem("Hamburger", "Гамбургер", 295, 17f, 14f, 24f, "Фастфуд"),
        FoodItem("French Fries", "Картофель фри", 312, 3.4f, 15f, 41f, "Фастфуд"),
        FoodItem("Pizza", "Пицца", 266, 11f, 10f, 33f, "Фастфуд"),
        FoodItem("Hot Dog", "Хот-дог", 290, 10f, 15f, 25f, "Фастфуд"),
        FoodItem("Sandwich", "Сэндвич", 250, 12f, 9f, 28f, "Фастфуд"),
        FoodItem("Donut", "Пончик", 452, 5.5f, 25f, 51f, "Фастфуд"),

        // Супы
        FoodItem("Soup", "Суп", 50, 3f, 1.5f, 6f, "Супы"),
        FoodItem("Borsch", "Борщ", 45, 2f, 1.2f, 6.5f, "Супы"),
        FoodItem("Chicken Soup", "Куриный суп", 38, 3.5f, 1.4f, 3.2f, "Супы"),
    )

    fun findFoodByName(query: String): FoodItem? {
        val lowerQuery = query.lowercase()
        return foods.find {
            it.name.lowercase().contains(lowerQuery) ||
            it.nameRu.lowercase().contains(lowerQuery)
        }
    }

    fun findFoodByLabel(label: String): FoodItem? {
        val lowerLabel = label.lowercase()
        return foods.find {
            it.name.lowercase().contains(lowerLabel) ||
            it.category.lowercase().contains(lowerLabel)
        }
    }

    fun getAllCategories(): List<String> {
        return foods.map { it.category }.distinct()
    }

    fun getFoodsByCategory(category: String): List<FoodItem> {
        return foods.filter { it.category == category }
    }
}
