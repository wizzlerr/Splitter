package com.ootb.service.profile.search.type;

/**
 * Created by Adam on 2017-04-06.
 */
public class SearchOutcome implements Comparable{

    private String name;
    private String url;
    private String profileUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setAddFriendUrl() {
        this.url = "/auth/profile/" + name + "/add";
    }

    public void setFriendProfileUrl() {
        this.profileUrl = "/auth/profile/"+name;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    @Override
    public int compareTo(Object o) {
        return this.name.compareTo(((SearchOutcome)o).getName());
    }

    public static final class SearchOutcomeBuilder {
        private String name;
        private String url;

        private SearchOutcomeBuilder() {
        }

        public static SearchOutcomeBuilder aSearchOutcome() {
            return new SearchOutcomeBuilder();
        }

        public SearchOutcomeBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public SearchOutcomeBuilder withUrl(String url) {
            this.url = url;
            return this;
        }

        public SearchOutcome build() {
            SearchOutcome searchOutcome = new SearchOutcome();
            searchOutcome.setName(name);
            searchOutcome.setUrl(url);
            return searchOutcome;
        }
    }


}
