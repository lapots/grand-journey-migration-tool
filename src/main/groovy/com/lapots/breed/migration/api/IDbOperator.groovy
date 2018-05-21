package com.lapots.breed.migration.api

interface IDbOperator {
    def insert(data)
    def executeFunction(name, ... params)
}