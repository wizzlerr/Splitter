package com.ootb.service.profile.search;

import com.ootb.db.util.Page;
import com.ootb.service.friends.FriendsService;
import com.ootb.service.friends.type.Friend;
import com.ootb.service.profile.search.type.SearchOutcome;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Adam on 2017-04-06.
 */
@Service
public class GroupAndProfileSearchService {

    @Autowired
    private FriendsService friendsService;

    @Autowired
    private SearchOutcomeFactory searchOutcomeFactory;

    public Pair<Integer, List<SearchOutcome>> getNonFriendPaged(String searchInput, int startIndex) {
        List<Friend> friends = friendsService.getNonFriends();
        List<SearchOutcome> searchOutcomes = searchOutcomeFactory.getSearchOutcomes(friends, searchInput);
        Page page = new Page(searchOutcomes, startIndex);

        return new Pair<>(page.getTotalPages(), (List<SearchOutcome>) page.getObjects());
    }
}
