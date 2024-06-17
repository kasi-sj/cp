class P {
    long x;
    long y;
    P(long x , long y){
        this.x = x;
        this.y = y;
    }
    
    P minus(P p){
        return new P(x-p.x,y-p.y);
    }
    
    static long cross(P a , P b){
        return a.x*b.y-a.y*b.x;
    }
    
    public String toString(){
        return "P{x="+x+",y="+y+"}";
    }
}