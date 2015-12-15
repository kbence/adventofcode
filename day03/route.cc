#include <fstream>
#include <iostream>
#include <set>

using namespace std;

struct coord {
    int x;
    int y;

    coord(int x, int y) : x(x), y(y) {}

    bool operator < (const coord& c) const {
        return y < c.y || (y == c.y && x < c.x);
    }
};

ostream& operator << (ostream& out, const coord& c) {
    return out << '(' << c.x << ", " << c.y << ')';
}

int main() {
    ifstream f("./data.txt");
    set<coord> houses;
    coord pos(100, 100);
    char ch;

    houses.insert(pos);

    while (!f.eof()) {
        f.read(&ch, 1);

        switch (ch) {
            case '^': pos.y--; break;
            case 'v': pos.y++; break;
            case '<': pos.x--; break;
            case '>': pos.x++; break;
            default:
                cout << "Illegal character: " << ch << endl;
        }

        houses.insert(pos);
    }

    cout << "Number of houses visited: " << houses.size() << endl;

    return 0;
}
