import spock.lang.Specification

class MastermindTest extends Specification {

    def "mastermind should take a secret"() {
        when:
        Mastermind mastermind = new Mastermind("red, blue, green, yellow")
        then:
        mastermind.secret != null
    }

    def "mastermind should take a guess"() {
        expect:
        Mastermind mastermind = new Mastermind(secret)
        Attempt attempt = mastermind.guess(guess)
        attempt.right == right
        attempt.misplaced == misplaced
        where:
        secret                    | guess                       | right | misplaced
        "red, red, red, red"      | "blue, blue, blue, blue"    | 0     | 0
        "red, red, red, blue"     | "pink, pink, pink, blue"    | 1     | 0
        "red, pink, red, pink"    | "red, pink, red, pink"      | 4     | 0
        "red, blue, red, blue"    | "red, green, red, green"    | 2     | 0
        "red, red, red, blue"     | "blue, green, green, green" | 0     | 1
        "red, red, blue, blue"    | "blue, blue, green, green"  | 0     | 2
        "blue, red, green, blue"  | "blue, red, blue, green"    | 2     | 2
        "pink, yellow, red, blue" | "pink, yellow, red, red"    | 3     | 0
        "pink, yellow, red, blue" | "red, red, red, red"        | 1     | 0
    }
}
