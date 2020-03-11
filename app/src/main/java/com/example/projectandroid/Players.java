package com.example.projectandroid;

public class Players {

    private String myName;
    private String myScore;

    public Players(){

    }

    public Players(String name, String nb){
        this.myName = name;
        this.myScore = nb;
    }

    public String getMyName() {
        return myName;
    }

    public void setMyName(String myName) {
        this.myName = myName;
    }

    public String getMyScore() {
        return myScore;
    }

    public void setMyScore(String myScore) {
        this.myScore = myScore;
    }

    @Override
    public String toString() {
        return "Score :"+ this.myScore+" Name :" + this.myName;
    }
}
