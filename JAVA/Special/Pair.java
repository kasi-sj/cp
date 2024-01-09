

class Pair<T extends Comparable<T>> implements Comparable<Pair<T>> {
    T first;
    T second;

    Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (first == null ? 0 : first.hashCode());
        hash = 31 * hash + (second == null ? 0 : second.hashCode());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Pair<?> pair = (Pair<?>) obj;

        if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
        return second != null ? second.equals(pair.second) : pair.second == null;
    }

    @Override
    public int compareTo(Pair<T> pair) {
        int firstComparison = first.compareTo(pair.first);
        if (firstComparison != 0) {
            return firstComparison;
        }
        return second.compareTo(pair.second);
    }
}