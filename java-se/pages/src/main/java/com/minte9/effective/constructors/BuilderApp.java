/**
 * Consider a builder when faced with many constructor parameters
 */

package com.minte9.effective.constructors;

public class BuilderApp {
    public static void main(String[] args) {

        N n = new N.Builder(240)
            .calories(100)
            .fat(11)
            .build(); 

        System.out.println(n.getFat()); 
            // 11
    }
}

class N {

    private final int size;
    private final int calories;
    private final int fat;

    public static class Builder {

        private int size;
        private int calories = 0;
        private int fat = 0;
        
        public Builder(int s) {
            size = s; 
        }

        public Builder calories(int c) { 
            calories = c; 
            return this; 
        }

        public Builder fat(int f) { 
            fat = f; 
            return this; 
        }

        public N build() { 
            return new N(this); 
        }
    }
    
    private N(Builder builder) {
        size = builder.size;
        calories = builder.calories;
        fat = builder.fat;
    }

    public int getSize() { 
        return size; 
    }

    public int getCalories() { 
        return calories; 
    }

    public int getFat() { 
        return fat; 
    }
}