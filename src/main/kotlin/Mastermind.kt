private fun String.toPegs(): List<Peg> =
    this.split(",").mapIndexed { index, it -> Peg(index, Color.valueOf(it.trim().toUpperCase())) }

internal class Mastermind(colors: String) {
    private val secret = colors.toPegs()
    fun guess(colors: String): Attempt = Attempt(secret, colors.toPegs())
}

data class Peg(val index: Int, val color: Color)

class Attempt(private val secret: List<Peg>, private val guess: List<Peg>) {
    val right: Int = secret
        .filter { isRight(it) }
        .count()
    val misplaced: Int = guess
        .filter { isWrong(it) }
        .filter { isInSecret(it) }
        .count()

    private fun isRight(peg: Peg) = guess.any { it == peg }
    private fun isWrong(peg: Peg) = secret.none { it == peg }
    private fun isInSecret(peg: Peg) = secret
        .filter { !isRight(it) }
        .any { it.color == peg.color }
}

enum class Color {
    RED, GREEN, YELLOW, BLUE, PINK, PURPLE
}