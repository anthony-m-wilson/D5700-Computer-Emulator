package org.example.instructions

abstract class Instruction(protected val nibbles: ByteArray) {
    init {
        require(nibbles.size == 3)
    }

    fun execute() {
        process()
        perform()
        incrementProgramCounter()
    }

    protected open fun incrementProgramCounter() {

    }

    protected abstract fun process()
    protected abstract fun perform()

}