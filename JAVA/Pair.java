class Pair implements Comparable{
    int first;
    int second;

    //String name;

    Pair(int first , int second,String name){
        this.first=first;
        this.second=second;
        //this.name=name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31*hash + first;
        hash = 31*hash + second;
        //hash = 32*hash + name==null?0:name.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        

        Pair user = (Pair) obj;
        return first == user.first
                //        && (name.equals(user.name)
                && second == user.second;
    }

    @Override
    public int compareTo(Object obj) {
        Pair user = (Pair)obj;
        int one = Integer.compare(first,user.first);
        int two = Integer.compare(first,user.second);
        //int the = name.compareTo(user.name);
        if(one==0)
            return two;
        return one;
    }
}