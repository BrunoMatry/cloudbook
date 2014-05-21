package model.friendmanager;

import java.util.List;
import model.node.AppVector;
import model.node.friend.Friend;
import model.node.friend.Member;

public interface IFriendManager {
    /**
     * Methode permettant d'ajouter le sender passer en paramètre à la liste des amis si il est pertinent et qu'il n'est pas déjà dans la liste
     * @param sender    Emetteur d'une requête qu'on veut ajouter à la liste d'amis
     * @return          Vrai si l'ajout est correctement effectué, faux sinon
     */
    boolean add(Member sender);
    
    /**
     * Methode permettant de nettoyer la liste des amis
     */
    void clear();
    
    /**
     * Methode permettant vérifier si le noeud identifier par l'id passé en paramètre est dans la liste d'amis
     * @param id    Id du noeud dont on veut savoir s'il est dans la liste d'amis
     * @return      Vrai si ami, faux sinon
     */
    boolean isFriend(int id);
    
    /**
     * Methode permettant de calculer la pertinence (la distance) du noeud dont le vecteur est passé en paramètre
     * @param v     Vecteur du noeud dont on veut calculer la pertinence
     * @return      La pertinence du noeud
     */
    double relevance(AppVector v);
    
    /**
     * Methode permettant de vérifier si le noeud dont l'AppVector est en paramètre est pertinent
     * @param v     Vecteur du noeud dont on veut savoir si il est pertinent
     * @return      Vrai si pertinent, faux sinon
     */
    boolean relevant(AppVector v);
    
    /**
     * Methode permettant d'enlever le noeud identifier par l'id passé en paramètre de la liste d'amis
     * @param id    Id du noeud à supprimer de la liste d'amis
     */
    void remove(int id);
    
    /**
     * Methode permettant de mettre à jour les informations du sender s'il est dans la liste des amis et de l'y ajouter s'il n'y est pas et qu'il est pertinent
     * @param sender    Emetteur d'une requête qu'on veut mettre à jour
     */
    void update(Member sender);
    
    /**
     * Methode permettant de récupérer les nb amis les plus pertinents
     * @param nb    nombre d'amis à récupérer
     * @return      Liste contenant les nb amis les plus pertinents 
     */
    List<Friend> getRelevantFriends(int nb);
    
    /**
     * Methode permettant de récupérer les nb amis à qui on fait le plus confiance
     * @param nb    nombre d'amis à récupérer
     * @return      Liste contenant les nb amis à qui on fait le plus confiance
     */
    List<Friend> getTrustedFriends(int nb);
    
    /**
     * Methode permettant de récupérer nb amis au hasard dans la liste d'amis
     * @param nb    nombre d'amis à récupérer
     * @return      Liste contenant nb amis pris au hasard 
     */
    List<Friend> getSomeFriends(int nb);
}
