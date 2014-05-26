package model.friendmanager;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import model.node.AppVector;
import model.node.Cloud;
import model.node.MyNode;
import model.node.InformationBox;
import model.node.friend.Friend;
import model.node.friend.Member;

public class FriendManager implements IFriendManager {
    
    protected static final double seuil = 30; //Distance en dessous de laquelle une noeud est considéré comme pertinent (valeure choisie arbitrairement, à modifier)
    protected static final int delay = 10; //Délai en jours après lequel on enlève un noeud de la liste d'amis si on a pas eu d'échange avec lui pendant cette période (valeure choisie arbitrairement, à modifier)
    
    protected MyNode node;
    
    /**
     * Constructor
     */
    public FriendManager() {
        node = new MyNode();
    }
    
    /**
     * Constructor
     * @param node current application
     */
    public FriendManager(MyNode node) {
        this.node = node;
    }

    @Override
    public boolean add(Member sender) {
        AppVector vector = sender.getVector();
        String id = sender.getId();
        Cloud cloud = sender.getCloud();
        if(!isFriend(id) && relevant(vector)) {
            Friend friend = new Friend(id, 0, relevance(vector), vector, cloud);
            node.addFriend(friend); //indice de confiance initialiser à 0 lors de l'ajout d'un nouvel ami
            return true;
        }
        return false;
    }
    
    @Override
    public void clear() {
        InformationBox<Friend> friends = node.getFriends();
        for(Friend friend : friends) {
            if(friend.daysSinceLastConnection() > delay)
                remove(friend.idProperty().get());
            //TODO : A compléter
        }   
    }

    @Override
    public boolean isFriend(String id) {
        InformationBox<Friend> friends = node.getFriends();
        for(Friend friend : friends) 
            if(friend.getId().equals(id))
                return true;
        return false;
    }

    @Override
    public double relevance(AppVector v) {
        AppVector vector = node.getVector();
        double xa = vector.getAppType(); 
        double ya = vector.getPerformanceNeed();
        double za = vector.getSpeedNeed(); 
        double xb = v.getAppType(); 
        double yb = v.getPerformanceNeed(); 
        double zb = v.getSpeedNeed(); 
        double dist = sqrt(pow(xb-xa, 2)+pow(yb-ya, 2)+pow(zb-za, 2));
        return dist;
    }
    
    @Override
    public boolean relevant(AppVector v) {
        //System.out.println("(Vector, seuil) = " + relevance(v) + " " + seuil);
        return relevance(v) < seuil;
    }

    @Override
    public void remove(String id) {
        InformationBox<Friend> friends = node.getFriends();
        for(int i = 0; i < friends.size(); i++) {
            if(friends.get(i).getId().equals(id)) {
                friends.remove(friends.get(i));
                break;
            }  
        }
    }

    @Override
    public void update(Member sender) {
        //On tente d'ajouter le sender à la liste d'amis
        if(add(sender))
            return;
        /*
        Si l'ajout échoue, soit le sender n'est pas pertinent, soit il est déja dans la liste des amis
        On parcours donc la liste des amis et si on l'y trouve on vérifie s'il est toujours pertinent
        si oui, on met à jour son vecteur sinon on le supprime de la liste d'amis
        */
        InformationBox<Friend> friends = node.getFriends();
        for(Friend friend : friends) {
            String id = sender.getId();
            if(friend.getId().equals(id)) {
                AppVector vector = sender.getVector();
                if(relevant(vector)) {
                    friend.setVector(vector);
                    friend.relevanceProperty().set(relevance(vector));
                } else
                    remove(id);
                break;
            }  
        }
    }
    
    @Override
    public List<Friend> getRelevantFriends(int nb) {
        InformationBox<Friend> allFriends = node.getFriends();
        if(allFriends.size() <= nb)
            return allFriends.boxObservableList();
        List<Friend> friends = new ArrayList<>();
        sortByRelevance(allFriends.boxObservableList(), 0, allFriends.size()-1);
        for(int i = 0; i<nb; i++) {
            friends.add(allFriends.get(i));
        }
        return friends;
    }
    
