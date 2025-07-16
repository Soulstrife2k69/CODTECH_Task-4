import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import java.util.List;

public class Recommender {
    private final UserBasedRecommender userBasedRecommender;

    public Recommender(DataModel model) throws TasteException {
        UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
        UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model);
        this.userBasedRecommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
    }

    public List<RecommendedItem> recommend(long userId, int numRecommendations) throws TasteException {
        return userBasedRecommender.recommend(userId, numRecommendations);
    }
}
