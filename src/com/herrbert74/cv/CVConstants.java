package com.herrbert74.cv;

public interface CVConstants {

	// Android backgrounds
	public static final String[] BACKGROUNDS_ANDROID = { "android_basic", "android_schools", "android_experience", "android_profiles",
			"android_hobbyprojects", "android_hobbies", "android_skills" };

	public static final int[] ICONS_ANDROID = new int[] { R.drawable.selector_ipi_android_01, R.drawable.selector_ipi_android_02,
			R.drawable.selector_ipi_android_03, R.drawable.selector_ipi_android_04, R.drawable.selector_ipi_android_05,
			R.drawable.selector_ipi_android_06, R.drawable.selector_ipi_android_07 };
	public static final int[] ICONS_SQUARES = new int[] { R.drawable.selector_ipi_squares_01, R.drawable.selector_ipi_squares_02,
		R.drawable.selector_ipi_squares_03, R.drawable.selector_ipi_squares_04, R.drawable.selector_ipi_squares_05,
		R.drawable.selector_ipi_squares_06, R.drawable.selector_ipi_squares_07 };

	// Style array
	public static final String[] CV_LINE_STYLES = { "list", "detail", "link", "picture", "list_bar", "" };

	// Prefs files
	public static final String PREFS_MAIN = "QuizheadsPrefs";
	public static final String CHAMP_PREFS_FILENAME = "ChampPrefs";
	public static final String STATS_PREFS_FILENAME = "StatPrefs";
	public static final String AWARDS_PREFS_FILENAME = "AwardsPrefs";

	// Databases
	public static final String DATABASE_NAME = "quiz";
	public static final int DATABASE_VERSION = 1;
	public static final String[] DATABASE_FIELDS = { "_id", "subject", "question", "answer1", "answer2", "answer3", "answer4", "note",
			"difficulty" };
	public static final String[] PRIVATE_TABLE_NAMES = { "private_normal_1", "private_normal_2", "private_normal_3", "private_shootout_1",
			"private_shootout_2", "private_shootout_3" };
	public static final String[] DRAWN_TABLES = { "drawn_normal", "drawn_shootout" };
	public static final String[] DRAWN_FIELDS = { "_id", "drawn" };

	// Email address
	public static final String DEVELOPER_EMAIL_ADDRESS = "herrbert74@gmail.com";

	static final String TYPEFACE_HEADINGS = "Diavlo_LIGHT_II_37.otf";

	// Web addresses
	static final String SERVER_DOMAIN = "http://herrbert74.biz.ht";
	static final String URL_PATH_CVLINES = "/cv.php?rquest=cvlines&id=%d";

	static final String MLK_WEB_ADDRESS = "http://www.babestudios.com";
	static final String MLK_EMAIL_ADDRESS = "babestudios@gmail.com";
}