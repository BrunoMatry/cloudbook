package model.friendmanager;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import model.node.AppVector;
import model.node.CloudBookNode;
import model.node.friend.Friend;
import model.node.friend.Member;

public class FriendManager implements IFriendManager {
    
    protected static final double seuil = 1; //Distance en dessous de laquelle une noeud est considéré comme pertinent (valeure choisie arbitrairement, à modifier)
    protected static final int delay = 10; //Délai en jours après lequel on enlève un noeud de la liste d'amis si on a pas eu d'échange avec lui pendant cette période (valeure choisie arbitrairement, à modifier)
    
    protected CloudBookNode node;
    
    public FriendManager(CloudBookNode node) {
        this.node = node;
    }

    @Override
    public boolean add(Member sender) {
        AppVector vector = sender.getVector();
        int id = sender.getId();
        if(!isFriend(id) && relevant(vector)) {
            node.addFriend(new Friend(id, 0, relevance(vector), vector)); //indice de confiance initialiser à 0 lors de l'ajout d'un nouvel ami
            return true;
        }
        return false;
    }
    
    @Override
    public void clear() {
        List<Friend> friends = node.getFriends();
        for(Friend friend : friends) {
            if(friend.daysSinceLastConnection() > delay)
                remove(friend.idProperty().get());
            //TODO : A compléter
        }   
    }

    @Override
    public boolean isFriend(int id) {
        List<Friend> friends = node.getFriends();
        for(Friend friend : friends) 
            if(friend.getId() == id)
                return true;
        return false;
    }

    @Override
    public double relevance(AppVector v) {
        AppVector vector = node.getVector();
        double xa = vector.appTypeProperty().get(); 
        double ya = vector.performanceProperty().get();
        double za = vector.speedProperty().get(); 
        double xb = v.appTypeProperty().get(); 
        double yb = v.performanceProperty().get(); 
        double zb = v.speedProperty().get(); 
        double dist = sqrt(pow(xb-xa, 2)+pow(yb-ya, 2)+pow(zb-za, 2));
        return dist;
    }
    
    @Override
    public boolean relevant(AppVector v) {
        return relevance(v) < seuil;
    }

    @Override
    public void remove(int id) {
        List<Friend> friends = node.getFriends();
        for(int i = 0; i < friends.size(); i++) {
            if(friends.get(i).getId() == id) {
                remove(i);
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
        On parcours donc la liste des amis et si on l'y trouve on vérifie si il est toujours pertinent
        si oui, on met à jour son vecteur sinon on le supprime de la liste d'amis
        */
        List<Friend> friends = node.getFriends();
        for(Friend friend : friends) {
            int id = sender.getId();
            if(friend.getId() == id) {
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
        List<Friend> allFriends = node.getFriends();
        if(allFriends.size() <= nb)
            return allFriends;
        List<Friend> friends = new ArrayList<>();
        sortByRelevance(allFriends, 0, allFriends.size()-1);
        for(int i = 0; i<nb; i++) {
            friends.add(allFriends.get(i));
        }
        return friends;
    }
    
    @Override
    public List<Friend> getTrustedFriends(int nb) {
        List<Friend> allFriends = node.getFriends();
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
        List<Friend> allFriends = node.getFriends();
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
}
