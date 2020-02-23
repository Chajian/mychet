package com.yezi.chet.sql.sqlite.person;

import com.yezi.chet.factory.PersonSqlFactory;

public class PersonSqlLite {

    PersonExcuteSqlLite excuteSql;

    PersonCreateSqlLite createSqlLite;

    public PersonSqlLite() {
        PersonSqlFactory personSqlFactory = new PersonSqlFactory();
        createSqlLite = personSqlFactory.getCreateSql();
        excuteSql = personSqlFactory.getExcuteSql(createSqlLite.create(),"friends","Persons");
    }

    public PersonExcuteSqlLite getExcuteSql() {
        return excuteSql;
    }

    public PersonCreateSqlLite getCreateSqlLite() {
        return createSqlLite;
    }
}
