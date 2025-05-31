package phuongA;

public class FPTTelecom extends FPTCorporation{
    //Pham vi: ngoai class nhung trong 1 package thong qua ke thua
    //Khong can khoi tao lai
    public void showTVName(){
        tvName = "TPBank LG";
        setTvName();

        tvColor = "Black";
        setTvColor();

        tvChannel = "";
        setTvChannel();

        tvVolume = ""; //private => khong cho phep truy cap
        setTvVolume(); //private => khong cho phep truy cap
    }
}
