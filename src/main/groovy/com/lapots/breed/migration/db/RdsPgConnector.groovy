package com.lapots.breed.migration.db

import com.lapots.breed.migration.api.IConnector
import com.lapots.breed.migration.api.IDbOperator
import groovy.sql.Sql

class RdsPgConnector implements IConnector, IDbOperator {
    def rds // to init
    def sql

    @Override
    def connect() {
        connection = Sql.newInstance("jdbc:postgresql://$rds.host:$rds.port/$rds.name",
            rds.login, rds.password, "org.postgresql.Driver")
    }

    @Override
    def closeConnection() {
        sql.close()
    }

    @Override
    def insert(data) {
        sql.withTransaction {
            sql.withBatch { stmt ->
                data.each {
                    dataItem ->
                        stmt.addBatch "insert into generator.generators (part_group_name, part_name, part_value) " +
                                "values ('$dataItem.part_group_name', '$dataItem.part_name', '$dataItem.part_value')"
                }
            }
        }
    }

    @Override
    def readAll() {
        // TODO: implement
    }

    @Override
    def readById(Object id) {
        // TODO: implement
    }
}
