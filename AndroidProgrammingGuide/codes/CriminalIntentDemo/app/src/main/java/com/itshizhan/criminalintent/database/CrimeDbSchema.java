package com.itshizhan.criminalintent.database;

public class CrimeDbSchema {
    //数据表
    public static final class CrimeTable{
        //表名称
        public static final String NAME = "crimes";

        //列
        public static final class Cols{
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DATE = "date";
            public static final String SOLVED = "solved";
        }
    }
}
