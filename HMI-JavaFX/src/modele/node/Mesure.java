package modele.node;

import java.util.Date;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.friendmanager.Information;

/**
 *
 * @author Bruno
 */
public class Mesure implements Information {
    //contenu
    private Date date;
    private transient final StringProperty stringDate = new SimpleStringProperty();
    private int _mesure1;
    private transient IntegerProperty mesure1 = new SimpleIntegerProperty();
    public IntegerProperty mesure1Property() {
        return mesure1;
    }
    private transient IntegerProperty mesure2 = new SimpleIntegerProperty();
    private transient IntegerProperty mesure3 = new SimpleIntegerProperty();
    private transient IntegerProperty mesure4 = new SimpleIntegerProperty();
    private transient IntegerProperty mesure5 = new SimpleIntegerProperty();
    
    protected String _description;
    protected transient StringProperty description;
    public StringProperty descriptionProperty() {
        return description;
    }
    
    public Mesure() {
        description = new SimpleStringProperty();
        description.set("Pas de mesure");
    }
    
    public Mesure(int mesure1, int mesure2, int mesure3, int mesure4, int mesure5) {
        description = new SimpleStringProperty("" + mesure1);
        this.actualizeDate();
        this.mesure1.set(mesure1);
        this.mesure2.set(mesure2);
        this.mesure3.set(mesure3);
        this.mesure4.set(mesure4);
        this.mesure5.set(mesure5);
    }
    
    private void actualizeDate() {
        this.date = new Date();
        this.stringDate.set(this.date.toString());
    }

    @Override
    public void saveProperties() {
        _description = description.get();
        _mesure1 = mesure1.get();
    }

    @Override
    public void restoreProperties() {
        description = new SimpleStringProperty(_description);
        mesure1 = new SimpleIntegerProperty(_mesure1);
    }
}
