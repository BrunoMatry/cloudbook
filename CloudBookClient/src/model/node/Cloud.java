package model.node;

import java.io.Serializable;

/**
 * Enum Cloud
 * Note : cet enum sera bien evidement amene a etre complete 
 * voir a etre remplace par une autre forme de structure de donnees.
 */
public enum Cloud implements Serializable {
    GOOGLE,
    AMAZON,
    WINDOWS,
    DEFAULT;

    @Override
    public String toString() {
        return name();
    }

}