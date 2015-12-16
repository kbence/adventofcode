.PHONY: all clean run

all: Lights.class

run: all
	java Lights

clean:
	rm -f *.class

%.class: %.java
	javac $<
