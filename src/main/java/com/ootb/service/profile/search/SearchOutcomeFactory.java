package com.ootb.service.profile.search;

import com.ootb.service.friends.type.Friend;
import com.ootb.service.profile.search.type.SearchOutcome;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ootb.service.profile.search.type.SearchOutcome.SearchOutcomeBuilder.aSearchOutcome;

/**
 * Created by Adam on 2017-04-06.
 */
@Component
public class SearchOutcomeFactory {

    public List<SearchOutcome> getSearchOutcomes(List<Friend> friends, String searchInput) {
        List<SearchOutcome> searchOutcomes = getSearchOutcomes(friends);
        Map<SearchOutcome,Integer> searchOutcomesLevenstein = new HashMap<>();

        List<SearchOutcome> result = new ArrayList<>();

        for(SearchOutcome searchOutcome : searchOutcomes) {
            searchOutcomesLevenstein.put(searchOutcome, StringUtils.getLevenshteinDistance(searchInput, searchOutcome.getName()));
        }

        searchOutcomesLevenstein.entrySet().stream().sorted(
                (e1, e2) -> (
                        e1.getValue().compareTo(e2.getValue()) != 0 ?
                                e1.getValue().compareTo(e2.getValue()) :
                                e1.getKey().compareTo(e2.getKey())
                        )
        )
                .forEachOrdered(x -> result.add(x.getKey()));

        for(SearchOutcome searchOutcome : result) {
            searchOutcome.setAddFriendUrl();
            searchOutcome.setFriendProfileUrl();
        }

        return result;
    }

    public List<SearchOutcome> getSearchOutcomes(List<Friend> friends) {
        List<SearchOutcome> result = new ArrayList<>();
        friends.forEach(friend -> result.add(getSearchOutcome(friend)));

        return result;
    }

    public SearchOutcome getSearchOutcome(Friend friend) {
        return aSearchOutcome().withName(friend.getNick()).build();
    }
}
