
package freakland2d;

/**
 *
 * @author Sebastian-PC
 */
public abstract class  Figure {
    protected int coordXcentral;
    protected int coordYCentral;
    protected String imgAddress;
    
    public Figure(){
        
    }

    public int getCoordXcentral() {
        return coordXcentral;
    }

    public void setCoordXcentral(int coordXcentral) {
        this.coordXcentral = coordXcentral;
    }

    public int getCoordYCentral() {
        return coordYCentral;
    }

    public void setCoordYCentral(int coordYCentral) {
        this.coordYCentral = coordYCentral;
    }

    public String getImgAddress() {
        return imgAddress;
    }
}
