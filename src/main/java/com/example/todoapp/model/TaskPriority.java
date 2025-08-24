package com.example.todoapp.model;

public enum TaskPriority {
    LOW("Basse"),
    MEDIUM("Moyenne"),
    HIGH("Haute"),
    URGENT("Urgente");
    
    private final String displayName;
    
    TaskPriority(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}
