package ua.com.itproekt.gup.model.order;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;

public class OrderFeedback {

    /**
     * History of buyer feedback
     */
    private List<BuyerFeedback> buyerFeedbackList;

    @Min(1)
    @Max(5)
    private int point;

    /**
     * String - user id
     */
    private Map<String, OrderFeedbackOptions> buyerFeedbackOptionsMap;
    // ---------------------------------------------------------------------------------------------


    @Size(min = 5, max = 500)
    private String sellerComment;
    private Long sellerCommentDate;

    /**
     * String - user id
     */
    private Map<String, OrderFeedbackOptions> sellerFeedbackOptions;


    // -------------------------------------------------------------------------------------------
    public OrderFeedback() {
    }

    public OrderFeedback setSellerCommentDateToCurrentDate() {
        this.sellerCommentDate = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        return this;
    }


    public void addNewBuyerFeedback(String feedbackText, int point) {
        this.buyerFeedbackList.add(new BuyerFeedback(feedbackText));
        this.point = point;
    }

    public void addUpdatedBuyerFeedback(String feedbackText) {
        this.buyerFeedbackList.add(new BuyerFeedback(feedbackText));
    }




    public class BuyerFeedback {

        @Size(min = 5, max = 500)
        String feedbackText;
        Long feedBackDate;


        public BuyerFeedback(String feedbackText) {
            this.feedbackText = feedbackText;
            this.feedBackDate = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        }

        public String getFeedbackText() {
            return feedbackText;
        }

        public void setFeedbackText(String feedbackText) {
            this.feedbackText = feedbackText;
        }

        public Long getFeedBackDate() {
            return feedBackDate;
        }

        public void setFeedBackDate(Long feedBackDate) {
            this.feedBackDate = feedBackDate;
        }

        @Override
        public String toString() {
            return "BuyerFeedback{" +
                    "feedbackText='" + feedbackText + '\'' +
                    ", feedBackDate=" + feedBackDate +
                    '}';
        }
    }
// -------------------------------------------------------------------------------------------


    public List<BuyerFeedback> getBuyerFeedbackList() {
        return buyerFeedbackList;
    }

    public OrderFeedback setBuyerFeedbackList(List<BuyerFeedback> buyerFeedbackList) {
        this.buyerFeedbackList = buyerFeedbackList;
        return this;
    }

    public int getPoint() {
        return point;
    }

    public OrderFeedback setPoint(int point) {
        this.point = point;
        return this;
    }

    public String getSellerComment() {
        return sellerComment;
    }

    public OrderFeedback setSellerComment(String sellerComment) {
        this.sellerComment = sellerComment;
        return this;
    }

    public Long getSellerCommentDate() {
        return sellerCommentDate;
    }

    public OrderFeedback setSellerCommentDate(Long sellerCommentDate) {
        this.sellerCommentDate = sellerCommentDate;
        return this;
    }

    public Map<String, OrderFeedbackOptions> getBuyerFeedbackOptionsMap() {
        return buyerFeedbackOptionsMap;
    }

    public OrderFeedback setBuyerFeedbackOptionsMap(Map<String, OrderFeedbackOptions> buyerFeedbackOptionsMap) {
        this.buyerFeedbackOptionsMap = buyerFeedbackOptionsMap;
        return this;
    }

    public Map<String, OrderFeedbackOptions> getSellerFeedbackOptions() {
        return sellerFeedbackOptions;
    }

    public OrderFeedback setSellerFeedbackOptions(Map<String, OrderFeedbackOptions> sellerFeedbackOptions) {
        this.sellerFeedbackOptions = sellerFeedbackOptions;
        return this;
    }
}
