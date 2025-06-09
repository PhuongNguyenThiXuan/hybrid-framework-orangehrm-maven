package phuongB;

import phuongA.FPTCorporation;

public class VinGroup {
    //Pham vi: Khac class, khac package => phai khoi tao lai

    public void showTVName(){
        FPTCorporation fpt = new FPTCorporation(); //Phai khoi tao lai
        fpt.tvName = "TPBank LG";
        fpt.setTvName();

//        fpt.setTvColor(); //protected => khac package khong dung duoc
//
//        fpt.tvChannel = ""; //default => khac package khong dung duoc
//        fpt.setTvChannel(); //default => khac package khong dung duoc
//
//        fpt.tvVolume = ""; //private => khong cho phep truy cap
//        fpt.setTvVolume(); //private => khong cho phep truy cap
    }
}
