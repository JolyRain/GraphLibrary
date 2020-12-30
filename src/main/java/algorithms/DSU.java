package algorithms;

import java.util.Random;

public class DSU {
    private int[] sets;

    public DSU(int size) {
        sets = new int[size];
        init(size);
    }

    private void init(int size) {
        for (int i = 0; i < size; i++) {
            sets[i] = i;
        }
    }

    public void makeSet(int x) {
        sets[x] = x;
    }

    public int find(int x) {
        if (sets[x] == x) return x;
        return sets[x] = find(sets[x]);
    }

    public boolean unite(int x, int y) {
        Random random = new Random();
        x = find(x);
        y = find(y);
        if (x == y) return false;
        if (random.nextBoolean()) {
            x = swap(y, y = x);
        }
        sets[x] = y;
        return true;
    }

    private int swap(int first, int second) {
        return first;
    }

}
