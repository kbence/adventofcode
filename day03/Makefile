.PHONY: all clean run

all: route

clean:
	rm -f route *.o

run: all
	./route

route: route.o
	g++ $^ -o $@

%.o: %.cc
	g++ -c $< -o $@
