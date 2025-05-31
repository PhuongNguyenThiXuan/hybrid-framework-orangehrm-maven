package phuongA;

public class TPBank {
    //Pham vi: ngoai class nhung trong 1 package

    public void showTVName(){
        FPTCorporation fpt = new FPTCorporation(); //Phai khoi tao lai
        fpt.tvName = "TPBank LG";
        fpt.setTvName();

        fpt.tvColor = "";
        fpt.setTvColor();

        fpt.tvChannel = "";
        fpt.setTvChannel();

        fpt.tvVolume = ""; //private => khong cho phep truy cap
        fpt.setTvVolume(); //private => khong cho phep truy cap
    }
}
