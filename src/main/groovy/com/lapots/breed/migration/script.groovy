package com.lapots.breed.migration

import com.lapots.breed.migration.rds.DbInfo
import com.lapots.breed.migration.util.DataUtil
import com.lapots.breed.migration.util.ResourceUtil

def db = new DbInfo(
        url         : "",
        port        : "",
        login       : "",
        password    : "",
        name        : ""
)

def json = ResourceUtil.loadClasspathJson("/short_file.json")
def data = DataUtil.transformJsonData(json)


