package org.example

import org.example.instructions.InstructionFactory

class Emulator {
    private var cpu: CPU = CPU()
    private var memory = arrayOf(Rom())
    private var display = Display()
    private var instructions = InstructionFactory()

}