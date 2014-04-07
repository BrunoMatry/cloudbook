package model.friendmanager;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import java.util.List;
import model.node.AppVector;
import model.node.CloudBookNode;
import model.node.Friend;
import model.request.Sender;


public class FriendManager implements IFriendManager {
    protected CloudBookNode node;
    
    public FriendManager(CloudBookNode node) {
        this.node = node;
    }

    @Override
    public boolean add(Sender sender) {
        AppVector vector = sender.getVector();
        int id = sender.getId();
        if(!isFriend(id) && relevant(vector)) {
            node.addFriend(new Friend(id, 0, true, vector)); //indice de confiance initialiser à 0 lors de l'ajout d'un nouvel ami
            return true;
        }
        return false;
    }
    
    @Override
    public void clear() {
        List<Friend> friends = node.getFriends();
        for(Friend friend : friends) {
            if(friend.daysSinceLastConnection() > 10) //délai de 10 jours choisi arbitrairement, à modifier 
                remove(friend.idProperty().get());
            //TODO : A compléter
        }   
    }

    @Override
    public boolean isFriend(int id) {
        List<Friend> friends = node.getFriends();
        for(Friend friend : friends) 
            if(friend.idProperty().get() == id)
                return true;
        return false;
    }

    @Override
    public boolean relevant(AppVector v) {
        AppVector vector = node.getVector();
        double xa = vector.appTypeProperty().get(); 
        double ya = vector.performanceProperty().get();
        double za = vector.speedProperty().get(); 
        double xb = v.appTypeProperty().get(); 
        double yb = v.performanceProperty().get(); 
        double zb = v.speedProperty().get(); 
        double dist = sqrt(pow(xb-xa, 2)+pow(yb-ya, 2)+pow(zb-za, 2));
        return dist < 1; //Pertinent si la distance est inférieure à 1 (valeure choisie arbitrairement, à modifier)
    }

    @Override
    public void remove(int id) {
        List<Friend> friends = node.getFriends();
        for(int i = 0; i < friends.size(); i++) {
            if(friends.get(i).idProperty().get() == id) {
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
            if(friend.idProperty().get() == id) {
                AppVector vector = sender.getVector();
                if(relevant(vector)) 
                    friend.setVector(vector);
                else
                    remove(id);
                break;
            }  
        }
    }
    
}
