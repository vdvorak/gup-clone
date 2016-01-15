package ua.com.itproekt.gup.model.tender.doer;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ua.com.itproekt.gup.model.profiles.Contact;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Document(collection = "doer")
public class Doer {
    @Id
    private String id;
    private String authorId;
    // this field mast never storeg in DB, it will be download at "read" time
    private Contact authorContacts;
    private List<String> naceIds;
    private String title;
    private String body;
    // ids of people or company, hwo work with this doer
    private List<DoerClient> clients;

    private int countVisit;
    private long dateOfCreate;
    private long dateOfUpdate;

    private List<Recall> recalls;
    private long recallCount;
    private String imageId;
    //answer (otklik)/raiting

    public Doer() {
        dateOfCreate = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public Contact getAuthorContacts() {
        return authorContacts;
    }

    public void setAuthorContacts(Contact authorContacts) {
        this.authorContacts = authorContacts;
    }

    public List<String> getNaceIds() {
        return naceIds;
    }

    public void setNaceIds(List<String> naceIds) {
        this.naceIds = naceIds;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<DoerClient> getClients() {
        return clients;
    }

    public void setClients(List<DoerClient> clients) {
        this.clients = clients;
    }

    public int getCountVisit() {
        return countVisit;
    }

    public void setCountVisit(int countVisit) {
        this.countVisit = countVisit;
    }

    public long getDateOfCreate() {
        return dateOfCreate;
    }

    public void setDateOfCreate(long dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
    }

    public List<Recall> getRecalls() {
        return recalls;
    }

    public void setRecalls(List<Recall> recalls) {
        this.recalls = recalls;
    }

    public long getRecallCount() {
        return recallCount;
    }

    public void setRecallCount(long recallCount) {
        this.recallCount = recallCount;
    }

    public long getDateOfUpdate() {
        return dateOfUpdate;
    }

    public void setDateOfUpdate(long dateOfUpdate) {
        this.dateOfUpdate = dateOfUpdate;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    @Override
    public String toString() {
        String authorContactsStr = "null";
        if(authorContacts != null){
            authorContactsStr = authorContacts.toString();
        }
        return "Doer{" +
                "id='" + id + '\'' +
                ", authorId='" + authorId + '\'' +
                ", authorContacts=" + authorContactsStr +
                ", naceIds=" + naceIds +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", confirmed clients=" + clients.stream().filter((s) -> s.isClientConfirm() && s.isDoerConfirm()).map(DoerClient::getId).toString() +
                ", NOT confirmed clients=" + clients.stream().filter((s) -> !s.isClientConfirm() || !s.isDoerConfirm()).map(DoerClient::getId).toString() +
                ", countVisit=" + countVisit +
                ", dateOfCreate=" + dateOfCreate +
                ", imageId=" + imageId +
                ", recalls=" + recalls +
                ", recallCount=" + recallCount +
                '}';
    }

    private String clientsIds(){
        StringBuilder result = new StringBuilder();
        for(DoerClient client:this.getClients()){

            result.append(client.getId());
            result.append(", ");
        }
        return result.toString();
    }
}
