package net.ukr.kekos222;

enum Figure {
    O ('O'), X('X');

    char display;

    Figure(char display){
        this.display = display;
    }

    public char getDisplay(){
        return display;
    }

}
