package com.example.mylocationweather.data

class LocationNullException() : RuntimeException("location is null after onSuccess Listener")
class LocationFailureException(message: String) : RuntimeException("fail to get location$message")