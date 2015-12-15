#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#define MIN(a, b) ( (a < b) ? (a) : (b) )

int main() {
    FILE *f = fopen("./data.txt", "r");
    char line[32];
    int wrapNeeded = 0, l, w, h, a, b, c;

    if (f) {
        while (!feof(f)) {
            fgets(line, sizeof(line), f);
            l = atoi(strtok(line, "x"));
            w = atoi(strtok(NULL, "x"));
            h = atoi(strtok(NULL, "x"));

            a = l * w;
            b = w * h;
            c = l * h;

            wrapNeeded += 2*a + 2*b + 2*c + MIN(a, MIN(b, c));
        }

        printf("All wrap needed: %d\n", wrapNeeded);

        fclose(f);
    }
}
