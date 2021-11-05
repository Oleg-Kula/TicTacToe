package net.ukr.kekos222;

class Player {
    private Figure figure;
    private int number;
    private boolean isCPU;

    Player(int number, Figure figure){
        this.number = number;
        this.figure = figure;
        this.isCPU = false;
    }

     Figure getFigure() {
        return figure;
    }

     int getNumber() {
        return number;
    }

     boolean isCPU() {
        return isCPU;
    }

     void setCPU(boolean CPU) {
        isCPU = CPU;
    }
}
