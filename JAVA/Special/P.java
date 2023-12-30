package Special;

class P{
    long x , y;
    P(long x , long y){
        this.x = x;
        this.y = y;
    }

    static P add(P a , P b){
        return new P(a.x+b.x,a.y+b.y);
    }

    static P sub(P a , P b){
        return new P(a.x-b.x,a.y-b.y);
    }

    static long cross(P a , P b){
        return a.x*b.y-a.y*b.x;
    }
}