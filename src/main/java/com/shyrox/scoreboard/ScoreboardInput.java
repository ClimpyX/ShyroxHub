package com.shyrox.scoreboard;

public class ScoreboardInput {
    private String left;
    private String right;
    
    public ScoreboardInput(final String left, final String right) {
        this.left = left;
        this.right = right;
    }
    
    public String getLeft() {
        return this.left;
    }
    
    public String getRight() {
        return this.right;
    }
}
