package com.example.smarttasknotes.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun nowText(): String =
    LocalDateTime.now()
        .format(DateTimeFormatter.ofPattern("MM-dd HH:mm"))