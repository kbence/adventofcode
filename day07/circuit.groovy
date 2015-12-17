
def loadCircuit(filename) {
    def definitions = new HashMap<String, String>()

    new File(filename).eachLine { line ->
        def parts = line.split('->').collect{ it.trim() }

        definitions[parts[1]] = parts[0]
    }

    definitions
}

cache = [:]

def calculate(circuit, term, depth=0) {
    if (cache.containsKey(term))
        return cache[term]

    if (term.matches(/^[0-9]+$/))
        return Integer.parseInt(term)

    def wireDef = circuit[term].split(' ')
    def result = 0

    switch (true) {
        case wireDef.length == 1:
            result = calculate(circuit, wireDef[0], depth+1)
            break

        case wireDef.length == 2 && wireDef[0] == 'NOT':
            result = 65535 - calculate(circuit, wireDef[1], depth+1)
            break

        case wireDef.length == 3 && ['OR', 'AND', 'LSHIFT', 'RSHIFT'].contains(wireDef[1]):
            // println wireDef
            def val1 = calculate(circuit, wireDef[0], depth+1)
            def val2 = calculate(circuit, wireDef[2], depth+1)

            switch (wireDef[1]) {
                case 'OR':     result = val1 | val2;  break
                case 'AND':    result = val1 & val2;  break
                case 'LSHIFT': result = val1 << val2; break
                case 'RSHIFT': result = val1 >> val2; break
            }
            break

        default:
            throw new Exception("Unknown formula: ${circuit[term]}")
    }

    cache[term] = result

    return result
}

def circuit = loadCircuit './data.txt'
printf "Value on wire 'a': %d\n", calculate(circuit, 'a')
