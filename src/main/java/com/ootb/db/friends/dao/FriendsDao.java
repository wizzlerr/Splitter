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
        List<Friend> friends = findAsFirstUsersFriend(user);
        friends.addAll(findAsSecondUsersFriend(user));
        return friends;
    }

    private List<Friend> findAsFirstUsersFriend(User user) {
        Criteria criteria = sessionFactory.openSession().createCriteria(Friend.class);
        criteria.add(Expression.eq("confirmed", true));

        criteria.add(Expression.eq("firstUser", user));
        return criteria.list();
    }

    private List<Friend> findAsSecondUsersFriend(User user) {
        Criteria criteria = sessionFactory.openSession().createCriteria(Friend.class);
        criteria.add(Expression.eq("confirmed", true));
        criteria.add(Expression.eq("secondUser", user));
        return criteria.list();
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

    public List<Friend> findPendingFriends(User user) {
        FriendsFilter filter = aFriendsFilter().withConfirmed(false).withFriend(user).build();
        return findAllByFilter(filter);
    }

    public Friend findById(long id) {
        return findAllByFilter(aFriendsFilter().withFriendshipId(id).build()).get(0);
    }

    public void confirmFriend(long id) {
        Friend friend = findById(id);
        friend.setConfirmed(true);
        update(friend);
    }
}
