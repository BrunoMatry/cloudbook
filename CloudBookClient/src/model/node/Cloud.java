package model.node;

import java.io.Serializable;

/**
 * Enum Clouds platforms
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