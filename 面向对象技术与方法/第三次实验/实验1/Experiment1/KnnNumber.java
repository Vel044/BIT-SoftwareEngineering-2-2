import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class KnnNumber {
    private Map<Integer, List<int[][]>> trainingData;
    private Map<Integer, List<int[][]>> testData;

    public KnnNumber() {
        trainingData = new HashMap<>();
        testData = new HashMap<>();
    }

    // 加载训练数据
    public void loadTrainingData(String filePath) throws IOException {
        for (int n = 0; n <= 9; n++) {
            for (int m = 0; m < 1000; m++) { // 假设每个数字有1000张不同的数据
                String fileName = filePath + "/" + n + "_" + m + ".txt";
                File file = new File(fileName);
                if (file.exists()) {
                    System.out.println("Opening " + fileName);
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    List<String> lines = new ArrayList<>();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        lines.add(line);
                    }
                    if (!lines.isEmpty()) {
                        int[][] image = convertStringToMatrix(lines);
                        trainingData.computeIfAbsent(n, k -> new ArrayList<>()).add(image);
                    }
                    reader.close();
                }
            }
        }
    }

    // 加载测试数据
    public void loadTestData(String filePath) throws IOException {
        for (int n = 0; n <= 9; n++) {
            for (int m = 0; m < 1000; m++) { // 假设每个数字有1000张不同的数据
                String fileName = filePath + "/" + n + "_" + m + ".txt";
                File file = new File(fileName);

                if (file.exists()) {
                    System.out.println("Opening " + fileName);
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    List<String> lines = new ArrayList<>();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        lines.add(line);
                    }
                    if (!lines.isEmpty()) {
                        int[][] image = convertStringToMatrix(lines);
                        testData.computeIfAbsent(n, k -> new ArrayList<>()).add(image);
                    }
                    reader.close();
                }
            }
        }
    }

    // 辅助方法：将字符串列表转换为二维整数数组表示的图像
    private int[][] convertStringToMatrix(List<String> lines) {
        int[][] image = new int[lines.size()][lines.get(0).length()];
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                image[i][j] = Character.getNumericValue(lines.get(i).charAt(j));
            }
        }
        return image;
    }

    // 计算两个二维整数数组之间的欧氏距离
    private int calculateDistance(int[][] image1, int[][] image2) {
        int distance = 0;
        for (int i = 0; i < image1.length; i++) {
            for (int j = 0; j < image1[i].length; j++) {
                int diff = image1[i][j] - image2[i][j];
                distance += diff * diff;
            }
        }
        return (int) Math.sqrt(distance);
    }

    /**
     * 使用K-最近邻算法对给定的图像进行分类。
     *
     * @param image 要分类的图像，表示为一个二维整数数组。
     * @param k     用于决定"最近邻"的数目。
     * @return 预测的类别标签。
     */
    public int classifyImage(int[][] image, int k) {
        // 创建一个映射，以存储每个类别标签与其对应的距离优先队列
        Map<Integer, PriorityQueue<Integer>> labelDistances = new HashMap<>();

        // 遍历所有已知的训练数据标签
        for (int label : trainingData.keySet()) {
            // 为每个标签创建一个最大堆，用来存储距离（这里用最大堆是为了方便删除最大的距离元素）
            labelDistances.put(label, new PriorityQueue<>((a, b) -> b - a));
            // 遍历与当前标签相关的所有训练图像
            for (int[][] trainImage : trainingData.get(label)) {
                // 计算当前训练图像与要分类的图像之间的距离
                int distance = calculateDistance(image, trainImage);
                // 获取当前标签的距离队列
                PriorityQueue<Integer> queue = labelDistances.get(label);
                // 将计算出的距离添加到队列中
                queue.add(distance);
                // 如果队列长度超过了k，移除最大的距离，保持队列大小为k
                if (queue.size() > k) {
                    queue.poll();
                }
            }
        }

        // 创建一个映射，用于存储每个标签的k个最小距离的总和
        Map<Integer, Integer> sums = new HashMap<>();
        for (int label : labelDistances.keySet()) {
            PriorityQueue<Integer> distances = labelDistances.get(label);
            int sum = 0;
            // 计算并累加每个标签的k个最小距离
            while (!distances.isEmpty()) {
                sum += distances.poll();
            }
            // 将每个标签的距离总和存储到映射中
            sums.put(label, sum);
        }

        // 在存储距离总和的映射中找到具有最小距离总和的标签，这是预测的类别
        return sums.entrySet().stream()
                .min(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(-1);
    }

    // 评估分类准确率
    public double evaluateAccuracy(int k) {
        int correctCount = 0;
        int totalCount = 0;
        for (int label : testData.keySet()) {
            for (int[][] testImage : testData.get(label)) {
                int predictedClass = classifyImage(testImage, k);
                if (predictedClass == label) {
                    correctCount++;
                }
                totalCount++;
            }
        }
        return totalCount == 0 ? 0 : (double) correctCount / totalCount;
    }

    public static void main(String[] args) throws IOException {
        KnnNumber knn = new KnnNumber();
        knn.loadTrainingData("digits/trainingDigits");
        knn.loadTestData("digits/testDigits");

        for (int i = 3; i <= 9; i++) {
            System.out.println("K = " + i);
            double accuracy = knn.evaluateAccuracy(i);
            System.out.println("Accuracy: " + (accuracy * 100) + "%");
        }
    }
}
