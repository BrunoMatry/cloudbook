package model.friendmanager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.node.AppVector;
import model.node.Cloud;
import model.node.InformationBox;
import model.node.friend.Friend;
import model.node.friend.Member;

public interface IFriendManager {
    /**
     * Adds a sender to the friends list if it's relevant
     * @param sender    sender of a request
     * @return          true if it's added, false otherwise
     */
    boolean add(Member sender);
    
    /**
     * Clears the fiends list (delete the friends that didn't connect for too long)
     */
    void clear();
    
    /**
     * Checks if a member is in the friends list
     * @param id    Id of the member
     * @return      True if friend, false otherwise
     */
    boolean isFriend(String id);
    
    /**
     * Computes the relevance
     * @param v     vector
     * @return      relevance computed from the given vector
     */
    double relevance(AppVector v);
    
    /**
     * Checks the relevance
     * @param v     vector
     * @return      True if relevant, false otherwise
     */
    boolean relevant(AppVector v);
    
    /**
     * Removes a friend from the friends list
     * @param id    Id of the friend to remove
     */
    void remove(String id);
    
    /**
     * Updates the friends list with the given sender 
     * (if in the list and still relevant: updates it's information, if not in the list and relevant: adds it, if in the list but not relevant anymore: removes it)
     * @param sender    sender of a request
     */
    void update(Member sender);
    
    /**
     * Gets the nb most relevant friends form the friends list
     * @param nb    number of friends
     * @return      List of nb friends
     */
    List<Friend> getRelevantFriends(int nb);
    
    /**
     * Gets the nb most trusted friends form the friends list
     * @param nb    number of friends
     * @return      List of nb friends
     */
    List<Friend> getTrustedFriends(int nb);
    
    /**
     * Gets the nb friends form the friends list
     * @param nb    number of friends
     * @return      List of nb friends
     */
    List<Friend> getSomeFriends(int nb);
    
    /**
     * Finds out the best rated cloud for this application
     * @return the best rated cloud for this application
     */
    public Cloud bestCloud();
    
    /**
     * Computes the relavance sum for each existing cloud
     * @return an association table containing all the scores
     */
    public Map<Cloud, Double> scores();
    
    /**
     * Computes the relevance sum over the friend list of friends who are on the specified cloud
     * @param cloud cloud we look for to increment relevance
     * @return the relevance sum over the friend list for the specified cloud
     */
    public double relevanceSum(Cloud cloud);
}
