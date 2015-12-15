.PHONY: all clean run

all: wrap

clean:
	rm -f wrap *.o

run: all
	./wrap

wrap: wrap.o
	gcc $^ -o $@

%.o: %.c
	gcc -c $< -o $@