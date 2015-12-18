.PHONY: all clean run

all: route

clean:
	rm -f route *.hi *.o

run: all
	./route

route: route.hs
	ghc route.hs
