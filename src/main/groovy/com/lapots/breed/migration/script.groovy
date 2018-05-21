package com.lapots.breed.migration

import com.lapots.breed.migration.db.RdsPgConnector
import com.lapots.breed.migration.domain.DbInfo
import com.lapots.breed.migration.util.DataUtil
import com.lapots.breed.migration.util.ResourceUtil

def db = new DbInfo(
        host       : "",
        port       : "",
        login      : "",
        password   : "",
        name       : ""
)

def json = ResourceUtil.loadClasspathJson("/short_file.json")
def data = DataUtil.transformJsonData(json)

def rdsConnection = new RdsPgConnector(rds: db)
rdsConnection.insert(data)
rdsConnection.closeConnection()
