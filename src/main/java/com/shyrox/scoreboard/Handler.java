package com.shyrox.scoreboard;

import com.shyrox.ShyroxHub;

public class Handler {

    private ShyroxHub plugin;
    
    public Handler(final ShyroxHub plugin) {
        this.plugin = plugin;
    }
    
    public void enable() {
    }
    
    public void disable() {
    }
    
    public ShyroxHub getInstance() {
        return this.plugin;
    }
}
