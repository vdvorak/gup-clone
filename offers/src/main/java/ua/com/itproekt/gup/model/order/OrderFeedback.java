package ua.com.itproekt.gup.model.order;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

public class OrderFeedback {

    // ----------------------------------------


    private List<BuyerFeedback> buyerFeedbackList;

    @Min(1)
    @Max(5)
    private int point;

    private int feedbackLikesCount;
    private int feedbackDislikesCount;
    private int feedbackSpamCount;


    // ----------------------------------------


    @Size(min = 5, max = 500)
    private String sellerComment;
    private Long sellerCommentDate;

    private int sellerCommentLikesCount;
    private int sellerCommentDislikesCount;
    private int sellerCommentSpamCount;


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

    public int getFeedbackLikesCount() {
        return feedbackLikesCount;
    }

    public OrderFeedback setFeedbackLikesCount(int feedbackLikesCount) {
        this.feedbackLikesCount = feedbackLikesCount;
        return this;
    }

    public int getFeedbackDislikesCount() {
        return feedbackDislikesCount;
    }

    public OrderFeedback setFeedbackDislikesCount(int feedbackDislikesCount) {
        this.feedbackDislikesCount = feedbackDislikesCount;
        return this;
    }

    public int getFeedbackSpamCount() {
        return feedbackSpamCount;
    }

    public OrderFeedback setFeedbackSpamCount(int feedbackSpamCount) {
        this.feedbackSpamCount = feedbackSpamCount;
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

    public int getSellerCommentLikesCount() {
        return sellerCommentLikesCount;
    }

    public OrderFeedback setSellerCommentLikesCount(int sellerCommentLikesCount) {
        this.sellerCommentLikesCount = sellerCommentLikesCount;
        return this;
    }

    public int getSellerCommentDislikesCount() {
        return sellerCommentDislikesCount;
    }

    public OrderFeedback setSellerCommentDislikesCount(int sellerCommentDislikesCount) {
        this.sellerCommentDislikesCount = sellerCommentDislikesCount;
        return this;
    }

    public int getSellerCommentSpamCount() {
        return sellerCommentSpamCount;
    }

    public OrderFeedback setSellerCommentSpamCount(int sellerCommentSpamCount) {
        this.sellerCommentSpamCount = sellerCommentSpamCount;
        return this;
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
    }


    @Override
    public String toString() {
        return "OrderFeedback{" +
                "buyerFeedbackList=" + buyerFeedbackList +
                ", point=" + point +
                ", feedbackLikesCount=" + feedbackLikesCount +
                ", feedbackDislikesCount=" + feedbackDislikesCount +
                ", feedbackSpamCount=" + feedbackSpamCount +
                ", sellerComment='" + sellerComment + '\'' +
                ", sellerCommentDate=" + sellerCommentDate +
                ", sellerCommentLikesCount=" + sellerCommentLikesCount +
                ", sellerCommentDislikesCount=" + sellerCommentDislikesCount +
                ", sellerCommentSpamCount=" + sellerCommentSpamCount +
                '}';
    }
}
