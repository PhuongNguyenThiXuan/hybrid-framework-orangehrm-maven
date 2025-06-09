package phuongB;

import phuongA.FPTCorporation;

public class FPTSoftware extends FPTCorporation {
    //Pham vi: Khac class, khac package => thong qua he thong ke thua khong can khoi tao lai

    public void showTVName(){
        tvName = "FPT LG";
        setTvName();

        tvColor = "Black";
        setTvColor();

//        tvChannel = ""; //default => khac package khong dung duoc
//        setTvChannel(); //default => khac package khong dung duoc
//
//        tvVolume = ""; //private => khong cho phep truy cap
//        setTvVolume(); //private => khong cho phep truy cap
    }
}
