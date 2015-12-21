package ua.com.itproekt.gup.model.tender.doer;

import java.util.List;

/**
 * Created by Комп2 on 10.11.2015.
 */
public class DoerFilterOptions extends Doer {
    private int skip;
    private int limit;
    private String searchField;
    private String sortDirection;
    private String sortField;
    private String clientId;
    private List<String> naceIdIn;

    public DoerFilterOptions() {
        this.limit = 10;
        this.skip = 0;
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

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public List<String> getNaceIdIn() {
        return naceIdIn;
    }

    public void setNaceIdIn(List<String> naceIdIn) {
        this.naceIdIn = naceIdIn;
    }

    @Override
    public String toString() {
        return "DoerFilterOptions{" +
                "skip=" + skip +
                ", limit=" + limit +
                ", searchField='" + searchField + '\'' +
                ", sortDirection='" + sortDirection + '\'' +
                ", sortField='" + sortField + '\'' +
                ", clientId='" + clientId + '\'' +
                ", naceIdIn=" + naceIdIn +
                '}';
    }
}
