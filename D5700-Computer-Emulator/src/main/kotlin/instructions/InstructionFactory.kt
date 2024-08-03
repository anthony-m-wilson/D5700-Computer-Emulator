package org.example.instructions

class InstructionFactory {
    private val instructions = arrayOf(
        ::Store,
        ::Add,
        ::Sub,
        ::Read,
        ::Write,
        ::Jump,
        ::ReadKeyboard,
        ::SwitchMemory,
        ::SkipEqual,
        ::SkipNotEqual,
        ::SetA,
        ::SetT,
        ::ReadT,
        ::ConvertToBaseTen,
        ::ConvertByteToASCII,
        ::Draw
    )

    fun createInstruction(nibble0: Byte, nibble1: Byte, nibble2: Byte, nibble3: Byte) : Instruction{
        val createInstruction = instructions[nibble0.toInt()]

        val instruction = createInstruction(byteArrayOf(nibble1, nibble2, nibble3))

        return instruction
    }
}