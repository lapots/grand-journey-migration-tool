package com.lapots.breed.migration

import com.lapots.breed.migration.db.RdsPgConnector
import com.lapots.breed.migration.domain.DbInfo
import com.lapots.breed.migration.util.ResourceUtil

def db = new DbInfo(
        host       : "",
        port       : "",
        login      : "",
        password   : "",
        name       : ""
)

def json = ResourceUtil.loadClasspathJson("/short_file.json")

7.times {
    basicStart(db)
}

def estimateTime(executionBlock) {
    long begin = System.currentTimeMillis()
    executionBlock()
    long result = System.currentTimeMillis() - begin
    println "Execution time: $result"
}

def coldStart(db) {
    def rdsConnection = new RdsPgConnector(rds: db)
    estimateTime({
        rdsConnection.connect()
        rdsConnection.executeFunction("generator.generatename", "alliance")
        rdsConnection.closeConnection()
    })
}

def basicStart(db) {
    def rdsConnection = new RdsPgConnector(rds: db)
    rdsConnection.connect()
    estimateTime({
        rdsConnection.executeFunction("generator.generatename", "alliance")
    })
    rdsConnection.closeConnection()
}