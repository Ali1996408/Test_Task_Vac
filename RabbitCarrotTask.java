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
