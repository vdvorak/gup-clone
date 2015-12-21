package ua.com.itproekt.gup.model.news;

import org.springframework.data.domain.Sort;

public final class BlogPostFilterOptions extends BlogPost {
    private int skip;
    private int limit;
    private Long afterDate;
    private String searchField;
    private Sort.Direction createdDateSortDirection;
    private Sort.Direction viewsSortDirection;
    private Sort.Direction totalLikesSortDirection;
    private Sort.Direction totalCommentsSortDirection;

    public BlogPostFilterOptions() {
        this.skip = 0;
        this.limit = 20;
    }

    public Sort.Direction getViewsSortDirection() {
        return viewsSortDirection;
    }

    public void setViewsSortDirection(Sort.Direction viewsSortDirection) {
        this.viewsSortDirection = viewsSortDirection;
    }

    public Sort.Direction getTotalLikesSortDirection() {
        return totalLikesSortDirection;
    }

    public void setTotalLikesSortDirection(Sort.Direction totalLikesSortDirection) {
        this.totalLikesSortDirection = totalLikesSortDirection;
    }

    public Sort.Direction getTotalCommentsSortDirection() {
        return totalCommentsSortDirection;
    }

    public void setTotalCommentsSortDirection(Sort.Direction totalCommentsSortDirection) {
        this.totalCommentsSortDirection = totalCommentsSortDirection;
    }

    public Long getAfterDate() {
        return afterDate;
    }

    public void setAfterDate(Long afterDate) {
        this.afterDate = afterDate;
    }

    public Sort.Direction getCreatedDateSortDirection() {
        return createdDateSortDirection;
    }

    public void setCreatedDateSortDirection(Sort.Direction createdDateSortDirection) {
        this.createdDateSortDirection = createdDateSortDirection;
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
}
