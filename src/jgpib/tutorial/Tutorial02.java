package jgpib.tutorial;

import jgpib.instruments.SantecTSL710;
import jgpib.instruments.intf.TunableLaser.PowerUnit;

public class Tutorial02 {
	public static void main(String[] args) {
		SantecTSL710 laser = new SantecTSL710(0, 1, 1550, 0, PowerUnit.dBm) ;
		System.out.println(laser.getPowerdBm());
	}
}