    @Override
    public List<Friend> getTrustedFriends(int nb) {
        List<Friend> allFriends = node.getFriends().boxObservableList();
        if(allFriends.size() <= nb)
            return allFriends;
        List<Friend> friends = new ArrayList<>();
        sortByConfidence(allFriends, 0, allFriends.size()-1);
        for(int i = 0; i<nb; i++) {
            friends.add(allFriends.get(i));
        }
        return friends;
    }
    
    @Override
    public List<Friend> getSomeFriends(int nb) {
        List<Friend> allFriends = node.getFriends().boxObservableList();
        if(allFriends.size() <= nb)
            return allFriends;
        List<Friend> friends = new ArrayList<>();
        for (Friend f : allFriends) {
            friends.add(f);
        }
        for(int i = 0; i<friends.size()-nb; i++) {
            Random rand = new Random();
            int j = rand.nextInt(friends.size());
            friends.remove(j);
        }
        return friends;
    }
    
    /**
     * Methode permettant de trier un liste d'amis par ordre de pertinence (les plus pertinent au début)
     * @param friends   liste d'amis à trier
     * @param left      rang à partir duquel le tri doit être fait
     * @param right     rang juqu'auquel le tri doit être fait
     */
    private void sortByRelevance(List<Friend> friends, int left, int right) {
        //algorithme quicksort
        int i = left, j = right;
        Friend tmp;
        Friend pivot = friends.get(left+(right-left)/2);
        while(i <= j) {
            while(friends.get(i).relevanceProperty().get() > pivot.relevanceProperty().get())
                i++;
            while(friends.get(j).relevanceProperty().get() < pivot.relevanceProperty().get())
                j--;
            if (i <= j) {
                tmp = friends.get(i);
                friends.set(i, friends.get(j));
                friends.set(j, tmp);
                i++;
                j--;
            }
        }
        if (left < j)
            sortByRelevance(friends, left, j);
        if (i < right)
            sortByRelevance(friends, i, right);
    }
    
    /**
     * Methode permettant de trier un liste d'amis par ordre de confiance (ceux à qui on fait le plus confiance au début)
     * @param friends   liste d'amis à trier
     * @param left      rang à partir duquel le tri doit être fait
     * @param right     rang juqu'auquel le tri doit être fait
     */
    private void sortByConfidence(List<Friend> friends, int left, int right) {
        //algorithme quicksort
        int i = left, j = right;
        Friend tmp;
        Friend pivot = friends.get(left+(right-left)/2);
        while(i <= j) {
            while(friends.get(i).confidenceProperty().get() > pivot.confidenceProperty().get())
                i++;
            while(friends.get(j).confidenceProperty().get() < pivot.confidenceProperty().get())
                j--;
            if (i <= j) {
                tmp = friends.get(i);
                friends.set(i, friends.get(j));
                friends.set(j, tmp);
                i++;
                j--;
            }
        }
        if (left < j)
            sortByConfidence(friends, left, j);
        if (i < right)
            sortByConfidence(friends, i, right);
    }
    
    /**
     * Finds out the best rated cloud for this application
     * @return the best rated cloud for this application
     */
    public Cloud bestCloud() {
        Map<Cloud, Double> scoreTable = scores();
        Cloud res = null;
        double max = 0.0;
        for(Cloud c : scoreTable.keySet()) {
            double d = scoreTable.get(c);
            if(d >= max) {
                max = d;
                res = c;
            }
        }
        return res;
    }
    
    /**
     * Computes the relavance sum for each existing cloud
     * @return an association table containing all the scores
     */
    public Map<Cloud, Double> scores() {
        Map<Cloud, Double> res = new HashMap<>();
        for(Cloud c : Cloud.values()) {
            double score = relevanceSum(c);
            res.put(c, score);
        }
        return res;
    }
    
    /**
     * Computes the relevance sum over the friend list of friends who are on the specified cloud
     * @param cloud cloud we look for to increment relevance
     * @return the relevance sum over the friend list for the specified cloud
     */
    public double relevanceSum(Cloud cloud) {
        InformationBox<Friend> friends = node.getFriends();
        double res = 0.0;
        for(Friend f : friends) {
            if(f.cloudProperty().get() == cloud)
                res += f.relevanceProperty().get();
        }
        return res;
    }
}
