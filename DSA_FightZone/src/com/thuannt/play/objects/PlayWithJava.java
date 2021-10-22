package com.thuannt.play.objects;

public final class PlayWithJava {
    
    public final int cannotChangeIt; // immutable variable can be change through re-create the object and change it through the constructor
    
    public PlayWithJava(int changeIt) {
        this.cannotChangeIt = changeIt;
    }

}
