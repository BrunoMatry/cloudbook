package model.node;

import java.util.Objects;

public class Friend extends Member {

    public Friend(int friendId, int cnfdnce, double rlvnce, AppVector vector) {
        super(friendId, cnfdnce, rlvnce, vector);
    }
    
    /**
     * equals
     * @param obj object to be compared with
     * @return true if the two objects have equals fields
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Friend other = (Friend) obj;
        return this.relevance.get() == other.relevance.get()
                && this.confidence.get() == other.confidence.get();
    }

    /**
     * hashCode
     * @return hash code
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.relevance);
        hash = 17 * hash + Objects.hashCode(this.confidence);
        hash = 17 * hash + Objects.hashCode(this.lastConnexion);
        return hash;
    }
}