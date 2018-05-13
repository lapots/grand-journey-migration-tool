package com.lapots.breed.migration.util

import groovy.json.JsonSlurper

class ResourceUtil {

    static def loadClasspathJson(filename) {
        new JsonSlurper().parse(getClass().getResourceAsStream(filename))
    }

}
