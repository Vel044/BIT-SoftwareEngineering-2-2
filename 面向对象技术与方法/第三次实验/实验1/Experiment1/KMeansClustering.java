import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.BufferedWriter;
import java.io.FileWriter;


public class KMeansClustering {
    private static final int K = 3; // 设置聚类数
    private static final int MAX_ITERATIONS = 1000;
    private static final double EPSILON = 0.0001;

    public static void main(String[] args) {
        List<IrisData> dataSet = loadIrisData("iris.data");
        if (dataSet.isEmpty()) {
            System.out.println("数据集为空，无法进行聚类。");
            return;
        }

        List<IrisData> centroids = initializeCentroids(dataSet);
        List<List<IrisData>> clusters = new ArrayList<>(K);
        List<IrisData> previousCentroids;

        for (int i = 0; i < K; i++) {
            clusters.add(new ArrayList<>());
        }

        int iterations = 0;
        do {
            assignToClusters(dataSet, centroids, clusters);
            previousCentroids = new ArrayList<>(centroids);
            updateCentroids(clusters, centroids);
            iterations++;
        } while (iterations < MAX_ITERATIONS && !converged(centroids, previousCentroids));

        writeClustersToFile(clusters);
        System.out.println("K-means clustering completed in " + iterations + " iterations.");
    }

    private static List<IrisData> loadIrisData(String filename) {
        List<IrisData> dataSet = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    double sepalLength = Double.parseDouble(parts[0]);
                    double sepalWidth = Double.parseDouble(parts[1]);
                    double petalLength = Double.parseDouble(parts[2]);
                    double petalWidth = Double.parseDouble(parts[3]);
                    String label = parts[4];
                    dataSet.add(new IrisData(sepalLength, sepalWidth, petalLength, petalWidth, label));
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return dataSet;
    }

    private static List<IrisData> initializeCentroids(List<IrisData> dataSet) {
        List<IrisData> centroids = new ArrayList<>();
        Random random = new Random();
        int size = dataSet.size();
        for (int i = 0; i < K; i++) {
            int index = random.nextInt(size);
            centroids.add(dataSet.get(index));
        }
        return centroids;
    }

    private static void assignToClusters(List<IrisData> dataSet, List<IrisData> centroids, List<List<IrisData>> clusters) {
        for (List<IrisData> cluster : clusters) {
            cluster.clear();
        }

        for (IrisData data : dataSet) {
            double minDistance = Double.MAX_VALUE;
            int clusterIndex = -1;

            for (int i = 0; i < K; i++) {
                double distance = euclideanDistance(data, centroids.get(i));
                if (distance < minDistance) {
                    minDistance = distance;
                    clusterIndex = i;
                }
            }

            clusters.get(clusterIndex).add(data);
        }
    }

    private static double euclideanDistance(IrisData point1, IrisData point2) {
        double sum = 0.0;
        sum += Math.pow(point1.sepalLength - point2.sepalLength, 2);
        sum += Math.pow(point1.sepalWidth - point2.sepalWidth, 2);
        sum += Math.pow(point1.petalLength - point2.petalLength, 2);
        sum += Math.pow(point1.petalWidth - point2.petalWidth, 2);
        return Math.sqrt(sum);
    }

    private static void updateCentroids(List<List<IrisData>> clusters, List<IrisData> centroids) {
        for (int i = 0; i < K; i++) {
            double sepalLengthSum = 0.0;
            double sepalWidthSum = 0.0;
            double petalLengthSum = 0.0;
            double petalWidthSum = 0.0;
            List<IrisData> cluster = clusters.get(i);

            for (IrisData data : cluster) {
                sepalLengthSum += data.sepalLength;
                sepalWidthSum += data.sepalWidth;
                petalLengthSum += data.petalLength;
                petalWidthSum += data.petalWidth;
            }

            int clusterSize = cluster.size();
            if (clusterSize > 0) {
                centroids.get(i).sepalLength = sepalLengthSum / clusterSize;
                centroids.get(i).sepalWidth = sepalWidthSum / clusterSize;
                centroids.get(i).petalLength = petalLengthSum / clusterSize;
                centroids.get(i).petalWidth = petalWidthSum / clusterSize;
            }
        }
    }

    private static boolean converged(List<IrisData> centroids, List<IrisData> previousCentroids) {
        for (int i = 0; i < K; i++) {
            if (euclideanDistance(centroids.get(i), previousCentroids.get(i)) > EPSILON) {
                return false;
            }
        }
        return true;
    }

    private static void writeClustersToFile(List<List<IrisData>> clusters) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("cluster.txt"))) {
            for (int i = 0; i < K; i++) {
                writer.write("Cluster " + (i + 1) + ":\n");
                for (IrisData data : clusters.get(i)) {
                    writer.write(data.toString() + "\n");
                }
                writer.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class IrisData {
        double sepalLength;
        double sepalWidth;
        double petalLength;
        double petalWidth;
        String label;

        public IrisData(double sepalLength, double sepalWidth, double petalLength, double petalWidth, String label) {
            this.sepalLength = sepalLength;
            this.sepalWidth = sepalWidth;
            this.petalLength = petalLength;
            this.petalWidth = petalWidth;
            this.label = label;
        }

        @Override
        public String toString() {
            return sepalLength + "," + sepalWidth + "," + petalLength + "," + petalWidth + "," + label;
        }
    }
}
