package ua.com.itproekt.gup.model.notice;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "notice")
public class Notice {
    @Id
    private String id;
    //user who will receive this notice
    private String userId;
    // this is id of entity, which is initiate this notice
    // for example it can be offer or doer wich have new comments or confirm, whatever
    private String initiatorId;
    // name of service which initiator is belong to
    private String serviceName;
    // for what sense this notice is
    //for example "unconfirmed client" or "new (not red) messages"
    private String noticePoint;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getInitiatorId() {
        return initiatorId;
    }

    public void setInitiatorId(String initiatorId) {
        this.initiatorId = initiatorId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getNoticePoint() {
        return noticePoint;
    }

    public void setNoticePoint(String noticePoint) {
        this.noticePoint = noticePoint;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", initiatorId='" + initiatorId + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", noticePoint='" + noticePoint + '\'' +
                '}';
    }
}
