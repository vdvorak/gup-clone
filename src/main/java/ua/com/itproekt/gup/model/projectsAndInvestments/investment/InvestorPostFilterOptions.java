package ua.com.itproekt.gup.model.projectsAndInvestments.investment;


public final class InvestorPostFilterOptions extends InvestorPost {
    private int skip;
    private int limit;

    public InvestorPostFilterOptions() {
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
}
