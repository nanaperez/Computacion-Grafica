
package freakyland;

/**
 *
 * @author Sebastian-PC
 */
public abstract class  Figura {
    
    protected int posX;     // Position in X.
    protected int posY;     // Position in Y.
    protected int hitBoxX;
    protected int hitBoxY;
    protected int sizeX;      // Size in pixels in X direction.
    protected int sizeY;      // Size in pixels in Y direction.
    protected String imgIcon; // Source for the image.

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getSizeX() {
        return sizeX;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    public String getImgIcon() {
        return imgIcon;
    }

    public void setImgIcon(String imgIcon) {
        this.imgIcon = imgIcon;
    }
    
}
