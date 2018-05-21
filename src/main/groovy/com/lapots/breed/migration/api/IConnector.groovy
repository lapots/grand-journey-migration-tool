package com.lapots.breed.migration.api

interface IConnector {
    def connect()
    def closeConnection()
}