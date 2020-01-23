package com.yezi.chet.sql.sqlite.person;

import com.yezi.chet.factory.PersonSqlFactory;
import com.yezi.chet.sql.BaseCreateSql;
import com.yezi.chet.sql.BaseExcuteSql;

public class PersonSqlLite {

    PersonExcuteSqlLite excuteSql;

    PersonCreateSqlLite createSqlLite;

    public PersonSqlLite() {
        PersonSqlFactory personSqlFactory = new PersonSqlFactory();
        createSqlLite = personSqlFactory.getCreateSql();
        excuteSql = personSqlFactory.getExcuteSql(createSqlLite.create());
    }

    public PersonExcuteSqlLite getExcuteSql() {
        return excuteSql;
    }

    public PersonCreateSqlLite getCreateSqlLite() {
        return createSqlLite;
    }
}
