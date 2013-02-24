package com.herrbert74.cv;

public interface CVConstants {

	//Styles
	public static final String[] STYLES = {"list", "detail", "list_bar"};
	
	//Android backgrounds
	public static final String[] BACKGROUNDS_ANDROID = {"android_basic", "android_schools", "android_experience", "android_profiles", "android_hobbyprojects", "android_hobbies", "android_skills"};
	
	//Prefs files
	public static final String PREFS_MAIN = "QuizheadsPrefs";
	public static final String CHAMP_PREFS_FILENAME = "ChampPrefs";
	public static final String STATS_PREFS_FILENAME = "StatPrefs";
	public static final String AWARDS_PREFS_FILENAME = "AwardsPrefs";
	
	//Databases
	public static final String DATABASE_NAME = "quiz";
	public static final int DATABASE_VERSION = 1;
	public static final String[] DATABASE_FIELDS = {"_id", "subject", "question", "answer1", "answer2", "answer3", "answer4", "note", "difficulty"};
	public static final String[] PRIVATE_TABLE_NAMES = {"private_normal_1", "private_normal_2", "private_normal_3", "private_shootout_1", "private_shootout_2", "private_shootout_3"};
	public static final String[] DRAWN_TABLES = {"drawn_normal", "drawn_shootout"};
	public static final String[] DRAWN_FIELDS = {"_id", "drawn"};
		
	//Email address
	public static final String DEVELOPER_EMAIL_ADDRESS = "herrbert74@gmail.com";
	
	static final String TYPEFACE_HEADINGS = "Diavlo_LIGHT_II_37.otf";
	
	//Found them on https://nookdeveloper.barnesandnoble.com/product/nook-device-specs.html
	static final String[] NOOK_DEVICE_BUILD_MODEL_CODES = {"bn_emu_addon", "NookColor", "BNRV200", "BNTV250", "BNTV250A", "BNTV400", "BNTV600"};
	//Found them on https://developer.amazon.com/sdk/fire/specifications.html
	static final String[] KINDLE_DEVICE_BUILD_MODEL_CODES = {"Kindle Fire", "KFOT", "KFTT", "WFJWI", "WFJWA"};
}