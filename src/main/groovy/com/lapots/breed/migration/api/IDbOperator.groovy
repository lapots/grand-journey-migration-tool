package com.lapots.breed.migration.api

interface IDbOperator {
    def insert(data)

    def readAll()
    def readById(id)
}