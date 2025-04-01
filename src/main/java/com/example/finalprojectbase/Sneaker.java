package com.example.finalprojectbase;

/**
 * @author James Homan
 * The type Sneaker.
 */
public class Sneaker {
    private String manufacturer;
    private String type;
    private double size;
    private String primaryColor;
    private String secondaryColor;
    private boolean isClean;

    /**
     * Instantiates a new Sneaker.
     *
     * @param manufacturer   the manufacturer
     * @param type           the type
     * @param size           the size
     * @param primaryColor   the primary color
     * @param secondaryColor the secondary color
     * @param isClean        the is clean
     */
    public Sneaker(String manufacturer, String type, double size, String primaryColor, String secondaryColor, boolean isClean) {
        this.manufacturer = manufacturer;
        this.type = type;
        this.size = size;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.isClean = isClean;
    }

    /**
     * Gets manufacturer.
     *
     * @return the manufacturer
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * Sets manufacturer.
     *
     * @param manufacturer the manufacturer
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets size.
     *
     * @return the size
     */
    public double getSize() {
        return size;
    }

    /**
     * Sets size.
     *
     * @param size the size
     */
    public void setSize(double size) {
        this.size = size;
    }

    /**
     * Gets primary color.
     *
     * @return the primary color
     */
    public String getPrimaryColor() {
        return primaryColor;
    }

    /**
     * Sets primary color.
     *
     * @param primaryColor the primary color
     */
    public void setPrimaryColor(String primaryColor) {
        this.primaryColor = primaryColor;
    }

    /**
     * Gets secondary color.
     *
     * @return the secondary color
     */
    public String getSecondaryColor() {
        return secondaryColor;
    }

    /**
     * Sets secondary color.
     *
     * @param secondaryColor the secondary color
     */
    public void setSecondaryColor(String secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    /**
     * Is clean boolean.
     *
     * @return the boolean
     */
    public boolean isClean() {
        return isClean;
    }

    /**
     * Sets clean.
     *
     * @param clean the clean
     */
    public void setClean(boolean clean) {
        isClean = clean;
    }

    @Override
    public String toString() {
        return "Sneaker{" +
                "manufacturer='" + manufacturer + '\'' +
                ", type='" + type + '\'' +
                ", size=" + size +
                ", primaryColor='" + primaryColor + '\'' +
                ", secondaryColor='" + secondaryColor + '\'' +
                ", isClean=" + isClean +
                '}';
    }
}
