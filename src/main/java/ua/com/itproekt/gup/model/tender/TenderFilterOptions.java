package ua.com.itproekt.gup.model.tender;

import java.time.LocalDateTime;
import java.util.List;

public class TenderFilterOptions extends Tender {

    private int skip;
    private int limit;
    private String searchField;
    private String sortDirection;
    private String sortField;
    private String memberId;
    private List<String> naceIdIn;
    private boolean justUsersNace;

    public TenderFilterOptions() {
        this.limit = 10;
        this.skip = 0;
        this.setBegin(-1L);
        this.setEnd(-1L);
    }

    public int getSkip() {
        return skip;
    }

    public void setSkip(int skip) {
        this.skip = skip;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getSearchField() {
        return searchField;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    @Override
    public String getId() {
        return super.getId();
    }

    @Override
    public void setId(String id) {
        super.setId(id);
    }

    @Override
    public String getTitle() {
        return super.getTitle();
    }

    @Override
    public void setTitle(String title) {
        super.setTitle(title);
    }

    @Override
    public String getNaceId() {
        return super.getNaceId();
    }

    @Override
    public void setNaceId(String naceId) {
        super.setNaceId(naceId);
    }

    @Override
    public String getBody() {
        return super.getBody();
    }

    @Override
    public void setBody(String body) {
        super.setBody(body);
    }

    @Override
    public TenderType getType() {
        return super.getType();
    }

    @Override
    public void setType(TenderType type) {
        super.setType(type);
    }

    @Override
    public List<Comment> getComments() {
        return super.getComments();
    }

    @Override
    public void setComments(List<Comment> comments) {
        super.setComments(comments);
    }

    @Override
    public void setBeginLocalDateTime(LocalDateTime time) {
        super.setBeginLocalDateTime(time);
    }

    @Override
    public void setEndLocalDateTime(LocalDateTime time) {
        super.setEndLocalDateTime(time);
    }

    public String getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public List<String> getNaceIdIn() {
        return naceIdIn;
    }

    public void setNaceIdIn(List<String> naceIdIn) {
        this.naceIdIn = naceIdIn;
    }

    public boolean isJustUsersNace() {
        return justUsersNace;
    }

    public void setJustUsersNace(boolean justUsersNace) {
        this.justUsersNace = justUsersNace;
    }

    @Override
    public String toString() {
        return "TenderFilterOptions{" +
                "skip=" + skip +
                ", limit=" + limit +
                ", searchField='" + searchField + '\'' +
                ", sortDirection='" + sortDirection + '\'' +
                ", sortField='" + sortField + '\'' +
                ", memberId='" + memberId + '\'' +
                ", naceIdIn=" + naceIdIn +
                ", justUsersNace=" + justUsersNace +
                '}';
    }
}
