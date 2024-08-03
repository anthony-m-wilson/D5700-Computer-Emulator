package org.example.instructions

abstract class Instruction(protected val nibbles: ByteArray) {
    init {
        require(nibbles.size == 3)
    }

    fun execute() {
        processNibbles()
        performOperation()
        incrementProgramCounter()
    }

    protected open fun incrementProgramCounter() {

    }

    protected abstract fun processNibbles()
    protected abstract fun performOperation()

}