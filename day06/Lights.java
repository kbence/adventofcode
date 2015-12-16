import java.util.*;
import java.io.*;

public class Lights {
    static class Coordinate {
        int x, y;

        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    enum Operation { TURN_ON, TURN_OFF, TOGGLE }

    static class Instruction {
        Operation op;
        Coordinate from, to;

        Instruction(Operation op, Coordinate from, Coordinate to) {
            this.op   = op;
            this.from = from;
            this.to   = to;
        }

        boolean isInside(Coordinate c) {
            return c.x >= from.x && c.x <= to.x &&
                   c.y >= from.y && c.y <= to.y;
        }

        boolean processLight(Coordinate position, boolean status)
                throws Exception
        {
            if (isInside(position)) {
                switch (op) {
                    case TURN_ON: return true;
                    case TURN_OFF: return false;
                    case TOGGLE: return !status;
                }
                throw new Exception("Illegal operation!");
            }

            return status;
        }
    }

    public static void main(String[] args) {
        new Lights().run();
    }

    void run() {
        try {
            Instruction[] instructions = loadInstructions();
            System.out.println(String.format("Loaded %d instructions", instructions.length));

            int lightsTurnedOn =  countLightsOn(instructions);
            System.out.println(String.format("Number of lights turned on: %d", lightsTurnedOn));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    int countLightsOn(Instruction[] instructions) throws Exception {
        int count = 0;

        for (int i = 1; i < 1000000; i++) {
            Coordinate position = new Coordinate(i % 1000, i / 1000);
            boolean status = false;

            int aaa = 0;

            for (Instruction instr : instructions) {
                status = instr.processLight(position, status);
            }

            if (status) {
                count++;
            }
        }


        return count;
    }

    Instruction[] loadInstructions() throws Exception {
        List<Instruction> list = new LinkedList<Instruction>();
        BufferedReader reader = new BufferedReader(new FileReader("./data.txt"));
        String line;

        while (null != (line = reader.readLine())) {
            Instruction instr = parseInstruction(line);

            if (null != (instr = parseInstruction(line))) {
                list.add(instr);
            }
        }

        return list.toArray(new Instruction[list.size()]);
    }

    Instruction parseInstruction(String line) throws Exception {
        Instruction instr = null;

        String[] split = line.split(" ");

        if ("turn".equals(split[0])) {
            Coordinate from = parseCoordinate(split[2]);
            Coordinate to   = parseCoordinate(split[4]);

            if ("on".equals(split[1])) {
                instr = new Instruction(Operation.TURN_ON, from, to);
            } else if ("off".equals(split[1])) {
                instr = new Instruction(Operation.TURN_OFF, from, to);
            }
        } else if ("toggle".equals(split[0])) {
            Coordinate from = parseCoordinate(split[1]);
            Coordinate to   = parseCoordinate(split[3]);
            instr = new Instruction(Operation.TOGGLE, from, to);
        }

        if (instr == null)
            throw new Exception(String.format("parse error in line: %s", line));

        return instr;
    }

    Coordinate parseCoordinate(String coord) {
        String[] split = coord.split(",");

        return new Coordinate(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
    }
}
