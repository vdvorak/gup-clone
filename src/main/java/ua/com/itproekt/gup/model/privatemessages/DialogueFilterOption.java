package ua.com.itproekt.gup.model.privatemessages;

import java.util.ArrayList;

/*
 * Created by Fairy on 30.11.2015.
 */
public class DialogueFilterOption extends Dialogue {
    private int skip;
    private int limit;
    private String searchField;

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

    public boolean addMember(Member member) {
        if(this.getMembers() == null){
            this.setMembers(new ArrayList<>());
        }
        return this.getMembers().add(member);
    }
}
