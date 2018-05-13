package com.lapots.breed.migration.rds

import groovy.sql.Sql

class SqlExec {

    static def createPostgresConnection(host, port, login, password, dbName) {
        Sql.newInstance(
                "jdbc:postgresql://$host:$port/$dbName",
                login,
                password,
                "org.postgresql.Driver"
        )
    }

    static def execGeneratorInsertBatch(sql, data) {
        sql.withTransaction {
            sql.withBatch { stmt ->
                data.each { dataItem ->
                    stmt.addBatch "insert into generator.generators (part_group_name, part_name, part_value) " +
                        "values ('$dataItem.part_group_name', '$dataItem.part_name', '$dataItem.part_value')"
                }
            }
        }
        sql.close()
    }

}
