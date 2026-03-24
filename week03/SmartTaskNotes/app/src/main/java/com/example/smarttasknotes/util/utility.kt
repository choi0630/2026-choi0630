package com.example.smarttasknoteapp.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun nowText(): String =
    LocalDateTime.now()
        .format(DateTimeFormatter.ofPattern("MM-dd HH:mm"))