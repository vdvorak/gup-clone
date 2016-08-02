package ua.com.itproekt.gup.model.order;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class OrderFeedback {


    @Size(min = 5, max = 500)
    private String feedback;

    @Min(1)
    @Max(5)
    private int point;

    private int likesCount;
    private int dislikesCount;
    private int spamCount;

    private Long feedBackDate;






    @Size(min = 5, max = 500)
    private String sellerComment;


    private Long sellerCommentDate;









    public OrderFeedback setFeedBackDateToCurrentDate() {
        this.feedBackDate = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        return this;
    }

    public OrderFeedback setSellerCommentDateToCurrentDate() {
        this.sellerCommentDate = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        return this;
    }

    public String getFeedback() {
        return feedback;
    }

    public OrderFeedback setFeedback(String feedback) {
        this.feedback = feedback;
        return this;
    }

    public String getSellerComment() {
        return sellerComment;
    }

    public OrderFeedback setSellerComment(String sellerComment) {
        this.sellerComment = sellerComment;
        return this;
    }

    public int getPoint() {
        return point;
    }

    public OrderFeedback setPoint(int point) {
        this.point = point;
        return this;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public OrderFeedback setLikesCount(int likesCount) {
        this.likesCount = likesCount;
        return this;
    }

    public int getDislikesCount() {
        return dislikesCount;
    }

    public OrderFeedback setDislikesCount(int dislikesCount) {
        this.dislikesCount = dislikesCount;
        return this;
    }

    public int getSpamCount() {
        return spamCount;
    }

    public OrderFeedback setSpamCount(int spamCount) {
        this.spamCount = spamCount;
        return this;
    }

    public Long getFeedBackDate() {
        return feedBackDate;
    }

    public OrderFeedback setFeedBackDate(Long feedBackDate) {
        this.feedBackDate = feedBackDate;
        return this;
    }

    public Long getSellerCommentDate() {
        return sellerCommentDate;
    }

    public OrderFeedback setSellerCommentDate(Long sellerCommentDate) {
        this.sellerCommentDate = sellerCommentDate;
        return this;
    }

    @Override
    public String toString() {
        return "OrderFeedback{" +
                "feedback='" + feedback + '\'' +
                ", sellerComment='" + sellerComment + '\'' +
                ", point=" + point +
                ", likesCount=" + likesCount +
                ", dislikesCount=" + dislikesCount +
                ", spamCount=" + spamCount +
                ", feedBackDate=" + feedBackDate +
                ", sellerCommentDate=" + sellerCommentDate +
                '}';
    }
}
