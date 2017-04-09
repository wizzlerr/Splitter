package com.ootb.db.friends.dao;

import com.ootb.db.friends.type.Friend;
import com.ootb.db.user.type.User;
import com.ootb.db.util.AbstractDao;
import com.ootb.db.util.Page;
import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ootb.db.friends.dao.FriendsFilter.FriendsFilterBuilder.aFriendsFilter;


/**
 * Created by Adam on 2017-04-02.
 */
@Repository
public class FriendsDao extends AbstractDao {

    public Page findByUserPaged(User user, int startIndex) {
        List<Friend> friends = findAllByFilter(aFriendsFilter().withUser(user).withConfirmed(true).build());

        return new Page(friends, startIndex);
    }

    public List<Friend> findByUser(User user) {
        return findAllByFilter(aFriendsFilter().withUser(user).withConfirmed(true).build());
    }

    public Friend findByUserFriend(User user, User friend) {
        return findAllByFilter(aFriendsFilter().withUser(user).withConfirmed(true).withFriend(friend).build()).get(0);
    }

    public List<Friend> findAllByFilter(FriendsFilter friendsFilter) {
        Criteria criteria = sessionFactory.openSession().createCriteria(Friend.class);

        if(friendsFilter.getFriendshipId()!=null){
            criteria.add(Expression.eq("id", friendsFilter.getFriendshipId()));
        }
        if(friendsFilter.getUser()!=null) {
            criteria.add(Expression.eq("firstUser", friendsFilter.getUser()));
        } if(friendsFilter.getFriend()!=null) {
            criteria.add(Expression.eq("secondUser", friendsFilter.getFriend()));
        }
        if(friendsFilter.isConfirmed()) {
            criteria.add(Expression.eq("confirmed", friendsFilter.isConfirmed()));
        } else {
            criteria.add(Expression.eq("confirmed", friendsFilter.isConfirmed()));
        }

        return criteria.list();
    }

    public void saveFriendship(Friend friend) {
        persist(friend);
    }

    public boolean remove(User loggedUser, User user) {
        Friend friend = findByUserFriend(loggedUser,user);
        if(friend != null) {
            delete(friend);
            return true;
        }
        return false;
    }
}
