package za.co.yellowfire.carat.web

import spock.lang.Specification

class HelloSpockTest extends Specification {
    def "test length"() {
        expect:
            name.size() == length
        where:
            name | length
            "Spock" | 5
    }
}