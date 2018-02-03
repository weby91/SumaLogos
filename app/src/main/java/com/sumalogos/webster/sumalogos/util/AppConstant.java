package com.sumalogos.webster.sumalogos.util;

import static com.sumalogos.webster.sumalogos.BuildConfig.BASE_URL;

/**
 * Created by webster on 18/01/18.
 */

public class AppConstant {
    public static final long SPLASH_TIME = 2000;
    public static final int TIME_OUT_LIMIT = 20;
    public static final String BASE_API_URL = BASE_URL;
    public static final String V1  = "v1/";
    public static final String V2  = "v2/";
    public static final String USER  = V1 + "user";
    public static final String USER_V2  = V2 + "user";
    public static final String DEVOTION  = V1 + "devotional";
    public static final String DEVOTION_V2 = V2 + "devotional";
    public static final int REQUEST_CODE_RC_SIGN_IN = 203;
    public static final String[] arrayMonthIndonesiaSentenceCase = new String[]{"Januari", "Februari", "Maret",
            "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "NoveStmber", "Desember"};

    public static final String[] oldTestamentBooks = new String[]{"Gen", "Exod", "Lev",
            "Num", "Deut", "Josh", "Judg", "Ruth", "1Sam", "2Sam", "1Kgs", "2Kgs",
            "1Chr", "2Chr", "Ezra", "Neh", "Esth", "Job", "Ps", "Prov", "Eccl",
            "Song", "Isa", "Jer", "Lam", "Ezek", "Dan", "Hos", "Joel", "Amos",
            "Obad", "Jonah", "Mic", "Nah", "Hab", "Zeph", "Hag", "Zech", "Mal"};

    public static final String[] newTestamentBooks = new String[]{"Matt", "Mark", "Luke",
            "John", "Acts", "Rom", "1Cor", "2Cor", "Gal", "Eph", "Phil", "Col",
            "1Thess", "2Thess", "1Tim", "2Tim", "Titus", "Phlm", "Heb", "Jas", "1Pet",
            "2Pet", "1John", "2John", "3John", "Jude", "Rev"};

}
