package ua.com.itproekt.gup.model.projectsAndInvestments.project;

import org.springframework.data.domain.Sort;

public final class ProjectFilterOptions extends Project {
    private int skip;
    private int limit;
    private boolean includeComments;
    private Sort.Direction createdDateSortDirection;
    private Sort.Direction viewsSortDirection;

    public ProjectFilterOptions() {
        this.skip = 0;
        this.limit = 20;
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

    public Sort.Direction getCreatedDateSortDirection() {
        return createdDateSortDirection;
    }

    public void setCreatedDateSortDirection(Sort.Direction createdDateSortDirection) {
        this.createdDateSortDirection = createdDateSortDirection;
    }

    public Sort.Direction getViewsSortDirection() {
        return viewsSortDirection;
    }

    public void setViewsSortDirection(Sort.Direction viewsSortDirection) {
        this.viewsSortDirection = viewsSortDirection;
    }

    public boolean isIncludeComments() {
        return includeComments;
    }

    public void setIncludeComments(boolean includeComments) {
        this.includeComments = includeComments;
    }
}
