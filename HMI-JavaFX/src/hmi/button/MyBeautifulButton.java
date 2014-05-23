package hmi.button;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class MyBeautifulButton extends Parent {
    
    Image hoverImage, onClickImage, defaultImage;
    protected ImageView currentImage;
    
    public MyBeautifulButton(Image def, Image hover, Image onClick) {
        defaultImage = def;
        onClickImage = onClick;
        hoverImage = hover;
        currentImage = new ImageView(defaultImage);
        
        setOnClick();
        setHover();
        setDefault();
    }
    
    private void setOnClick() {
        this.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent me){
                currentImage.setImage(onClickImage);
            }
        });
    }
    
    private void setHover() {
       this.setOnMouseEntered(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent me){
                currentImage.setImage(hoverImage);
            }
        });
    }
    
    private void setDefault() {
        this.setOnMouseExited(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent me){
                currentImage.setImage(defaultImage);
            }
        });
    }
}
