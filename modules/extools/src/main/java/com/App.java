package com;

import com.whisman.extools.sql.PagerUtils;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        String sql = "select distinct user_id from ac_account order by abv asc";

        String count = PagerUtils.count(sql,"mysql");

        String pageSQL = PagerUtils.limit(sql,"mysql",100,100);

        System.out.println(count);
        System.out.println(pageSQL);

    }
}
