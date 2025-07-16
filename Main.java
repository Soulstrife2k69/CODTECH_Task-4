import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            // Default parameters
            String filePath = "src/sample_data.csv";
            long userId = 1;
            int numRecommendations = 3;

            // Simple argument parsing (can be enhanced)
            if (args.length > 0) {
                userId = Long.parseLong(args[0]);
                if (args.length > 1) {
                    numRecommendations = Integer.parseInt(args[1]);
                }
            }

            // Load data model
            DataModel model = DataLoader.loadModel(filePath);

            // Create recommender
            Recommender recommender = new Recommender(model);

            // Generate recommendations
            List<RecommendedItem> recommendations = recommender.recommend(userId, numRecommendations);

            // Display results
            System.out.println("Recommendations for user " + userId + "(List Length:"+recommendations.size()+")"+":");

            for (RecommendedItem recommendation : recommendations) {
                System.out.printf("Product: %d, Estimated Preference: %.2f%n",
                        recommendation.getItemID(), recommendation.getValue());
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid number format: " + e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            System.out.println("Error loading data file: " + e.getMessage());
            System.exit(1);
        } catch (TasteException e) {
            System.out.println("Error generating recommendations: " + e.getMessage());
            System.exit(1);
        }
    }
}
