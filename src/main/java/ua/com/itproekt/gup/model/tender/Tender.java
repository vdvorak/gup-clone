package ua.com.itproekt.gup.model.tender;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Set;


@Document(collection = "tender")
public class Tender {
    @Id
    private String id;
    private String authorId;
    private String title;
    private String naceId;
    private String body;
    private TenderType type;
    private List<Comment> comments;
    private List<Member> members;
    private List<Propose> proposes;
    private Long begin;
    private Long end;
    private Long visited;
    private Set<String> uploadFilesIds;
    private Address address;
    private Boolean hidePropose;

    public Tender(){
        begin = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        LocalDateTime l = LocalDateTime.now().plusDays(20L);
        end = l.toInstant(ZoneOffset.UTC).toEpochMilli();
        hidePropose = true;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNaceId() {
        return naceId;
    }

    public void setNaceId(String naceId) {
        this.naceId = naceId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public TenderType getType() {
        return type;
    }

    public void setType(TenderType type) {
        this.type = type;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Long getBegin() {
        return begin;
    }

    public void setBegin (Long begin) {
        this.begin = begin;
    }

    public void setBeginLocalDateTime(LocalDateTime time) {
        this.begin = time.toInstant(ZoneOffset.UTC).toEpochMilli();
    }

    public Long getEnd() {
        return end;
    }

    public void setEnd(Long end) {
        this.end = end;
    }

    public void setEndLocalDateTime(LocalDateTime time) {
        this.end = time.toInstant(ZoneOffset.UTC).toEpochMilli();
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public List<Propose> getProposes() {
        return proposes;
    }

    public void setProposes(List<Propose> proposes) {
        this.proposes = proposes;
    }

    public Long getVisited() {
        return visited;
    }

    public void setVisited(Long visited) {
        this.visited = visited;
    }

    public Set<String> getUploadFilesIds() {
        return uploadFilesIds;
    }

    public void setUploadFilesIds(Set<String> uploadFilesIds) {
        this.uploadFilesIds = uploadFilesIds;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Boolean getHidePropose() {
        return hidePropose;
    }

    public void setHidePropose(Boolean hidePropose) {
        this.hidePropose = hidePropose;
    }

    @Override
    public String toString() {
        return "Tender{" +
                "id='" + id + '\'' +
                ", authorId='" + authorId + '\'' +
                ", title='" + title + '\'' +
                ", naceId='" + naceId + '\'' +
                ", body='" + body + '\'' +
                ", type=" + type +
                ", comments=" + comments +
                ", members=" + members +
                ", proposes=" + proposes +
                ", begin=" + begin +
                ", end=" + end +
                ", visited=" + visited +
                ", uploadFilesIds=" + uploadFilesIds +
                ", address=" + address +
                ", hidePropose=" + hidePropose +
                '}';
    }
}