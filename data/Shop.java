package shop;

import java.util.Scanner;

public class Shop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Список возможных товаров для покупки");

        Product[] products = {
                new Product(1, "Хлеб", 20),
                new Product(2, "Соль", 10),
                new Product(3, "Молоко", 30)
        };

        Item[] items = new Item[1000];
        for (int i = 0; i < 1000; i++) {
            items[i] = null;
        }

        for (int i = 0; i < products.length; i++) {
            System.out.println(products[i].toString());
        }

        int currentIndex = 0;

        while (true) {
            System.out.println("Выберите товар и количество товара или введите `end` для завершения программы");
            String inputString = scanner.nextLine();
            if (inputString.equals("end")) {
                System.out.println("Ваша корзина:");
                System.out.println("Наименование товара\tКоличество\tЦена\tОбщая стоимость");
                int sumProducts = 0;
                for (Item item : items) {
                    if (item != null) {
                        String currentName = products[item.productId - 1].name;
                        int currentPrice = products[item.productId - 1].price;
                        int sumCurrentProduct = item.productCount * currentPrice;
                        sumProducts += sumCurrentProduct;
                        System.out.println(currentName + "\t\t\t\t" + item.productCount + "\t\t\t" +
                                currentPrice + "\t\t" + sumCurrentProduct);
                    }
                }
                System.out.println("\t\t\t\t\t\t\t\tИтого:" + "\t" + sumProducts);
                break;
            }

            // Тут мы разбиваем массив и достаем из него productId и productCount
            String[] parts = inputString.split(" ");
            int productId = Integer.parseInt(parts[0]);
            int productCount = Integer.parseInt(parts[1]);

            // Выполняем проверку, ввел пользователь новый тип товара,
            // или повторил один из имеющихся
            boolean isNewKindOfItem = true; // булевый флаг 'новый' товар или 'повторяющийся'
            int indexOdExistProduct = -1; // В эту переменную созраним индекс повторенного товара
            for (int i = 0; i < 1000; i++) { // Пробегаемся по массиву
                if (items[i] != null) { // Ищем не null элементы
                    if (items[i].productId == productId) { // Если товар с таким productId уже есть в корзине
                        isNewKindOfItem = false; // Помечаем, что это не новый тип товара
                        indexOdExistProduct = i; // Запоминаем индекс товара в массиве
                        break; // Выходим из цикла
                    }
                }
            }

            if (isNewKindOfItem) { // Если это новый тип товара
                Item currentItem = new Item(productId, productCount); // Создаем новый товар
                items[currentIndex] = currentItem; // Записываем его в массив
                currentIndex += 1; // Увеличиваем индекс для записи информации о следующем товаре
            } else { // Если такой товар уже есть в корзине
                items[indexOdExistProduct].productCount += productCount; // Находим его и прибавляем количество
            }
        }
    }
}
