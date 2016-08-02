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

    public void setBuyerFeedbackList(List<BuyerFeedback> buyerFeedbackList) {
        this.buyerFeedbackList = buyerFeedbackList;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getFeedbackLikesCount() {
        return feedbackLikesCount;
    }

    public void setFeedbackLikesCount(int feedbackLikesCount) {
        this.feedbackLikesCount = feedbackLikesCount;
    }

    public int getFeedbackDislikesCount() {
        return feedbackDislikesCount;
    }

    public void setFeedbackDislikesCount(int feedbackDislikesCount) {
        this.feedbackDislikesCount = feedbackDislikesCount;
    }

    public int getFeedbackSpamCount() {
        return feedbackSpamCount;
    }

    public void setFeedbackSpamCount(int feedbackSpamCount) {
        this.feedbackSpamCount = feedbackSpamCount;
    }

    public String getSellerComment() {
        return sellerComment;
    }

    public void setSellerComment(String sellerComment) {
        this.sellerComment = sellerComment;
    }

    public Long getSellerCommentDate() {
        return sellerCommentDate;
    }

    public void setSellerCommentDate(Long sellerCommentDate) {
        this.sellerCommentDate = sellerCommentDate;
    }

    public int getSellerCommentLikesCount() {
        return sellerCommentLikesCount;
    }

    public void setSellerCommentLikesCount(int sellerCommentLikesCount) {
        this.sellerCommentLikesCount = sellerCommentLikesCount;
    }

    public int getSellerCommentDislikesCount() {
        return sellerCommentDislikesCount;
    }

    public void setSellerCommentDislikesCount(int sellerCommentDislikesCount) {
        this.sellerCommentDislikesCount = sellerCommentDislikesCount;
    }

    public int getSellerCommentSpamCount() {
        return sellerCommentSpamCount;
    }

    public void setSellerCommentSpamCount(int sellerCommentSpamCount) {
        this.sellerCommentSpamCount = sellerCommentSpamCount;
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
