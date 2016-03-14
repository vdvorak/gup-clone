package ua.com.itproekt.gup.model.tender;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Document(collection = "tender")
public class Tender {
    @Id
    private String id;
    private String authorId;
    private String title;
    private Set<String> naceIds;
    private String body;
    private TenderType type;
    private List<Member> members;
    private List<Propose> proposes;
    private Long begin;
    private Long end;
    private Long visited;
    private Map<String, String> uploadFilesIds;
    private Address address;
    private Boolean hidePropose;
    private String tenderNumber;
    private Integer expectedPrice;
    private Integer proposeNumber;
    private Boolean hideContact;
    private String winnerId;

    public Tender(){
        begin = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        visited = 0l;
        proposes = new ArrayList<Propose>();
        members = new ArrayList<Member>();
    }

    public Boolean getHidePropose() {
        return hidePropose;
    }

    public Boolean getHideContact() {
        return hideContact;
    }

    public String getTenderNumber() {
        return tenderNumber;
    }

    public void setTenderNumber(String tenderNumber) {
        this.tenderNumber = tenderNumber;
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

    public Set<String> getNaceIds() {
        return naceIds;
    }

    public void setNaceIds(Set<String> naceIds) {
        this.naceIds = naceIds;
    }

    public void addNeceId(String naceId) {
        naceIds.add(naceId);
    }

    public void addNeceId(Set<String> naceIds) {
        this.naceIds.addAll(naceIds);
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
        this.setProposeNumber();
    }

    public Long getVisited() {
        return visited;
    }

    public void setVisited(Long visited) {
        this.visited = visited;
    }

    public Map<String, String> getUploadFilesIds() {
        return uploadFilesIds;
    }

    public void setUploadFilesIds(Map<String, String> uploadFilesIds) {
        this.uploadFilesIds = uploadFilesIds;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Boolean isHidePropose() {
        return hidePropose;
    }

    public void setHidePropose(Boolean hidePropose) {
        this.hidePropose = hidePropose;
    }

    public Integer getExpectedPrice() {
        return expectedPrice;
    }

    public void setExpectedPrice(Integer expectedPrice) {
        this.expectedPrice = expectedPrice;
    }

    public Integer getProposeNumber() {
        if(proposes == null) return 0;
        return proposes.size();
    }

    public void setProposeNumber() {
        if (proposes == null){
            proposeNumber = 0;
        }else {
            proposeNumber = proposes.size();
        }
    }

    public void setProposeNumber(Integer num) {
        setProposeNumber();
    }

    public Boolean isHideContact() {
        return hideContact;
    }

    public void setHideContact(Boolean hideContact) {
        this.hideContact = hideContact;
    }

    public String getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(String winnerId) {
        long now = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        if (end == null || now < end){
            end = now;
        }
        this.winnerId = winnerId;
    }

    @Override
    public String toString() {
        return "Tender{" +
                "id='" + id + '\'' +
                ", authorId='" + authorId + '\'' +
                ", tender number='" + tenderNumber + '\'' +
                ", title='" + title + '\'' +
                ", expected price='" + expectedPrice + '\'' +
                ", naceIds='" + naceIds + '\'' +
                ", body='" + body + '\'' +
                ", type=" + type +
                ", members=" + members +
                ", proposes=" + proposes +
                ", begin=" + begin +
                ", end=" + end +
                ", visited=" + visited +
                ", uploadFilesIds=" + uploadFilesIds +
                ", address=" + address +
                ", hidePropose=" + hidePropose +
                ", hideContact=" + hideContact +
                ", winner id=" + winnerId +
                '}';
    }
}