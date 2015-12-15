.PHONY: all clean run

all: adventcoin

clean:
	rm -f adventcoin

run: all
	./adventcoin

adventcoin: src/santa/advent/coin.go
	(cd $$(dirname $<) && go build -o ../../../$@)
