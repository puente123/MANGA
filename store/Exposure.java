package store;

public enum Exposure{ SHADE("Full Shade"), PARTSUN("Half Sun/Shade"), SUN("Full Sun");

private final String description;

private Exposure(String description){
    this.description = description;
}

@Override
public String toString(){

    return description;

}

}