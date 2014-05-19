package model.friendmanager;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import java.util.List;
import model.node.AppVector;
import model.node.CloudBookNode;
import model.node.Friend;
import model.request.Sender;

public class FriendManager implements IFriendManager {
    
    protected static final double seuil = 1; //Distance en dessous de laquelle une noeud est considéré comme pertinent (valeure choisie arbitrairement, à modifier)
    protected static final int delay = 10; //Délai en jours après lequel on enlève un noeud de la liste d'amis si on a pas eu d'échange avec lui pendant cette période (valeure choisie arbitrairement, à modifier)
    
    protected CloudBookNode node;
    
    public FriendManager(CloudBookNode node) {
        this.node = node;
    }

    @Override
    public boolean add(Sender sender) {
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
                remove(friend.getId());
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
    public void update(Sender sender) {
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
                    friend.setRelevance(relevance(vector));
                } else
                    remove(id);
                break;
            }  
        }
    }   
}