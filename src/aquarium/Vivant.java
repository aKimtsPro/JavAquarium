package aquarium;

public abstract class Vivant {

    private int pv = 10;
    private int age = 0;

    public Vivant() {
    }

    public Vivant(int age) {
        this.age = age;
    }

    public int getPv() {
        return pv;
    }
    public int getAge() {
        return age;
    }

    public abstract void estMange();
    public void vieillir(){
        age++;
    }
    protected void setPv(int pv){
        if( pv >= 0 )
            this.pv = pv;
    }
    public boolean estMort(){
        return getPv() <= 0 || getAge() > 20;
    }

}
