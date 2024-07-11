
 RabbitCarrotTask

RabbitCarrotTask — это Java-программа, которая определяет максимальное количество моркови, которое кролик может собрать, имея заданный список весов моркови, максимальное количество ходок и максимальный вес, который кролик может нести за одну ходку.

 Описание

Основная цель программы — вычислить максимальный общий вес моркови, которую можно собрать за определенное количество ходок, при этом вес каждой ходки не должен превышать допустимый максимум.

 Основные компоненты

 Метод main
- Инициализирует список весов моркови.
- Определяет максимальное количество ходок (maxHops) и максимальный вес моркови на одну ходку (maxWeightPerHop).
- Вызывает метод getMaxCarrots, чтобы вычислить максимальный вес моркови, которую можно собрать, и выводит результат.

 Метод getMaxCarrots
- Генерирует все возможные комбинации весов моркови, которые не превышают максимальный вес на одну ходку.
- Сортирует эти комбинации в порядке убывания общего веса.
- Суммирует веса морковных комбинаций для разрешенного количества ходок (maxHops).

 Метод generateCombinations
- Рекурсивно создает все возможные комбинации весов моркови, которые не превышают заданный максимальный вес на одну ходку.
- Используется метод обратного отслеживания (backtracking) для создания комбинаций.

 Метод sum
- Вычисляет сумму весов в заданном списке целых чисел.

## Использование

Для запуска программы компилируйте и выполняйте класс RabbitCarrotTask с помощью компилятора Java.

javac RabbitCarrotTask.java
java RabbitCarrotTask


Ожидаемый вывод покажет максимальный вес моркови, который кролик может собрать при заданных ограничениях.

## Пример

Если список весов моркови равен [1, 2, 3, 4, 5], максимальное количество ходок 10, и максимальный вес на одну ходку 5, вывод программы будет:

Maximum carrots: 15


В этом примере кролик может сделать 10 ходок, но максимум моркови он собирает за меньшее количество ходок за счет оптимального выбора весов моркови.

 Код

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class RabbitCarrotTask {
    public static void main(String[] args) {
        // Открываем список наличной морковки
        List<Integer> carrotWeights = new ArrayList<>();
        carrotWeights.add(1);
        carrotWeights.add(2);
        carrotWeights.add(3);
        carrotWeights.add(4);
        carrotWeights.add(5);

        // Максимальное количество ходок
        int maxHops = 10;
        // Максимальная весовая масса морковки на одну ходку
        int maxWeightPerHop = 5;

        // Получим максимальное количество морковки
        int maxCarrotsCollected = getMaxCarrots(carrotWeights, maxHops, maxWeightPerHop);
        System.out.println("Maximum carrots: " + maxCarrotsCollected);
    }

    public static int getMaxCarrots(List<Integer> carrotWeights, int maxHops, int maxWeightPerHop) {
        // Создаем список для хранения всех возможных вариантов (комбинаций масс моркови)
        List<List<Integer>> combinations = new ArrayList<>();
        generateCombinations(carrotWeights, combinations, new ArrayList<>(), 0, maxWeightPerHop);

        // Сортируем комбинации по убыванию суммы масс
        combinations.sort((list1, list2) -> Integer.compare(sum(list2), sum(list1)));

        int totalCarrots = 0;
        int hop = 0;
        while (hop < maxHops && hop < combinations.size()) {
            totalCarrots += sum(combinations.get(hop));
            hop++;
        }

        return totalCarrots;
    }

    public static void generateCombinations(List<Integer> carrotWeights, List<List<Integer>> combinations, List<Integer> currentCombo, int start, int maxWeight) {
        if (sum(currentCombo) <= maxWeight) {
            combinations.add(new ArrayList<>(currentCombo));
        }

        for (int i = start; i < carrotWeights.size(); i++) {
            currentCombo.add(carrotWeights.get(i));
            generateCombinations(carrotWeights, combinations, currentCombo, i + 1, maxWeight);
            currentCombo.remove(currentCombo.size() - 1);


        }
    }

    public static int sum(List<Integer> list) {
        int sum = 0;
        for (int num : list) {
            sum += num;
        }
        return sum;
    }
    }



