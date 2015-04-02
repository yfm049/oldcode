package com.yfm.adapter;

import java.io.File;

public class TypeFilter {

	// fba3.05Ä£ÄâÆ÷ .zip
	// fpseÄ£ÄâÆ÷ .img .bin .iso .pbp
	// n64oidÄ£ÄâÆ÷ .v64 .n64
	// gbaoid .gba
	// snesoid .sfc .smc
	// genoid .smd
	// gbcoid .gb .gbc
	// nesoid .nes
	// gearoid gg sms

	public static boolean isGameFile(File file) {
		String filename = file.getName().toLowerCase();

		if (filename.endsWith("zip") || filename.endsWith("v64")
				|| filename.endsWith("n64") || filename.endsWith("gba")
				|| filename.endsWith("sfc") || filename.endsWith("smc")
				|| filename.endsWith("smd") || filename.endsWith("gb")
				|| filename.endsWith("gg") || filename.endsWith("sms")
				|| filename.endsWith("gbc") || filename.endsWith("nes")) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isFbaGame(File file) {
		String filename = file.getName().toLowerCase();
		if (filename.endsWith("zip")) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isn64oidGame(File file) {
		String filename = file.getName().toLowerCase();
		if (filename.endsWith("v64")||filename.endsWith("n64")) {
			return true;
		} else {
			return false;
		}
	}
	public static boolean isGbAGame(File file) {
		String filename = file.getName().toLowerCase();
		if (filename.endsWith("gba")) {
			return true;
		} else {
			return false;
		}
	}
	public static boolean isSfcGame(File file) {
		String filename = file.getName().toLowerCase();
		if (filename.endsWith("sfc")||filename.endsWith("smc")) {
			return true;
		} else {
			return false;
		}
	}
	public static boolean isSmdGame(File file) {
		String filename = file.getName().toLowerCase();
		if (filename.endsWith("smd")) {
			return true;
		} else {
			return false;
		}
	}
	public static boolean isGbcGame(File file) {
		String filename = file.getName().toLowerCase();
		if (filename.endsWith("gbc")||filename.endsWith("gb")) {
			return true;
		} else {
			return false;
		}
	}
	public static boolean isNesGame(File file) {
		String filename = file.getName().toLowerCase();
		if (filename.endsWith("nes")) {
			return true;
		} else {
			return false;
		}
	}
	public static boolean isGeaGame(File file) {
		String filename = file.getName().toLowerCase();
		if (filename.endsWith("gg")||filename.endsWith("sms")) {
			return true;
		} else {
			return false;
		}
	}
}
