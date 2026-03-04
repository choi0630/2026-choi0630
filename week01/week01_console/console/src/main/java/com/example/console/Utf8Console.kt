package com.example.console

import java.io.PrintStream
import java.nio.charset.StandardCharsets

fun enableUtf8Console() {
    // p0:"output stream", p1 : autoflush ("newline이 출력되면 자동 flush), p2 : 인코딩 모드
    System.setOut(PrintStream(System.out, true, StandardCharsets.UTF_8))
    System.setErr(PrintStream(System.err, true, StandardCharsets.UTF_8))
}